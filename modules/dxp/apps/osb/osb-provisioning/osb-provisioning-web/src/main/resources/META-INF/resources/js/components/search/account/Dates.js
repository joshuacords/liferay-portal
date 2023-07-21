/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {ACCOUNTS_PORTLET_NAMESPACE as NAMESPACE} from '../../../utilities/constants';
import DatePicker from '../../DatePicker';

function Dates() {
	return (
		<div className="panel-body">
			<div className="col-md-12 form-group">
				<label htmlFor="createdByEmailAddress">
					{Liferay.Language.get('created-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="createdByEmailAddress"
					name={`${NAMESPACE}createdByEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-liferay-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="createdAfter">
					{Liferay.Language.get('created-after')}
				</label>

				<DatePicker
					id="createdAfter"
					inputName={`${NAMESPACE}createDateGT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="createdBefore">
					{Liferay.Language.get('created-before')}
				</label>

				<DatePicker
					id="createdBefore"
					inputName={`${NAMESPACE}createDateLT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="modifiedAfter">
					{Liferay.Language.get('modified-after')}
				</label>

				<DatePicker
					id="modifiedAfter"
					inputName={`${NAMESPACE}modifiedDateGT`}
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="modifiedBefore">
					{Liferay.Language.get('modified-before')}
				</label>

				<DatePicker
					id="modifiedBefore"
					inputName={`${NAMESPACE}modifiedDateLT`}
				/>
			</div>
		</div>
	);
}

export default Dates;
