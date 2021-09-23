package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.Enterprise;
import es.udc.fi.dc.fd.model.entities.User;

public class EnterpriseConversor {
	
	private EnterpriseConversor() {}

    public final static EnterpriseDto toEnterpriseDto(EnterpriseDto t) {
        return new EnterpriseDto(t.getId(), t.getEnterpriseName(), t.getAcronim(), 
        		t.getFundation(), t.getIncomes(), t.getAnnualBenefits());
    }

    public final static List<EnterpriseDto> toEnterprisesDtos(List <EnterpriseDto> list) {
        return list.stream().map(t -> toEnterpriseDto(t)).collect(Collectors.toList());
    }
    
    /**
	 * To user.
	 *
	 * @param userDto the user dto
	 * @return the user
	 */
	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(), enterpriseDto.getFundation(), enterpriseDto.getIncomes(), enterpriseDto.getAnnualBenefits());
	}

}
