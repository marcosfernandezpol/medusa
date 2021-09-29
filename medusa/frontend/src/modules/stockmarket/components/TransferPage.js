import React from 'react';
import {useSelector, useDispatch} from 'react-redux';



import Transfer from './Transfer';


const TransferPage = () => {

    const dispatch = useDispatch();

	   
    return (

        <div>
			<h1>Transfers</h1>
			
            <Transfer/>
        </div>

    );

}

export default TransferPage;