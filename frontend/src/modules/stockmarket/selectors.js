

const getModuleState = state => state.market;
const getModuleStateUser = state => state.users;

export const getBalanceAux = state => 
    getModuleState(state).balance;



export const getBalance = state => {

	if(	getBalanceAux(state)==null){
		const balance = getModuleStateUser(state).user.balance;
		return balance;
	}
	else{
		const balance2 = getBalanceAux(state).money;
		return balance2;
	}
}

export const getForms = state => state.form;
