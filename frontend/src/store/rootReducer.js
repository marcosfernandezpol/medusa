import {combineReducers} from 'redux';
import { reducer as formReducer } from 'redux-form'


import app from '../modules/app';
import users from '../modules/users';
import enterprises from '../modules/search';
import market from '../modules/stockmarket';

const rootReducer = combineReducers({
	app: app.reducer,
    users: users.reducer,
	enterprises: enterprises.reducer,
	market: market.reducer,
	form:formReducer
});

export default rootReducer;
