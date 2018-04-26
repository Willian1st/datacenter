package w.g.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPageAdapter extends PagerAdapter {
	private List<View> viewList;

	public ViewPageAdapter(List<View> viewList) {
		super();
		this.viewList = viewList;
	}

	// 判断是否由对象生成界面
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 销毁position位置的界面
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(viewList.get(position));
	}

	// 初始化position位置的界面
	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(viewList.get(position));
		return viewList.get(position);
	}

	// 获取当前窗体界面数
	@Override
	public int getCount() {
		return viewList.size();
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

}
