import React from 'react';
import PropTypes from 'prop-types';
import { FormattedMessage } from 'react-intl';
import * as selectors from '../selectors';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import * as actions from '../actions';


import TrashSvg from './trash-alt-solid.svg';

const FindOrdersResult = ({ orders, displayTrash }) => {

	const enterprises = useSelector(selectors.getEnterprises);
	const dispatch = useDispatch();
	var order;
	const history = useHistory();

	for (let i = 0; i < orders.length; i++) {
		order = orders[i];
	}


	const handleClick = ({ order }) => {
		dispatch(actions.deleteOrder(order.id, displayTrash));
		window.location.reload();
	}


	return (
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
					<th scope="col">
						<FormattedMessage id='project.global.fields.orderType' />
					</th>
					{displayTrash && <th scope="col"></th>}
				</tr>
			</thead>

			<tbody>

				{orders.map(order =>
					<tr>
						<td> {new Date(order.requestDate).toLocaleString()} </td>
						<td> {order.orderType} </td>
						{order.price != 0 && <td> {order.price} € </td>}
						{order.price == 0 && <td> <FormattedMessage id='project.global.fields.priceNotEspecified' /> </td>}
						<td> {order.number} </td>
						<td> {selectors.getEnterpriseName(enterprises, order.enterpriseId)} </td>
						<td> {new Date(order.deadline).toLocaleString()} </td>
						<td> {order.orderLineType} </td>

						{displayTrash && <td> <button class="btn btn-danger shadow-none text-centerS" onClick={() => handleClick({ order })}> <img src={TrashSvg} width="16em" height="16em"/></button></td>}
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