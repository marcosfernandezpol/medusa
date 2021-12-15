import React from 'react';
import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import {FindEnterprises} from '../../search';
import {CreateEnterpriseAccessButton,TransferPage} from '../../stockmarket';
import {ShowBalance} from '../../users';

import users from '../../users';

const Header = () => {

    const userName = useSelector(users.selectors.getUserName);
	const userType = useSelector(users.selectors.getUserType);
	const loggedIn = useSelector(users.selectors.isLoggedIn);
	const isAdmin = useSelector(users.selectors.isAdmin);
    
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
				
                  {loggedIn &&  <Link className="navbar-brand" to="/search/enterprises"><FormattedMessage id='project.global.fields.enterprises'/></Link>}
			       
				  {(loggedIn && isAdmin==false) && <Link className="navbar-brand" to="/search/orders"><FormattedMessage id='project.global.fields.orders' /></Link>}	     
                
				</ul>
				
				
                {userName ? 

                <ul className="navbar-nav">

					{(isAdmin==false && userType=='STANDARD') && <Link className="navbar-brand" to="/users/premium/"> <FormattedMessage id="project.app.GetPremium"/></Link>}
					{(isAdmin==false && userType=='PREMIUM') && <Link className="navbar-brand" to="/users/premium/"> <FormattedMessage id="project.app.IsPremium"/></Link>}		

					{isAdmin==false && <Link className="navbar-brand" to="/market/transfer"> <FormattedMessage id="project.app.Transfer"/></Link>}

					{isAdmin==false && <Link className="navbar-brand" to="/"><ShowBalance/></Link>}
					
					{isAdmin && <li className="nav-item dropdown">

                        <a className="dropdown-toggle nav-link" href="/"
                            data-toggle="dropdown">
                            <span className="fas fa-hammer"></span>&nbsp;
                            <FormattedMessage id="project.app.Management"/>
                        </a>
						
                        <div className="dropdown-menu dropdown-menu-right">
                           	{isAdmin && <Link className="dropdown-item" to="/market/create_enterprise">
                                <FormattedMessage id="project.app.Header.createEnterprise"/>
                            </Link>
							}
                        </div>
                    </li>}
                    
                    <li className="nav-item dropdown">

                        <a className="dropdown-toggle nav-link" href="/"
                            data-toggle="dropdown">
                            <span className="fas fa-user"></span>&nbsp;
                            {userName}
                        </a>
						
                        <div className="dropdown-menu dropdown-menu-right">	
							<Link className="dropdown-item" to="/search/users/actions">
                                <FormattedMessage id="project.users.Actions.title"/>
                            </Link>
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
