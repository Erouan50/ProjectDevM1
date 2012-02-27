package org.youfood.integrationtest.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.dao.OrderDao;
import org.youfood.exception.CoreException;
import org.youfood.model.Order;
import org.youfood.module.TestModule;
import org.youfood.utils.GuiceJUnitRunner;
import org.youfood.utils.JpaControl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class OrderDaoIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoIntegrationTest.class);

    private static IDataSet dataSet;

    @Inject
    private static JpaControl control;
    @Inject
    private OrderDao orderDao;
    @Inject
    private EntityManager em;

    @Test
    public void testGetAllOrder() {
        List<Order> orders = orderDao.getAllOrder();
        assertEquals(orders.size(), 4);
    }

    @Test
    public void testGetOrderById() {
        Order expected = new Order();
        expected.setId(1000L);
        Order result = orderDao.getOrderById(1000L);
        assertEquals(expected, result);
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
            dataSet = new FlatXmlDataSet(ClassLoader.getSystemResourceAsStream("org/youfood/datatest/insert_order.xml"));
            control.startJpa();
        } catch (IOException e) {
            throw new CoreException("Unable to read insert_order.xml DataSet file", e);
        } catch (DataSetException e) {
            throw new CoreException("Unable to read insert_order.xml DataSet file", e);
        }
    }

    @AfterClass
    public static void treadDown() {
        control.stopJpa();
    }
}
