/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {NAMESPACE} from './constants';

/**
 * Formats true or false filter values to yes or no display values
 * @param {string} value The filter value to be evaluated
 * @returns {string} New display value
 */
export function formatFilterValue(value) {
	switch (value) {
		case 'false':
			return Liferay.Language.get('no');
		case 'false,true':
			return (
				Liferay.Language.get('no') + ', ' + Liferay.Language.get('yes')
			);
		case 'true':
			return Liferay.Language.get('yes');
		case 'true,false':
			return (
				Liferay.Language.get('yes') + ', ' + Liferay.Language.get('no')
			);
		default:
			return value;
	}
}

function formatPlaceholder(filters, getFilterDisplayNameCallback) {
	return Object.entries(filters)
		.filter(([key]) => getFilterDisplayNameCallback(key))
		.map(
			([key, value]) =>
				getFilterDisplayNameCallback(key) +
				': ' +
				formatFilterValue(value)
		)
		.join(', ');
}

function formatSearchFilters(namespace = NAMESPACE) {
	const searchParams = new URLSearchParams(window.location.search);
	const searchFilters = {};

	// Suppress eslint false alarm for unused var
	/* eslint-disable no-unused-vars */

	// Project has no IE11 constraint, prefer to use for...of loop
	/* eslint-disable-next-line no-for-of-loops/no-for-of-loops */
	for (const [key, value] of searchParams.entries()) {
		if (validateParameterNames(key, namespace) && value) {
			searchFilters[key.replace(namespace, '')] = value;
		}
	}
	/* eslint-enable no-unused-vars */

	return searchFilters;
}

/**
 * This helper matches a search param name for account search and returns the
 * localized display name.
 * @param {string} name The filter name to be evaluated
 * @returns {string} New display value
 */
export function getAccountSearchFilterDisplayName(name) {
	let displayName;

	switch (name) {
		case 'activeSLAs':
			displayName = Liferay.Language.get('subscription-level');
			break;
		case 'code':
			displayName = Liferay.Language.get('code');
			break;
		case 'countryName':
			displayName = Liferay.Language.get('country');
			break;
		case 'createDateGT':
			displayName = Liferay.Language.get('created-after');
			break;
		case 'createDateLT':
			displayName = Liferay.Language.get('created-before');
			break;
		case 'createdByEmailAddress':
			displayName = Liferay.Language.get('created-by');
			break;
		case 'externalAccountKey':
			displayName = Liferay.Language.get('external-account-key');
			break;
		case 'flsTeamName':
			displayName = Liferay.Language.get('first-line-support');
			break;
		case 'internals':
			displayName = Liferay.Language.get('internal');
			break;
		case 'name':
			displayName = Liferay.Language.get('account-name');
			break;
		case 'modifiedDateGT':
			displayName = Liferay.Language.get('modified-after');
			break;
		case 'modifiedDateLT':
			displayName = Liferay.Language.get('modified-before');
			break;
		case 'notes':
			displayName = Liferay.Language.get('notes');
			break;
		case 'parentAccountName':
			displayName = Liferay.Language.get('parent-account');
			break;
		case 'partners':
			displayName = Liferay.Language.get('partner');
			break;
		case 'partnerTeamName':
			displayName = Liferay.Language.get('partner-reseller-si');
			break;
		case 'providesFLS':
			displayName = Liferay.Language.get('provides-fls');
			break;
		case 'receivesFLS':
			displayName = Liferay.Language.get('receives-fls');
			break;
		case 'regions':
			displayName = Liferay.Language.get('support-region');
			break;
		case 'salesInfo':
			displayName = Liferay.Language.get('sales-info');
			break;
		case 'subscriptionStates':
			displayName = Liferay.Language.get('subscription-state');
			break;
		case 'tiers':
			displayName = Liferay.Language.get('tier');
			break;
		case 'workerContactEmailAddress':
			displayName = Liferay.Language.get('project-worker');
			break;
		default:
			return;
	}

	return displayName;
}

/**
 * This helper matches a search param name for license search and returns the
 * localized display name.
 * @param {string} name The filter name to be evaluated
 * @returns {string} New display value
 */
