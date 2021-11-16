import React from 'react';
import PropTypes from 'prop-types';
import { FormattedMessage } from 'react-intl';
import Orders from './Orders';
import * as selectors from '../selectors';
import { useSelector} from 'react-redux';


const FindOrdersResult = ({ orders }) =>{

	const enterprises = useSelector(selectors.getEnterprises);


	return(
	<table className="table table-striped table-hover text-center">

		<thead>
			<tr>
				<th scope="col">
					<FormattedMessage id='project.global.fields.requestdate' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.orderType' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.price' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.actionsNumber' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.enterpriseName' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.deadLine' />
				</th>
			</tr>
		</thead>

		<tbody>

			{orders.map(order =>
				<tr>
					<td> {new Date(order.requestDate).toLocaleString()} </td>
					<td> {order.orderType} </td>
					<td> {order.price} € </td>
					<td> {order.number} </td>
					<td> {selectors.getEnterpriseName(enterprises,order.enterpriseId)} </td>
					<td> {order.deadline} </td>

				</tr>


			)}

		</tbody>

	</table>
	)

}


FindOrdersResult.propTypes = {
	FindOrdersResult: PropTypes.array.isRequired,
};



export default FindOrdersResult;