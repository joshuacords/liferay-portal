/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './MockFieldRegister.soy';

import Component from 'metal-component';
import Soy from 'metal-soy';
import Config from 'metal-state/lib/Config';

import templates from './MockField.soy';

class MockField extends Component {
	emitFieldEdited(value, fieldName) {
		this.value = value;

		this.emit('fieldEdited', {
			fieldInstance: this,
			originalEvent: {
				delegateTarget: this.element.querySelector('p'),
				target: {
					getAttribute: () => {
						return fieldName;
					}
				}
			},
			value
		});
	}
}

MockField.STATE = {
	fieldName: Config.string(),
	label: Config.string(),
	options: Config.array(),
	readOnly: Config.bool(),
	type: Config.string(),
	value: Config.any()
};

Soy.register(MockField, templates);

export default MockField;
