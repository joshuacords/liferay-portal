/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export function showNotification(
	message,
	type = 'success',
	closeable = true,
	duration = 500
) {
	if (!window.AUI) {
		return;
	}

	AUI().use('liferay-notification', () => {
		new Liferay.Notification({
			closeable,
			delay: {
				hide: 5000,
				show: 0
			},
			duration,
			message,
			render: true,
			title: Liferay.Language.get(type),
			type
		});
	});
}

export function showErrorNotification(
	e = Liferay.Language.get('unexpected-error')
) {
	showNotification(e, 'danger');
}
