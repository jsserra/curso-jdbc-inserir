package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	//Connection vai ser o objeto de conexão com BD do JDBC. Obs. o import do Connection é java.sql
	private static Connection conn = null;
	
	//Método para conectar com BD
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}			
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;		
	}
	
	//Método para fechar a conexão
	public static void closeConnectio() {
		if (conn != null) {
			try {
				conn.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Método responsável por carregar as propriedadese que estão no arquivo db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs); //load faz leitura do arquivo Properties apontado pelo FileInputStream fs
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//Método criado para fechar o Statement
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Método criado para fechar o Res
	public static void closesResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
