package w.g.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public final class DatabaseHelper extends SQLiteOpenHelper {
	private static final int version = 0x0001;
	private static DatabaseHelper databaseHelper;
	private static final String DATABASE_NAME = "alpha";
	private static final String SQL_SECURITY = "create table security("
	// 唯一标识符id
			+ "id int primary key not null,"
			// 用户类型
			+ "user_type varchar(10),"
			// 激活码
			+ "active_code varchar(10),"
			// 激活时间
			+ "active_time datetime,"
			// 可查询次数
			+ "query_times int,"
			// 已查询次数
			+ "queried_times int,"
			// 可重置次数
			+ "reset_times int,"
			// 已重置次数
			+ "reseted_times int,"
			// 是否有效
			+ "valid int)";

	public DatabaseHelper getDatabaseHelper(Context context) {
		if (databaseHelper == null) {
			databaseHelper = new DatabaseHelper(context);
		}
		return databaseHelper;
	}

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DatabaseHelper", "创建授权表结构");
		// 授权表
		db.execSQL(SQL_SECURITY);
		initSecurity(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("DatabaseHelper", "原版本号:" + oldVersion);
		Log.i("DatabaseHelper", "新版本号:" + newVersion);
		// 升级时删除表
		if (newVersion > oldVersion) {
			Log.i("DatabaseHelper", "升级时删除表");
			db.execSQL("drop table if exists security");
			this.onCreate(db);
		} else {
			Log.i("DatabaseHelper", "保留原数据库");
		}

	}

	void initSecurity(SQLiteDatabase db) {
		Log.i("SecurityDao", "初始化security表");
		// 高级用户
		Object[] classic = { "classic", "000-100-000", new Date(), 100000, 0,
				100, 0, 0 };
		// 普通用户
		Object[] ordinary = { "ordinary", "000-000-100", new Date(), 100, 0,
				10, 0, 0 };
		List<Object[]> l = new ArrayList<Object[]>();
		l.add(classic);
		l.add(ordinary);
		String intSql = "insert into security(user_type,active_code,active_time,query_times,queried_times,reset_times,reseted_times,valid) values(?,?,?,?,?,?,?,?)";
//		try {
			// 创建或打开一个读写数据库
			db = getDatabaseHelper(null).getWritableDatabase();
			for (Object[] o : l) {
				db.execSQL(intSql, o);
			}
		/*} catch (Exception e) {
			Log.e("SecurityDao", "数据库访问失败:" + e.toString());
		} finally {
			db.close();
		}*/
	}
}
