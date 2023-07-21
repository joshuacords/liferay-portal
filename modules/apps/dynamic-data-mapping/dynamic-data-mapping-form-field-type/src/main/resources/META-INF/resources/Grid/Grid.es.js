/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../FieldBase/FieldBase.es';

import './GridRegister.soy';

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './Grid.soy';

class Grid extends Component {
	prepareStateForRender(state) {
		return {
			...state,
			columnText: Liferay.Language.get('column'),
			rowText: Liferay.Language.get('row')
		};
	}

	_handleFieldBlurred(event) {
		this.emit('fieldBlurred', {
			fieldInstance: this,
			originalEvent: event
		});
	}

	_handleFieldChanged(event) {
		const {target} = event;
		const value = {
			...this.value,
			[target.name]: target.value
		};

		this.setState(
			{
				value
			},
			() => {
				this.emit('fieldEdited', {
					fieldInstance: this,
					originalEvent: event,
					value
				});
			}
		);
	}

	_handleFieldFocused(event) {
		this.emit('fieldFocused', {
			fieldInstance: this,
			originalEvent: event
		});
	}
}

Grid.STATE = {
	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?array<object>}
	 */

	columns: Config.arrayOf(
		Config.shapeOf({
			label: Config.string(),
			value: Config.string()
		})
	).value([
		{
			label: 'col1',
			value: 'fieldId'
		}
	]),

	/**
	 * @default false
	 * @memberof Grid
	 * @type {?bool}
	 */

	evaluable: Config.bool().value(false),

	fieldName: Config.string(),

	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default false
	 * @memberof Grid
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default false
	 * @memberof Grid
	 * @type {?(bool|undefined)}
	 */

	required: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?array<object>}
	 */

	rows: Config.arrayOf(
		Config.shapeOf({
			label: Config.string(),
			value: Config.string()
		})
	).value([
		{
			label: 'row',
			value: 'jehf'
		}
	]),

	/**
	 * @default true
	 * @memberof Grid
	 * @type {?(bool|undefined)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @memberof Grid
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default grid
	 * @memberof Grid
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('grid'),

	/**
	 * @default {}
	 * @memberof Grid
	 * @type {?(string|undefined)}
	 */

	value: Config.object().value({})
};

Soy.register(Grid, templates);

export default Grid;
