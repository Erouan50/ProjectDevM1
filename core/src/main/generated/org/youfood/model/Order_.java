package org.youfood.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Long> id;
	public static volatile ListAttribute<Order, Menu> menus;
	public static volatile SingularAttribute<Order, Date> creationDate;
	public static volatile SingularAttribute<Order, Integer> tableId;
	public static volatile SingularAttribute<Order, Integer> status;

}

