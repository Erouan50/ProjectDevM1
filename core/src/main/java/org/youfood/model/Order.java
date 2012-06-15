package org.youfood.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@Table(name = "ORDERS")
@NamedQueries({
        @NamedQuery(name = "findAllOrder", query = "SELECT o FROM Order AS o"),
        @NamedQuery(name = "findOrderByIdWithMenus", query = "SELECT o FROM Order AS o JOIN o.menus WHERE o.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer tableId;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @ManyToMany
    private List<Menu> menus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    
}
