package com.ganit.chat;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ganit.chat.model.Message;
import com.ganit.chat.model.User;
import com.ganit.chat.repository.UserRepository;
import com.ganit.chat.service.MessageService;

@ActiveProfiles("test")
@SpringBootTest(classes = ChatAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ChatAppApplicationTests  {
	
	 public static final String IMAGE_VERSION = "postgres:13.1-alpine";
	 public static final String DATABASE_NAME = "postgres";
	 public static final String USER_NAME = "postgres";
	 public static final String PASSWORD = "postgres";


	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserRepository userRepo;
	
	
	@Container
    public static PostgreSQLContainer<?> postgreSQLContainer =  new PostgreSQLContainer<>(IMAGE_VERSION).withDatabaseName(DATABASE_NAME).withUsername(USER_NAME).withPassword(PASSWORD);
	
	
	@DynamicPropertySource
	public static  void overrideResource(DynamicPropertyRegistry reg) {
		reg.add("spring.datasource.url" , postgreSQLContainer::getJdbcUrl);
		reg.add("spring.datasource.password" ,postgreSQLContainer::getPassword);
		reg.add("spring.datasource.username" , postgreSQLContainer::getUsername);
		reg.add("spring.jpa.generate-ddl" , ()->true);
		reg.add("spring.jpa.hibernate.ddl-auto" , ()->"create-drop");
	
	}
	

	
	@Test
    public void createUser1() {
    	User ganesh =new User("Ganesh", "ramg");
    	HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(ganesh, headers);
    	ResponseEntity<String> response=restTemplate.postForEntity("/user", request, String.class);
    	assertEquals(200, response.getStatusCodeValue());
    	
    }
    
  
	@Test
    public void createUserWithNickNameTest(){
		HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(headers);
		ResponseEntity<String>  response=restTemplate.postForEntity("/user/gopr",request, String.class);
		assertEquals(200, response.getStatusCodeValue());
		
    }
    
	@Test
    public void sendMessageTest() {
		String messageTxt="Hi How are you!";
         Message msg= new Message();
         msg.setFrom(2L);
         msg.setTo(1L);
         msg.setMessage(messageTxt);
         messageService.updateMessage(msg);
         List<Message> recieved=messageService.findAllRecieved(1L);
         assertEquals(recieved.get(0).getMessage(),messageTxt);
    }
	
	
    
}