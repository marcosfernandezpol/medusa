import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';
import { Errors, Success } from '../../common'

import * as actionss from '../actions';
import * as selectors from '../selectors';

const CreateEnterprise = ({ }) => {

	const dispatch = useDispatch();
	const history = useHistory();
	const [backendErrors, setBackendErrors] = useState(null);
	const [success, setSuccess] = useState(null);
	const [enterpriseName, setEnterpriseName] = useState("");
	const [acronim, setAcronim] = useState("");
	const [fundation, setFundation] = useState("");
	const [incomes, setIncomes] = useState("");
	const [annualBenefits, setAnnualBenefits] = useState("");
	const [actions, setActions] = useState("");
	const [actionsPrice, setActionsPrice] = useState("");

	let form;

	const handleSubmit = event => {

		event.preventDefault();

		if (form.checkValidity()) {

			dispatch(actionss.createEnterprise(
				{ enterpriseName, acronim, fundation, incomes, annualBenefits, actions, actionsPrice },
				() => history.push('/market/createEnterpriseCompleted'),
				errors => setBackendErrors(errors)
			));


		} else {

			form.classList.add('was-validated');

		}

	}
	return (

		<div>
			<Errors errors={backendErrors}
				onClose={() => setBackendErrors(null)} />
			<Success message={success}
				onClose={() => setSuccess(null)} />
			<div className="card bg-light border-dark">
				<h5 className="card-header">
					<FormattedMessage id="project.enterprise.CreateEnterprise.title" />
				</h5>
				<div className="card-body">
					<form ref={node => form = node}
						className="needs-validation" noValidate
						onSubmit={(e) => handleSubmit(e)}>

						<div className="form-group row">
							<label htmlFor="name" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.enterpriseName" />
							</label>
							<div className="col-md-4">
								<input type="text" id="name" className="form-control" 
									value={enterpriseName}
									onChange={e => setEnterpriseName(e.target.value)}
									required maxLength='64' />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="acronim" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.acronim" />
							</label>
							<div className="col-md-4">
								<input type="text" id="acronim" className="form-control" 
									value={acronim}
									onChange={e => setAcronim(e.target.value)}
									required minLength='1' maxLength='8'/>
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="fundation" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.fundation" />
							</label>
							<div className="col-md-4">
								<input type="date" id="fundation" className="form-control"
									value={fundation}
									onChange={e => setFundation(e.target.value)}
									required />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="fincomes" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.incomes" />
							</label>
							<div className="col-md-4">
								<input type="number" step="0.01" id="incomes" className="form-control"
									value={incomes}
									onChange={e => setIncomes(e.target.value)}
									required minLength='1' maxLength='10'/>
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="annualBenefits" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.annualBenefits" />
							</label>
							<div className="col-md-4">
								<input type="number" step="0.01" id="annualBenefits" className="form-control"
									value={annualBenefits}
									onChange={e => setAnnualBenefits(e.target.value)}
									required minLength='1' maxLength='10' />
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="actions" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.actions" />
							</label>
							<div className="col-md-4">
								<input type="number" step="" id="actions" className="form-control"
									value={actions}
									onChange={e => setActions(e.target.value)}
									required minLength='1' maxLength='10'/>
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required.actions' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<label htmlFor="actionsPrice" className="col-md-3 col-form-label">
								<FormattedMessage id="project.global.fields.actionsPrice" />
							</label>
							<div className="col-md-4">
								<input type="number" step="0.01" id="actionsPrice" className="form-control"
									value={actionsPrice}
									onChange={e => setActionsPrice(e.target.value)}
									required minLength='1' maxLength='10'/>
								<div className="invalid-feedback">
									<FormattedMessage id='project.global.validator.required' />
								</div>
							</div>
						</div>

						<div className="form-group row">
							<div className="offset-md-3 col-md-1">
								<button type="submit" className="btn btn-secondary">
									<FormattedMessage id="project.global.buttons.CreateEnterpriseButton" />
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

	)

}
export default CreateEnterprise;
