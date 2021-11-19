package es.udc.fi.dc.fd.model.entities;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserTest {
	
	@Test
	public void userTest() {
		
		String login = "login";
		String firstname = "firstName";
		String lastname = "lastName";
		String password = "password";
		String city = "city";
		String country = "country";
		float balance = 1500F;
		
		User user = new User(login,firstname,lastname,password,city,country,balance);
		User user2 = new User();
		user2.setLogin(login);
		user2.setFirstName(firstname);
		user2.setLastName(lastname);
		user2.setPassword(password);
		user2.setCity(city);
		user2.setCountry(country);
		user2.setBalance(balance);
		
		
		
		Assert.assertTrue(user.equals(user2));
		Assert.assertTrue(user.hashCode()==(user2.hashCode()));
		user2.setEmail(country);
		user2.setRole(User.RoleType.CLIENT);
		user2.setId(1L);
		String str = "User [id=" + 1 + ", login=" + login + ", firstName=" + firstname + ", lastName=" + lastname
				+ ", password=" + password + ", city=" + city + ", country=" + country + ", role=" + User.RoleType.CLIENT + ", email="
				+ country + ", balance=" + balance + "]";
		Assert.assertTrue(user2.toString().equals(str));
		
	}

}