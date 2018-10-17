package w.g.Temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import w.g.Temp.test.bean.ListObject;

public class ListTest {
	@Test
	public void LinkedListTest() {
		LinkedList<ListObject> list = new LinkedList<ListObject>();
		list.add(new ListObject("1", "系统"));
		list.add(new ListObject("3", "系统"));
		list.add(new ListObject("2", "系统"));
		list.addFirst(new ListObject("7", "系统"));
		for (ListObject listObject : list) {
			System.out.println(listObject.toString());
		}
	}

	@Test
	public void swap() {
		List<ListObject> list = new ArrayList<ListObject>();
		list.add(new ListObject("1", "系统"));
		list.add(new ListObject("3", "系统"));
		ListObject o3 = new ListObject("2", "系统");
		list.add(o3);
		for (ListObject listObject : list) {
			System.out.println(listObject.toString());
		}
		System.out.println(list.indexOf(o3));
		Collections.swap(list, 1, 2);
		for (ListObject listObject : list) {
			System.out.println(listObject.toString());
		}
	}
}
