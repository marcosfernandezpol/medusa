import {React, useEffect} from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

import EuroSvg from './euro-sign-solid.svg';

import * as stockMarketSelector from '../../stockmarket/selectors';

const ShowBalance = () => {
	

    const balance = useSelector(stockMarketSelector.getBalance);
	
	
    return (
        <div>
			<FormattedMessage id="project.users.ShowBalance.balance"/> : {balance}&nbsp;
            <img src={EuroSvg} width="18em" height="18em"/>
        </div>
    );

}

export default ShowBalance;