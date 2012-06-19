package org.youfood.android.activity.list;

import com.j256.ormlite.dao.Dao;
import org.interakting.inlab.activity.collection.IItem;
import org.interakting.inlab.activity.collection.provider.DefaultListItemProvider;
import org.interakting.inlab.event.EventManager;
import org.interakting.inlab.event.ExceptionEvent;
import org.youfood.android.database.DatabaseHelper;
import org.youfood.android.database.entity.CategoryEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * User: GJean Date: 17/06/12 Time: 01:46
 */
public class YouFoodListItemProvider extends DefaultListItemProvider {

    private String name;

    private String defaultImage;

    public YouFoodListItemProvider(String name, String defaultImage) {
        this.name = name;
        this.defaultImage = defaultImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    protected List<IItem> load() {
        try {
            final Dao<CategoryEntity, Long> dao = this.getDao();

            return (List<IItem>) (List<?>) dao.queryForAll();

        } catch (Exception e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
        return super.load();
    }

    protected Dao<CategoryEntity, Long> getDao() {
        try {
            return DatabaseHelper.getInstance().getDao(CategoryEntity.class);
        } catch (Exception e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
        return null;
    }

    public IItem create()

    {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("");

        return categoryEntity;

    }

    @Override
    public void add(IItem item) {
        try {
            Dao<CategoryEntity, Long> dao = this.getDao();

            if (null != dao)
                dao.create((CategoryEntity) item);
            super.add(item);
        } catch (SQLException e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
    }

    public void add(final IItem create, final int index) {
        try {
            Dao<CategoryEntity, Long> dao = this.getDao();

            if (null != dao)
                dao.create((CategoryEntity) create);

            super.add(create, index);
        } catch (Exception e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
    }

    @Override
    public void update(IItem item) {
        try {
            Dao<CategoryEntity, Long> dao = this.getDao();

            if (null != dao)
                dao.createOrUpdate((CategoryEntity) item);

            super.update(item);
        } catch (Exception e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
    }

    @Override
    public void remove(IItem item) {
        try {
            Dao<CategoryEntity, Long> dao = this.getDao();

            if (null != dao)
                dao.delete((CategoryEntity) item);

            super.remove(item);
        } catch (Exception e) {
            EventManager.getInstance().sendEvent(new ExceptionEvent(e));
        }
    }


}
