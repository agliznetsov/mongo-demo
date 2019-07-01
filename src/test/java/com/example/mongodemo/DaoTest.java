package com.example.mongodemo;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.bson.Document;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;

@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
@Import(MongoConfiguration.class)
@ContextConfiguration(initializers = {DaoTest.Initializer.class})
public class DaoTest {

	private static final int MONGO_PORT = 27017;

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of("spring.data.mongodb.port=" + mongo.getMappedPort(MONGO_PORT))
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	@ClassRule
	public static GenericContainer mongo = new GenericContainer("mongo:4.0.10").withExposedPorts(MONGO_PORT);

	@Autowired
	UserRepository userRepository;

	@Test
	public void saveUser() {
		User user = User.builder().name(randomString()).build();
		user = userRepository.save(user);
		assertNotNull(user.getId());
	}

	@Test(expected = DuplicateKeyException.class)
	public void saveUserWithDuplicateName() {
		String name = randomString();
		userRepository.save(User.builder().name(name).build());
		userRepository.save(User.builder().name(name).build());
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveUserWithEmptyName() {
		userRepository.save(User.builder().name("").build());
	}



	private String randomString() {
		return UUID.randomUUID().toString();
	}


}
