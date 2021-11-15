import React from 'react';

import PropTypes from 'prop-types';
import { useSelector, useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import * as actions from '../actions';


const ModifyAvaliableButton = ({ id, enterprise }) => {
	
	const dispatch = useDispatch();
	const history = useHistory();

	const handleClick = ({ enterprise }) => {
		const result = {
			id: enterprise.id,
			enterpriseName: enterprise.enterpriseName,
			creatorId: enterprise.creatorId,
			acronim: enterprise.acronim,
			fundation: enterprise.fundation,
			incomes: enterprise.incomes,
			actions: enterprise.actions,
			actionsPrice: enterprise.actionsPrice,
			avaliable: enterprise.avaliable
		}
		
		

		dispatch(actions.setUnavaliable(
			result,
			enterprise.id,
			history.push('/'),
			errors => setBackendErrors(errors)));

	}
	
	if (id == enterprise.id) {
		return (
			<td>
			<button className="btn btn-primary" onClick={() => handleClick({ enterprise })}>
				 {enterprise.avaliable &&  <i> Disable </i>}

				{!enterprise.avaliable && <i> Enable </i>}
			</button>
			</td>


		);
	}else {
		return (
			<td>
			{enterprise.avaliable &&  <i> YES </i>}
			{!enterprise.avaliable &&  <i> NO </i>}
			</td>
		);
	}

}


export default ModifyAvaliableButton;