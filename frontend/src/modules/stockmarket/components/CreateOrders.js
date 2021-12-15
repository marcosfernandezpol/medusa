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
	const [deadline, setDeadline] = useState('');
	const [time, setTime] = useState('');
	const [backendErrors, setBackendErrors] = useState(null);
	const { id, type } = useParams();
	let form;

	const handleSubmit = event => {

		event.preventDefault();

		if (form.checkValidity()) {
			
			const fechaHora = deadline+"T"+time+":00";

			dispatch(actions.createOrder(
				{
					type: type.trim(),
					orderLineType: 'LIMIT',
					price: priceActions.trim(),
					number: numberActions.trim(),
					enterpriseId: id.trim(),
					deadline: fechaHora
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
							<label htmlFor="deadline" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.deadLine" />
							</label>
							<div className="col-md-4">
								<input type="date" id="deadline" className="form-control"
									value={deadline}
									min = {new Date().toISOString().split('T')[0]}
									onChange={e => setDeadline(e.target.value)}
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
								<input type="time" id="deadline-time" className="form-control"
									value={time}
									onChange={e => setTime(e.target.value)}
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