/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-uad-export',
	A => {
		var Lang = A.Lang;

		var isString = Lang.isString;

		var RENDER_INTERVAL_IDLE = 60000;

		var RENDER_INTERVAL_IN_PROGRESS = 2000;

		var UADExport = A.Component.create({
			ATTRS: {
				exportProcessesNode: {
					setter: '_setNode'
				},

				exportProcessesResourceURL: {
					setter: 'isString'
				}
			},

			AUGMENTS: [Liferay.PortletBase],

			EXTENDS: A.Base,

			NAME: 'uadexport',

			prototype: {
				_isBackgroundTaskInProgress() {
					var instance = this;

					var exportProcessesNode = instance.get(
						'exportProcessesNode'
					);

					return !!exportProcessesNode.one(
						'.export-process-status-in-progress'
					);
				},

				_renderExportProcesses() {
					var instance = this;

					var exportProcessesNode = instance.get(
						'exportProcessesNode'
					);
					var exportProcessesResourceURL = instance.get(
						'exportProcessesResourceURL'
					);

					if (exportProcessesNode && exportProcessesResourceURL) {
						Liferay.Util.fetch(exportProcessesResourceURL)
							.then(response => {
								return response.text();
							})
							.then(response => {
								exportProcessesNode.plug(A.Plugin.ParseContent);

								exportProcessesNode.empty();

								exportProcessesNode.setContent(response);

								instance._scheduleRenderProcess();
							});
					}
				},

				_scheduleRenderProcess() {
					var instance = this;

					var renderInterval = RENDER_INTERVAL_IDLE;

					if (instance._isBackgroundTaskInProgress()) {
						renderInterval = RENDER_INTERVAL_IN_PROGRESS;
					}

					instance._renderTimer = A.later(
						renderInterval,
						instance,
						instance._renderExportProcesses
					);
				},

				_setNode(val) {
					var instance = this;

					if (isString(val)) {
						val = instance.one(val);
					}
					else {
						val = A.one(val);
					}

					return val;
				},

				destructor() {
					var instance = this;

					if (instance._renderTimer) {
						instance._renderTimer.cancel();
					}
				},

				initializer() {
					var instance = this;

					instance._renderTimer = A.later(
						RENDER_INTERVAL_IN_PROGRESS,
						instance,
						instance._renderExportProcesses
					);

					Liferay.once(
						'beforeNavigate',
						instance.destroy.bind(instance)
					);
				}
			}
		});

		Liferay.UADExport = UADExport;
	},
	'',
	{
		requires: ['aui-parse-content', 'liferay-portlet-base']
	}
);
