import React from 'react';
import {useSelector} from 'react-redux';

import * as selectors from '../selectors';

const ShowBalance = () => {

    const user = useSelector(selectors.getUser);
    return (
        <div>
			Saldo: {user.balance}&nbsp;
            <span className="fas fa-euro-sign"></span>
        </div>
    );

}

export default ShowBalance;