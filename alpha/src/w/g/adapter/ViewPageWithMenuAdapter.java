package w.g.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPageWithMenuAdapter extends PagerAdapter {
	private List<View> views;

	public ViewPageWithMenuAdapter(List<View> views) {
		this.views = views;
	}

	@Override
	public boolean isViewFromObject(View view, Object arg1) {
		return view == arg1;
	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}
}
