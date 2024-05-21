package br.edu.fateczl.Produto_JavaWebSpring.persistence;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class GenericDao {
    private Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        String hostName = "localhost";
        String port = "1433";
        String dataBaseName = "springProduto";
        String user = "Luan";
        String passwd = "123456";

        Class.forName("net.sourceforge.jtds.jdbc.Driver"); // driver
        connection = DriverManager.getConnection(String.format(
                "jdbc:jtds:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s",
                hostName,
                port,
                dataBaseName,
                user,
                passwd
        ));

        return connection;
    }
}
