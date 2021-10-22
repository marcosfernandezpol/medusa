import React from 'react';
import {FormattedMessage} from 'react-intl';

const Home = () => {

	return (
		<div className="text-center">
		    <div>
				<h1 > <FormattedMessage id="project.global.fields.homeTitle"/> </h1>
		    </div>
				
			<div>
				<FormattedMessage id="project.global.fields.homeSubTitle"/>
			</div>
		</div>
		

	)
};

export default Home;