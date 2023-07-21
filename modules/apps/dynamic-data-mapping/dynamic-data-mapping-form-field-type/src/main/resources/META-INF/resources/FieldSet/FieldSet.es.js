/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../FieldBase/FieldBase.es';

import './FieldSetRegister.soy';

import 'dynamic-data-mapping-form-renderer/js/components/PageRenderer/PageRenderer.es';
import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './FieldSet.soy';

class FieldSet extends Component {
	prepareNestedRows({nestedFields, rows}) {
		return rows.map(row => ({
			...row,
			columns: row.columns.map(column => ({
				...column,
				fields: column.fields.map(fieldName => {
					return nestedFields.find(
						nestedField => nestedField.fieldName === fieldName
					);
				})
			}))
		}));
	}

	prepareStateForRender(state) {
		return {
			...state,
			nestedRows: this.prepareNestedRows(state)
		};
	}

	willReceiveState(changes) {
		if (changes.nestedFields || changes.rows) {
			this.forceUpdate();
		}
	}

	_handleFieldBlurred(event) {
		this.emit('fieldBlurred', event);
	}

	_handleFieldEdited(event) {
		this.emit('fieldEdited', event);
	}

	_handleFieldFocused(event) {
		this.emit('fieldFocused', event);
	}

	_setRows(rows) {
		if (typeof rows === 'string') {
			try {
				return JSON.parse(rows);
			}
			catch (e) {
				return [];
			}
		}

		return rows;
	}
}

FieldSet.STATE = {
	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	errorMessage: Config.string(),

	/**
	 * @default false
	 * @instance
	 * @memberof FieldSet
	 * @type {?bool}
	 */

	evaluable: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	fieldName: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	name: Config.string().required(),

	/**
	 * @default []
	 * @instance
	 * @memberof FieldSet
	 * @type {?(Array)}
	 */

	nestedFields: Config.array().value([]),

	/**
	 * @default []
	 * @instance
	 * @memberof FieldSet
	 * @type {?(Array)}
	 */

	nestedRows: Config.array()
		.internal()
		.value([]),

	/**
	 * @default '000000'
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	predefinedValue: Config.string().value('000000'),

	/**
	 * @default false
	 * @instance
	 * @memberof FieldSet
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool().value(false),

	/**
	 * @default false
	 * @instance
	 * @memberof FieldSet
	 * @type {?(bool|undefined)}
	 */

	required: Config.bool().value(false),

	/**
	 * @default []
	 * @instance
	 * @memberof FieldSet
	 * @type {?(Array|string)}
	 */

	rows: Config.oneOfType([Config.array(), Config.string()])
		.setter('_setRows')
		.value([]),

	/**
	 * @default true
	 * @instance
	 * @memberof FieldSet
	 * @type {?(bool|undefined)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldSet
	 * @type {?(string|undefined)}
	 */

	value: Config.string()
};

Soy.register(FieldSet, templates);

export {FieldSet};
export default FieldSet;
