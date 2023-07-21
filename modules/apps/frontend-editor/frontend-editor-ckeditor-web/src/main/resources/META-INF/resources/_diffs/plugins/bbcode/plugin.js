/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

(function() {
	var commandObject = {
		context: 'pre',

		exec(editor) {
			editor.focus();
			editor.fire('saveSnapshot');

			var elementPath = new CKEDITOR.dom.elementPath(
				editor.getSelection().getStartElement()
			);

			var elementAction = 'apply';

			var preElement = new CKEDITOR.style({
				element: 'pre'
			});

			preElement._.enterMode = editor.config.enterMode;

			if (preElement.checkActive(elementPath)) {
				elementAction = 'remove';
			}

			preElement[elementAction](editor.document);

			var preBlock = editor.document.findOne('pre');

			if (preBlock && preBlock.getChildCount() === 0) {
				preBlock.appendBogus();
			}

			setTimeout(() => {
				editor.fire('saveSnapshot');
			}, 0);
		},

		refresh(editor, path) {
			var firstBlock = path.block || path.blockLimit;

			var buttonState = CKEDITOR.TRISTATE_OFF;

			var element = editor.elementPath(firstBlock);

			if (element.contains('pre', 1)) {
				buttonState = CKEDITOR.TRISTATE_ON;
			}

			this.setState(buttonState);
		}
	};

	CKEDITOR.plugins.add('bbcode', {
		init(editor) {
			var instance = this;

			var path = instance.path;

			var dependencies = [
				CKEDITOR.getUrl(path + 'bbcode_data_processor.js'),
				CKEDITOR.getUrl(path + 'bbcode_parser.js')
			];

			CKEDITOR.scriptLoader.load(dependencies, () => {
				var bbcodeDataProcessor = CKEDITOR.plugins.get(
					'bbcode_data_processor'
				);

				bbcodeDataProcessor.init(editor);
			});

			editor.addCommand('bbcode', commandObject);
		}
	});
})();
