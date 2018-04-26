package w.g.adapter;

import java.util.ArrayList;
import java.util.List;

import w.g.bean.SecurityBean;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import w.g.R;

public class SecurityListAdapter extends BaseAdapter {
	private List<SecurityBean> l = new ArrayList<SecurityBean>();

	private Activity activity;

	public SecurityListAdapter(List<SecurityBean> securityBean,
			Activity activity) {
		this.activity = activity;
		this.l = securityBean;
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
	public View getView(int position, View view, ViewGroup vg) {
		if (view == null) {
			view = View.inflate(activity, R.layout.security_list_view, null);
		}
		TextView user_type = (TextView) view.findViewById(R.id.user_type);
		TextView active_time = (TextView) view.findViewById(R.id.active_time);
		TextView queried_times = (TextView) view
				.findViewById(R.id.queried_times);
		TextView reset_times = (TextView) view.findViewById(R.id.reset_times);
		TextView query_times = (TextView) view.findViewById(R.id.query_times);
		TextView reseted_times = (TextView) view
				.findViewById(R.id.reseted_times);
		user_type.setText(l.get(position).getUserType());
		active_time.setText(l.get(position).getActiveTime());
		queried_times.setText(l.get(position).getQueriedTimes());
		reseted_times.setText(l.get(position).getResetedTimes());
		query_times.setText(l.get(position).getQueryTimes());
		reset_times.setText(l.get(position).getResetTimes());
		return view;
	}

}
