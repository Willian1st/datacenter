package w.g.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import w.g.R;
import w.g.adapter.CustomSimpleAdapter;
import w.g.bean.Category;
import w.g.util.DensityUtil;
import w.g.util.StringUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class GridViewActivity extends Activity implements OnItemClickListener {
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	private GridView gv = null;
	Intent intent = null;
	// 进度对话框
	public ProgressDialog pd = null;
	// ---
	private int mColumnWidthDip;
	@SuppressWarnings("unused")
	private int mFlingVelocityDip;
	@SuppressWarnings("unused")
	private int mCid;
	@SuppressWarnings("unused")
	private String mCatName;
	@SuppressWarnings("unused")
	private ArrayList<HashMap<String, Object>> mNewsData;
	@SuppressWarnings("unused")
	private ListView mNewsList;
	@SuppressWarnings("unused")
	private SimpleAdapter mNewsListAdapter;
	@SuppressWarnings("unused")
	private LayoutInflater mInflater;
	@SuppressWarnings("unused")
	private Button mTitlebarRefresh;
	@SuppressWarnings("unused")
	private ProgressBar mLoadnewsProgress;
	@SuppressWarnings("unused")
	private Button mLoadMoreBtn;
	private final int COLUMNWIDTHPX = 155;
	private final int FLINGVELOCITYPX = 800; // 滚动距离

	// --

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		gv = (GridView) findViewById(R.id.gridView1);
		ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("1", "小功能");
		al.add(hm);
		hm = new HashMap<String, Object>();
		hm.put("1", "例子");
		al.add(hm);
		hm = new HashMap<String, Object>();
		hm.put("1", "设置");
		al.add(hm);
		hm = new HashMap<String, Object>();
		hm.put("1", "UnDone");
		al.add(hm);
		Log.i("GridViewActivity", "将静态数据映射到指定的视图XML...");
		SimpleAdapter sa = new SimpleAdapter(this, al, R.layout.button,
				new String[] { "1" }, new int[] { R.id.button1 });
		Log.i("GridViewActivity", "把map中key为1的value绑定到R.id.button1上...");
		gv.setAdapter(sa);
		Log.i("GridViewActivity", "添加按钮并且显示...");
		gv.setOnItemClickListener(this);
		{
			mInflater = getLayoutInflater();
			mNewsData = new ArrayList<HashMap<String, Object>>();

			// 把px转换成dip
			mColumnWidthDip = DensityUtil.px2dip(this, COLUMNWIDTHPX);
			mFlingVelocityDip = DensityUtil.px2dip(this, FLINGVELOCITYPX);

			// 获取新闻分类
			String[] categoryArray = getResources().getStringArray(
					R.array.categories);
			// 把新闻分类保存到List中
			final List<HashMap<String, Category>> categories = new ArrayList<HashMap<String, Category>>();
			// 分割新闻类型字符串
			for (int i = 0; i < categoryArray.length; i++) {
				String[] temp = categoryArray[i].split("[|]");
				if (temp.length == 2) {
					int cid = StringUtil.String2Int(temp[0]);
					String title = temp[1];
					Category type = new Category(cid, title);
					HashMap<String, Category> hashMap = new HashMap<String, Category>();
					hashMap.put("category_title", type);
					categories.add(hashMap);
				}
			}
			// 默认选中的新闻分类
			mCid = 1;
			mCatName = "国内";
			// 创建Adapter，指明映射字段
			CustomSimpleAdapter categoryAdapter = new CustomSimpleAdapter(this,
					categories, R.layout.category_title,
					new String[] { "category_title" },
					new int[] { R.id.category_title });

			GridView category = new GridView(this);
			category.setColumnWidth(mColumnWidthDip);// 每个单元格宽度
			category.setNumColumns(GridView.AUTO_FIT);// 单元格数目
			category.setGravity(Gravity.CENTER);// 设置对其方式
			// 设置单元格选择是背景色为透明，这样选择时就不现实黄色北京
			category.setSelector(new ColorDrawable(Color.TRANSPARENT));
			// 根据单元格宽度和数目计算总宽度
			int width = mColumnWidthDip * categories.size();
			LayoutParams params = new LayoutParams(width,
					LayoutParams.MATCH_PARENT);
			// 更新category宽度和高度，这样category在一行显示
			category.setLayoutParams(params);
			// 设置适配器
			category.setAdapter(categoryAdapter);
			// 把category加入到容器中
			LinearLayout categoryList = (LinearLayout) findViewById(R.id.category_layout);
			categoryList.addView(category);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// 小功能
		if (position == 0) {
			Log.i("GridViewActivity", "第" + position + "按钮被点击,显示按钮...");
			gv = (GridView) findViewById(R.id.gridView1);
			ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
			// 在新界面添加按钮名称
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("2", "返回主界面");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("2", "手机状态");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("2", "计时器");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("2", "手机信息");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("2", "输入信息");
			al.add(map);
			SimpleAdapter sa = new SimpleAdapter(this, al, R.layout.button1,
					new String[] { "2" }, new int[] { R.id.button1 });
			// 添加按钮并且显示
			gv.setAdapter(sa);
			// 设置事件监听
			OnItemClickListener button = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int position, long arg3) {
					if (position == 0) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								MainActivity.class);
						Log.i("GridViewActivity", "跳往:MainActivity.class");
						GridViewActivity.this.startActivity(intent);

					} else if (position == 1) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								BatteryActivity.class);
						Log.i("GridViewActivity", "跳往:BatteryActivity.class");
						GridViewActivity.this.startActivity(intent);
					} else if (position == 2) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								TimerActivity.class);
						Log.i("GridViewActivity", "跳往:TimerActivity.class");
						GridViewActivity.this.startActivity(intent);
					} else if (position == 3) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								ViewActivity.class);
						Log.i("GridViewActivity", "跳往:ViewActivity.class");
						GridViewActivity.this.startActivity(intent);
					} else if (position == 4) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								InputActivity.class);
						Log.i("GridViewActivity", "跳往:InputActivity.class");
						GridViewActivity.this.startActivity(intent);
					}
				}
			};
			gv.setOnItemClickListener(button);
		}
		// 例子
		if (position == 1) {
			Log.i("GridViewActivity", "第" + position + "按钮被点击,显示按钮...");
			gv = (GridView) findViewById(R.id.gridView1);
			ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
			// 在新界面添加按钮名称
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("3", "ViewPageActivity");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("3", "TabActivity");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("3", "MenuTabActivity");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("3", "ProgressDialog");
			al.add(map);
			map = new HashMap<String, Object>();
			map.put("3", "AlertDialog");
			al.add(map);
			SimpleAdapter sa = new SimpleAdapter(this, al, R.layout.button1,
					new String[] { "3" }, new int[] { R.id.button1 });
			// 添加按钮并且显示
			gv.setAdapter(sa);
			// 设置事件监听
			OnItemClickListener button = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view,
						int position, long arg3) {
					if (position == 0) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								ViewPageActivity.class);
						Log.i("GridViewActivity", "跳往:ViewPageActivity.class");
						GridViewActivity.this.startActivity(intent);

					} else if (position == 1) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								TabActivity.class);
						Log.i("GridViewActivity", "跳往:TabActivity.class");
						GridViewActivity.this.startActivity(intent);
					} else if (position == 2) {
						Log.i("GridViewActivity", "第" + position + "按钮被点击...");
						Intent intent = new Intent(GridViewActivity.this,
								MenuTabActivity.class);
						Log.i("GridViewActivity", "跳往:TimerActivity.class");
						GridViewActivity.this.startActivity(intent);
					} else if (position == 3) {
						new AlertDialog.Builder(GridViewActivity.this)
								.setTitle("标题")
								.setMessage("O(∩_∩)O哈哈~")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface arg0,
													int arg1) {
											}
										}).show();
					} else if (position == 4) {
						final CharSequence title = "嗬嗬";
						final CharSequence body = "O(∩_∩)O哈哈~,3s后关闭";
						pd = ProgressDialog.show(GridViewActivity.this, title,
								body, true);
						new Thread() {
							public void run() {
								try {
									sleep(3000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								} finally {
									pd.dismiss();
								}
							}
						}.start();
					}
				}
			};
			gv.setOnItemClickListener(button);
		}

		// 程序设置
		if (position == 2) {
			Log.i("GridViewActivity", "第" + position + "按钮被点击,显示按钮...");
			intent = new Intent(GridViewActivity.this, SettingActivity.class);
			startActivity(intent);
		}
	}
}
