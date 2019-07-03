package com.example.mongodemo;

import static com.example.mongodemo.model.ApplicationRole.ADMIN;
import static com.example.mongodemo.model.ApplicationRole.USER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import com.example.mongodemo.dao.ProductionRepository;
import com.example.mongodemo.dao.UserProductionRepository;
import com.example.mongodemo.dao.UserRepository;
import com.example.mongodemo.model.ApplicationRole;
import com.example.mongodemo.model.Production;
import com.example.mongodemo.model.User;
import com.example.mongodemo.model.UserProduction;

@RunWith(SpringRunner.class)
@DataMongoTest
@Import(MongoConfiguration.class)
public class DaoTest {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductionRepository productionRepository;
	@Autowired
	UserProductionRepository userProductionRepository;

	@Test
	public void testSaveUser() {
		User user = saveUser(randomString());
		assertNotNull(user.getId());
	}

	@Test(expected = DuplicateKeyException.class)
	public void saveUserWithDuplicateName() {
		String name = randomString();
		saveUser(name);
		saveUser(name);
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveUserWithEmptyName() {
		saveUser("");
	}

	@Test
	public void findByName() {
		User user = saveUser(randomString());

		User user2 = userRepository.findByName(user.getName());
		assertEquals(user.getId(), user2.getId());
	}

	@Test
	public void customFind() {
		saveUser(randomString());
		List<User> users = userRepository.customFind();
		assertTrue(users.size() > 0);
	}

	@Test
	public void userProduction() {
		userProductionRepository.deleteAll();

		User user = saveUser(randomString());
		Production production = saveProduction(randomString());
		saveUserProduction(user, production);

		{
			UserProduction up = userProductionRepository.findAll().iterator().next();
			assertNotNull(up.getUser());
			assertNotNull(up.getProduction());
		}

		{
			assertEquals(1, userProductionRepository.findByUser(user).size());
			assertEquals(1, userProductionRepository.findByProduction(production).size());
		}

		{
			userRepository.delete(user);
			UserProduction up = userProductionRepository.findByProduction(production).get(0);
			assertNull(up.getUser());
		}
	}

	@Test
	public void testProductionOptimisticLocking() {
		Production p1 = saveProduction(randomString());
		Production p2 = productionRepository.findById(p1.getId()).get();

		p1.setName("newname");
		productionRepository.save(p1);

		p2.setName("othername");
		try {
			productionRepository.save(p2);
		} catch (OptimisticLockingFailureException e) {
			//should fail
		}

		assertEquals("newname", productionRepository.findById(p1.getId()).get().getName());
	}

	private void saveUserProduction(User user, Production production) {
		UserProduction up = new UserProduction();
		up.setUser(user);
		up.setProduction(production);
		userProductionRepository.save(up);
	}

	private User saveUser(String name) {
		User user = new User();
		user.setName(name);
		user.getRoles().add(ADMIN);
		user.getRoles().add(USER);
		return userRepository.save(user);
	}

	private Production saveProduction(String name) {
		Production production = new Production();
		production.setName(name);
		return productionRepository.save(production);
	}

	private String randomString() {
		return UUID.randomUUID().toString();
	}


}
