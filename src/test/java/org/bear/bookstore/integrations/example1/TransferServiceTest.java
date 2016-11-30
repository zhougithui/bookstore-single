package org.bear.bookstore.integrations.example1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/example1/app-config.xml")
@ActiveProfiles("dev")
@TestPropertySource(properties="key1=value1")
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;
    
    /**
     * el获取属性的值
     */
    @Value("${key1}")
    private String key1;
    
    @Test
    public void testTransferService() {
    	System.out.println(key1);
    	transferService.findUsers();
    }
}