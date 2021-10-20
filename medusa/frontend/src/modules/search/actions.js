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

export const searchEnterpriseById = id => (dispatch) => {
	backend.searchService.findEnterprise(id,
		enterprise => dispatch(searchEnterpriseByIdCompleted(enterprise)))
}


