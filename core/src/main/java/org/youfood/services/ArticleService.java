package org.youfood.services;

import org.youfood.model.Article;
import org.youfood.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class ArticleService {

    @PersistenceContext
    private EntityManager em;

    private List<Article> getArticleByOrder(Order order) {
        Query query = em.createNamedQuery("findAllArticleByOrder");
        query.setParameter("order", order);
        return query.getResultList();
    }
}
