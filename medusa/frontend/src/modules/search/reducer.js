import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
	enterprises: null,
	enterprise: null
}


const enterprises = (state = initialState.enterprises, action) => {
	
	switch (action.type){
		
		case actionTypes.CLEAR_ENTERPRISES:
			return initialState.enterprises;
			
		case actionTypes.SEARCH_ENTERPRISES_COMPLETED:
			return action.enterprises;
			
		default:
			return state;
	}
}

const enterprise = (state = initialState.enterprise, action) => {
	
	switch(action.type){
		
		case actionTypes.SEARCH_ENTERPRISE_BY_ID_COMPLETED:
			return action.enterprise;
			
		case actionTypes.CLEAR_ENTERPRISE:
			return initialState.enterprise;
			
		default:
			return state; 
	}
}


const reducer = combineReducers({
	enterprises,
	enterprise
});

export default reducer;