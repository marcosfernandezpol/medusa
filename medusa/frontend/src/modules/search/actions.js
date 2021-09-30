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


export const searchAllEnterprises = () => (dispatch, getState) => {

 		dispatch(clearSearchEnterprises());
	
        backend.searchService.findAllEnterprises(
            enterprises => dispatch(searchAllEnterprisesCompleted(enterprises))
        );
        
}
							
							
							