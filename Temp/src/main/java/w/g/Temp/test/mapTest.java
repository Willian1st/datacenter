package w.g.Temp.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class mapTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("3");
		list.add("2");
		Collections.reverse(list);
		for (String string : list) {
			System.out.println(string);
		}
		List<String> listLinked = new LinkedList<String>();
		listLinked.add("1");
		listLinked.add("3");
		listLinked.add("2");
		Collections.reverse(listLinked);
		for (String string : listLinked) {
			System.out.println(string);
		}
	}
}
