/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {FLOATING_TOOLBAR_BUTTONS} from '../../utils/constants';
import {destroy, init} from './EditableRichTextFragmentProcessor.es';

/**
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

	const editButton = {...FLOATING_TOOLBAR_BUTTONS.edit};

	if (editableValues.mappedField || editableValues.fieldId) {
		editButton.cssClass =
			'fragments-editor__floating-toolbar--mapped-field disabled fragments-editor__floating-toolbar--disabled';
	}

	buttons.push(editButton);

	const mapButton = {...FLOATING_TOOLBAR_BUTTONS.map};

	if (editableValues.fieldId || editableValues.mappedField) {
		mapButton.cssClass = 'fragments-editor__floating-toolbar--mapped-field';
	}

	buttons.push(mapButton);

	return buttons;
}

/**
 * @param {string} content editableField's original HTML
 * @param {string} value Translated/segmented value
 * @param {object} editableValues values of the element
 * @return {string} Transformed content
 */
function render(content, value, editableValues) {
	const wrapper = document.createElement('div');

	wrapper.innerHTML = content;

	const config = (editableValues && editableValues.config) || {};
	const link = wrapper.querySelector('a');

	if (link) {
		link.innerHTML = value;

		if (config.href) {
			link.href = config.href;
		}

		if (config.target) {
			link.target = config.target;
		}

		if (config.buttonType) {
			Array.from(link.classList).forEach(elementClass => {
				if (
					elementClass.indexOf('btn-') === 0 ||
					elementClass === 'btn'
				) {
					link.classList.remove(elementClass);
				}
			});

			if (config.buttonType && config.buttonType === 'link') {
				link.classList.add('link');
			}
			else {
				link.classList.add('btn');
				link.classList.add(`btn-${config.buttonType}`);
			}
		}
	}

	return wrapper.innerHTML;
}

export default {
	destroy,
	getFloatingToolbarButtons,
	init,
	render
};
