import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';
 

const initialState = {
	market: null,
	balance : null,
}

const addEnterprise = (state = initialState.market, action) => {
  switch (action.type) {
    case actionTypes.CREATE_ENTERPRISE_COMPLETED: {
		
      	if (!state) return action.enterprise;
      
		return { ...state, enterprise: action.enterprise }
	}

    default:
      return state
  }
}

const balance = (state = initialState.balance, action) => {
	
	switch (action.type){
			
		case actionTypes.TRANSFER_COMPLETED:
			return action.balance;
			
		default:
			return state;
	}
}

const reducer = combineReducers({
	addEnterprise,
	balance
});

export default reducer;