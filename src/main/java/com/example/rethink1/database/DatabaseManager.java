//package com.example.rethink1.database;
//
////import nl.rug.aoop.asteroids.model.Player;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.PersistenceException;
//import javax.swing.*;
//import java.nio.file.Path;
//import java.util.List;
//
///**
// * TODO: I (Joy) have to make this class for our database, this was from AOOP
// */
//
///**
// * The class represents the database Manager. This database Manager uses ObjectDB to store objects in the database.
// */
//public class DatabaseManager {
//
//    public static final String SELECT_ALL_PLAYERS = "SELECT p FROM Player p";
//    public static final String SELECT_PLAYER_WITH_NAME = "SELECT p FROM Player p WHERE p.name='";
//    public static final String END_STRING = "'";
//
//    private EntityManagerFactory managerFactory;
//    private EntityManager manager;
//    private static DatabaseManager instance;
//
//    /**
//     * If there does not exists a DatabaseManager we create a new DatabaseManager,
//     * otherwise we return the instance of the DatabaseManager.
//     *
//     * @return One instance of the DatabaseManager.
//     */
//    public static DatabaseManager getInstance(){
//        if (instance == null) {
//            instance = new DatabaseManager("highScoreDB");
//        }
//        return instance;
//    }
//
//    /**
//     * Initialize a databaseManager with initDatabase.
//     *
//     * @param dbName The file name, where the database should be stored.
//     */
//    private DatabaseManager(String dbName) {
//        initDatabase(dbName);
//    }
//
//    /**
//     * Creates a path name, an EntityManagerFactory and EntityManager.
//     *
//     * @param name file name, where the database should be stored.
//     */
//    private void initDatabase(String name){
//        try{
//            Path dbPath = Path.of("db", name + ".odb");
//            managerFactory = Persistence.createEntityManagerFactory(dbPath.toString());
//            manager = managerFactory.createEntityManager();
//        }
//        catch(PersistenceException e){
//            JOptionPane.showMessageDialog(null, "You cannot make use of this game sorry");
//            System.exit(0);
//        }
//
//    }
//
//    /**
//     * Closes the EntityManager and EntityManagerFactory.
//     */
//    public void closeDataBase(){
//        manager.close();
//        managerFactory.close();
//    }
//
//    /**
//     * We check if the username of the player already exists in the database by creating a query with the player's username.
//     * If the player does exists we update the database with the player's object, otherwise we added to the database.
//     *
//     * @param player The player that has played the game.
//     */
//    public void addToDataBase(Player player){
//        var query = manager.createQuery(SELECT_PLAYER_WITH_NAME + player.getName() + END_STRING, Player.class);
//        try {
//            query.getSingleResult();
//            updatePlayer(player);
//        }
//        catch(Exception e){
//            addPlayer(player);
//        }
//    }
//
//    /**
//     * The player's object is added to the database.
//     *
//     * @param player The player that has played the game.
//     */
//    public void addPlayer(Player player){
//        manager.getTransaction().begin();
//        manager.persist(player);
//        manager.getTransaction().commit();
//    }
//
//    /**
//     * We create a query of the player's data that is already in the database.
//     * We check if the score of the player is higher than the score that the player already has in the database.
//     * If so we update the score of the player, otherwise not.
//     *
//     * @param player The player that has played the game.
//     */
//    public void updatePlayer(Player player){
//        var query = manager.createQuery(SELECT_PLAYER_WITH_NAME + player.getName() + END_STRING, Player.class);
//        manager.getTransaction().begin();
//        Player matchedPlayer = query.getSingleResult();
//        if(matchedPlayer.getScore() <= player.getScore()) {
//            matchedPlayer.setScore(player.getScore());
//        }
//        manager.getTransaction().commit();
//    }
//
//    /**
//     * Creates a query of all the data and returns a list with getResultList.
//     *
//     * @return A list of all the players and their highScore.
//     */
//    public List<Player> getAllPLayers(){
//        var query = manager.createQuery(SELECT_ALL_PLAYERS, Player.class);
//        return  query.getResultList();
//    }
//
//
//}
