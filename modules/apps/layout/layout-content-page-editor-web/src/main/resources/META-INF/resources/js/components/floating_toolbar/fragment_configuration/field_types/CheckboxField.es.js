/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './CheckboxFieldDelegateTemplate.soy';
import templates from './CheckboxField.soy';

/**
 * CheckboxField
 */
class CheckboxField extends Component {
	/**
	 * Handle Checkbox Value Change
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleCheckboxValueChanged(event) {
		const targetElement = event.delegateTarget;

		this.emit('fieldValueChanged', {
			name: this.field.name,
			value: targetElement.checked
		});
	}
}

CheckboxField.STATE = {
	/**
	 * The configuration field
	 * @review
	 * @type {object}
	 */
	field: Config.shapeOf({
		dataType: Config.string(),
		defaultValue: Config.bool(),
		description: Config.string(),
		label: Config.string(),
		name: Config.string(),
		type: Config.string(),
		typeOptions: Config.object()
	})
};

Soy.register(CheckboxField, templates);

export {CheckboxField};
export default CheckboxField;
