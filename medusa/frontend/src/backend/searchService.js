import {fetchConfig, appFetch} from './appFetch';



export const findAllEnterprises = (onSuccess) => 
    appFetch('/search/enterprises', fetchConfig('GET'), onSuccess);