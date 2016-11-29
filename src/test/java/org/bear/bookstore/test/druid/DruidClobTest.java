package org.bear.bookstore.test.druid;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/test/resources/spring-druid.xml"}
		)
public class DruidClobTest {
	@Autowired JdbcTemplate jdbcTemplate;
	
	@Test
	public void testClob(){
		jdbcTemplate.execute("insert into test values(?)", new PreparedStatementCallback<Object>() {

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				String clobContent = "This is a very very long string";  
		        StringReader reader = new StringReader(clobContent);  
		        ps.setCharacterStream(1, reader, clobContent.length());
		        ps.execute();
				return null;
			}
		});
	}
}
