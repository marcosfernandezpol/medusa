import React from 'react';
import {Link} from 'react-router-dom';
import { FormattedMessage } from 'react-intl';


const CreateEnterpriseAccessButton = () => {
	return (
		<Link className="btn btn-secondary" to="/market/create_enterprise">
			<FormattedMessage id="project.global.buttons.AccessCreateEnterprise" />
		</Link>
	)
}

export default CreateEnterpriseAccessButton;