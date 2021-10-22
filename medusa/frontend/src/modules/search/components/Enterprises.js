import React from 'react';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';
import FindEnterpriseLink from './FindEnterpriseLink'
const Enterprises = ({ enterprises }) => (

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

			</tr>
		</thead>

		<tbody>

			{enterprises.map(enterprise =>
				<tr key = {enterprise.id}>
					<td><FindEnterpriseLink id={enterprise.id} name = {enterprise.enterpriseName}/></td>
					<td> {enterprise.acronim} </td>
					<td> {enterprise.fundation} </td>
					<td> {enterprise.incomes} </td>
					<td> {enterprise.actions} </td>
					<td> {enterprise.actionsPrice} </td>
				</tr>


			)}

		</tbody>

	</table>

);

Enterprises.propTypes = {
	Enterprises: PropTypes.array.isRequired,
};

export default Enterprises;