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
							
							
							