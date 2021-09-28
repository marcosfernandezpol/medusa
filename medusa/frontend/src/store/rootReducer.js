import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import enterprises from '../modules/search';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
	enterprises: enterprises.reducer
});

export default rootReducer;
