import { fetchConfig, appFetch } from './appFetch';


export const findAllEnterprises = (onSuccess) =>
	appFetch('/search/enterprises', fetchConfig('GET'), onSuccess);

export const findEnterprise = (id, onSuccess) =>
	appFetch(`/search/enterprise/${id}`, fetchConfig('GET'), onSuccess);
	
export const findEnterpriseHistoric = (id, onSuccess) =>
	appFetch(`/search/enterprise/${id}/historic`, fetchConfig('GET'), onSuccess);

export const findOrders = (option, avaliable, onSuccess) =>{
	
	let path=`/search/orders?option=${option}&avaliable=${avaliable}`;
	
	console.log(path);
	
	appFetch(path, fetchConfig('GET'), onSuccess);
	}

