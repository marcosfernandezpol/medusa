import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
	enterprises: null,
	enterprise:null,
	bought: null,
	notBought: null,
	sold: null,
	notSold: null
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

const bought = (state = initialState.bought, action) => {
	
	switch (action.type){
		
		case actionTypes.CLEAR_BOUGHT:
			return initialState.bought;
			
		case actionTypes.SEARCH_BOUGHT_COMPLETED:
			return action.bought;
			
		default:
			return state;
	}
}

const notBought = (state = initialState.notBought, action) => {
	
	switch (action.type){
		
		case actionTypes.CLEAR_NOT_BOUGHT:
			return initialState.notBought;
			
		case actionTypes.SEARCH_NOT_BOUGHT_COMPLETED:
			return action.notBought;
			
		default:
			return state;
	}
}


const sold = (state = initialState.sold, action) => {
	
	switch (action.type){
		
		case actionTypes.CLEAR_SOLD:
			return initialState.sold;
			
		case actionTypes.SEARCH_SOLD_COMPLETED:
			return action.sold;
			
		default:
			return state;
	}
}


const notSold = (state = initialState.notSold, action) => {
	
	switch (action.type){
		
		case actionTypes.CLEAR_NOT_SOLD:
			return initialState.notSold;
			
		case actionTypes.SEARCH_NOT_SOLD_COMPLETED:
			return action.notSold;
			
		default:
			return state;
	}
}


const reducer = combineReducers({
	enterprises,
	enterprise,
	bought,
	notBought,
	sold,
	notSold
});

export default reducer;