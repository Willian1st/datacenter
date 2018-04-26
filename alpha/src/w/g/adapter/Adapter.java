package w.g.adapter;

import java.util.ArrayList;
import java.util.List;

import w.g.bean.Bean;

import w.g.R;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
	private List<Bean> l = new ArrayList<Bean>();

	private Activity activity;

	public Adapter(List<Bean> list, Activity activity) {
		this.activity = activity;
		this.l = list;
	}

	@Override
	public int getCount() {
		return l.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = View.inflate(activity, R.layout.list_view, null);
			System.out.println("第" + arg0 + "个listView为null...");
			System.out.println("View arg1如果为null则加载空R.layout.list_view");
		}
		TextView t1 = (TextView) arg1.findViewById(R.id.textView1);
		TextView t2 = (TextView) arg1.findViewById(R.id.textView2);
		String h = l.get(arg0).getInfoStr();
		System.out.println("给第" + arg0 + "个listView赋值:" + h);
		t1.setText(l.get(arg0).getInfo());
		t2.setText(l.get(arg0).getInfoStr());
		return arg1;
	}

}
