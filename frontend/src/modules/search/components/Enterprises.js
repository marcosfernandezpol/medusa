import React from 'react';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import FindEnterpriseLink from './FindEnterpriseLink';
import ModifyAvaliableButton from './ModifyAvaliableButton';
import PremiumLink from './PremiumLink';
import * as userSelectors from '../../users/selectors';
import * as actions from '../actions';


const Enterprises = ({ enterprises }) => {

	const user = useSelector(userSelectors.getUser);
	const dispatch = useDispatch();
	const history = useHistory();
	const yes = "YES";
	const no = "NO";


	Enterprises.propTypes = {
		Enterprises: PropTypes.array.isRequired,
	};

	return (

		<table className="table table-striped table-hover text-center">

			<thead>
				<tr>
					<th scope="col">
						<FormattedMessage id='project.global.fields.enterpriseName' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.acronim' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.fundationDate' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.incomes' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.actions' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.actionsPrice' />
					</th>
					<th scope="col">
						<FormattedMessage id='project.global.fields.avaliable' />
					</th>

				</tr>
			</thead>

			<tbody>

				{enterprises.map(enterprise =>
					<tr key={enterprise.id}>
						<td><FindEnterpriseLink id={enterprise.id} name={enterprise.enterpriseName} /></td>
						<td> {enterprise.acronim} </td>
						<td> {enterprise.fundation} </td>
						<td> {enterprise.incomes} </td>
						<td> {enterprise.actions} </td>
						{ (enterprise.actionsPrice==0) ? <td> {<PremiumLink name={<FormattedMessage id= 'project.global.fields.notAvaliable'/>} /> } </td> :  <td> {enterprise.actionsPrice} </td>}
						<ModifyAvaliableButton id = {user.id} enterprise={enterprise} />

					</tr>


				)}

			</tbody>

		</table>

	);

}

export default Enterprises;