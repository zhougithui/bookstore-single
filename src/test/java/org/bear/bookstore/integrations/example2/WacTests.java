package org.bear.bookstore.integrations.example2;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@ContextHierarchy(
		@ContextConfiguration("classpath:example2/app-config-web.xml")
		)
@TestPropertySource(properties="port:80")
public class WacTests extends AppConfig{
	@Autowired
    WebApplicationContext wac; // cached

    @Autowired
    MockServletContext servletContext; // cached

    @Autowired
    MockHttpSession session;

    @Autowired
    MockHttpServletRequest request;

    @Autowired
    MockHttpServletResponse response;

    @Autowired
    ServletWebRequest webRequest;
    
    private MockMvc mockMvc;
    
    WebClient webClient;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.webClient = MockMvcWebClientBuilder.webAppContextSetup(wac).build();
    }
    
    //@Test
    public void getAccount() throws Exception {
    	this.mockMvc
    	.perform(MockMvcRequestBuilders
    			.post("/ulist")
    			.contentType(MediaType.APPLICATION_JSON_UTF8))
    	
    	.andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("admin"));
    }
    
    //@Test
    public void saveU() throws Exception {
    	String password = "password";
		String userName = "userName";
		ResultActions resultAction = this.mockMvc.perform(MockMvcRequestBuilders
    			.get("/preSave"))
    	
		.andDo(MockMvcResultHandlers.print())
		/**
		 * [Fatal Error] :1:1: 文件提前结束。
		 * @throws Exception
		 */
    	/*.andExpect(MockMvcResultMatchers.xpath("//input[@name='" + userName + "']").exists())
		.andExpect(MockMvcResultMatchers.xpath("//input[@name='" + password + "']").exists())*/;
		MvcResult result = resultAction.andReturn();
		MockHttpServletResponse resp = result.getResponse();
		
		
		MockHttpServletRequestBuilder createMessage = MockMvcRequestBuilders.post("/saveu")
				.param(userName, "Spring Rocks")
				.param(password, "In case you didn't know, Spring Rocks!");
		
		mockMvc.perform(createMessage)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("Spring Rocks"));
    	/*
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk())
    	.andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("admin"));*/
    }
    
    @Test
    public void testHtmlUnit(){
    	try {
			HtmlPage createMsgFormPage = webClient.getPage("https://www.baidu.com");
			System.out.println(createMsgFormPage.asText());
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
    }
}
