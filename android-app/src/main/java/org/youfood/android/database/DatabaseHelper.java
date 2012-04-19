package org.youfood.android.database;

import org.interakting.inlab.activity.AbstractActivity;
import org.interakting.inlab.database.AbstractDatabaseHelper;
import org.youfood.android.database.entity.MenuEntity;

/**
 * User: GJean Date: 28/03/12 Time: 15:22
 */
public class DatabaseHelper extends AbstractDatabaseHelper
{
	private static DatabaseHelper singleton;

	public static void init(AbstractActivity activity, String dataBaseName, int dataBaseVersion)
	{
		singleton = new DatabaseHelper(activity, dataBaseName, dataBaseVersion);
	}

	public static DatabaseHelper getInstance()
	{
		return singleton;
	}

	private DatabaseHelper(final AbstractActivity activity, final String dataBaseName, final int dataBaseVersion)
	{
		super(activity, dataBaseName, dataBaseVersion);
	}

	@Override
	public Class<?>[] getClasses()
	{
		Class<?>[] classes = new Class<?>[2];

		classes[0] = MenuEntity.class;

		return classes;
	}

	@Override
	public void close()
	{
		super.close();
	}

}
