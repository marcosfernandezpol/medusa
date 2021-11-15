import * as actionTypes from './actionTypes';
import backend from '../../backend';



export const transferEventCompleted = (balance) => ({
    type: actionTypes.TRANSFER_COMPLETED,
	balance
})

export const cleartransferEvent = () => ({
    type: actionTypes.CLEAR_TRANSFER_EVENT
})


export const transfer = (money, operation, onSuccess, onErrors) => dispatch => {
	dispatch(cleartransferEvent())
	backend.stockMarketService.transfer( 
		money, 
		operation, 
		balance => {
			dispatch(transferEventCompleted(balance));
			onSuccess()
		},
		onErrors)
};

export const createEnterpriseCompleted = enterprise => ({
  type: actionTypes.CREATE_ENTERPRISE_COMPLETED,
  enterprise
})


export const createEnterprise = (enterprise, onSuccess, onErrors) => dispatch => {	
	
	backend.stockMarketService.createEnterprise(
		enterprise,
		enterpriseReturned => {
			dispatch(createEnterpriseCompleted(enterpriseReturned))
			onSuccess()
		},
		onErrors
	)
}

export const updateEnterpriseCompleted = enterprise => ({
  type: actionTypes.UPDATE_ENTERPRISE_COMPLETED,
  enterprise
})

export const updateEnterprise = (id,annualBenefits, onSuccess, onErrors) => dispatch => {	
	
	backend.stockMarketService.updateEnterprise(
		id,
		annualBenefits,
		enterpriseReturned => {
			dispatch(updateEnterpriseCompleted(enterpriseReturned))
			onSuccess()
		},
		onErrors
	)
}

export const createOrder = (order, onSuccess, onErrors) => dispatch => {
	backend.stockMarketService.createOrder(
		order,
		onSuccess, onErrors)
}
							
							