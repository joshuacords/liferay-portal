/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
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
