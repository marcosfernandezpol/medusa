import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory, useParams } from 'react-router-dom';

import { Errors } from '../../common';
import * as actions from '../actions';

const CreateOrders = () => {

	const dispatch = useDispatch();
	const history = useHistory();
	const [numberActions, setNumberActions] = useState('');
	const [priceActions, setPriceActions] = useState('');
	const [backendErrors, setBackendErrors] = useState(null);
	const { id, enterpriseName, type } = useParams();
	let form;

	const handleSubmit = event => {

		event.preventDefault();

		if (form.checkValidity()) {

			dispatch(actions.createOrder(
				{
					type: type.trim(),
					price: priceActions.trim(),
					number: numberActions.trim(),
					enterpriseId: id.trim()
				},
				() => history.push('/search/orders'),
				errors => setBackendErrors(errors),
			));

		} else {

			setBackendErrors(null);
			form.classList.add('was-validated');

		}

	}

	return (
		<div>
			<Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
			<div className="card bg-light border-dark">
				<h5 className="card-header">						<FormattedMessage id='project.global.form.buySellForm'/>
				</h5>
				<div className="card-body">
					<form ref={node => form = node}
						className="needs-validation" noValidate
						onSubmit={e => handleSubmit(e)}>
						<div className="form-group row">
							<label htmlFor="numberActions" className="col-md-3 col-form-label">									<FormattedMessage id='project.global.fields.actionsNumber'/>
                            </label>
							<div className="col-md-4">
								<input type="text" id="userName" className="form-control"
									value={numberActions}
									onChange={e => setNumberActions(e.target.value)}
									autoFocus
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>


						<div className="form-group row">
							<label htmlFor="priceActions" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.actionPrice" />
							</label>
							<div className="col-md-4">
								<input type="text" id="priceActions" className="form-control"
									value={priceActions}
									onChange={e => setPriceActions(e.target.value)}
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>
						<div className="form-group row">
							<div className="offset-md-3 col-md-1">
								<button type="submit" className="btn btn-secondary">
									<FormattedMessage id="project.global.buttons.submitOperation" />
								</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	);

}

export default CreateOrders;