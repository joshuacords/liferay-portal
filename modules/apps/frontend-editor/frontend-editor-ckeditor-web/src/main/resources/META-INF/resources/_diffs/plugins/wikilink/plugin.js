/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

CKEDITOR.plugins.add('wikilink', {
	init(editor) {
		var instance = this;

		editor.addCommand('link', new CKEDITOR.dialogCommand('link'));
		editor.addCommand('unlink', new CKEDITOR.unlinkCommand());

		editor.ui.addButton('Link', {
			command: 'link',
			label: editor.lang.link.toolbar
		});

		editor.ui.addButton('Unlink', {
			command: 'unlink',
			label: editor.lang.link.unlink
		});

		CKEDITOR.dialog.add('link', instance.path + 'dialogs/link.js');

		editor.on('selectionChange', event => {
			// document.queryCommandEnabled does not work for this in Firefox.
			// Use element paths to detect the state.

			var command = editor.getCommand('unlink');

			var commandState = CKEDITOR.TRISTATE_DISABLED;

			var lastElement = event.data.path.lastElement;

			if (lastElement) {
				var element = lastElement.getAscendant('a', true);

				if (
					element &&
					element.getName() == 'a' &&
					element.getAttribute('href')
				) {
					commandState = CKEDITOR.TRISTATE_OFF;
				}
			}

			command.setState(commandState);
		});

		editor.on('doubleclick', event => {
			var element =
				CKEDITOR.plugins.link.getSelectedLink(editor) ||
				event.data.element;

			if (!element.isReadOnly() && element.is('a')) {
				event.data.dialog = 'link';
			}
		});

		if (editor.addMenuItems) {
			editor.addMenuItems({
				link: {
					command: 'link',
					group: 'link',
					label: editor.lang.link.menu,
					order: 1
				},
				unlink: {
					command: 'unlink',
					group: 'link',
					label: editor.lang.link.unlink,
					order: 5
				}
			});
		}

		if (editor.contextMenu) {
			editor.contextMenu.addListener(element => {
				var selectionObj = null;

				if (element && !element.isReadOnly()) {
					element = CKEDITOR.plugins.link.getSelectedLink(editor);

					if (element) {
						selectionObj = {
							link: CKEDITOR.TRISTATE_OFF,
							unlink: CKEDITOR.TRISTATE_OFF
						};
					}
				}

				return selectionObj;
			});
		}
	}
});

CKEDITOR.plugins.link = {
	getSelectedLink(editor) {
		var selectedLink = null;

		try {
			var selection = editor.getSelection();

			if (selection.getType() == CKEDITOR.SELECTION_ELEMENT) {
				var selectedElement = selection.getSelectedElement();

				if (selectedElement.is('a')) {
					selectedLink = selectedElement;
				}
			}
			else {
				var range = selection.getRanges(true)[0];

				range.shrink(CKEDITOR.SHRINK_TEXT);

				var root = range.getCommonAncestor();

				selectedLink = root.getAscendant('a', true);
			}
		}
		catch (e) {}

		return selectedLink;
	}
};

CKEDITOR.unlinkCommand = function() {};

CKEDITOR.unlinkCommand.prototype = {
	exec(editor) {
		var selection = editor.getSelection();

		var bookmarks = selection.createBookmarks();
		var ranges = selection.getRanges();

		var length = ranges.length;

		for (var i = 0; i < length; i++) {
			var rangeRoot = ranges[i].getCommonAncestor(true);

			var element = rangeRoot.getAscendant('a', true);

			if (!element) {
				continue;
			}

			ranges[i].selectNodeContents(element);
		}

		selection.selectRanges(ranges);
		editor.document.$.execCommand('unlink', false, null);
		selection.selectBookmarks(bookmarks);
	},

	startDisabled: true
};
