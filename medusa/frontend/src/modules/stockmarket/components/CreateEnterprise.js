import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';
import { Errors } from '../../common'

import * as actions from '../actions';
import * as selectors from '../selectors';

const CreateEnterprise = ({}) => {

	    const dispatch = useDispatch();
	    const history = useHistory();
		const [errors, setErrors] = useState(null);

		let form;
	
	const handleSubmit = event => {
	
	        event.preventDefault();
	
	        if (form.checkValidity()) {
	            
	            dispatch(actions.createEnterprise(
					enterprise,
	                () => history.push('/stockmarket/createEnterpriseCompleted'),
	                errors => setErrors(errors)
	            ));
	            
	
	        } else {
	
	            setErrors(null);
	            form.classList.add('was-validated');
	
	        }

    }
return (
	<div>
		Holaaaa
	</div>
)

}
export default CreateEnterprise;
