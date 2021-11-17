import React from 'react';
import { FormattedMessage } from 'react-intl';
import * as selectors from '../selectors';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import * as actions from '../actions';


const FindActionsResult = () => {

	const dispatch = useDispatch();

	useEffect(() => {

		dispatch(actions.searchActions());

	}, []);




	const userActions = useSelector(selectors.getActions);

	return (

		<div>
			<h1 className="table table-striped table-hover text-center">
				<FormattedMessage id='project.global.fields.actionsList' />
			</h1>
			<table className="table table-striped table-hover text-center">

				<thead>
					<tr>
						<th scope="col">
							<FormattedMessage id='project.global.fields.enterpriseName' />
						</th>
						<th scope="col">
							<FormattedMessage id='project.global.fields.actionsNumber' />
						</th>
					</tr>
				</thead>
				{!(userActions == null) && <tbody>
					{userActions.map(userAction =>
						<tr>
							<td> {userAction.enterprise.enterpriseName} </td>
							<td> {userAction.number} </td>
						</tr>
					)}
				</tbody>}



			</table>
		</div>
	)

}

export default FindActionsResult;