/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import core from 'metal';
import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './ColorPickerInput.soy';

/**
 * ColorPickerInput
 *
 * This class creates an input with a ColorPickerPopover binded to it.
 */

class ColorPickerInput extends Component {
	/**
	 * @inheritDoc
	 */

	rendered() {
		const instance = this;

		AUI().use('aui-color-picker-popover', A => {
			instance.colorPickerPopover = new A.ColorPickerPopover({
				color: instance.color,
				constrain: true,
				trigger: '#' + instance.id,
				zIndex: Liferay.zIndex.POPOVER
			});

			instance.colorPickerPopover.render(instance.element);
			instance.colorPickerPopover.after(
				'select',
				instance.setColor_,
				instance
			);
		});
	}

	/**
	 * Sets the selected color
	 *
	 * @param  {Event} event
	 */

	setColor_(event) {
		this.color = event.color;
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

ColorPickerInput.STATE = {
	/**
	 * Current selected color
	 * @type {String}
	 */

	color: {
		validator: core.isString
	},

	/**
	 * Input disabled state
	 * @type {Boolean}
	 */

	disabled: {
		validator: core.isBoolean,
		value: false
	},

	/**
	 * Id for the input
	 * @type {String}
	 */

	id: {
		validator: core.isString
	},

	/**
	 * Input css classes
	 * @type {String}
	 */

	inputClasses: {
		validator: core.isString
	},

	/**
	 * Label for the input
	 * @type {String}
	 */

	label: {
		validator: core.isString
	},

	/**
	 * Name for the input
	 * @type {String}
	 */

	name: {
		validator: core.isString
	}
};

Soy.register(ColorPickerInput, templates);

export default ColorPickerInput;
