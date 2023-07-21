/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openImageSelector} from '../../utils/FragmentsEditorDialogUtils';
import {FLOATING_TOOLBAR_BUTTONS} from '../../utils/constants';

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

	const backgroundImageButton = {
		...FLOATING_TOOLBAR_BUTTONS.fragmentBackgroundImage
	};
	const mapButton = {...FLOATING_TOOLBAR_BUTTONS.map};

	if (editableValues.mappedField || editableValues.fieldId) {
		backgroundImageButton.cssClass =
			'disabled fragments-editor__floating-toolbar--disabled fragments-editor__floating-toolbar--mapped-field';
		mapButton.cssClass = 'fragments-editor__floating-toolbar--mapped-field';
	}

	buttons.push(backgroundImageButton, mapButton);

	return buttons;
}

/**
 * Show the image selector dialog and calls the given callback when an
 * image is selected.
 * @param {function} callback
 * @param {string} imageSelectorURL
 * @param {string} portletNamespace
 * @review
 */
function init(callback, imageSelectorURL, portletNamespace) {
	openImageSelector({
		callback,
		imageSelectorURL,
		portletNamespace
	});
}

/**
 * @param {HTMLElement} element
 * @param {object} [backgroundImage={}]
 */
function render(element, backgroundImage = '') {
	element.style.backgroundImage = backgroundImage
		? `url("${
				backgroundImage.url ? backgroundImage.url : backgroundImage
		  }")`
		: '';
	element.style.backgroundSize = 'cover';
}

export default {
	destroy,
	getFloatingToolbarButtons,
	init,
	render
};
