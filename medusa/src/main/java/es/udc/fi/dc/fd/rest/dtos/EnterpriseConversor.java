package es.udc.fi.dc.fd.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.fi.dc.fd.model.entities.Enterprise;

public class EnterpriseConversor {

	private EnterpriseConversor() {}

    public final static EnterpriseDto toEnterpriseDto(Enterprise t) {
        return new EnterpriseDto(t.getId(), t.getEnterpriseName(), t.getAcronim(), 
        		t.getFundation(), t.getIncomes(), t.getAnnualBenefits(), t.getActions(), t.getActionsPrice());
    }

    public final static List<EnterpriseDto> toEnterprisesDtos(List<Enterprise> list) {
        return list.stream().map(t -> toEnterpriseDto(t)).collect(Collectors.toList());
    }
    
	 /* To user.
	 *
	 * @param enterpriseDto the Enterprise dto
	 * @return the enterprise
	 */
	public static final Enterprise toEnterprise(EnterpriseDto enterpriseDto) {

		return new Enterprise(enterpriseDto.getEnterpriseName(), enterpriseDto.getAcronim(), enterpriseDto.getFundation(), 
								enterpriseDto.getIncomes(), enterpriseDto.getAnnualBenefits(),
								enterpriseDto.getActions(), enterpriseDto.getActionsPrice());

	}

}