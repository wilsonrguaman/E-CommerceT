package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser1() {
		
	}
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userwilson = new User("name@code", "3419", "wilson", "guaman");
		userwilson.addRole(roleAdmin);
		
		User savedUser = repo.save(userwilson);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateTwoRoles() {
		
		User wg = new User("name@coder", "3419y", "Manny", "Gonzales");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		wg.addRole(roleEditor);
		wg.addRole(roleAssistant);
		
		User savedUser = repo.save(wg);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
		public void testListAllUsers() {
			Iterable<User> listUsers = (List<User>) repo.findAll();
			listUsers.forEach(user -> System.out.println(user));
			
	}
	@Test
	public void testGetUserById() {
		User userwilson = repo.findById(1).get();
		System.out.println(userwilson);
		assertThat(userwilson).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userwilson = repo.findById(1).get();
		userwilson.setEnabled(true);
		userwilson.setEmail("wilsonguaman@gmail.com");
		repo.save(userwilson);
	}
	@Test
	public void testUpdateUserRoles() {
		User userManny = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleShipper = new Role(4);
		userManny.getRoles().remove(roleEditor);
		userManny.addRole(roleShipper);
		
		repo.save(userManny);
		
	}
	@Test
	public void testDeleteUser() {
		Integer userId =9;
		repo.deleteById(userId);
		
	}
	@Test
	public void testGetUserByEmail() {
		String email = "wilsonguaman30@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	// 35 section- updating to the circle one
	@Test
	public void testDisableUser() {
		Integer id = 1;
		repo.updateEnabledStatus(id, false);
	}
	@Test
	public void testEnableUser() {
		Integer id = 19;
		repo.updateEnabledStatus(id, true);
	}
	//7:00min the last test the above 
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		assertThat(listUsers.size()).isEqualTo(pageSize);
		
	}
	@Test
	public void testSearchUsers() {
		String keyword = "wilson";
		
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		//assertThat(listUsers.size()).isEqualTo(pageSize);
		
		assertThat(listUsers.size()).isGreaterThan(0);
		
		
	}
}
