import {combineReducers} from 'redux';
import { reducer as formReducer } from 'redux-form'


import app from '../modules/app';
import users from '../modules/users';
import enterprises from '../modules/search';

const rootReducer = combineReducers({
	app: app.reducer,
    users: users.reducer,
	enterprises: enterprises.reducer,
	form:formReducer
});

export default rootReducer;
