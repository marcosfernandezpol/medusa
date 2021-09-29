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
	let form;

	const handleSubmit = event => {

		event.preventDefault();

		dispatch(actions.transfer(
			money,
			operation,
			() => history.push('/'),
			errors => setBackendErrors(errors),

		));

	}

	return (
		<div>
			<div className="card bg-light border-dark">
				<h5 className="card-header">
					<FormattedMessage id="project.users.Transfer.title" />
				</h5>
				<div className="card-body">
					<form ref={node => form = node}
						className="needs-validation" noValidate
						onSubmit={e => handleSubmit(e)}>
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
								<input type="text" id="operation" className="form-control"
									value={operation}
									onChange={e => setOperation(e.target.value)}
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>
						<div className="form-group row">
							<div className="offset-md-3 col-md-1">
								<button type="submit" className="btn btn-primary">
									<FormattedMessage id="project.users.Transfer.title" />
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