package w.g.activity;

import java.util.ArrayList;
import java.util.List;

import w.g.adapter.SecurityListAdapter;
import w.g.bean.SecurityBean;
import w.g.dao.SecurityDao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import w.g.R;

public class SettingActivity extends Activity {
	private static final String LOG_TAG = new Object() {
		public String getClassName() {
			String className = this.getClass().getName();
			return className.substring(className.lastIndexOf('.') + 1,
					className.lastIndexOf('$'));
		}
	}.getClassName();
	private SecurityDao securityDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, "进入设置");
		setContentView(R.layout.activity_setting);
		ListView lv = (ListView) findViewById(R.id.listView1);
		List<SecurityBean> securityBean = new ArrayList<SecurityBean>();
		securityDao = new SecurityDao(this);
		securityBean = securityDao.queryStatus();
		Log.i(LOG_TAG, "加载安全配置列表");
		lv.setAdapter(new SecurityListAdapter(securityBean, this));
	}

}
