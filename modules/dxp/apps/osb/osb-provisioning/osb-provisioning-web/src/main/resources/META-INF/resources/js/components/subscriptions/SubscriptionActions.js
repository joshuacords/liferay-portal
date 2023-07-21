/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {useSubscriptions} from '../../hooks/subscriptions';
import {
	ADD_SUBSCRIPTIONS,
	EDIT_SUBSCRIPTIONS,
	NAMESPACE
} from '../../utilities/constants';
import CancelLink from '../CancelLink';

function SubscriptionActions({
	allowSave,
	backURL,
	formAction,
	redirectURL,
	subscriptionsType
}) {
	const [subscriptions] = useSubscriptions();

	const [disableSave, setDisableSave] = useState(!allowSave);

	useEffect(() => {
		function validateEnableSave() {
			return subscriptions
				.toList()
				.every(
					subscription =>
						subscription.salesforceOpportunityKey &&
						subscription.validateAllDates()
				);
		}

		if (validateEnableSave() && allowSave) {
			setDisableSave(false);
		}
		else {
			setDisableSave(true);
		}
	}, [allowSave, subscriptions]);

	function processSubmissionData() {
		const submissionData = subscriptions
			.toList()
			.toJS()
			.map(entry => {
				entry['externalLinks'] = [
					{
						domain: 'salesforce',
						entityId: entry.salesforceOpportunityKey,
						entityName: 'opportunity',
						key: entry.externalLinkKey
					}
				];
				entry['originalEndDate'] = formatDateSecondFormat(
					entry.originalEndDate
				);
				entry['properties'] = {sizing: entry.sizing};
				entry['startDate'] = formatDateSecondFormat(entry.startDate);

				if (subscriptionsType === EDIT_SUBSCRIPTIONS) {
					entry['endDate'] = formatDateSecondFormat(entry.endDate);
				}

				delete entry['externalLinkKey'];
				delete entry['index'];
				delete entry['productName'];
				delete entry['salesforceOpportunityKey'];
				delete entry['sizing'];

				return entry;
			});

		return JSON.stringify(submissionData);
	}

	function formatDateSecondFormat(val) {
		return JSON.stringify(val)
			.replace(/.(\d+)Z/g, 'Z')
			.replace(/"/g, '');
	}

	return (
		<form action={formAction} method="post">
			<input
				name={`${NAMESPACE}data`}
				type="hidden"
				value={processSubmissionData()}
			/>

			<div className="button-holder">
				<button
					className="btn btn-primary"
					disabled={disableSave}
					type="submit"
				>
					{Liferay.Language.get('save')}
				</button>

				{!!backURL && (
					<a
						className="btn btn-secondary"
						href={backURL}
						type="button"
					>
						{Liferay.Language.get('previous')}
					</a>
				)}

				<CancelLink redirect={redirectURL} />
			</div>
		</form>
	);
}

SubscriptionActions.propTypes = {
	allowSave: PropTypes.bool.isRequired,
	backURL: PropTypes.string,
	formAction: PropTypes.string.isRequired,
	redirectURL: PropTypes.string.isRequired,
	subscriptionsType: PropTypes.oneOf([ADD_SUBSCRIPTIONS, EDIT_SUBSCRIPTIONS])
		.isRequired
};

export default SubscriptionActions;
