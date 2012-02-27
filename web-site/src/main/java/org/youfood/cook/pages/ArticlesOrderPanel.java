package org.youfood.cook.pages;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.youfood.model.Article;
import org.youfood.model.Order;
import org.youfood.services.ArticleService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class ArticlesOrderPanel extends VerticalLayout {

    private ArticleService articleService;
    private Order order;
    private List<Label> labels = new ArrayList<Label>();

    public ArticlesOrderPanel() {

    }

    @Inject
    public ArticlesOrderPanel(Order order, ArticleService articleService) {
        this.order = order;
        this.articleService = articleService;
    }

    public void setOrder(Order order) {
        this.order = order;
        repaint();
    }

    private void repaint() {
        removeAllComponents();
        List<Article> articles = articleService.getArticleByOrder(order);
        for (Article article : articles) {
            Label label = new Label(article.getName());
            labels.add(label);
            addComponent(label);
        }
    }
}
