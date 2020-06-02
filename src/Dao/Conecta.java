package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class Conecta {
    public static Connection getConexao()throws Exception{
        //Configura servidor de Banco de Dados e nome do Banco
        //String serverName = "localhost";
        //String serverName = "localhost:3306";
        
        //String mydatabase = "db.sqlite3";

        //Login e senha do banco

        //Carregando o JDBC Driver
        String currentDirectory = System.getProperty("user.dir");
        System.out.print(currentDirectory);
        // Criando a conexï¿½o com o Banco de Dados
        String url = "jdbc:sqlite:"+currentDirectory+"/db.sqlite3";// a JDBC url
        Connection  con = DriverManager.getConnection(url);

        return con;        
    }
}
