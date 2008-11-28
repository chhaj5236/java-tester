package org.jtester.unitils.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.database.config.DataSourceFactory;

public class JTesterSourceFactory implements DataSourceFactory {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(JTesterSourceFactory.class);

	public JTesterSourceFactory() {
	}

	public void init(Properties configuration) {
	}

	public DataSource createDataSource() {
		String[] db = { "classpath:/org/jtester/unitils/database/jtester-datasource.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(db);
		JTesterDataSource datasource = (JTesterDataSource) context.getBean("dataSource");
		datasource.init();
		return datasource;
	}
}
