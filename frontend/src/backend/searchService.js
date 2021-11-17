import { fetchConfig, appFetch } from './appFetch';


export const findAllEnterprises = (onSuccess) =>
	appFetch('/search/enterprises', fetchConfig('GET'), onSuccess);

export const findEnterprise = (id, onSuccess) =>
	appFetch(`/search/enterprise/${id}`, fetchConfig('GET'), onSuccess);
	
export const findEnterpriseHistoric = (id,numberOfDays, onSuccess) =>{
	appFetch(`/search/enterprise/${id}/historic?numberOfDays=${numberOfDays}`, fetchConfig('GET'), onSuccess);
}
export const findOrders = (option, avaliable, onSuccess) =>{
	
	let path=`/search/orders?option=${option}&avaliable=${avaliable}`;
	
	appFetch(path, fetchConfig('GET'), onSuccess);
	}
	
export const setUnavaliable = (enterprise, enterpriseId, onSuccess, onErrors) =>{
	
	let path=`/market/avaliable/${enterpriseId}`;
	
	
	appFetch(path, fetchConfig('PUT',enterprise), onSuccess);
	}

export const findActions = (onSuccess) => {
	appFetch(`/search/user/actions`, fetchConfig('GET'), onSuccess)
}
