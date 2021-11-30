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
	let cancelled = null;
	let executedAux = null;
		
	if (bought) {
		notExecuted=bought;
		if (sold)
			notExecuted = bought.concat(sold) ;
	} else
		notExecuted = sold;
		
	if (notBought) {
		executed = notBought;
		if (notSold)
			executed = notBought.concat(notSold);
	} else 
		executed = notSold;
		
	if (executed){
		cancelled = executed.filter(execute => execute.cancelled==true);
		executedAux = executed.filter(execute => execute.cancelled==false);
	}

	return (
		<div>
			<h1 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.orderList'/> </h1>
			<h2 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.notExecutedList'/> </h2>
			{notExecuted && <FindOrdersResult orders={notExecuted} displayTrash = {true}/>}
			<h2 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.executedList'/> </h2>
			{executedAux && <FindOrdersResult orders={executedAux} displayTrash = {false}/>}
			<h2 className="table table-striped table-hover text-center"> <FormattedMessage id='project.global.fields.cancelledList'/> </h2>
			{cancelled && <FindOrdersResult orders={cancelled} displayTrash = {false}/>}
			
			
        </div>
	);

}

export default FindOrders;