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

import {formatDate} from './date';

export const BINARY_SELECTION = [
	{
		label: Liferay.Language.get('yes'),
		value: true
	},
	{
		label: Liferay.Language.get('no'),
		value: false
	}
];

export const DASH = '-';

// When a ISO 8601 format date string is passed into new Date(), it's treated as if it's starting at UTC midnight of the given date
export const CURRENT_TIME = new Date(formatDate(new Date()));

// Namespaces

export const ACCOUNTS_PORTLET_NAMESPACE =
	window.ProvisioningConstants.accountsPortletNamespace;
export const NAMESPACE = window.ProvisioningConstants.namespace;

// Regex

export const PATTERN_IP_ADDRESS_V4 = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
export const PATTERN_IP_ADDRESS_V6 = /^(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4})*::(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4})*|(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){7}$/;
export const PATTERN_MAC_ADDRESS = /^([0-9A-Fa-f]{2}[. :-]){5}([0-9A-Fa-f]{2})|([0-9A-Fa-f]{4}[. :-][0-9A-Fa-f]{4}[. :-][0-9A-Fa-f]{4})$/;

// Contact Role

export const CONTACT_ROLE_ADMINISTRATOR =
	window.ProvisioningConstants.contactRole.administrator;

// Inline edit field types

export const FIELD_SIZE_DEFAULT = 'default';
export const FIELD_SIZE_SMALL = 'small';

export const FIELD_TYPE_EXTERNAL = 'external';
export const FIELD_TYPE_NONEDITABLE = 'noneditable';
export const FIELD_TYPE_SELECT = 'select';
export const FIELD_TYPE_TEXT = 'text';
export const FIELD_TYPE_TEXTAREA = 'textarea';
export const FIELD_TYPE_TOGGLE = 'toggle';

// License fields maximum length

export const NEW_LICENSE_DESCRIPTION_MAX_LENGTH = 255;
export const NEW_LICENSE_NAME_OWNER_MAX_LENGTH = 75;

// License types

export const LICENSE_TYPE_CLUSTER =
	window.ProvisioningConstants.licenseType.cluster;
export const LICENSE_TYPE_DEVELOPER =
	window.ProvisioningConstants.licenseType.developer;
export const LICENSE_TYPE_DEVELOPER_CLUSTER =
	window.ProvisioningConstants.licenseType.developerCluster;
export const LICENSE_TYPE_PRODUCTION =
	window.ProvisioningConstants.licenseType.production;
export const LICENSE_TYPE_VIRTUAL_CLUSTER =
	window.ProvisioningConstants.licenseType.virtualCluster;
export const NO_SERVER_ID_LICENSE_TYPES =
	window.ProvisioningConstants.licenseType.noServerIdTypes;
export const RESTRICTED_EXPIRATION_DATE_TYPES =
	window.ProvisioningConstants.licenseType.restrictedExpirationDateTypes;

// Note actions

export const ADD_NOTE = 'addNote';
export const ARCHIVE_NOTE = 'archiveNote';
export const EDIT_NOTE = 'editNote';
export const PIN_NOTE = 'pinNote';

// Note properties

export const NOTE_FORMAT_HTML = window.ProvisioningConstants.noteFormat.html;
export const NOTE_FORMAT_PLAIN =
	window.ProvisioningConstants.noteFormat.plaintext;
export const NOTE_PRIORITY_PINNED = 1;
export const NOTE_PRIORITY_UNPINNED = 2;
export const NOTE_STATUS_APPROVED =
	window.ProvisioningConstants.noteStatus.approved;
export const NOTE_STATUS_ARCHIVED =
	window.ProvisioningConstants.noteStatus.archived;
export const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;
export const NOTE_TYPE_SALES = window.ProvisioningConstants.noteType.sales;

// Product purchase actions

export const ADD_SUBSCRIPTIONS = 'add';
export const EDIT_SUBSCRIPTIONS = 'edit';

// Product purchase statuses

export const PRODUCT_PURCHASE_STATUS_APPROVED =
	window.ProvisioningConstants.productPurchaseStatus.approved;
export const PRODUCT_PURCHASE_STATUS_CANCELLED =
	window.ProvisioningConstants.productPurchaseStatus.cancelled;
