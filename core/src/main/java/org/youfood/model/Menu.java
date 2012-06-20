package org.youfood.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@Table(name = "MENU")
@NamedQueries({
        @NamedQuery(name = "findAllMenu", query = "SELECT m FROM Menu AS m ORDER BY m.availableStartDate DESC"),
        @NamedQuery(name = "findAllMenuBetweenDates", query = "SELECT m FROM Menu AS m WHERE :date BETWEEN m.availableStartDate AND m.availableEndDate ORDER BY m.availableStartDate DESC"),
        @NamedQuery(name = "findAllMenuByCategory", query = "SELECT m FROM Menu AS m WHERE m.category = :category ORDER BY m.availableStartDate DESC")
})
@DiscriminatorValue(value = "menu")
@XmlRootElement
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    private String description;
    private String picturePath;
    @Temporal(TemporalType.DATE)
    private Date availableStartDate;
    @Temporal(TemporalType.DATE)
    private Date availableEndDate;
    @ManyToOne
    @JoinColumn(name = "Category")
    private Category category;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (id != null ? !id.equals(menu.id) : menu.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
