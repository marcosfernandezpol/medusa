import { fetchConfig, appFetch } from './appFetch';


export const findAllEnterprises = (onSuccess) =>
	appFetch('/search/enterprises', fetchConfig('GET'), onSuccess);

export const findEnterprise = (id, onSuccess) =>
	appFetch(`/search/enterprise/${id}`, fetchConfig('GET'), onSuccess);