package org.youfood.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Menu.class)
public abstract class Menu_ {

	public static volatile SingularAttribute<Menu, Long> id;
	public static volatile SingularAttribute<Menu, Category> category;
	public static volatile SingularAttribute<Menu, String> picturePath;
	public static volatile SingularAttribute<Menu, Date> availableEndDate;
	public static volatile SingularAttribute<Menu, String> description;
	public static volatile SingularAttribute<Menu, String> name;
	public static volatile SingularAttribute<Menu, Date> availableStartDate;

}

