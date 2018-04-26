package w.g.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import android.os.Environment;

/**
 * 写入日志到文件
 * 
 * @author Ws
 * 
 */
public class LogWriterUtil {
	private OutputStreamWriter owrite;
	private BufferedWriter bwriter;
	private String filePath = Environment.getExternalStorageDirectory()
			+ File.separator + "WiFiLog.xml";
	private File f;

	LogWriterUtil() {
		try {
			f = new File(filePath);
			if (!f.exists()) {
				f.createNewFile();
			}
			owrite = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			bwriter = new BufferedWriter(owrite);
		} catch (Exception e) {

		}
	}

	/**
	 * Log输出到日志
	 * 
	 * @param log
	 * @throws IOException
	 */
	public void print(Class<?> cls, String log) {
		try {
			bwriter.append(DateTimeUtil.showTime(new Date()));
			if (cls != null) {
				bwriter.append(cls.getSimpleName() + ": ");
			}
			bwriter.append(log);
			bwriter.append("\n");
			bwriter.flush();
		} catch (Exception e) {
			System.out.println("写入日志出错! ");
			e.printStackTrace();
		}
	}
}
