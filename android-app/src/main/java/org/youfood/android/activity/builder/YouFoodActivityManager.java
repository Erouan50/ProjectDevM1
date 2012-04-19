package org.youfood.android.activity.builder;

import org.interakting.inlab.activity.IActivity;

/**
 * User: GJean Date: 28/03/12 Time: 15:11
 */
public class YouFoodActivityManager
{

	static private YouFoodActivityManager _instance = new YouFoodActivityManager();

	private YouFoodActivityManager()
	{

	}

	static public YouFoodActivityManager getInstance()
	{
		return YouFoodActivityManager._instance;
	}

	public void build(final IActivity activity)
	{

	}

}
