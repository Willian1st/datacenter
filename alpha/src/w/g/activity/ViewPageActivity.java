package w.g.activity;

import java.util.ArrayList;
import java.util.List;

import w.g.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Viewpager实现引导页面 实现功能：1、左右滑动时，当position的页面显示时，底下的小点中相应的小点为高亮状态
 * 2、点击底下的小点时，viewpager将显示对应index的页面
 * 
 * @author wanghaisheng
 * 
 */
public class ViewPageActivity extends Activity {
	private ViewPager viewPager;
	// 界面List
	private List<View> viewList = new ArrayList<View>();
	// view的背景图片
	private final int[] iconIds = new int[] { R.drawable.bg_1, R.drawable.bg_2,
			R.drawable.bg_3, R.drawable.bg_4, R.drawable.bg_5 };
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置界面
		setContentView(R.layout.activity_viewpage);
		inflater = LayoutInflater.from(this);
		// 取得ViewPage
		this.viewPager = (ViewPager) findViewById(R.id.viewPagers);
		// 分页效果
		final RadioGroup dotGroupButton = (RadioGroup) findViewById(R.id.dotGroupButton);
		for (int i = 0; i < iconIds.length; i++) {
			// 具体页面
			final View itemView = inflater.inflate(R.layout.pager_item, null);
			itemView.setBackgroundResource(iconIds[i]);
			// 下部的页码
			final RadioButton dotButton = new RadioButton(this);
			dotButton.setId(i);
			dotButton.setLayoutParams(new RadioGroup.LayoutParams(
					RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT));
			dotButton.setPadding(20, 20, 20, 20);
			dotButton.setButtonDrawable(R.drawable.dot_bg);
			dotButton.setTag(i);// 设置当前位置
			// 点击对应的点时，Viewpager切换到对应的page,并将点击的点设置为高亮
			dotButton
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								viewPager.setCurrentItem((Integer) dotButton
										.getTag());
							}
						}
					});
			dotGroupButton.addView(dotButton);
			dotGroupButton.check(0);// 将第一个小白点设置为高亮
			viewList.add(itemView);
		}

		viewPager.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(viewList.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(viewList.get(position));
				return viewList.get(position);
			}

			@Override
			public int getCount() {
				return iconIds.length;
			}

			@Override
			public void finishUpdate(View arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void restoreState(Parcelable arg0, ClassLoader arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public Parcelable saveState() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void startUpdate(View arg0) {
				// TODO Auto-generated method stub

			}

		});

		/**
		 * 监听PageChange事件，切换底部小点的高亮位置
		 */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				((RadioButton) dotGroupButton.getChildAt(position))
						.setChecked(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

}
