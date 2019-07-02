package com.example.mongodemo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongodemo.dao.UserRepository;
import com.example.mongodemo.service.MyService;

//ignored beacause transactions are only supported in replica set configuration
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceTest {

	@Autowired
	MyService myService;

	@Autowired
	UserRepository userRepository;

	@Test
	public void testSave() {
		String name = UUID.randomUUID().toString();
		myService.trySaveUserProduction(name, false);
		assertNotNull(userRepository.findByName(name));
	}

	@Test
	public void testSaveRollback() {
		String name = UUID.randomUUID().toString();
		try {
			myService.trySaveUserProduction(name, true);
		}catch (IllegalStateException e) {
			//nop
		}
		assertNull(userRepository.findByName(name));
	}
}
