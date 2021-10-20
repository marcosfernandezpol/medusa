import React from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const FindEnterpriseLink = ({ id, name }) => {
	return (
		<Link to={`/search/enterprise/${id}`}>
			{name}
		</Link>

	);

}

FindEnterpriseLink.propTypes = {
	id: PropTypes.number.isRequired
};

export default FindEnterpriseLink;