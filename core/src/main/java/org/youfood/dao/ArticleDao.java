package org.youfood.dao;

import org.youfood.model.Article;
import org.youfood.model.Order;

import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface ArticleDao {

    List<Article> getArticleByOrder(Order order);
}
