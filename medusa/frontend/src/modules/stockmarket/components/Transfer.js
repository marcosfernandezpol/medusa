import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions'
import users from '../../users';

const Transfer = () => {

	const dispatch = useDispatch();
	const history = useHistory();
	const [money, setMoney] = useState('');
	const [operation, setOperation] = useState('');
	const [backendErrors, setBackendErrors] = useState(null);
	const [origin, setOrigin]=useState('');
	let form;

	const handleSubmit = event => {

		event.preventDefault();

		if (form.checkValidity()) {
			dispatch(actions.transfer(
				money,
				operation,
				() => history.push('/'),
				errors => setBackendErrors(errors)));
			
		} else {
            setBackendErrors(null);
			form.classList.add('was-validated');
		}
	}

	return (
		
		<div>
			<Errors errors={backendErrors} 
				onClose={() => setBackendErrors(null)}/>
			<div className="card bg-light border-dark">
				<h5 className="card-header">
					<FormattedMessage id="project.users.Transfer.title" />
				</h5>
				<div className="card-body">
					<form ref={node => form = node}
						className="needs-validation" noValidate
						onSubmit={e => handleSubmit(e)}>
						<div className="form-group row">
							<label htmlFor="text" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.origin" />
							</label>
							<div className="col-md-4">
								<input type="text" maxlength="24" id="origin" className="form-control"
									value={origin}
									onChange={e => setOrigin(e.target.origin)}
									autoFocus
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>
						<div className="form-group row">
							<label htmlFor="money" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.Money" />
							</label>
							<div className="col-md-4">
								<input type="number" step="0.01" id="money" className="form-control"
									value={money}
									onChange={e => setMoney(e.target.value)}
									autoFocus
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>
						<div className="form-group row">
							<label htmlFor="password" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.operation" />
							</label>
							<div className="col-md-4">
                                <select onChange={e => setOperation(e.target.value)} class="form-select" type="text" id="operation" className="form-control"> 
                                  	<option selected value="Select the operation">----Select operation----</option>
									<option value="INCOME">INCOME</option>
									<option value="WITHDRAW">WITHDRAW</option>                                                 
                                </select>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
						</div>
						<div className="form-group row">
							<div className="offset-md-3 col-md-1">
								<button type="submit" className="btn btn-primary">
									<FormattedMessage id="project.users.Transfer.button" />
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	);

}

export default Transfer;