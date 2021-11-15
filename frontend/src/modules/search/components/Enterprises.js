import React from 'react';
import { FormattedMessage } from 'react-intl';
import PropTypes from 'prop-types';
import {useSelector, useDispatch} from 'react-redux';
import { useHistory } from 'react-router-dom';
import FindEnterpriseLink from './FindEnterpriseLink'
import * as userSelectors from '../../users/selectors';
import * as actions from '../actions';


const Enterprises = ({ enterprises }) => {
	
	const user = useSelector(userSelectors.getUser);
	const dispatch = useDispatch();
	const history = useHistory();
	const yes = "YES";
	const no = "NO";
	
	const handleClick = ({enterprise}) => {
		const result = {
			id:enterprise.id,
    		enterpriseName:enterprise.enterpriseName,
    		creatorId:enterprise.creatorId,
    		acronim:enterprise.acronim,
    		fundation:enterprise.fundation,
    		incomes:enterprise.incomes,
    		actions:enterprise.actions,
    		actionsPrice:enterprise.actionsPrice,
    		avaliable: enterprise.avaliable
		}
		
		dispatch(actions.setUnavaliable(
			result, 
			enterprise.id,
			() => history.push('/'),
            errors => setBackendErrors(errors)));

		Enterprises.propTypes = {
			Enterprises: PropTypes.array.isRequired,
		};
		
	}
	
	return(

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
				<tr key = {enterprise.id}>
					<td><FindEnterpriseLink id={enterprise.id} name = {enterprise.enterpriseName}/></td>
					<td> {enterprise.acronim} </td>
					<td> {enterprise.fundation} </td>
					<td> {enterprise.incomes} </td>
					<td> {enterprise.actions} </td>
					<td> {enterprise.actionsPrice} </td>
					{enterprise.avaliable==true &&<td> {yes} </td>}
					{enterprise.avaliable==false &&<td> {no} </td>}
						{(user.id==enterprise.creatorId && enterprise.avaliable==true) && 
							<button onClick={handleClick({enterprise})}>Disable</button>}
						{(user.id==enterprise.creatorId && enterprise.avaliable==false) &&    
							<td> <button onClick={handleClick({enterprise})}>Enable</button></td>}
				</tr>


			)}

		</tbody>

	</table>

);

}

export default Enterprises;