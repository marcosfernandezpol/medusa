const getModuleState = state => state.enterprises;


export const getEnterprises = state => getModuleState(state).enterprises;


export const getEnterpriseName = (enterprises, id) => {
	
	if (!enterprises){
		return '';
	}
	
	const enterprise = enterprises.find(enteprise => enteprise.id === id);
	
	if (!enterprise){
		return '';
	}
	
	return enterprise.enterpriseName;	
	
}

export const getEnterprise = state =>
	getModuleState(state).enterprise;
	
export const getEnterpriseHistoric = state =>
	getModuleState(state).enterpriseHistoric;

export const getEnterpriseSearch = state =>
    getModuleState(state).enterprisesSearch;


export const getBought = state => getModuleState(state).bought;
export const getNotBought = state => getModuleState(state).notBought;
export const getSold = state => getModuleState(state).sold;
export const getNotSold = state => getModuleState(state).notSold;

export const getActions = state => getModuleState(state).actions;