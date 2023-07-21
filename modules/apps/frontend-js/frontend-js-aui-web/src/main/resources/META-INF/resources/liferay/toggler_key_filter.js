/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-toggler-key-filter',
	A => {
		var KeyMap = A.Event.KeyMap;

		var NAME = 'togglerkeyfilter';

		var TogglerKeyFilter = A.Component.create({
			ATTRS: {
				filter: {
					validator: Array.isArray,
					value: [
						KeyMap.ESC,
						KeyMap.LEFT,
						KeyMap.NUM_MINUS,
						KeyMap.NUM_PLUS,
						KeyMap.RIGHT,
						KeyMap.SPACE
					]
				}
			},

			EXTENDS: A.Plugin.Base,

			NAME,

			NS: NAME,

			prototype: {
				_headerEventHandler(event) {
					var instance = this;

					var validAction = event.type === instance._toggleEvent;

					if (!validAction) {
						validAction =
							instance.get('filter').indexOf(event.keyCode) > -1;
					}

					var retVal;

					if (!validAction) {
						retVal = new A.Do.Prevent();
					}

					return retVal;
				},

				initializer() {
					var instance = this;

					instance._toggleEvent = instance
						.get('host')
						.get('toggleEvent');

					instance.beforeHostMethod(
						'headerEventHandler',
						instance._headerEventHandler,
						instance
					);
				}
			}
		});

		Liferay.TogglerKeyFilter = TogglerKeyFilter;
	},
	'',
	{
		requires: ['aui-event-base']
	}
);
