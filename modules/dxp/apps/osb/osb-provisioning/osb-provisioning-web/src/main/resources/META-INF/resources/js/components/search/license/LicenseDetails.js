/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import {BINARY_SELECTION, NAMESPACE} from '../../../utilities/constants';
import CheckboxGroups from '../CheckboxGroups';

function LicenseDetails() {
	return (
		<div className="panel-body">
			<div className="col-md-6 form-group">
				<label htmlFor="accountKey">
					{Liferay.Language.get('account-key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="accountKey"
					name={`${NAMESPACE}accountKey`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="productPurchaseKey">
					{Liferay.Language.get('product-purchase-key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="productPurchaseKey"
					name={`${NAMESPACE}productPurchaseKey`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="accountName">
					{Liferay.Language.get('account-name')}
				</label>
				<input
					className="form-control form-control-sm"
					id="accountName"
					name={`${NAMESPACE}accountName`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="owner">{Liferay.Language.get('owner')}</label>
				<input
					className="form-control form-control-sm"
					id="owner"
					name={`${NAMESPACE}owner`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="hostName">
					{Liferay.Language.get('host-name')}
				</label>
				<input
					className="form-control form-control-sm"
					id="hostName"
					name={`${NAMESPACE}hostName`}
					type="text"
				/>
			</div>

			<div className="col-md-6 form-group">
				<label htmlFor="ipAddress">
					{Liferay.Language.get('ip-address')}
				</label>
				<input
					className="form-control form-control-sm"
					id="ipAddress"
					name={`${NAMESPACE}ipAddress`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="macAddress">
					{Liferay.Language.get('mac-address')}
				</label>
				<input
					className="form-control form-control-sm"
					id="macAddress"
					name={`${NAMESPACE}macAddress`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="serverId">
					{Liferay.Language.get('server-id')}
				</label>
				<input
					className="form-control form-control-sm"
					id="serverId"
					name={`${NAMESPACE}serverId`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="licenseKey">
					{Liferay.Language.get('key')}
				</label>
				<input
					className="form-control form-control-sm"
					id="licenseKey"
					name={`${NAMESPACE}key`}
					type="text"
				/>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="creatorEmailAddress">
					{Liferay.Language.get('created-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="creatorEmailAddress"
					name={`${NAMESPACE}creatorEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-4 form-group">
				<label htmlFor="modifiedEmailAddress">
					{Liferay.Language.get('last-edited-by')}
				</label>
				<input
					className="form-control form-control-sm"
					id="modifiedEmailAddress"
					name={`${NAMESPACE}modifiedEmailAddress`}
					type="email"
				/>
				<div className="form-feedback-group">
					<div className="form-text">
						{Liferay.Language.get('users-email-address')}
					</div>
				</div>
			</div>

			<div className="col-md-4 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('active')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="activeLicenses"
				/>
			</div>
		</div>
	);
}

export default LicenseDetails;
