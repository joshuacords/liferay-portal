/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Mocks the
 * Liferay.Language.get() method and returns the value for a given language key.
 * Liferay.Service methods and returns a Promise of an array.
 */
window.Liferay = {
	Language: {
		get: key => key
	}
};

/**
 * Mocks the portlet constants.
 */
window.ProvisioningConstants = {
	accountsPortletNamespace:
		'_com_liferay_osb_provisioning_web_portlet_AccountsPortlet_',
	contactRole: {
		administrator: 'Administrator'
	},
	licenseType: {
		cluster: 'cluster',
		developer: 'developer',
		developerCluster: 'developer_cluster',
		noServerIdTypes: [
			'developer',
			'developer_cluster',
			'elastic',
			'enterprise',
			'oem',
			'virtual_cluster'
		],
		production: 'production',
		restrictedExpirationDateTypes: [
			'enterprise',
			'limited',
			'oem',
			'virtual_cluster'
		],
		virtualCluster: 'virtual_cluster'
	},
	namespace: 'namespace',
	noteFormat: {
		html: 'HTML',
		plaintext: 'plain'
	},
	noteStatus: {
		approved: 'Approved',
		archived: 'Archived'
	},
	noteType: {
		general: 'General',
		sales: 'Sales'
	},
	productId: {
		commerce: 'commerce-id',
		portal: 'Portal'
	},
	productPurchaseStatus: {
		approved: 'Approved',
		cancelled: 'Cancelled'
	}
};

/**
 * Mocks the form submission behavior
 */
HTMLFormElement.prototype.submit = jest.fn();
