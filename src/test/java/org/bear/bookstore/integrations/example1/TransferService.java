package org.bear.bookstore.integrations.example1;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
	@Autowired JdbcTemplate jdbcTemplate;
	
	public void findUsers(){
		//sec_user
		jdbcTemplate.query("select * from sec_user", new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.print("name=" + rs.getString("username") + ",");
				System.out.println("pwd=" + rs.getString("password"));
				return null;
			}
			
		});
	}
}
