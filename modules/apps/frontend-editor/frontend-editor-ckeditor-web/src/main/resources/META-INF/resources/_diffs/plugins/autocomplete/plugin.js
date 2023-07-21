/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	CKEDITOR.plugins.add('autocomplete', {
		init(editor) {
			var instance = this;

			AUI().use('aui-debounce', 'liferay-autocomplete-input', A => {
				var path = instance.path;

				var dependencies = [CKEDITOR.getUrl(path + 'autocomplete.js')];

				CKEDITOR.scriptLoader.load(dependencies, () => {
					var liferayAutoCompleteCKEditor = new Liferay.AutoCompleteCKEditor(
						A.merge(editor.config.autocomplete, {
							editor,
							width: 300
						})
					);

					liferayAutoCompleteCKEditor.render();

					liferayAutoCompleteCKEditor.detach('valueChange');
				});
			});
		}
	});
})();
