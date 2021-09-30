import {
  fetchConfig,
  appFetch,
  setServiceToken,
  getServiceToken,
  removeServiceToken,
  setReauthenticationCallback,
} from "./appFetch";

export const login = (userName, password, onSuccess, onErrors, reauthenticationCallback) =>
    appFetch('/users/login', fetchConfig('POST', {userName, password}),
        authenticatedUser => {
            setServiceToken(authenticatedUser.serviceToken);
            setReauthenticationCallback(reauthenticationCallback);
            onSuccess(authenticatedUser);
        }, 
        onErrors);

export const transfer = (money, operation, onSuccess, 
							onErrors) =>
	appFetch('/market/transfer',fetchConfig('POST', {money, operation}), onSuccess, onErrors);
	
export const createEnterprise = (enterprise, onSuccess, onErrors) => {
	appFetch('/market/create_enterprise', fetchConfig('POST', enterprise), onSuccess, onErrors);
	
	
}