import React from 'react';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';

const Orders = ({orders}) => (
	

	<table className="table table-striped table-hover text-center">

		<thead>
			<tr>
				<th scope="col">
					<FormattedMessage id='project.global.fields.enterpriseName' />
				</th>

			</tr>
		</thead>

		<tbody>

			{orders.map(order =>
				<tr>
					<td> {order.enterpriseName} </td>
				</tr>

			)}

		</tbody>

	</table>
);

Orders.propTypes = {
	Orders: PropTypes.array.isRequired,
};

export default Orders;