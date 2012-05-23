package org.youfood.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Entity
@DiscriminatorValue("manager")
@NamedQueries({
        @NamedQuery(name = "findManagerByUsername", query = "SELECT manager FROM Manager AS manager WHERE manager.username = :username")
})
public class Manager extends User {
}
