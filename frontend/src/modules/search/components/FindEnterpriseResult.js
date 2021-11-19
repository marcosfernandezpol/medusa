import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import { FormattedMessage, FormattedNumber } from 'react-intl';
import { useHistory, useParams } from 'react-router-dom';

import { useEffect } from 'react';
import users from '../../users';
import * as selectors from '../selectors';
import { Errors, BackLink } from '../../common';
import * as actions from '../actions';
import { Pager } from '../../common';
import GraphicChart from '../../stockmarket/components/GraphicChart';


const FindEnterpriseResult = () => {

	const enterprise = useSelector(selectors.getEnterprise);
	const isAdmin = useSelector(users.selectors.isAdmin);
	const dispatch = useDispatch();
	const [numDays, setNumDays] = useState('');
	const [loaded, setLoaded] = useState(false);
	let form;


	const history = useHistory();
	const { id } = useParams();
	const buy = 1;
	const sell = 0;

	const handleClick = () => {
		const enterpriseId = Number(id);
		history.push(`/market/update_enterprise/${id}`)
	}
	const handleClick1 = ({ id, type }) => {
		const enterpriseId = Number(id);
		history.push(`/market/create_order/${id}`)
	}


	useEffect(() => {
		const enterpriseId = Number(id);
		if (!Number.isNaN(enterpriseId)) {
			dispatch(actions.searchEnterpriseById(enterpriseId));
			dispatch(actions.searchEnterpriseHistoric(enterpriseId, 1));

		}

		return () => null;

	}, [id, dispatch, !numDays]);

	const handleSubmit = (event) => {
		event.preventDefault();

		const enterpriseId = Number(id);
		let number = Number(numDays);
		if (!Number.isNaN(enterpriseId)) {
			dispatch(actions.searchEnterpriseHistoric(enterpriseId, numDays));

		}
	}

	const data = useSelector(selectors.getEnterpriseHistoric);


	if (!enterprise) {

		return null;
	}

	if (!data) {

		return null;
	}


	const dataExample = [
		{
			"id": "Enterprise",
			"color": "hsl(255, 70%, 50%)",
			"data": data
		}
	]

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
			<div className="col-md-4">
				<form ref={node => form = node}
					className="needs-validation" noValidate
					onSubmit={handleSubmit}>
					<input type="number" step="1" id="numDays" className="form-control"
						value={numDays}
						onChange={e => setNumDays(e.target.value)}
						placeholder="Number of Days"
						autoFocus
						required />
					<button onClick={handleSubmit} className="fas fa-sort"></button>
				</form>
			</div>
			<div style={{ height: 500 }}>
				<GraphicChart data={dataExample} />
			</div>
			{isAdmin &&
				<div className="card text-center">&nbsp;
				<h4>
						<FormattedMessage id='project.global.fields.anualBenefits' /> &nbsp;
					<button onClick={handleClick} class="fas fa-plus"></button>
					</h4>


				</div>
			}

			{!isAdmin &&
				<div className="card text-center">&nbsp;
				<h4>
						<Link to={`/market/create_order/${id}/enterpriseName=${enterprise.enterpriseName}/${buy}`} className="btn btn-secondary mx-5">
							<FormattedMessage id='project.global.buttons.buy' />
						</Link>



						<Link to={`/market/create_order/${id}/enterpriseName=${enterprise.enterpriseName}/${sell}`} className="btn btn-secondary mx-5">
							<FormattedMessage id='project.global.buttons.sell' />
						</Link> &nbsp;

						<Link to={`/market/create_order_market_price/${id}/${buy}`} className="btn btn-secondary mx-5">
							<FormattedMessage id='project.global.buttons.marketPriceBuy' />
						</Link>

						<Link to={`/market/create_order_market_price/${id}/${sell}`} className="btn btn-secondary mx-5">
							<FormattedMessage id='project.global.buttons.marketPriceSell' />
						</Link>


					</h4>

				</div>
			}
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