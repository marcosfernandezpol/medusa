import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import { FormattedMessage, FormattedNumber } from 'react-intl';
import { useHistory, useParams } from 'react-router-dom';

import { useEffect } from 'react';
import * as selectors from '../selectors';
import { Errors, BackLink } from '../../common';
import * as actions from '../actions';
import { Pager } from '../../common';


const FindEnterpriseResult = () => {

	const enterprise = useSelector(selectors.getEnterprise);
	const dispatch = useDispatch();

	const history = useHistory();
	const { id } = useParams();
	
	const handleClick=() => {
		const enterpriseId = Number(id);
		history.push(`/market/update_enterprise/${id}`)
	}

	useEffect(() => {
		const enterpriseId = Number(id);
		if (!Number.isNaN(enterpriseId)) {
			dispatch(actions.searchEnterpriseById(enterpriseId));
		}

		return () => dispatch(actions.clearSearchEnterprise());

	}, [id, dispatch]);

	if (!enterprise) {

		return <p>{"hola"}</p>;
	}

	return (

		<div>

			<BackLink />

			<div className="card text-center">
				<div className="card-body">
					<h4 className="card-title">{enterprise.enterpriseName}</h4>
					<h6 className="card-text">
						<FormattedMessage id='project.global.fields.acronim' />:&nbsp;
                        {enterprise.acronim}
					</h6>
					<h6 className="card-text">
						<FormattedMessage id='project.global.fields.fundationDate' />:&nbsp;
                        {new Date(enterprise.fundation).toLocaleString()}
					</h6>
					<p className="card-text">
						<FormattedMessage id='project.global.fields.incomes' />:&nbsp;
                         : <FormattedNumber value={enterprise.incomes} /> €
                </p>
					<h5 className="card-text">
						<FormattedMessage id='project.global.fields.actions' />:&nbsp;
                        {enterprise.actions}
					</h5>

					<h5 className="card-text font-weight-bold">
						<FormattedMessage id='project.global.fields.actionsPrice' />
                    :   <FormattedNumber value={enterprise.actionsPrice} />€
                </h5>
				</div>
			</div>
			
			<div className="card text-center">&nbsp;
				<h4>	
					<FormattedMessage id='project.global.fields.anualBenefits'/> &nbsp; 
					<button onClick={handleClick} class="fas fa-plus"></button>
				</h4>
				
					
			</div>
			<table className="table table-striped table-hover text-center">
				<th scope="col">
					<FormattedMessage id='project.global.fields.year' />
				</th>
				<th scope="col">
					<FormattedMessage id='project.global.fields.benefits' />
				</th>

				<tbody>
					{enterprise.anualBenefitsDto.map(annual =>
						<tr>
							<td> {annual.year} </td>
							<td> <FormattedNumber value={annual.benefits} />€ </td>
						</tr>
					)}
				</tbody>
			</table>


		</div>

	);

}

export default FindEnterpriseResult;