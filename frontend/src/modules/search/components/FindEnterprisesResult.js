import React, {useState, useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';
import * as actions from '../actions';
import Enterprises from './Enterprises';

const FindEnterprisesResult = () => {

    const EnterpriseSearch = useSelector(selectors.getEnterprises);
    const dispatch = useDispatch();
	const [enterpriseName, setEnterpriseName] = useState('');

	const filterEnterprise = () => {
		
		if(EnterpriseSearch)
			return EnterpriseSearch.filter(enterprise => enterprise.enterpriseName.includes(enterpriseName));
		else
			return EnterpriseSearch;
	}
	
    useEffect(() => {

        dispatch(actions.searchAllEnterprises());

    }, []);

    if (!EnterpriseSearch) {
		
        return null;
    }
    return (
		<div>
			<h1 className="table table-striped table-hover text-center">
					<FormattedMessage id='project.global.fields.enterprises'/>
			</h1>
			<form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>
				<input id="keywords" type="text" className="form-control mr-sm-2" 
						placeholder="search" value={enterpriseName} onChange={e => setEnterpriseName(e.target.value)}/>
			</form>
			<div ><h1></h1></div>
			<Enterprises enterprises={filterEnterprise()}/>
      	</div>
    );
}	
export default FindEnterprisesResult;
