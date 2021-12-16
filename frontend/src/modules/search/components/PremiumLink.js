import React from 'react';
import { Link } from 'react-router-dom';

const PremiumLink = ({ name }) => {
	return (
		<Link to={`/users/premium/`}>
			{name}
		</Link>

	);
}

export default PremiumLink;