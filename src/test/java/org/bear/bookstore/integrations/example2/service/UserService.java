package org.bear.bookstore.integrations.example2.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bear.bookstore.integrations.example2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired JdbcTemplate jdbcTemplate;
	
	public List<User> findUsers(){
		//sec_user
		return jdbcTemplate.query("select * from sec_user", new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setUserName(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				return u;
			}
			
		});
	}
}
