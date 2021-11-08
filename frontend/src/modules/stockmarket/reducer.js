import { combineReducers } from 'redux';
import * as actionTypes from './actionTypes';
 
const initialState = {
	enterprises: null
}

const addEnterprise = (state = initialState.enterprises, action) => {
  switch (action.type) {
    case actionTypes.CREATE_ENTERPRISE_COMPLETED: {
		
      	if (!state) return action.enterprise;
      
		return { ...state, enterprise: action.enterprise }
	}

    default:
      return state
  }
}

const reducer = combineReducers({
	addEnterprise
});

export default reducer;