export function getLicenseKeySearchFilterDisplayName(name) {
	let displayName;

	switch (name) {
		case 'accountKey':
			displayName = Liferay.Language.get('account-key');
			break;
		case 'accountName':
			displayName = Liferay.Language.get('account-name');
			break;
		case 'activeLicenses':
			displayName = Liferay.Language.get('active');
			break;
		case 'createDateGT':
			displayName = Liferay.Language.get('created-after');
			break;
		case 'createDateLT':
			displayName = Liferay.Language.get('created-before');
			break;
		case 'creatorEmailAddress':
			displayName = Liferay.Language.get('created-by');
			break;
		case 'expirationDateGT':
			displayName = Liferay.Language.get('expires-after');
			break;
		case 'expirationDateLT':
			displayName = Liferay.Language.get('expires-before');
			break;
		case 'hostName':
			displayName = Liferay.Language.get('host-name');
			break;
		case 'ipAddress':
			displayName = Liferay.Language.get('ip-address');
			break;
		case 'key':
			displayName = Liferay.Language.get('key');
			break;
		case 'macAddress':
			displayName = Liferay.Language.get('mac-address');
			break;
		case 'modifiedDateGT':
			displayName = Liferay.Language.get('modified-after');
			break;
		case 'modifiedDateLT':
			displayName = Liferay.Language.get('modified-before');
			break;
		case 'modifiedEmailAddress':
			displayName = Liferay.Language.get('last-edited-by');
			break;
		case 'owner':
			displayName = Liferay.Language.get('owner');
			break;
		case 'productPurchaseKey':
			displayName = Liferay.Language.get('product-purchase-key');
			break;
		case 'products':
			displayName = Liferay.Language.get('product');
			break;
		case 'productVersions':
			displayName = Liferay.Language.get('product-version');
			break;
		case 'serverId':
			displayName = Liferay.Language.get('server-id');
			break;
		case 'startDateGT':
			displayName = Liferay.Language.get('started-after');
			break;
		case 'startDateLT':
			displayName = Liferay.Language.get('started-before');
			break;
		case 'types':
			displayName = Liferay.Language.get('license-type');
			break;
		default:
			return;
	}

	return displayName;
}

/**
 * This helper looks for the provided search parameter and returns its value if
 * there is any.
 * @param {string} param The name of the search parameter
 * @returns {string} The value of the search parameter.
 */
export function getSearchParameter(param) {
	const searchParams = new URLSearchParams(window.location.search);

	return searchParams.has(param) ? searchParams.get(param) : '';
}

/**
 * Generates placeholder text in the search input based on the
 * search params and results conducted via the advanced search.
 * @param {string} defaultPlaceholder The default search placeholder.
 * @param {function} getFilterDisplayNameCallback The function that constructs
 * the placeholder text.
 * @param {string} namespace The namespace on the query params.
 * @param {function} searchFilterProcesser Custom function that allows for
 * additional processing for the formatted search filters.
 * @returns {string} The final placeholder text to be displayed.
 */
export function getSearchPlaceholder({
	defaultPlaceholder = Liferay.Language.get('search'),
	getFilterDisplayNameCallback,
	namespace = NAMESPACE,
	searchFilterProcesser
}) {
	const searchFilters = searchFilterProcesser
		? searchFilterProcesser(formatSearchFilters(namespace))
		: formatSearchFilters(namespace);

	if (formatPlaceholder(searchFilters, getFilterDisplayNameCallback)) {
		return formatPlaceholder(searchFilters, getFilterDisplayNameCallback);
	}
	else {
		return defaultPlaceholder;
	}
}

/**
 * Updates the aria attributes on the advanced search component when it's opened
 * or closed.
 * @param {string} id Identifier for the attribute
 * @param {bool} state Aria state to set
 */
export function setAdvancedSearchAriaAttributes(id, state) {
	const advancedSearchBtn = document.getElementById(id);

	if (advancedSearchBtn) {
		advancedSearchBtn.setAttribute('aria-expanded', state);

		const ariaLabel = state
			? Liferay.Language.get('close-advanced-search')
			: Liferay.Language.get('open-advanced-search');

		advancedSearchBtn.setAttribute('aria-label', ariaLabel);
	}
}

function validateParameterNames(name, namespace = NAMESPACE) {
	return (
		name.startsWith(namespace) &&
		!name.endsWith('advancedSearch') &&
		!name.endsWith('andOperator') &&
		!name.endsWith('cur') &&
		!name.endsWith('delta')
	);
}
