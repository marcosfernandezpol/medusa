import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { FormattedMessage } from 'react-intl';
import { useHistory } from 'react-router-dom';
import { Errors, Success, BackLink } from '../../common'
import * as selectors from '../selectors';

import * as actions from '../actions';

import TickSVG from './check-solid.svg';
import CrossSVG from './times-solid.svg';


const Premium = ({ }) => {

	const dispatch = useDispatch();
	const history = useHistory();
	const [backendErrors, setBackendErrors] = useState(null);
	const [success, setSuccess] = useState(null);

	const user = useSelector(selectors.getUser);

	const handleClick = () => {


		dispatch(actions.upgrade(user,
			() => window.location.reload("/"),

			errors => setBackendErrors(errors),
		))
	};


	return (

		<div>
			<BackLink />
			<h1 className="table table-striped table-hover text-center">
				<FormattedMessage id='project.app.IsPremiumTitle' />

			</h1>

			<table className="table  table-hover text-center">

				<thead>
					<tr>
						<th>
						</th>
						<th scope="col">
							<FormattedMessage id='project.app.IsPremium' />
						</th>
						<th scope="col">
							<FormattedMessage id='project.app.IsNotPremium' />
						</th>


					</tr>
				</thead>

				<tbody>

					<tr>
						<td> <FormattedMessage id='project.global.user.premium' /> </td>
						<td> <img src={TickSVG} width="18em" height="18em"/> </td>
						<td> <img src={CrossSVG} width="18em" height="18em"/> </td>
					</tr>

					<tr>
						<td> <FormattedMessage id='project.global.user.premium1' /> </td>
						<td> <img src={TickSVG} width="18em" height="18em"/> </td>
						<td> <img src={CrossSVG} width="18em" height="18em"/> </td>
					</tr>

					<tr>
						<td>  </td>
						<td> 20€ </td>
						<td> <FormattedMessage id='project.global.fields.FREE' /> </td>
					</tr>

				</tbody>

			</table>
			<Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />



			{	user.type == 'STANDARD' &&
				<div className="text-center">
					<button class=" center btn btn-info shadow-none" onClick={() => handleClick()}><FormattedMessage id='project.app.IsPremiumButton' /></button>
				</div>
			}

		</div>

	)

}
export default Premium;
