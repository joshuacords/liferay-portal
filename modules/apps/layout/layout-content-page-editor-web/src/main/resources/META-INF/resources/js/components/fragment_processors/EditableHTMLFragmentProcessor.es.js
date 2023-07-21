/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openToast} from 'frontend-js-web';

import {getFloatingToolbarButtons} from './EditableRichTextFragmentProcessor.es';

let _changedCallback = null;
let _destroyedCallback = null;
let _dialog;
let _editableElement;
let _editor;

/**
 * Destroys, if any, an existing instance of LiferayFullScreenSourceEditor.
 */

function destroy() {
	if (_dialog) {
		_editableElement.removeAttribute('style');

		_dialog.destroy();
		_editor.destroy();

		_dialog = null;
		_editableElement = null;
		_editor = null;

		_destroyedCallback();
		_destroyedCallback = null;

		_changedCallback = null;
	}
}

/**
 * Creates an instance of LiferayFullScreenSourceEditor and destroys the existing one if any.
 * @param {HTMLElement} editableElement
 * @param {string} fragmentEntryLinkId
 * @param {string} portletNamespace
 * @param {Object} options
 * @param {function} changedCallback
 * @param {function} destroyedCallback
 * @param {Event} event
 * @param {string} type
 * @param {string} initialValue
 */
function init(
	editableElement,
	fragmentEntryLinkId,
	portletNamespace,
	options,
	changedCallback,
	destroyedCallback,
	event,
	type,
	initialValue
) {
	const _destroy = destroy;

	_editableElement = editableElement;

	_changedCallback = changedCallback;
	_destroyedCallback = destroyedCallback;

	Liferay.Util.openWindow(
		{
			dialog: {
				after: {
					destroy() {
						_destroy();
					}
				},
				constrain: true,
				cssClass:
					'lfr-fulscreen-source-editor-dialog modal-full-screen',
				destroyOnHide: true,
				modal: true,
				'toolbars.footer': [
					{
						label: Liferay.Language.get('cancel'),
						on: {
							click() {
								_dialog.hide();
							}
						}
					},
					{
						cssClass: 'btn-primary',
						label: Liferay.Language.get('save'),
						on: {
							click() {
								const annotations = _editor._editor
									.getSession()
									.getAnnotations();

								const errorAnnotations = annotations.filter(
									annotation =>
										annotation.type === 'error' &&
										annotation.text !==
											'Named entity expected. Got none.'
								);

								if (errorAnnotations.length) {
									const errorMessage = errorAnnotations
										.map(annotation => annotation.text)
										.join('\n');

									openToast({
										message: errorMessage,
										title: Liferay.Language.get('error'),
										type: 'danger'
									});
								}
								else {
									_changedCallback(_editor.get('value'));
									_dialog.hide();
								}
							}
						}
					}
				]
			},

			title: Liferay.Language.get('edit-content')
		},
		dialog => {
			_dialog = dialog;

			Liferay.Util.getTop()
				.AUI()
				.use('liferay-fullscreen-source-editor', A => {
					_editor = new A.LiferayFullScreenSourceEditor({
						boundingBox: dialog
							.getStdModNode(A.WidgetStdMod.BODY)
							.appendChild('<div></div>'),
						previewCssClass: 'alloy-editor',
						value: initialValue || editableElement.innerHTML
					}).render();
				});
		}
	);
}

/**
 * @param {string} content editableField's original HTML
 * @param {string} value Translated/segmented value
 * @return {string} Transformed content
 */
function render(content, value) {
	return value;
}

export default {
	destroy,
	getFloatingToolbarButtons,
	init,
	render
};
