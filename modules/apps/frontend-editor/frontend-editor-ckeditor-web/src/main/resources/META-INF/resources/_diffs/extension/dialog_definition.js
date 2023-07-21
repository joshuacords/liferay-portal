/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

CKEDITOR.on('dialogDefinition', event => {
	if (event.editor === ckEditor) {
		var boundingWindow = event.editor.window;

		var dialogDefinition = event.data.definition;

		var dialog = event.data.dialog;

		var onShow = dialogDefinition.onShow;

		dialogDefinition.onShow = function() {
			if (typeof onShow === 'function') {
				onShow.apply(this, arguments);
			}

			centerDialog();
		};

		var centerDialog = function() {
			var dialogSize = dialog.getSize();

			var x = window.innerWidth / 2 - dialogSize.width / 2;
			var y = window.innerHeight / 2 - dialogSize.height / 2;

			dialog.move(x, y, false);
		};

		AUI().use('aui-debounce', A => {
			boundingWindow.on(
				'resize',
				A.debounce(() => {
					centerDialog();
				}, 250)
			);
		});

		var clearEventHandler = function() {
			Liferay.detach('resize', boundingWindow);
		};

		Liferay.once('destroyPortlet', clearEventHandler);
	}
});
