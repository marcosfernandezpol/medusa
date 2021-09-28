import React from 'react';
import {useSelector} from 'react-redux';

import users from '../../users';
import {CreateEnterpriseAccessButton} from '../../stockmarket';

const Home = () => {
	
	const loggedIn = useSelector(users.selectors.isLoggedIn);
    const user = useSelector(users.selectors.getUser);

	return (
		
	    <div className="text-center d-md-flex justify-content-between">
			<div>{loggedIn && /*user.role == 'ADMIN'*/  <CreateEnterpriseAccessButton/>}</div>
			<div><h1>Pagina principal</h1></div>
			<div></div>
	    </div>

	)
};

export default Home;