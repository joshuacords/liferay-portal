/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './FloatingToolbarColorPicker.soy';

/**
 * FloatingToolbarColorPicker
 */
class FloatingToolbarColorPicker extends Component {
	/**
	 * Continues the propagation of the color button clicked event
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleColorButtonClick(event) {
		this.emit('colorClicked', {
			color: event.delegateTarget.dataset.backgroundColorCssClass
		});
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FloatingToolbarColorPicker.STATE = {
	/**
	 * Available colors
	 * @instance
	 * @memberof FloatingToolbarColorPicker
	 * @review
	 * @type {Array}
	 */
	colors: Config.array().required(),

	/**
	 * Selected color
	 * @instance
	 * @memberof FloatingToolbarColorPicker
	 * @review
	 * @type {string}
	 */
	selectedColor: Config.string(),

	/**
	 * Show clear button or not
	 * @instance
	 * @memberof FloatingToolbarColorPicker
	 * @review
	 * @type {boolean}
	 */
	showClearButton: Config.bool(false)
};

Soy.register(FloatingToolbarColorPicker, templates);

export {FloatingToolbarColorPicker};
export default FloatingToolbarColorPicker;
