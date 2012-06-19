package org.youfood.android.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import org.interakting.inlab.activity.collection.IItem;

import java.util.Collection;

/**
 * User: GJean Date: 16/06/12 Time: 18:16
 */
@DatabaseTable
public class CategoryEntity implements IItem {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @ForeignCollectionField
    private Collection<MenuEntity> menuEntities;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, Collection<MenuEntity> menuEntities) {

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

    public Collection<MenuEntity> getMenuEntities() {
        return menuEntities;
    }
}
