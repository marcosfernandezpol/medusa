import React from 'react';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import {enterpriseLink} from '../../common';

const  Enterprises = ({enterprises}) => (

    <table className="table table-striped table-hover text-center">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.enterpriseName'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.acronim'/>
                </th>
				<th scope="col">
                    <FormattedMessage id='project.global.fields.fundationDate'/>
                </th>
				<th scope="col">
                    <FormattedMessage id='project.global.fields.anualBenefits'/>
                </th>
				<th scope="col">
                    <FormattedMessage id='project.global.fields.incomes'/>
                </th>

            </tr>
        </thead>

        <tbody>

			{enterprises.map(enterprise =>
                    <tr>
						<td> {enterprise.enterpriseName} </td>
						<td> {enterprise.acronim} </td>
						<td> {enterprise.fundation} </td>
						<td> {enterprise.annualBenefits} </td>
						<td> {enterprise.incomes} </td>
					</tr>
					
					
			)}       
            
        </tbody>

    </table>

);

Enterprises.propTypes = {
    Enterprises: PropTypes.array.isRequired,
};

export default Enterprises;