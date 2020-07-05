package com.tyss.ems.id;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class EmployeeIdGenerator implements IdentifierGenerator{
	private final String DEFAULT_SEQUENCE_NAME = "hibernate_sequence";
	Serializable result = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		try {
			connection = session.connection();
			statement = connection.createStatement();
			try {
				int next_val = 0;
				statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=next_val+10001");
				resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
			} catch (Exception e) {
				int next_val = 0;
				statement.execute("CREATE table " + DEFAULT_SEQUENCE_NAME + " (next_val INT NOT NULL)");
				statement.executeUpdate("INSERT INTO " + DEFAULT_SEQUENCE_NAME + " VALUES(0)");
				statement.executeUpdate("UPDATE " + DEFAULT_SEQUENCE_NAME + " SET next_val=(next_val)");
				resultSet = statement.executeQuery("SELECT next_val FROM  " + DEFAULT_SEQUENCE_NAME);
			}
			if (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				String prefix = "TYC";
				int nextValue = resultSet.getInt(1);
				result = prefix.concat((nextValue + 1) +"");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}



}
