import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindEnterprises = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {

        dispatch(actions.searchAllEnterprises());
        history.push('/search/enterprises');

    });

    return null;

}

export default FindEnterprises;