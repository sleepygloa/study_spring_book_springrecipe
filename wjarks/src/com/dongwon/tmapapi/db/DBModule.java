package com.dongwon.tmapapi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.LinkedList;

import com.dongwon.tmapapi.TMapAPI;

public class DBModule {
	private Connection connection;
	private LinkedList<Statement> statements = new LinkedList<>();
	private LinkedList<ResultSet> resultSets = new LinkedList<>();

	public static class Builder{
		private String host = "";
		private short port = 0;
		private String db = "";
		private String id = "";
		private String pw = "";
		private String sid = "";
		
		public Builder() {}
		
		public Builder host(String host) {
			if(host.endsWith("/"))
				host.substring(0, host.length()-1);
			if(host.startsWith("jdbc")) {
				host = host.replace("jdbc:oracle:thin://", "");
			}
			if(host.contains(":")) {
				port = Short.parseShort(host.split(":")[1]);
				host = host.split(":")[0];
			}
			this.host = host;
			return this;
		}
		public Builder port(short port) {
			this.port = port;
			return this;
		}
		public Builder db(String db) {
			this.db = db;
			return this;
		}
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		public Builder pw(String pw) {
			this.pw = pw;
			return this;
		}
		public Builder sid(String sid) {
			this.sid = sid;
			return this;
		}
		public DBModule build() {
			return new DBModule(this);
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	private DBModule(Builder b) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.setLoginTimeout(30);
			this.connection = DriverManager.getConnection("jdbc:oracle:thin:@"+b.host+":"+b.port+":"+b.sid+"", b.id, b.pw);
			TMapAPI.logger.log(Level.INFO, "DB Connected! "+"jdbc:oracle:thin:@"+b.host+":"+b.port+":"+b.sid+" ID:"+b.id);
			if(b.db.length() > 0) {
				this.connection.createStatement().execute("alter session set current_schema="+b.db);
				TMapAPI.logger.log(Level.INFO, "Schema : "+b.db);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean execute(String sql) {
		try {
			Statement stmt = connection.createStatement();
			statements.add(stmt);
			boolean res = stmt.execute(sql);
			stmt.close();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public QueryResult executeQuery(String sql) {
		try {
			Statement stmt = connection.createStatement();
			statements.add(stmt);
			ResultSet res = stmt.executeQuery(sql);
			resultSets.add(res);
			QueryResult qr = new QueryResult(res, stmt);
			return qr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdate(String sql) {
		try {
			Statement stmt = connection.createStatement();
			statements.add(stmt);
			int res = stmt.executeUpdate(sql);
			stmt.close();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeAll() {
		for (Statement s : statements) {
			try {
				if(!s.isClosed())
					s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (ResultSet rs : resultSets) {
			try {
				if(!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
