/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

window.Liferay = window.Liferay || {};

Liferay.Widget = function(options) {
	options = options || {};

	var height = options.height || '100%';
	var width = options.width || '100%';

	var id =
		options.id ||
		'_Liferay_widget' + Math.ceil(Math.random() * new Date().getTime());
	var url =
		options.url ||
		'http://www.liferay.com/widget/web/guest/community/forums/-/message_boards';

	var html =
		'<iframe frameborder="0" height="' +
		height +
		'" id="' +
		id +
		'" src="' +
		url +
		'" width="' +
		width +
		'"></iframe>';

	document.write(html);
};
