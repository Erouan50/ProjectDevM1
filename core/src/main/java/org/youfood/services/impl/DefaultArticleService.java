package org.youfood.services.impl;

import org.youfood.dao.ArticleDao;
import org.youfood.model.Article;
import org.youfood.model.Order;
import org.youfood.services.ArticleService;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class DefaultArticleService implements ArticleService, Serializable {

    @Inject
    private ArticleDao articleDao;

    @Override
    public List<Article> getArticleByOrder(Order order) {
        return articleDao.getArticleByOrder(order);
    }
}
