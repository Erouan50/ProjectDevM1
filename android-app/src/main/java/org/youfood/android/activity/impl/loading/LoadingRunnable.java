package org.youfood.android.activity.impl.loading;

/**
 * User: GJean Date: 28/03/12 Time: 17:27
 */
public class LoadingRunnable implements Runnable
{
	private LoadingActivity activity;

	public LoadingRunnable(LoadingActivity activity)
	{
		this.activity = activity;
	}

	@Override
	public void run()
	{
		if (null != activity)
		{
			// activity.load();
		}
	}
}
