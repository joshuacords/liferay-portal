/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {
	ACCOUNTS_PORTLET_NAMESPACE,
	BINARY_SELECTION
} from '../../../utilities/constants';
import CheckboxGroups from '../CheckboxGroups';

function Categorization({
	activeSLANames,
	regionNames,
	subscriptionStateNames,
	tierNames
}) {
	function processCheckboxGroupFieldValues(fieldValues) {
		return fieldValues.map(value => ({
			label: value,
			value
		}));
	}

	function processSubscriptionStateFieldValues() {
		return subscriptionStateNames.map(value => ({
			checked: value !== 'N/A' ? true : false,
			label: value,
			value
		}));
	}

	function simplifySLANames(names) {
		return names.map(name => ({
			label: name.replace(' Subscription', ''),
			value: name
		}));
	}

	return (
		<div className="panel-body">
			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('partner')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="partners"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('provides-fls')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="providesFLS"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('receives-fls')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="receivesFLS"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('internal')}
				</h5>

				<CheckboxGroups
					fieldValues={BINARY_SELECTION}
					inputName="internals"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('tier')}
				</h5>

				<CheckboxGroups
					fieldValues={processCheckboxGroupFieldValues(tierNames)}
					inputName="tiers"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-state')}
				</h5>

				<CheckboxGroups
					fieldValues={processSubscriptionStateFieldValues()}
					inputName="subscriptionStates"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('subscription-level')}
				</h5>

				<CheckboxGroups
					fieldValues={simplifySLANames(activeSLANames)}
					inputName="activeSLAs"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>

			<div className="col-md-3 form-group">
				<h5 className="form-check-inline">
					{Liferay.Language.get('support-region')}
				</h5>

				<CheckboxGroups
					fieldValues={processCheckboxGroupFieldValues(regionNames)}
					inputName="regions"
					namespace={ACCOUNTS_PORTLET_NAMESPACE}
				/>
			</div>
		</div>
	);
}

Categorization.propTypes = {
	activeSLANames: PropTypes.array.isRequired,
	regionNames: PropTypes.array.isRequired,
	subscriptionStateNames: PropTypes.array.isRequired,
	tierNames: PropTypes.array.isRequired
};

export default Categorization;
