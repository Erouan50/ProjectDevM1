package org.youfood.android.database.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.interakting.inlab.activity.collection.IItem;

import java.util.Date;

/**
 * User: GJean Date: 16/06/12 Time: 18:16
 */
@DatabaseTable
public class MenuEntity implements IItem {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private String picturePath;
    @DatabaseField(dataType = DataType.DATE)
    private Date availableStartDate;
    @DatabaseField(dataType = DataType.DATE)
    private Date availableEndDate;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private CategoryEntity category;


    public MenuEntity(int id, String name, String description, String picturePath, Date availableStartDate, Date availableEndDate, CategoryEntity category) {

        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.picturePath = picturePath;
        this.availableStartDate = availableStartDate;
        this.availableEndDate = availableEndDate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Date getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(Date availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public Date getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(Date availableEndDate) {
        this.availableEndDate = availableEndDate;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuEntity)) return false;

        MenuEntity that = (MenuEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
