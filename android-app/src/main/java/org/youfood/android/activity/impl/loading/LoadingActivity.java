package org.youfood.android.activity.impl.loading;

import org.youfood.android.R;
import org.youfood.android.activity.YouFoodActivity;

import android.widget.ProgressBar;

/**
 * User: GJean Date: 28/03/12 Time: 16:42
 */
public class LoadingActivity extends YouFoodActivity
{

	private ProgressBar progressBar;

	@Override
	public int getContentViewId()
	{

		return R.layout.loading_view;

	}

	@Override
	public void viewDidLoad()
	{
		super.viewDidLoad();

		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);

		this.post(new LoadingRunnable(this));
		if (null != progressBar)
			this.load();

	}

	public void load()
	{
		this.initData();
		this.initUser();
		this.initGridView();
	}

	public void initData()
	{
		this.progressBar.setProgress(25);
	}

	public void initUser()
	{
		this.progressBar.setProgress(50);
	}

	public void initGridView()
	{
		this.progressBar.setProgress(100);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

	}

}
