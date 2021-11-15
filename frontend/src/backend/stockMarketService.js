import {
	fetchConfig,
	appFetch,
} from "./appFetch";


export const transfer = (money, operation, onSuccess,onErrors) =>{
	appFetch('/market/transfer', fetchConfig('POST', { money, operation }), onSuccess, onErrors);
	}

export const createEnterprise = (enterprise, onSuccess, onErrors) =>
	appFetch('/market/create_enterprise', fetchConfig('POST', enterprise), onSuccess, onErrors);

export const updateEnterprise = (id,annualBenefits,onSuccess, onErrors) =>
	appFetch(`/market/update_enterprise/${id}`, fetchConfig('PUT', annualBenefits), onSuccess, onErrors);
	
export const createOrder = (order, onSuccess, onErrors) =>
	appFetch(`/market/order`, fetchConfig('POST', order), onSuccess, onErrors);

