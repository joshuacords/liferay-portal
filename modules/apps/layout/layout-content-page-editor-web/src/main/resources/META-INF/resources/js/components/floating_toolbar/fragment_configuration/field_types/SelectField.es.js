/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './SelectFieldDelegateTemplate.soy';
import {setIn} from '../../../../utils/FragmentsEditorUpdateUtils.es';
import templates from './SelectField.soy';

/**
 * SelectField
 */
class SelectField extends Component {
	/**
	 * @inheritdoc
	 * @review
	 */
	prepareStateForRender(state) {
		const nextState = state;

		let selectedOption = this.field.defaultValue;

		if (
			this.configurationValues &&
			this.configurationValues[this.field.name]
		) {
			selectedOption = this.configurationValues[this.field.name];
		}

		return setIn(nextState, ['selectedOption'], selectedOption);
	}

	/**
	 * Handle Select Value Change
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleSelectValueChanged(event) {
		const targetElement = event.delegateTarget;

		this.emit('fieldValueChanged', {
			name: this.field.name,
			value: targetElement.options[targetElement.selectedIndex].value
		});
	}
}

SelectField.STATE = {
	/**
	 * Fragment Entry Link Configuration values
	 * @instance
	 * @memberOf FragmentEntryLink
	 * @type {object}
	 */
	configurationValues: Config.object(),

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

Soy.register(SelectField, templates);

export {SelectField};
export default SelectField;
