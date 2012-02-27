package org.youfood.integrationtest.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.*;
import org.junit.internal.matchers.StringContains;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.dao.ArticleDao;
import org.youfood.exception.CoreException;
import org.youfood.model.Article;
import org.youfood.model.Content;
import org.youfood.model.Menu;
import org.youfood.model.Order;
import org.youfood.module.TestModule;
import org.youfood.utils.GuiceJUnitRunner;
import org.youfood.utils.JpaControl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class ArticleDaoIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoIntegrationTest.class);

    private static IDataSet dataSet;

    @Inject
    private static JpaControl control;

    @Inject
    private ArticleDao articleDao;
    @Inject
    private EntityManager em;

    @Test
    public void testGetArticleByOrderWhitJustContent() {
        Order order = new Order();
        order.setId(1L);
        List<Article> articles = articleDao.getArticleByOrder(order);
        for (Article article : articles) {
            assertEquals(article.getClass().getName(), Content.class.getName());
            assertThat(article.getName(), Is.is(AnyOf.anyOf(getContentNameMatcherList())));
        }
    }

    @Test
    public void testGetArticleByOrderWhitContentAndMenu() {
        Order order = new Order();
        order.setId(2L);
        List<Article> articles = articleDao.getArticleByOrder(order);
        for (Article article : articles) {
            assertThat(article.getClass().getName(), Is.is(AnyOf.anyOf(
                    StringContains.containsString(Content.class.getName()),
                    StringContains.containsString(Menu.class.getName())
            )));
            assertThat(article.getName(), Is.is(AnyOf.anyOf(getContentAndMenuNameMatcherList())));
        }
    }

    @Before
    public void setUp() {
        try {
            IDatabaseConnection connection = new DatabaseConnection(((EntityManagerImpl) em.getDelegate()).getServerSession().getAccessor().getConnection());
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } catch (DatabaseUnitException e) {
            throw new CoreException("Unable to create connection", e);
        } catch (SQLException e) {
            throw new CoreException("Unable to execute insertion", e);
        }
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            dataSet = new FlatXmlDataSet(ClassLoader.getSystemResourceAsStream("org/youfood/datatest/insert_articles_and_orders.xml"));
            control.startJpa();
        } catch (IOException e) {
            throw new CoreException("Unable to read insert_menus.xml DataSet file", e);
        } catch (DataSetException e) {
            throw new CoreException("Unable to read insert_menus.xml DataSet file", e);
        }
    }

    @AfterClass
    public static void treadDown() {
        control.stopJpa();
    }

    private List getContentNameMatcherList() {
        List<Matcher<String>> contentsMatcher = new ArrayList<Matcher<String>>();
        contentsMatcher.add(StringContains.containsString("sel"));
        contentsMatcher.add(StringContains.containsString("poivre"));
        contentsMatcher.add(StringContains.containsString("sucre"));
        contentsMatcher.add(StringContains.containsString("farine"));
        return contentsMatcher;
    }

    private List getContentAndMenuNameMatcherList() {
        List<Matcher<String>> contentsMatcher = new ArrayList<Matcher<String>>();
        contentsMatcher.add(StringContains.containsString("sel"));
        contentsMatcher.add(StringContains.containsString("fast_menu"));
        contentsMatcher.add(StringContains.containsString("lowcost_menu"));
        return contentsMatcher;
    }
}