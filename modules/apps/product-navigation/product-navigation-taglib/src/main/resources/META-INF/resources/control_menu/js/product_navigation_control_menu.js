/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-product-navigation-control-menu',
	A => {
		var ControlMenu = {
			init(containerId) {
				var instance = this;

				var controlMenu = A.one(containerId);

				if (controlMenu) {
					var namespace = controlMenu.attr('data-namespace');

					instance._namespace = namespace;

					Liferay.Util.toggleControls(controlMenu);

					var eventHandle = controlMenu.on(
						['focus', 'mousemove', 'touchstart'],
						() => {
							Liferay.fire('initLayout');

							eventHandle.detach();
						}
					);
				}
			}
		};

		Liferay.ControlMenu = ControlMenu;
	},
	'',
	{
		requires: [
			'aui-node',
			'aui-overlay-mask-deprecated',
			'event-move',
			'event-touch',
			'liferay-menu-toggle'
		]
	}
);
