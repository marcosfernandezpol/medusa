 import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import {CreateEnterprise, EnterpriseCreated} from '../../stockmarket';
import {FindEnterprises, FindEnterprisesResult} from './../../search';
import users from '../../users';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
	/*const user = useSelector(users.selectors.getUser);
	const role = user.role;*/
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>				
				{loggedIn && <Route exact path="/market/create_enterprise"><CreateEnterprise/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
				{loggedIn && <Route exact path="/search/enterprises"><FindEnterprisesResult/></Route>}
				{loggedIn && /*role == 'ADMIN'*/<Route exact path="/market/createEnterpriseCompleted"><EnterpriseCreated/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;