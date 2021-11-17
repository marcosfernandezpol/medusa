import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Switch } from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import { Login, SignUp, UpdateProfile, ChangePassword, Logout } from '../../users';
import { CreateEnterprise, UpdateEnterprise, TransferPage, CreateOrders } from '../../stockmarket';
import { FindEnterprisesResult,FindEnterpriseResult, FindOrders , FindActionsResult} from './../../search';
import users from '../../users';

const Body = () => {

	const loggedIn = useSelector(users.selectors.isLoggedIn);
	const isAdmin = useSelector(users.selectors.isAdmin);

	return (

		<div className="container">
			<br />
			<AppGlobalComponents />
			<Switch>
				<Route exact path="/"><Home /></Route>
				{loggedIn && <Route exact path="/market/create_enterprise"><CreateEnterprise /></Route>}
				{loggedIn && <Route exact path="/search/orders"><FindOrders/></Route>}
				{loggedIn && <Route exact path="/users/update-profile"><UpdateProfile /></Route>}
				{loggedIn && <Route exact path="/users/change-password"><ChangePassword /></Route>}
				{loggedIn && <Route exact path="/users/logout"><Logout /></Route>}
				{!loggedIn && <Route exact path="/users/login"><Login /></Route>}
				{!loggedIn && <Route exact path="/users/signup"><SignUp /></Route>}
				{loggedIn && <Route exact path="/search/enterprises"><FindEnterprisesResult /></Route>}
				{loggedIn && <Route exact path="/search/users/actions"><FindActionsResult /></Route>}
				{loggedIn && <Route exact path="/search/enterprise/:id"><FindEnterpriseResult /></Route>}
				{(loggedIn && !isAdmin) && <Route exact path="/market/transfer"><TransferPage /></Route>}
				{(loggedIn && isAdmin) && <Route exact path="/market/update_enterprise/:id"><UpdateEnterprise /></Route>}
				{(loggedIn && !isAdmin) && <Route exact path="/market/transfer"><TransferPage /></Route>}
				{(loggedIn && !isAdmin) && <Route exact path="/market/create_order/:id/:entepriseName/:type"><CreateOrders /></Route>}
				<Route><Home /></Route>
			</Switch>
		</div>

	);

};

export default Body;