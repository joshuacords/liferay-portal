/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openImageSelector} from '../../utils/FragmentsEditorDialogUtils';
import {FLOATING_TOOLBAR_BUTTONS} from '../../utils/constants';

/**
 * Handle item selector image changes and propagate them with an
 * "editableChanged" event.
 * @param {object} image
 * @param {HTMLElement} editableElement
 * @param {string} fragmentEntryLinkId
 * @param {function} changedCallback
 * @private
 */
function _handleImageEditorChange(
	image,
	editableElement,
	fragmentEntryLinkId,
	changedCallback
) {
	const imageElement = editableElement.querySelector('img');

	if (imageElement && image.url) {
		imageElement.src = image.url;

		changedCallback(image);
	}
}

/**
 * Do nothing, as LiferayItemSelectorDialog is automatically
 * destroyed on hide.
 * @review
 */
function destroy() {}

/**
 * @param {object} editableValues
 * @return {object[]} Floating toolbar panels
 */
function getFloatingToolbarButtons(editableValues) {
	const buttons = [];

	const linkButton = {...FLOATING_TOOLBAR_BUTTONS.link};

	if (
		editableValues.config &&
		(editableValues.config.fieldId ||
			editableValues.config.href ||
			editableValues.config.mappedField)
	) {
		linkButton.cssClass =
			'fragments-editor__floating-toolbar--linked-field';
	}

	buttons.push(linkButton);

	if (!editableValues.mappedField && !editableValues.fieldId) {
		buttons.push(FLOATING_TOOLBAR_BUTTONS.imageProperties);
	}

	const mapButton = {...FLOATING_TOOLBAR_BUTTONS.map};

	if (editableValues.fieldId || editableValues.mappedField) {
		mapButton.cssClass = 'fragments-editor__floating-toolbar--mapped-field';
	}

	buttons.push(mapButton);

	return buttons;
}

/**
 * Show the image selector dialog and calls the given callback when an
 * image is selected.
 * @param {HTMLElement} editableElement
 * @param {string} fragmentEntryLinkId
 * @param {string} portletNamespace
 * @param {{imageSelectorURL: string}} options
 * @param {function} changedCallback
 * @param {function} destroyedCallback
 * @review
 */
function init(
	editableElement,
	fragmentEntryLinkId,
	portletNamespace,
	options,
	changedCallback,
	destroyedCallback
) {
	const {imageSelectorURL} = options;

	openImageSelector({
		callback: image => {
			_handleImageEditorChange(
				image,
				editableElement,
				fragmentEntryLinkId,
				changedCallback
			);
		},
		destroyedCallback,
		imageSelectorURL,
		portletNamespace
	});
}

/**
 * @param {string} content editableField's original HTML
 * @param {object} value Translated/segmented value
 * @param {object} editableValues values of the element
 * @return {string} Transformed content
 */
function render(content, value, editableValues) {
	const wrapper = document.createElement('div');

	const config = (editableValues && editableValues.config) || {};

	wrapper.innerHTML = content;

	const image = wrapper.querySelector('img');

	if (image) {
		const imageValue =
			value && typeof value !== 'string' ? value.url : value;

		if (imageValue) {
			image.src = imageValue;
		}

		if (config.alt) {
			image.alt = config.alt;
		}
	}

	if (editableValues && editableValues.config && editableValues.config.href) {
		const link = document.createElement('a');
		const {config} = editableValues;

		link.href = config.href;

		if (config.target) {
			link.target = config.target;
		}

		link.appendChild(image);

		return link.outerHTML;
	}
	else {
		return wrapper.innerHTML;
	}
}

export default {
	destroy,
	getFloatingToolbarButtons,
	init,
	render
};
