package es.udc.fi.dc.fd.rest.dtos;

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
public class ChangePasswordTest {
	
	@Test
	public void changePasswordTest() {
		ChangePasswordParamsDto passParams = new ChangePasswordParamsDto();
		String oldPassword = "old";
		String newPassword = "new";
		passParams.setNewPassword(newPassword);
		passParams.setOldPassword(oldPassword);
		
		Assert.assertTrue(passParams.getNewPassword().equals(newPassword));
		Assert.assertTrue(passParams.getOldPassword().equals(oldPassword));
			
	}

}