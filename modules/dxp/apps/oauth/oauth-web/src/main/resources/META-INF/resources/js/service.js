/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

Liferay.Service.register(
	'Liferay.Service.OAuth',
	'com.liferay.oauth.service',
	'oauth-portlet'
);

Liferay.Service.registerClass(Liferay.Service.OAuth, 'OAuthApplication', {
	addOAuthApplication: true,
	deleteLogo: true,
	deleteOAuthApplication: true,
	updateOAuthApplication: true
});

Liferay.Service.registerClass(Liferay.Service.OAuth, 'OAuthUser', {
	deleteOAuthUser: true
});
