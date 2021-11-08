import React, { useState } from 'react';
import {useDispatch,useSelector } from 'react-redux';
import { useHistory,useParams } from 'react-router-dom';
import FieldArrayForm from './FieldArrayForm'
import * as actions from '../actions';
import * as selectors from '../selectors';
import { Errors, Success } from '../../common'
import {formValueSelector} from 'redux-form';



const UpdateEnterprise = ({ }) => {
	
	

	
	const dispatch=useDispatch();
	const history = useHistory();
	const { id } = useParams();
	const enterpriseId = Number(id);
	const [backendErrors, setBackendErrors] = useState(null);
	const [success, setSuccess] = useState(null);
	
	const handleSubmit = (values) => {
			
			dispatch(actions.updateEnterprise(
				enterpriseId,
				values,
				() => history.push(`/search/enterprise/${id}`),
				errors => setBackendErrors(errors)
			));

	}
	
	const handleFail = (errors, dispatch, submitErrors) => {
		window.alert(submitErrors)
		
	}
	
	return (
		<div>
			<Errors errors={backendErrors}
				onClose={() => setBackendErrors(null)} />
			<Success message={success}
				onClose={() => setSuccess(null)} />
			<FieldArrayForm onSubmit={handleSubmit} onSubmitFail={handleFail}/>
		</div>
	)

}
export default UpdateEnterprise;