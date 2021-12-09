package es.udc.fi.dc.fd.rest.dtos;

import java.sql.Date;
import java.util.*;


public class EnterpriseDto {

	/**
	 * The Interface AllValidations.
	 */
	public interface AllValidations {
	}

	/**
	 * The Interface UpdateValidations.
	 */
	public interface UpdateValidations {
	}

	private Long id;

	private String enterpriseName;

	private String acronim;

	private Date fundation;

	private Float incomes;

	private int actions;

	private Float actionsPrice;

	private List<AnnualBenefitsDto> anualBenefitsDto;
	
	private boolean avaliable;

	public EnterpriseDto() {
	}


	public EnterpriseDto(Long id, String enterpriseName, String acronim, Date fundation, Float incomes, int actions,
			Float actionsPrice, List<AnnualBenefitsDto> anualBenefitsDto, boolean avaliable) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.actions = actions;
		this.actionsPrice = actionsPrice;
		this.anualBenefitsDto = anualBenefitsDto;
		this.avaliable = avaliable;
	}

	public EnterpriseDto(Long id, String enterpriseName, String acronim, Date fundation, Float incomes, int actions,
			Float actionsPrice, List<AnnualBenefitsDto> anualBenefitsDto) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.acronim = acronim;
		this.fundation = fundation;
		this.incomes = incomes;
		this.actions = actions;
		this.actionsPrice = actionsPrice;
		this.anualBenefitsDto = anualBenefitsDto;
		this.avaliable = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAcronim() {
		return acronim;
	}

	public void setAcronim(String acronim) {
		this.acronim = acronim;
	}

	public Date getFundation() {
		return fundation;
	}

	public void setFundation(Date fundation) {
		this.fundation = fundation;
	}

	public Float getIncomes() {
		return incomes;
	}

	public void setIncomes(Float incomes) {
		this.incomes = incomes;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	public Float getActionsPrice() {
		return actionsPrice;
	}

	public void setActionsPrice(Float actionsPrice) {
		this.actionsPrice = actionsPrice;
	}

	public List<AnnualBenefitsDto> getanualBenefitsDto() {
		return anualBenefitsDto;
	}

	public void setanualBenefitsDto(List<AnnualBenefitsDto> anualBenefitsDto) {
		this.anualBenefitsDto = anualBenefitsDto;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean availiable) {
		this.avaliable = availiable;
	}


	@Override
	public int hashCode() {
		return Objects.hash(acronim, actions, actionsPrice, anualBenefitsDto, avaliable, enterpriseName, fundation, id,
				incomes);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnterpriseDto other = (EnterpriseDto) obj;
		return Objects.equals(acronim, other.acronim) && actions == other.actions
				&& Objects.equals(actionsPrice, other.actionsPrice)
				&& Objects.equals(anualBenefitsDto, other.anualBenefitsDto) && avaliable == other.avaliable
				&& Objects.equals(enterpriseName, other.enterpriseName) && Objects.equals(fundation, other.fundation)
				&& Objects.equals(id, other.id) && Objects.equals(incomes, other.incomes);
	}
	
	
	
	

}