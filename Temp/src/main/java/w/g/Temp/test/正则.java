package w.g.Temp.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则 {
	public static void main(String[] args) {
		String str = "<node key=001 dfsdfs";
		String regex = "/\\s/";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		System.out.println(m.matches());
	}
}
