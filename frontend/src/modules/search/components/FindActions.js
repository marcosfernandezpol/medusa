import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindActions = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {

        history.push('/search/user/actions');

    }, []);

    return null;

}

export default FindActions;