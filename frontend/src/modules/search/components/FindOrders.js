import { useEffect} from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {FormattedMessage} from 'react-intl';
import FindOrdersResult from './FindOrdersResult'

import * as actions from '../actions';
import * as selectors from '../selectors';

const FindOrders = () => {

	const dispatch = useDispatch();
	
	//const history = useHistory();

	useEffect(() => {
		
		dispatch(actions.searchNotSoldOrders(false, false,
			errors => setBackendErrors(errors)
		));
		
		dispatch(actions.searchSoldOrders(false, true,
			errors => setBackendErrors(errors)
		));
		
		dispatch(actions.searchBoughtOrders(true, true,
			errors => setBackendErrors(errors)
		));
		
		dispatch(actions.searchNotBoughtOrders(true, false,
			errors => setBackendErrors(errors)
		));
		
		dispatch(actions.searchAllEnterprises());
		
		
	},[]);
	
	const bought = useSelector(selectors.getBought);
	const notBought = useSelector(selectors.getNotBought);
	const sold = useSelector(selectors.getSold);
	const notSold = useSelector(selectors.getNotSold);
	
	let executed = null;
	let notExecuted = null;
		
	if (bought) {
		executed=bought;
		if (sold)
			executed = bought.concat(sold) ;
	} else
		executed = sold;
		
	if (notBought) {
		notExecuted = notBought;
		if (notSold)
			notExecuted = notBought.concat(notSold);
	} else 
		notExecuted = notSold;


	return (
		<div>
			<h1 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.orderList'/> </h1>
			<h2 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.notExecutedList'/> </h2>
			{executed && <FindOrdersResult orders={executed} displayTrash = {true}/>}
			<h2 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.executedList'/> </h2>
			{notExecuted && <FindOrdersResult orders={notExecuted} displayTrash = {false}/>}
			
			
        </div>
	);

}

export default FindOrders;