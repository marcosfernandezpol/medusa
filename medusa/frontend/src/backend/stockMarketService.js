import {
  fetchConfig,
  appFetch,
} from "./appFetch";


export const transfer = (money, operation, onSuccess, 
							onErrors) =>
	appFetch('/market/transfer',fetchConfig('POST', {money, operation}), onSuccess, onErrors);
	
export const createEnterprise = (enterprise, onSuccess, onErrors) => {
	appFetch('/market/create_enterprise', fetchConfig('POST', enterprise), onSuccess, onErrors);
	
	
}