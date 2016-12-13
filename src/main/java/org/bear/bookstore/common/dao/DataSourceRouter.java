package org.bear.bookstore.common.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("################determine datasource######################");
		return "dataSource";
	}

}
