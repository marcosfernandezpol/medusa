import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';


import * as selectors from '../selectors';
import {Errors} from '../../common';
import * as actions from '../actions';
import {Pager} from '../../common';
import Enterprises from './Enterprises';


const FindEnterprisesResult = () => {

    const EnterpriseSearch = useSelector(selectors.getEnterprises);
    const dispatch = useDispatch();

    if (!EnterpriseSearch) {
		
        return null;
    }
	   
    return (

        <div>
			<h1>Lista de empresas</h1>
			
            <Enterprises enterprises={EnterpriseSearch}/>
        </div>

    );

}

export default FindEnterprisesResult;