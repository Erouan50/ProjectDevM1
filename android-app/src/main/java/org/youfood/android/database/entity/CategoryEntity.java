package org.youfood.android.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.interakting.inlab.activity.collection.IItem;

import java.util.List;

/**
 * User: GJean Date: 16/06/12 Time: 18:16
 */
@DatabaseTable
public class CategoryEntity implements IItem {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private List<MenuEntity> menuEntities;

    public CategoryEntity(String name, List<MenuEntity> menuEntities) {

        this.name = name;
        this.menuEntities = menuEntities;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<MenuEntity> getMenuEntities() {
        return menuEntities;
    }
}
