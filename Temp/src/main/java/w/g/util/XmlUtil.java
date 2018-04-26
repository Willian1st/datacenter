package w.g.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * XML工具类
 * 
 */
public class XmlUtil {
	private final static Log logger = LogFactory.getLog(XmlUtil.class);

	/**
	 * 根据文件路径解析XML成文档对象
	 * 
	 * @param path
	 * @return
	 */
	public static Document parseXML2Object(String path) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			File file = new File("src/main/java/" + path);
			document = reader.read(file);
		} catch (Exception e) {
			logger.error("XML解析失败：" + e.getMessage());
		}
		return document;
	}

	/**
	 * 获取表名
	 * 
	 * @param path
	 * @param attributeName
	 * @return
	 */
	public static String getTableName(String path, String attributeName) {
		String result = "";
		try {
			Document document = parseXML2Object(path);
			Element rootElement = document.getRootElement();
			Element context = rootElement.element("context");
			Element element = context.element("table");
			result = element.attributeValue(attributeName);
		} catch (Exception e) {
			logger.error("获取表名失败：" + e.getMessage());
		}
		return result;
	}

}
