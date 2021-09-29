import * as actionTypes from './actionTypes';
import backend from '../../backend';



export const transferEventCompleted = () => ({
    type: actionTypes.TRANSFER_COMPLETED,
})

export const cleartransferEvent = () => ({
    type: actionTypes.CLEAR_TRANSFER_EVENT
})


export const transfer = (userId, money, operation, onSuccess, 
							onErrors) => dispatch => {
	dispatch(cleartransferEvent())
	backend.stockMarketService.transfer(userId, money, operation, () => {
		dispatch(transferEventCompleted());
		onSuccess();
		},
		onErrors)
};

export const createEnterpriseCompleted = result => ({
  type: actionTypes.CREATE_ENTERPRISE_COMPLETED,
  result
})

export const createEnterprise = (enterprise, onSuccess, onErrors) => dispatch => {	
	backend.stockMarketService.createEnterprise(
		enterprise,
		result => {
			dispatch(createEnterpriseCompleted(result))
			onSuccess()
		},
		onErrors
	)
}
							
							
							