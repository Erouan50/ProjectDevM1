package org.youfood.android.database.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.interakting.inlab.activity.collection.IItem;

import java.util.Date;

/**
 * User: GJean Date: 16/06/12 Time: 22:54
 */
@DatabaseTable
public class OrderEntity implements IItem {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private int tableId;
    @DatabaseField
    private int Status;
    @DatabaseField(dataType = DataType.DATE)
    private Date creationDate;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private MenuEntity menuEntities;

    public OrderEntity(int id, int tableId, int status, Date creationDate, MenuEntity menuEntities) {
        super();
        this.id = id;
        this.tableId = tableId;
        Status = status;
        this.creationDate = creationDate;
        this.menuEntities = menuEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public MenuEntity getMenuEntities() {
        return menuEntities;
    }

    public void setMenuEntities(MenuEntity menuEntities) {
        this.menuEntities = menuEntities;
    }

}
