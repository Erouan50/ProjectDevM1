package org.youfood.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllList", query = "SELECT m FROM Menu AS m")
})
@DiscriminatorValue(value = "menu")
public class Menu extends Article {

    @ManyToMany
    private Collection<Content> contents;


    public Collection<Content> getContents() {
        return contents;
    }

    public void setContents(Collection<Content> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        return true;
    }

    @Override
    public int hashCode() {
        return contents != null ? contents.hashCode() : 0;
    }
}
