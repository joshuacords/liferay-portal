/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

Liferay.on = function() {};

Liferay.fire = function() {};

Liferay.detach = function() {};

(function(A, Liferay) {
	var CLICK_EVENTS = {};

	var DOC = A.config.doc;

	Liferay.provide(
		Liferay,
		'delegateClick',
		(id, fn) => {
			var el = DOC.getElementById(id);

			if (!el || el.id != id) {
				return;
			}

			var guid = A.one(el)
				.addClass('lfr-delegate-click')
				.guid();

			CLICK_EVENTS[guid] = fn;

			if (!Liferay._baseDelegateHandle) {
				Liferay._baseDelegateHandle = A.getBody().delegate(
					'click',
					Liferay._baseDelegate,
					'.lfr-delegate-click'
				);
			}
		},
		['aui-base']
	);

	Liferay._baseDelegate = function(event) {
		var id = event.currentTarget.attr('id');

		var fn = CLICK_EVENTS[id];

		if (fn) {
			fn.apply(this, arguments);
		}
	};

	Liferay._CLICK_EVENTS = CLICK_EVENTS;

	A.use('attribute', 'oop', A => {
		A.augment(Liferay, A.Attribute, true);
	});
})(AUI(), Liferay);
