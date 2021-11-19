package es.udc.fi.dc.fd.rest.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.fi.dc.fd.model.entities.ActionPriceHistoric;
import es.udc.fi.dc.fd.model.entities.Enterprise;





@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ActionPriceHistoricTest {
	
	@Test
	public void actionPriceHistoricTest() {
		ActionPriceHistoricDto historic = new ActionPriceHistoricDto();
		long id = 1;
		String x = "x";
		float y = 10F;
		historic.setId(id);
		historic.setX(x);
		historic.setY(y);
		
		ActionPriceHistoricDto historic2 = new ActionPriceHistoricDto(x,y);
		historic2.setId(id);
		Assert.assertTrue(historic.equals(historic2));
		Assert.assertTrue(historic.getId()==id);
		Assert.assertTrue(historic.getX()==x);
		Assert.assertTrue(historic.getY()==y);
		Assert.assertTrue(historic.hashCode()==historic2.hashCode());
			
	}
	
	@Test
	public void actionPriceHistoricConversorTest() {
		ActionPriceHistoricDto historic = new ActionPriceHistoricDto();
		long id = 1;
		String x = "2020-12-12 00:00:00";
		float y = 10F;
		historic.setId(id);
		historic.setX(x);
		historic.setY(y);
		
		ActionPriceHistoric actionPriceHistoric = new ActionPriceHistoric();

		Enterprise enterprise = new Enterprise();
		LocalDateTime date = LocalDateTime.now();
		actionPriceHistoric.setId(1L);
		actionPriceHistoric.setEnterprise(enterprise);
		actionPriceHistoric.setDate(date);
		actionPriceHistoric.setPrice(1.5F);
		
		ActionPriceHistoricDto historicAux = new ActionPriceHistoricDto();
		historicAux = ActionPriceHistoricConversor.toActionPriceHistoricDto(actionPriceHistoric);
		
		ActionPriceHistoric historicAux2 = new ActionPriceHistoric();
		historicAux2 = ActionPriceHistoricConversor.toActionPriceHistoric(historic);
		
		ActionPriceHistoricDto historic2 = new ActionPriceHistoricDto(x,y);
		historic2.setId(id);
		Assert.assertTrue(historicAux.getClass()==historic.getClass());
		Assert.assertTrue(historicAux2.getClass()==actionPriceHistoric.getClass());
		Assert.assertTrue(historic.equals(historic2));
		Assert.assertTrue(historic.getId()==id);
		Assert.assertTrue(historic.getX()==x);
		Assert.assertTrue(historic.getY()==y);
		Assert.assertTrue(historic.hashCode()==historic2.hashCode());
		
		List<ActionPriceHistoricDto> dtoList = new ArrayList<>();
		List<ActionPriceHistoricDto> dtoListAux = new ArrayList<>();
		List<ActionPriceHistoric> notDtoList = new ArrayList<>();
		notDtoList.add(actionPriceHistoric);
		dtoList = ActionPriceHistoricConversor.toActionPriceHistoricsDto(notDtoList);
		Assert.assertTrue(dtoList.getClass()==dtoListAux.getClass());
		
		
		EnterpriseDto enterpriseAux = new EnterpriseDto();
		Enterprise aux = new Enterprise();
		aux = ActionPriceHistoricConversor.toEnterprise(enterpriseAux);
		Assert.assertTrue(aux.getClass()==enterprise.getClass());
		
		
		
	}

}