package util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {
    private static BlockingQueue<Connection> connections;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Class.forName("util.ConnectionManager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadConnections();

    }

    private ConnectionManager() {
    }

    public static Connection connect(String url,String username,String password) {
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() {
        try {
            return DriverManager.getConnection(PropertiesManager.getProperties("url"),
                    PropertiesManager.getProperties("username"), PropertiesManager.getProperties("password"));
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Connection get() {
        try {
            return connections.take();
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
    }

    private static void loadConnections() {
        connections = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            Connection connection = connect();
            var proxyConnection = (Connection)Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                    ((proxy, method, args) -> method.getName().equals("close") ?
                            connections.add((Connection) proxy) :
                            method.invoke(connection, args)));
            connections.add(proxyConnection);

        }
    }





}
