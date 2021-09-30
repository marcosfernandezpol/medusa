import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const ShowBalance = () => {

    const user = useSelector(selectors.getUser);
    return (
        <div>
			<FormattedMessage id="project.users.ShowBalance.balance"/> : {user.balance}&nbsp;
            <span className="fas fa-euro-sign"></span>
        </div>
    );

}

export default ShowBalance;