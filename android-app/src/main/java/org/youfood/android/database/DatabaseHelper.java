package org.youfood.android.database;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.support.ConnectionSource;
import org.interakting.inlab.activity.AbstractActivity;
import org.interakting.inlab.database.AbstractDatabaseHelper;
import org.youfood.android.database.entity.CategoryEntity;
import org.youfood.android.database.entity.MenuEntity;
import org.youfood.android.database.entity.OrderEntity;

/**
 * User: GJean Date: 28/03/12 Time: 15:22
 */
@SuppressWarnings("uncheked")
public class DatabaseHelper extends AbstractDatabaseHelper {
    private static DatabaseHelper singleton;

    public static void init(AbstractActivity activity, String dataBaseName, int dataBaseVersion) {
        singleton = new DatabaseHelper(activity, dataBaseName, dataBaseVersion);
    }

    public static DatabaseHelper getInstance() {
        return singleton;
    }

    private DatabaseHelper(final AbstractActivity activity, final String dataBaseName, final int dataBaseVersion) {
        super(activity, dataBaseName, dataBaseVersion);
    }

    @Override
    public Class<?>[] getClasses() {
        Class<?>[] classes = new Class<?>[4];

        classes[0] = CategoryEntity.class;
        classes[1] = MenuEntity.class;
        classes[2] = OrderEntity.class;

        return classes;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        super.close();
    }

}
