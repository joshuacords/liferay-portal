/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-staging',
	A => {
		var StagingBar = {
			init(config) {
				var instance = this;

				var namespace = config.namespace;

				instance.markAsReadyForPublicationURL =
					config.markAsReadyForPublicationURL;

				instance.layoutRevisionStatusURL =
					config.layoutRevisionStatusURL;

				instance._namespace = namespace;

				instance.viewHistoryURL = config.viewHistoryURL;

				Liferay.publish({
					fireOnce: true
				});

				Liferay.after('initStagingBar', () => {
					var body = A.getBody();

					if (body.hasClass('has-staging-bar')) {
						var stagingLevel3 = A.one(
							'.staging-bar-level-3-message'
						);

						body.addClass(
							stagingLevel3 === null
								? 'staging-ready'
								: 'staging-ready-level-3'
						);
					}
				});

				Liferay.fire('initStagingBar', config);
			}
		};

		Liferay.StagingBar = StagingBar;
	},
	'',
	{
		requires: ['aui-io-plugin-deprecated', 'aui-modal']
	}
);
