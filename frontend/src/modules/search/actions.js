import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';


export const searchAllEnterprisesCompleted = (enterprises) => ({
	type: actionTypes.SEARCH_ENTERPRISES_COMPLETED,
	enterprises
});

export const clearSearchEnterprises = () => ({
	type: actionTypes.CLEAR_ENTERPRISES
})

export const clearSearchEnterprise = () => ({
	type: actionTypes.CLEAR_ENTERPRISE
})

export const clearSearchEnterpriseHistoric = () => ({
	type: actionTypes.CLEAR_ENTERPRISE_HISTORIC
})

export const searchAllEnterprises = () => (dispatch, getState) => {

	dispatch(clearSearchEnterprises());

	backend.searchService.findAllEnterprises(
		enterprises => dispatch(searchAllEnterprisesCompleted(enterprises))
	);
}

export const searchEnterpriseByIdCompleted = enterprise => ({
	type: actionTypes.SEARCH_ENTERPRISE_BY_ID_COMPLETED,
	enterprise
})

export const searchEnterpriseHistoricCompleted = enterpriseHistoric => ({
	type: actionTypes.SEARCH_ENTERPRISE_HISTORIC_COMPLETED,
	enterpriseHistoric
})

export const searchEnterpriseById = id => (dispatch) => {
	backend.searchService.findEnterprise(id,
		enterprise => dispatch(searchEnterpriseByIdCompleted(enterprise)))
}

export const searchEnterpriseHistoric = (id, numberOfDays) => (dispatch) => {
	dispatch(clearSearchEnterpriseHistoric());
	backend.searchService.findEnterpriseHistoric(id,numberOfDays,
		enterpriseHistoric => dispatch(searchEnterpriseHistoricCompleted(enterpriseHistoric)))
}



export const searchBoughtOrdersCompleted = (bought) => ({
	type: actionTypes.SEARCH_BOUGHT_COMPLETED,
	bought
});

export const clearBoughtSearchOrders = () => ({
	type: actionTypes.CLEAR_BOUGHT
})


export const searchBoughtOrders = (option, avaliable, onErrors) => (dispatch ,getState) => {

	dispatch(clearBoughtSearchOrders());

	backend.searchService.findOrders(
		option,
		avaliable,
		bought => dispatch(searchBoughtOrdersCompleted(bought))
	);
}


export const searchNotBoughtOrdersCompleted = (notBought) => ({
	type: actionTypes.SEARCH_NOT_BOUGHT_COMPLETED,
	notBought
});

export const clearNotBoughtSearchOrders = () => ({
	type: actionTypes.CLEAR_NOT_BOUGHT
})


export const searchNotBoughtOrders = (option, avaliable, onErrors) => (dispatch,getState) => {

	dispatch(clearNotBoughtSearchOrders());

	backend.searchService.findOrders(
		option,
		avaliable,
		notBought => dispatch(searchNotBoughtOrdersCompleted(notBought))
	);
}




export const searchSoldOrdersCompleted = (sold) => ({
	type: actionTypes.SEARCH_SOLD_COMPLETED,
	sold
});

export const clearSoldSearchOrders = () => ({
	type: actionTypes.CLEAR_SOLD
})


export const searchSoldOrders = (option, avaliable, onErrors) => (dispatch,getState) => {

	dispatch(clearSoldSearchOrders());

	backend.searchService.findOrders(
		option,
		avaliable,
		sold => dispatch(searchSoldOrdersCompleted(sold))
	);
}



export const searchNotSoldOrdersCompleted = (notSold) => ({
	type: actionTypes.SEARCH_NOT_SOLD_COMPLETED,
	notSold
});

export const clearNotSoldSearchOrders = () => ({
	type: actionTypes.CLEAR_NOT_SOLD
})


export const searchNotSoldOrders = (option, avaliable,onErrors) => (dispatch,getState) => {
	
	dispatch(clearNotSoldSearchOrders());
	backend.searchService.findOrders(
		option,
		avaliable,
		notSold => dispatch(searchNotSoldOrdersCompleted(notSold))
	);
}

export const clearUnavaliable = () => ({
	type: actionTypes.CLEAR_UNAVALIABLE
})

export const setUnavaliableCompleted = enterprise => ({
    type: actionTypes.SET_UNAVALIABLE_COMPLETED,
    enterprise
})

export const setUnavaliable = (enterprise,enterpriseId,onSuccess,onErrors) => (dispatch, getState) => {
	
	backend.searchService.setUnavaliable(
		enterprise,
		enterpriseId,
		enterprise => {
			dispatch(setUnavaliableCompleted(enterprise));
		},
		onErrors
	);
	
	
}

