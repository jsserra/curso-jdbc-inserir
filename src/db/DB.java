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
	//Connection vai ser o objeto de conex�o com BD do JDBC. Obs. o import do Connection � java.sql
	private static Connection conn = null;
	
	//M�todo para conectar com BD
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
	
	//M�todo para fechar a conex�o
	public static void closeConnectio() {
		if (conn != null) {
			try {
				conn.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//M�todo respons�vel por carregar as propriedadese que est�o no arquivo db.properties
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
	
	//M�todo criado para fechar o Statement
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//M�todo criado para fechar o Res
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
