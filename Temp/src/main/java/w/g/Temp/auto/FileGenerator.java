package w.g.Temp.auto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class FileGenerator {

	public static void create(Table table) throws Exception {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();

		// Template list = ve.getTemplate("list.jsp", "UTF-8");
		Template add = ve.getTemplate("form.jsp", "UTF-8");
		// Template view = ve.getTemplate("view.jsp", "UTF-8");
		// merge(list, ctx, "list");

		merge(add, table, "form");

	}

	private static void merge(Template template, Table table, String tableName)
			throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, IOException {
		VelocityContext ctx = new VelocityContext();
		ctx.put("table", table);
		FileWriter writer = null;
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String path = fsv.getHomeDirectory().toString();// 获取当前用户桌面路径
		File directory = new File(path);
		if (directory.exists()) {
		} else {
			directory.createNewFile();
		}
		try {
			writer = new FileWriter(directory + "\\" + tableName + ".jsp");
			template.merge(ctx, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
