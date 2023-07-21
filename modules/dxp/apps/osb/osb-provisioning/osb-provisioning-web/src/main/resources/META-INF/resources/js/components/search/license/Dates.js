/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {NAMESPACE} from '../../../utilities/constants';
import DatePicker from '../../DatePicker';

function Dates() {
	return (
		<div className="panel-body panel-dates">
			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('created-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="createdAfter">
							{Liferay.Language.get('after')}
						</label>

						<DatePicker
							id="createdAfter"
							inputName={`${NAMESPACE}createDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="createdBefore">
							{Liferay.Language.get('before')}
						</label>

						<DatePicker
							id="createdBefore"
							inputName={`${NAMESPACE}createDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('modified-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="modifiedAfter">
							{Liferay.Language.get('after')}
						</label>

						<DatePicker
							id="modifiedAfter"
							inputName={`${NAMESPACE}modifiedDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="modifiedBefore">
							{Liferay.Language.get('before')}
						</label>

						<DatePicker
							id="modifiedBefore"
							inputName={`${NAMESPACE}modifiedDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('started-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="startedAfter">
							{Liferay.Language.get('after')}
						</label>

						<DatePicker
							id="startedAfter"
							inputName={`${NAMESPACE}startDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="startedBefore">
							{Liferay.Language.get('before')}
						</label>

						<DatePicker
							id="startedBefore"
							inputName={`${NAMESPACE}startDateLT`}
						/>
					</div>
				</div>
			</div>

			<div className="col-md-6 form-group">
				<h4>{Liferay.Language.get('expires-between')}</h4>

				<div className="row">
					<div className="col-md-6">
						<label htmlFor="expiresAfter">
							{Liferay.Language.get('after')}
						</label>

						<DatePicker
							id="expiresAfter"
							inputName={`${NAMESPACE}expirationDateGT`}
						/>
					</div>

					<div className="col-md-6">
						<label htmlFor="expiresBefore">
							{Liferay.Language.get('before')}
						</label>

						<DatePicker
							id="expiresBefore"
							inputName={`${NAMESPACE}expirationDateLT`}
						/>
					</div>
				</div>
			</div>
		</div>
	);
}

export default Dates;
