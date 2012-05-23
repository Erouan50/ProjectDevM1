package org.youfood.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllList", query = "SELECT m FROM Menu AS m"),
        @NamedQuery(name = "findAllMenuBetweenDates", query = "SELECT m FROM Menu AS m WHERE :date BETWEEN m.availableStartDate AND m.availableEndDate")
})
@DiscriminatorValue(value = "menu")
public class Menu extends Article {

    @ManyToMany
    private Collection<Content> contents;
    @Temporal(TemporalType.DATE)
    private Date availableStartDate;
    @Temporal(TemporalType.DATE)
    private Date availableEndDate;

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
