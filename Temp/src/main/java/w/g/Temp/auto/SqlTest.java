package w.g.Temp.auto;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import w.g.util.XmlUtil;

public class SqlTest {
	private final static Log logger = LogFactory.getLog(SqlTest.class);

	public static final String DATA_BASE = "lrapp";// 库名
	public static final String TABLE = XmlUtil.getTableName("generatorConfigBase.xml", "tableName");// 表名
	public static final String TABLE_NAME = "";// 表名
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String pwd = "root";
	private static final String user = "root";
	private static final String url = "jdbc:mysql://localhost/" + DATA_BASE + "?user=" + user + "&password=" + pwd
			+ "&useUnicode=true&characterEncoding=UTF-8";
	private static Connection getConnection = null;
	private static List<String> exclude;

	public static void main(String[] args) {
		exclude = new ArrayList<String>();
		exclude.add("id");
		exclude.add("sjgxsj");
		exclude.add("sjgxr");
		exclude.add("sjcjsj");
		exclude.add("sjcjr");
		exclude.add("zzjgdm");
		exclude.add("shzt");
		exclude.add("sfgq");
		getConnection = getConnections();
		try {
			DatabaseMetaData dbmd = getConnection.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
			String tableName = TABLE;
			String tableRemark = TABLE_NAME;
			boolean flag = false;
			while (resultSet.next()) {
				String name = resultSet.getString("TABLE_NAME");
				if (tableName.equalsIgnoreCase(name)) {
					flag = true;
					ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
					logger.info(tableName + "\t\n" + tableRemark);
					Table table = new Table();
					table.setTableName(tableName);
					table.setRemark(tableRemark);
					List<Column> list = new ArrayList<Column>();
					while (rs.next()) {
						Column column = new Column();
						int nullable = rs.getInt("NULLABLE");
						column.setNullable(nullable);
						String COLUMN_NAME = rs.getString("COLUMN_NAME").toLowerCase();
						if (exclude.contains(COLUMN_NAME)) {
							continue;
						}
						String REMARKS = rs.getString("REMARKS");
						column.setName(COLUMN_NAME);
						if ("DATETIME".equals("TYPE_NAME")) {
							column.setLength(rs.getString("10"));
						} else {
							column.setLength(rs.getString("COLUMN_SIZE"));
						}

						String TYPE_NAME = rs.getString("TYPE_NAME");
						column.setRemark(REMARKS);
						if (REMARKS.contains("下拉")) {
							column.setRemark(REMARKS.substring(0, REMARKS.indexOf("下拉")));
							column.setType("SELECT");
							String select = "selectData";
							try {
								select = REMARKS.substring(REMARKS.indexOf("(") + 1, REMARKS.indexOf(")"));
							} catch (Exception e) {
								try {
									select = REMARKS.substring(REMARKS.indexOf("（") + 1, REMARKS.indexOf("）"));
								} catch (Exception e1) {
									logger.info(REMARKS + " 注释里没有指定枚举XML文件名/数据字典名");
								}
							}
							column.setSelect(select);
						} else if (REMARKS.contains("文件上传")) {
							column.setRemark(REMARKS.substring(0, REMARKS.indexOf("(文件上传")));
							column.setType("UPLOAD");
						} else if (REMARKS.contains("拓展字段")) {
							column.setRemark(REMARKS.substring(0, REMARKS.indexOf("(拓展字段")));
							column.setType("HIDE");
						} else {
							column.setType(TYPE_NAME);
						}
						logger.info(column.toString());
						list.add(column);
					}
					table.setColumnNum(list.size());
					table.setColumns(list);
					FileGenerator.create(table);
				}
			}
			if (!flag) {
				logger.error("表不存在：" + TABLE);
			}
		} catch (Exception e) {
			logger.error("表不存在：" + e.getCause());
		}
	}

	public static Connection getConnections() {
		try {
			Class.forName(driver);
			getConnection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getConnection;
	}

	public static String getSchema() throws Exception {
		String schema;
		schema = getConnection.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();

	}

}
