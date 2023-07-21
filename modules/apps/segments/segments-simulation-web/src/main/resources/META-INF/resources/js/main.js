/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-portlet-segments-simulation',
	A => {
		var Lang = A.Lang;

		var SegmentsSimulation = A.Component.create({
			ATTRS: {
				deactivateSimulationUrl: {
					validator: Lang.isString
				},

				form: {
					validator: Lang.isObject
				},

				simulateSegmentsEntriesUrl: {
					validator: Lang.isString
				}
			},

			AUGMENTS: [Liferay.PortletBase],

			EXTENDS: A.Base,

			NAME: 'segmentsSimulation',

			prototype: {
				_bindUI() {
					var instance = this;

					instance._eventHandles = [];

					instance._eventHandles.push(
						Liferay.on(
							'SimulationMenu:closeSimulationPanel',
							A.bind('_deactivateSimulation', instance)
						),
						Liferay.on(
							'SimulationMenu:openSimulationPanel',
							A.bind('_simulateSegmentsEntries', instance)
						),
						A.on('beforeunload', () => {
							instance._deactivateSimulation();
						})
					);

					var form = instance.get('form');

					A.one('#' + form.id).delegate(
						'click',
						instance._simulateSegmentsEntries,
						'input',
						instance
					);
				},

				_deactivateSimulation() {
					var instance = this;

					var form = instance.get('form');

					Liferay.Util.fetch(
						instance.get('deactivateSimulationUrl'),
						{
							body: new FormData(form),
							method: 'POST'
						}
					).then(() => {
						A.all('#' + form.id + ' input').set('checked', false);
					});
				},

				_simulateSegmentsEntries() {
					var instance = this;

					Liferay.Util.fetch(
						instance.get('simulateSegmentsEntriesUrl'),
						{
							body: new FormData(instance.get('form')),
							method: 'POST'
						}
					).then(() => {
						const iframe = A.one('#simulationDeviceIframe');

						if (iframe) {
							const iframeWindow = A.Node.getDOMNode(
								iframe.get('contentWindow')
							);

							if (iframeWindow) {
								iframeWindow.location.reload();
							}
						}
					});
				},

				destructor() {
					var instance = this;

					new A.EventHandle(instance._eventHandles).detach();
				},

				initializer() {
					var instance = this;

					instance._bindUI();
				}
			}
		});

		Liferay.Portlet.SegmentsSimulation = SegmentsSimulation;
	},
	'',
	{
		requires: ['aui-base', 'liferay-portlet-base']
	}
);
