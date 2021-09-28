import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
	enterprises: null
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

const reducer = combineReducers({
	enterprises
});

export default reducer;