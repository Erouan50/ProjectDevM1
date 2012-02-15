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

}
