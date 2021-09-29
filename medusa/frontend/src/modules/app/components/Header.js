import React from 'react';
import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import {FindEnterprises} from '../../search';
import {CreateEnterpriseAccessButton} from '../../stockmarket';

import users from '../../users';

const Header = () => {

    const userName = useSelector(users.selectors.getUserName);
	const loggedIn = useSelector(users.selectors.isLoggedIn);
	const role = useSelector(users.selectors.isAdmin);
    
    return (

        <nav className="navbar navbar-expand-lg navbar-light bg-light border">
            <Link className="navbar-brand" to="/">Medusa</Link>
            <button className="navbar-toggler" type="button" 
                data-toggle="collapse" data-target="#navbarSupportedContent" 
                aria-controls="navbarSupportedContent" aria-expanded="false" 
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
				
                <ul className="navbar-nav mr-auto">
				
                  {loggedIn &&  <Link className="navbar-brand" to="/search/find-enterprises">Lista de Empresas</Link>}
			            <button className="navbar-toggler" type="button" 
			                data-toggle="collapse" data-target="#navbarSupportedContent" 
			                aria-controls="navbarSupportedContent" aria-expanded="false" 
			                aria-label="Toggle navigation">
			                <span className="navbar-toggler-icon"></span>
			            </button>
                </ul>
				
				
                {userName ? 

                <ul className="navbar-nav">
                    
                    <li className="nav-item dropdown">

                        <a className="dropdown-toggle nav-link" href="/"
                            data-toggle="dropdown">
                            <span className="fas fa-user"></span>&nbsp;
                            {userName}
                        </a>
						
                        <div className="dropdown-menu dropdown-menu-right">
                            <Link className="dropdown-item" to="/users/update-profile">
                                <FormattedMessage id="project.users.UpdateProfile.title"/>
                            </Link>
                            <Link className="dropdown-item" to="/users/change-password">
                                <FormattedMessage id="project.users.ChangePassword.title"/>
                            </Link>
                            <div className="dropdown-divider"></div>
                            <Link className="dropdown-item" to="/users/logout">
                                <FormattedMessage id="project.app.Header.logout"/>
                            </Link>
							{role && <div className="dropdown-divider"></div>}
                           	{role && <Link className="dropdown-item" to="/market/create_enterprise">
                                <FormattedMessage id="project.app.Header.createEnterprise"/>
                            </Link>
							}
							
                        </div>

                    </li>

                </ul>
                
                :

                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" to="/users/login">
                            <FormattedMessage id="project.users.Login.title"/>
                        </Link>
                    </li>
                </ul>
                
                }

            </div>
        </nav>

    );

};

export default Header;