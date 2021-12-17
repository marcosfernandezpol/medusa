const getModuleState = state => state.users;

export const getUser = state => 
    getModuleState(state).user;

export const isLoggedIn = state =>
    getUser(state) !== null

export const isAdmin = state =>
    isLoggedIn(state) ? getUser(state).role == "ADMIN" : false;

export const getUserName = state => 
    isLoggedIn(state) ? getUser(state).login : null;

export const getUserBalance = state => 
    isLoggedIn(state) ?  getUser(state).balance : null;

export const getUserType = state =>
	isLoggedIn(state) ? getUser(state).type : null;