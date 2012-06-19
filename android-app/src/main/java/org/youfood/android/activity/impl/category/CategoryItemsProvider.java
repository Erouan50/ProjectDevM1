package org.youfood.android.activity.impl.category;

import com.j256.ormlite.dao.Dao;
import org.interakting.inlab.activity.collection.IItem;
import org.interakting.inlab.event.EventManager;
import org.interakting.inlab.event.ExceptionEvent;
import org.youfood.android.activity.list.YouFoodListItemProvider;
import org.youfood.android.database.entity.CategoryEntity;
import org.youfood.android.database.entity.MenuEntity;

import java.util.List;

/**
 * User: GJean
 * Date: 16/06/12
 * Time: 23:10
 */
public class CategoryItemsProvider extends YouFoodListItemProvider {

    private String name;
    private String defaultImage;

    private MenuEntity menuEntities;

    public CategoryItemsProvider(String name, String defaultImage) {
        super(name, defaultImage);

        this.name = name;
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
}
