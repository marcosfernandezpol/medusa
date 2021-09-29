import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {useEffect} from 'react';
import * as selectors from '../selectors';
import {Errors} from '../../common';
import * as actions from '../actions';
import {Pager} from '../../common';
import Enterprises from './Enterprises';


const FindEnterprisesResult = () => {

    const EnterpriseSearch = useSelector(selectors.getEnterprises);
    const dispatch = useDispatch();

    const history = useHistory();

    useEffect(() => {

        dispatch(actions.searchAllEnterprises());
        history.push('/search/enterprises');

    });

    if (!EnterpriseSearch) {
		
        return null;
    }
	   
    return (

        <div>
			<h1 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.enterprisesList'/> </h1>
			
            <Enterprises enterprises={EnterpriseSearch}/>
        </div>

    );

}

export default FindEnterprisesResult;