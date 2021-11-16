import {React, useEffect} from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

import * as stockMarketSelector from '../../stockmarket/selectors';

const ShowBalance = () => {
	

    const balance = useSelector(stockMarketSelector.getBalance);
	
	
    return (
        <div>
			<FormattedMessage id="project.users.ShowBalance.balance"/> : {balance}&nbsp;
            <span className="fas fa-euro-sign"></span>
        </div>
    );

}

export default ShowBalance;