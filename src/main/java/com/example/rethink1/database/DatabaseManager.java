package com.example.rethink1.database;

import com.example.rethink1.stock_ordering.Supplier;
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
    public static final String SELECT_ALL_PRODUCTS = "SELECT p FROM Product p";
    public static final String SELECT_PRODUCT_WITH_UID = "SELECT p FROM Product p WHERE p.productUID='";
    public static final String END_STRING = "'";
    private static final String SELECT_ALL_SUPPLIERS = "SELECT s FROM Supplier s";
    private static final String SELECT_ALL_SHOPPINGPORTFOLIOS = "SELECT s FROM ShoppingPortfolio s";
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
    private DatabaseManager(String dbName) {
        initDatabase(dbName);
    }

    /**
     * Creates a path name, an EntityManagerFactory and EntityManager.
     *
     * @param name file name, where the database should be stored.
     */
    private void initDatabase(String name){
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
     * We check if the UID of the product already exists in the database by creating a query with the product's UID.
     * If the product does exists we update the database with the product's object, otherwise we added to the database.
     *
     * @param product The player that has played the game.
     */
    public void addProductToDataBase(Product product){
        var query = manager.createQuery(SELECT_PRODUCT_WITH_UID + product.getProductUID() + END_STRING, Product.class);
        try {
            query.getSingleResult();
//            updateProduct(product);
        }
        catch(Exception e){
            addProduct(product);
        }
    }

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
     * The product's object is added to the database.
     *
     * @param product The product that is in store.
     */
    public void addProduct(Product product){
        manager.getTransaction().begin();
        manager.persist(product);
        manager.getTransaction().commit();
    }

    public void addSupplier(Supplier supplier){
        manager.getTransaction().begin();
        manager.persist(supplier);
        manager.getTransaction().commit();
    }

    public void addShoppingPortfolio(ShoppingPortfolio shoppingPortfolio){
        manager.getTransaction().begin();
        manager.persist(shoppingPortfolio);
        manager.getTransaction().commit();
    }



//    /**
//     * We create a query of the player's data that is already in the database.
//     * We check if the score of the player is higher than the score that the player already has in the database.
//     * If so we update the score of the player, otherwise not.
//     *
//     * @param player The player that has played the game.
//     */
//    public void updatePlayer(Product product){
//        var query = manager.createQuery(SELECT_PRODUCT_WITH_UID + product.getProductUID() + END_STRING, Product.class);
//        manager.getTransaction().begin();
//        Product matchedProduct = query.getSingleResult();
//        if(matchedProduct.getScore() <= player.getScore()) {
//            matchedPlayer.setScore(player.getScore());
//        }
//        manager.getTransaction().commit();
//    }

    public void updatePortfolio(ShoppingPortfolio shoppingPortfolio){
        var query = manager.createQuery(SELECT_PRODUCT_WITH_UID + shoppingPortfolio.getCustomerUID() + END_STRING, ShoppingPortfolio.class);
        manager.getTransaction().begin();
        ShoppingPortfolio matchedPortfolio = query.getSingleResult();
//        idk if you can replace an array with an array, or will you get double values
//        matchedPortfolio.setPurchaseHistory(shoppingPortfolio.getPurchaseHistory());
        manager.getTransaction().commit();
    }

    /**
     * TODO: create delete functions
     */
    public void removeProduct(String productUID){
        try {
            Product product = manager.find(Product.class, productUID);
            manager.getTransaction().begin();
            manager.remove(product);
            manager.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Product is no longer in the inventory");
        }
    }

    public void removeSupplier(int supplierUID){
        try {
            Supplier supplier = manager.find(Supplier.class, supplierUID);
            manager.getTransaction().begin();
            manager.remove(supplier);
            manager.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Product is no longer in the inventory");
        }
    }

    public void removeShoppingPortfolio(String customerUID){
        try {
            ShoppingPortfolio shoppingPortfolio = manager.find(ShoppingPortfolio.class, customerUID);
            manager.getTransaction().begin();
            manager.remove(shoppingPortfolio);
            manager.getTransaction().commit();
        }catch(Exception e){
            System.out.println("Product is no longer in the inventory");
        }
    }


    /**
     * Creates a query of all the data and returns a list with getResultList.
     *
     * @return A list of all the players and their highScore.
     */
    public List<Product> getAllProducts(){
        var query = manager.createQuery(SELECT_ALL_PRODUCTS, Product.class);
        return  query.getResultList();
    }

    public List<Supplier> getAllSuppliers(){
        var query = manager.createQuery(SELECT_ALL_SUPPLIERS, Supplier.class);
        return  query.getResultList();
    }

    public List<ShoppingPortfolio> getAllShoppingPortfolios(){
        var query = manager.createQuery(SELECT_ALL_SHOPPINGPORTFOLIOS, ShoppingPortfolio.class);
        return  query.getResultList();
    }



}
