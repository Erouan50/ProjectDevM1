package org.youfood.dao.jpa;

import com.google.inject.Provider;
import org.youfood.dao.ArticleDao;
import org.youfood.model.Article;
import org.youfood.model.Order;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class ArticleDaoJpa implements ArticleDao {

    @Inject
    private Provider<EntityManager> provider;

    @Override
    public List<Article> getArticleByOrder(Order order) {
        EntityManager em = provider.get();
        Query query = em.createNamedQuery("findAllArticleByOrder");

        query.setParameter("order", order);
        return query.getResultList();
    }
}
