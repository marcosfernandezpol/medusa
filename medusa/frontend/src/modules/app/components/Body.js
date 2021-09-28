 import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import {FindEnterprisesResult,FindEnterprises} from '../../search';

import users from '../../users';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
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
				{loggedIn && <Route exact path="/search/find-enterprises"><FindEnterprises/></Route>}
				{loggedIn && <Route exact path="/search/enterprises"><FindEnterprisesResult/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;