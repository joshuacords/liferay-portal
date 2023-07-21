/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI().ready('liferay-sign-in-modal', A => {
	var BODY = A.getBody();

	var signIn = A.one('a.sign-in');

	if (signIn && signIn.getData('redirect') !== 'true') {
		signIn.plug(Liferay.SignInModal);
	}

	var fullScreenToggleIcon = A.one(
		'.fjord-header-fullscreen .navbar-toggler'
	);

	if (fullScreenToggleIcon) {
		fullScreenToggleIcon.on('click', event => {
			BODY.toggleClass(
				'overflow-hidden',
				event.currentTarget.hasClass('collapsed')
			);
		});
	}
});
