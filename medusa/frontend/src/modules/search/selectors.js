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
	
	return enterprise.name;	
	
}

export const getEnterprise = state =>
	getModuleState(state).enterprise;
