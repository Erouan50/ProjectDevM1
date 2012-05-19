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
import org.youfood.dao.MenuDao;
import org.youfood.exception.CoreException;
import org.youfood.model.Menu;
import org.youfood.module.TestModule;
import org.youfood.utils.GuiceJUnitRunner;
import org.youfood.utils.JpaControl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({TestModule.class})
public class MenuDaoIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuDaoIntegrationTest.class);

    private static IDataSet dataSet;

    @Inject
    private static JpaControl control;
    @Inject
    private MenuDao menuDao;
    @Inject
    private EntityManager em;

    @Test
    public void testAddMenu() {
        Menu menu = new Menu();
        menu.setName("sushi");
        menuDao.addMenu(menu);
        Menu result = menuDao.getMenuById(menu.getId());
        assertEquals(result, menu);
    }

    @Test
    public void testGetMenu() {
        Menu expected = new Menu();
        expected.setId(1000L);
        expected.setName("pizza");
        Menu result = menuDao.getMenuById(1000L);
        assertEquals(expected, result);
    }

    @Test
    public void testGetListMenu() {
        List<Menu> menus = menuDao.getAllMenu();
        assertEquals(menus.size(), 4);
    }

    @Test
    public void testUpdateMenu() {
        String updateName = "poulet";
        Menu expected = new Menu();
        expected.setId(1000L);
        expected.setName(updateName);
        Menu original = menuDao.getMenuById(1000L);
        assertNotSame(expected, original);
        original.setName(updateName);
        menuDao.updateMenu(original);
        Menu result = menuDao.getMenuById(1000L);
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteMenu() {
        Menu menu = new Menu();
        menu.setId(1000L);
        menuDao.removeMenu(menu);
        Menu result = menuDao.getMenuById(1000L);
        assertNull(result);
    }

//    @Test
    public void testGetMenusWeekByDate() {
        Date date = new GregorianCalendar(2012, 3, 6).getTime();
        List<Menu> menus = menuDao.getMenusWeekByDate(date);
        assertEquals(3, menus.size());
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
            dataSet = new FlatXmlDataSet(ClassLoader.getSystemResourceAsStream("org/youfood/datatest/insert_menus.xml"));
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
}