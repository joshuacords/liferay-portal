/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	var pluginName = 'a11yhelpbtn';

	CKEDITOR.plugins.add(pluginName, {
		init(editor) {
			var helpText = CKEDITOR.env.mac ? ' Option+0' : ' Alt+0';

			if (editor.ui.addButton) {
				editor.ui.addButton('A11YBtn', {
					command: 'a11yHelp',
					label: Liferay.Language.get('action.HELP') + helpText
				});
			}

			editor.on('uiSpace', event => {
				var toolbarHTML = event.data.html;

				var a11ybtnIndex = toolbarHTML.indexOf('cke_button__a11ybtn');

				if (a11ybtnIndex !== -1) {
					var a11ToolbarIndex = toolbarHTML.lastIndexOf(
						'class="cke_toolbar"',
						a11ybtnIndex
					);

					var toolbarText = toolbarHTML
						.substr(a11ToolbarIndex)
						.replace(
							'class="cke_toolbar cke_toolbar_last"',
							'class="cke_toolbar cke_toolbar_last cke_toolbar__a11yhelpbtn"'
						);

					event.data.html =
						toolbarHTML.substr(0, a11ToolbarIndex) + toolbarText;
				}
			});
		}
	});
})();
