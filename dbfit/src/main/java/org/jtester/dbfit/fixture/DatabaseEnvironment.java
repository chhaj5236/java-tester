package org.jtester.dbfit.fixture;

import java.sql.SQLException;

import dbfit.environment.DB2Environment;
import dbfit.environment.DBEnvironment;
import dbfit.environment.DbEnvironmentFactory;
import dbfit.environment.DerbyEnvironment;
import dbfit.environment.MySqlEnvironment;
import dbfit.environment.OracleEnvironment;
import dbfit.environment.SqlServerEnvironment;
import fit.Parse;

public class DatabaseEnvironment extends fitlibrary.SequenceFixture {
	public void doTable(Parse table) {
		if (args.length > 0) {
			DBEnvironment oe;
			String requestedEnv = args[0].toUpperCase().trim();
			if ("ORACLE".equals(requestedEnv)) {
				oe = new OracleEnvironment();
			} else if ("MYSQL".equals(requestedEnv)) {
				oe = new MySqlEnvironment();
			} else if ("SQLSERVER".equals(requestedEnv)) {
				oe = new SqlServerEnvironment();
			} else if ("DB2".equals(requestedEnv)) {
				oe = new DB2Environment();
			} else if ("DERBY".equals(requestedEnv)) {
				oe = new DerbyEnvironment();
			} else
				throw new UnsupportedOperationException("DB Environment not supported:" + args[0]);
			DbEnvironmentFactory.setDefaultEnvironment(oe);
			// setSystemUnderTest(oe);
		}
		super.doTable(table);
	}

	// workaround for fitlibrary limitation with system under test & abstract
	// classes
	public void rollback() throws SQLException {
		DbEnvironmentFactory.getDefaultEnvironment().rollback();
	}

	public void commit() throws SQLException {
		DbEnvironmentFactory.getDefaultEnvironment().commit();
	}

	public void connect(String connectionString) throws SQLException {
		DbEnvironmentFactory.getDefaultEnvironment().connect(connectionString);
	}

	public void close() throws SQLException {
		DbEnvironmentFactory.getDefaultEnvironment().closeConnection();
	}

	public void connect(String dataSource, String username, String password) throws SQLException {
		DbEnvironmentFactory.getDefaultEnvironment().connect(dataSource, username, password);
	}

	public void setOption(String option, String value) {
		dbfit.util.Options.setOption(option, value);
	}
}