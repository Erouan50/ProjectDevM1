package org.youfood.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@Table(name = "Orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrder", query = "SELECT o FROM Order AS o")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer tableId;
    @ManyToMany
    @JoinTable(name = "Orders_Articles")
    private Collection<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
