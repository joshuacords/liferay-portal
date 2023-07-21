/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './TextFieldDelegateTemplate.soy';
import templates from './TextField.soy';

/**
 * TextField
 */
class TextField extends Component {
	/**
	 * @inheritdoc
	 * @review
	 */
	prepareStateForRender(state) {
		let nextState = state;

		const value = state.configurationValues[this.field.name];

		if (value) {
			nextState = {
				...state,
				configurationValues: {
					...state.configurationValues,
					[this.field.name]: value
				}
			};
		}

		return nextState;
	}

	/**
	 * Handle Text Value Change
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleTextValueChanged(event) {
		const targetElement = event.delegateTarget;

		this.emit('fieldValueChanged', {
			name: this.field.name,
			value: `${targetElement.value}`
		});
	}
}

TextField.STATE = {
	/**
	 * The configuration field
	 * @review
	 * @type {object}
	 */
	field: Config.shapeOf({
		dataType: Config.string(),
		defaultValue: Config.string(),
		description: Config.string(),
		label: Config.string(),
		name: Config.string(),
		type: Config.string(),
		typeOptions: Config.object()
	})
};

Soy.register(TextField, templates);

export {TextField};
export default TextField;
