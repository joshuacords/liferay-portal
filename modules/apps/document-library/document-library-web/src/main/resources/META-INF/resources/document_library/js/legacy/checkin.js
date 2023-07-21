/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'document-library-checkin',
	A => {
		var DocumentLibraryCheckin = {
			showDialog(namespace, callback) {
				var contentId = namespace + 'versionDetails';
				var versionDetailsDialog = Liferay.Util.Window.getWindow({
					dialog: {
						bodyContent: A.one('#' + contentId).html(),
						destroyOnHide: true,
						height: 400,
						'toolbars.footer': [
							{
								cssClass: 'btn-link',
								label: Liferay.Language.get('cancel'),
								on: {
									click() {
										Liferay.Util.getWindow(
											contentId + 'Dialog'
										).destroy();
									}
								}
							},
							{
								cssClass: 'btn-primary',
								label: Liferay.Language.get('save'),
								on: {
									click() {
										var versionIncrease = false;
										var versionIncreaseElement = document.querySelector(
											"input[name='" +
												namespace +
												"versionDetailsVersionIncrease']:checked"
										);

										if (versionIncreaseElement) {
											versionIncrease =
												versionIncreaseElement.value;
										}

										var changeLog = '';
										var changeLogElement = document.getElementById(
											namespace +
												'versionDetailsChangeLog'
										);

										if (changeLogElement) {
											changeLog = changeLogElement.value;
										}

										callback(versionIncrease, changeLog);
									}
								}
							}
						],
						width: 700
					},
					dialogIframe: {
						bodyCssClass: 'dialog-with-footer'
					},
					id: contentId + 'Dialog',
					title: Liferay.Language.get('describe-your-changes')
				});

				versionDetailsDialog.render();
			}
		};

		Liferay.DocumentLibraryCheckin = DocumentLibraryCheckin;
	},
	'',
	{
		requires: ['liferay-util-window']
	}
);
