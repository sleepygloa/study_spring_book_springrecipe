package com.dongwon.tmapapi.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryResult {
	private ResultSet resultSet;
	private Statement statement;
	
	public QueryResult(ResultSet resultSet, Statement statement) {
		this.resultSet = resultSet;
		this.statement = statement;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public void close() {
		try {
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
