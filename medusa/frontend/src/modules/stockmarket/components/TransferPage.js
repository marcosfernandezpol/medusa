import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';



import Transfer from './Transfer';


const TransferPage = () => {

    const dispatch = useDispatch();

	   
    return (

        <div>
			<h1>
				<FormattedMessage id="project.global.fields.transfers"/>
			</h1>
			
            <Transfer/>
        </div>

    );

}

export default TransferPage;