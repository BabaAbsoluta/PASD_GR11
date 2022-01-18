package com.example.rethink1.database;

import com.example.rethink1.stock_ordering.Supplier;
import com.example.rethink1.stock_prediction.InventorySpace;
import com.example.rethink1.stock_prediction.Manager;
import com.example.rethink1.stock_prediction.Product;
import com.example.rethink1.stock_prediction.ShoppingPortfolio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.*;
import java.nio.file.Path;
import java.util.List;

/**
 * TODO: Add all the functions for the different classes
 */

/**
 * The class represents the database Manager. This database Manager uses ObjectDB to store objects in the database.
 */
public class DatabaseManager {

    /*SIDENOTE: Those errors do not go away, the compiler does not complain!!*/
    public static final String END_STRING = "'";

    /*SQL for the list and info of the objects in the database*/
    private static final String SELECT_ALL_SUPPLIERS = "SELECT s FROM Supplier s";
    private static final String SELECT_ALL_SHOPPINGPORTFOLIOS = "SELECT s FROM ShoppingPortfolio s";
    private static final String SELECT_ALL_INVENTORY = "SELECT s FROM InventorySpace s";
    private static final String SELECT_MANAGER = "SELECT m FROM Manager m";

    /*SQL for the update methods*/
    private static final String SELECT_SHOPPINGPORT_WITH_CUID = "SELECT s FROM ShoppingPortfolio s WHERE s.customerUID='";

    private EntityManagerFactory managerFactory;
    private EntityManager manager;
    private static DatabaseManager instance;

    /**
     * If there does not exists a DatabaseManager we create a new DatabaseManager,
     * otherwise we return the instance of the DatabaseManager.
     *
     * @return One instance of the DatabaseManager.
     */
    public static DatabaseManager getInstance(){
        if (instance == null) {
            instance = new DatabaseManager("rethinkSupermarketDB");
        }
        return instance;
    }

    /**
     * Initialize a databaseManager with initDatabase.
     *
     * @param dbName The file name, where the database should be stored.
     */
    public DatabaseManager(String dbName) {
        initDatabase(dbName);
    }

    /**
     * Creates a path name, an EntityManagerFactory and EntityManager.
     *
     * @param name file name, where the database should be stored.
     */
    public void initDatabase(String name){
        try{
            Path dbPath = Path.of("db", name + ".odb");
            managerFactory = Persistence.createEntityManagerFactory(dbPath.toString());
            manager = managerFactory.createEntityManager();
        }
        catch(PersistenceException e){
            JOptionPane.showMessageDialog(null, "Error");
            System.exit(0);
        }

    }

    /**
     * Closes the EntityManager and EntityManagerFactory.
     */
    public void closeDataBase(){
        manager.close();
        managerFactory.close();
    }


    /**
     * We check if the UID of the shopping portfolio already exists in the database by creating a query with the shopping portfolio's UID.
     * If the shopping portfolio does exists we update the database with the shopping portfolio's object, otherwise we added to the database.
     *
     * @param shoppingPortfolio The shopping portfolio of the customer.
     */
    public void addPortfolioToDataBase(ShoppingPortfolio shoppingPortfolio){
        var query = manager.createQuery(SELECT_SHOPPINGPORT_WITH_CUID + shoppingPortfolio.getCustomerUID() + END_STRING, ShoppingPortfolio.class);
        try {
            query.getSingleResult();
            updatePortfolio(shoppingPortfolio);
        }
        catch(Exception e){
            addShoppingPortfolio(shoppingPortfolio);
        }
    }

    /**
     * The inventory's object is added to the database.
     *
     * @param inventorySpace The inventory of the store.
     */
    public void addInventory(InventorySpace inventorySpace){
        manager.getTransaction().begin();
        manager.persist(inventorySpace);
        manager.getTransaction().commit();
    }

    public void addSupplier(Supplier supplier){
        manager.getTransaction().begin();
        manager.persist(supplier);
        manager.getTransaction().commit();
    }

    public void addShoppingPortfolio(ShoppingPortfolio shoppingPortfolio){
        //manager.getTransaction().begin();
        manager.persist(shoppingPortfolio);
        //manager.getTransaction().commit();
    }

    public void addManager(Manager supermarketManager){
        manager.getTransaction().begin();
        manager.persist(supermarketManager);
        manager.getTransaction().commit();
    }



    public void updatePortfolio(ShoppingPortfolio shoppingPortfolio){
        removeShoppingPortfolio(shoppingPortfolio.getCustomerUID());
        addShoppingPortfolio(shoppingPortfolio);

    }

    public void updateInventory(InventorySpace inventorySpace){
        removeInventory(inventorySpace.getInventoryUID());
        addInventory(inventorySpace);
    }

    public void updateSupplier(Supplier supplier){
        removeSupplier(supplier.getSupplierUID());
        addSupplier(supplier);
    }


    public void removeInventory(int inventoryUID){
        try {
            InventorySpace inventorySpace = manager.find(InventorySpace.class, inventoryUID);
            manager.getTransaction().begin();
            manager.remove(inventorySpace);
            manager.getTransaction().commit();
            System.out.println("Inventory is removed");
        }catch(Exception e){
            System.out.println("Inventory is removed");
        }
    }

    public void removeSupplier(int supplierUID){
        try {
            Supplier supplier = manager.find(Supplier.class, supplierUID);
            manager.getTransaction().begin();
            manager.remove(supplier);
            manager.getTransaction().commit();
            System.out.println("Supplier is removed");
        }catch(Exception e){
            System.out.println("Supplier is removed");
        }
    }

    public void removeShoppingPortfolio(String customerUID){
        try {
            ShoppingPortfolio shoppingPortfolio = manager.find(ShoppingPortfolio.class, customerUID);
            manager.getTransaction().begin();
            manager.remove(shoppingPortfolio);
            manager.getTransaction().commit();
            System.out.println("Shopping portfolio is removed");
        }catch(Exception e){
            System.out.println("Shopping portfolio is removed");
        }
    }

    public void removeManager(long employeeUID){
        try {
            Manager supermarketManager = manager.find(Manager.class, employeeUID);
            manager.getTransaction().begin();
            manager.remove(supermarketManager);
            manager.getTransaction().commit();
            System.out.println("Manager is removed");
        }catch(Exception e){
            System.out.println("Manager is removed");
        }
    }


    /**
     * Creates a query of all the data and returns a list with getResultList.
     *
     * @return The information of the inventory.
     */
    public List<InventorySpace> getInfoInventory(){
        var query = manager.createQuery(SELECT_ALL_INVENTORY, InventorySpace.class);
        return  query.getResultList();
    }

    /**
     * Creates a query of all the data and returns a list with getResultList.
     *
     * @return A list of all the suppliers.
     */
    public List<Supplier> getAllSuppliers(){
        var query = manager.createQuery(SELECT_ALL_SUPPLIERS, Supplier.class);
        return  query.getResultList();
    }

    /**
     * Creates a query of all the data and returns a list with getResultList.
     *
     * @return A list of all the shopping portfolio of the registered customers.
     */
    public List<ShoppingPortfolio> getAllShoppingPortfolios(){
        var query = manager.createQuery(SELECT_ALL_SHOPPINGPORTFOLIOS, ShoppingPortfolio.class);
        return  query.getResultList();
    }

    /**
     * Creates a query of all the data and returns a list with getResultList.
     *
     * @return The information of the manager.
     */
    public List<Manager> getInfoManager(){
        var query = manager.createQuery(SELECT_MANAGER, Manager.class);
        return  query.getResultList();
    }



}
