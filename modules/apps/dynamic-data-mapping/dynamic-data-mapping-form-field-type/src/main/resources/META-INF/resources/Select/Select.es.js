/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../FieldBase/FieldBase.es';

import '../Text/Text.es';

import './SelectRegister.soy';

import './SingleSelectedOption.soy';

import 'clay-dropdown';

import 'clay-icon';

import 'clay-label';
import Component from 'metal-component';
import dom from 'metal-dom';
import {EventHandler} from 'metal-events';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import {setJSONArrayValue} from '../util/setters.es';
import templates from './Select.soy';

class Select extends Component {
	addValue(value) {
		const currentValue = this._getArrayValue(this.value);
		const newValue = [...currentValue];

		if (value) {
			newValue.push(value);
		}

		return newValue;
	}

	attached() {
		this._eventHandler = new EventHandler();

		this._eventHandler.add(
			dom.on(document, 'click', this._handleDocumentClicked.bind(this))
		);
	}

	deleteValue(value) {
		const currentValue = this._getArrayValue(this.value);

		return currentValue.filter(v => v !== value);
	}

	disposeInternal() {
		super.disposeInternal();

		this._eventHandler.removeAllListeners();
	}

	prepareStateForRender(state) {
		const {predefinedValue, value} = state;
		const {fixedOptions, multiple, options, showEmptyOption} = this;
		const predefinedValueArray = this._getArrayValue(predefinedValue);
		let valueArray = this._getArrayValue(value);

		valueArray = this._isEmptyArray(valueArray)
			? predefinedValueArray
			: valueArray;

		valueArray = valueArray.filter((value, index) => {
			return multiple ? true : index === 0;
		});

		let newOptions = [...options]
			.map((option, index) => {
				return {
					...this._prepareOption(option, valueArray),
					separator:
						fixedOptions.length > 0 && index === options.length - 1
				};
			})
			.concat(
				fixedOptions.map(option =>
					this._prepareOption(option, valueArray)
				)
			)
			.filter(({value}) => value !== '');

		if (!multiple && showEmptyOption) {
			const emptyOption = {
				label: Liferay.Language.get('choose-an-option'),
				value: ''
			};

			newOptions = [emptyOption, ...newOptions];
		}

		return {
			...state,
			options: newOptions,
			value: valueArray.filter(value =>
				newOptions.some(option => value === option.value)
			)
		};
	}

	setValue(value) {
		const newValue = [];

		if (value) {
			newValue.push(value);
		}

		return newValue;
	}

	_getArrayValue(value) {
		let newValue = value || '';

		if (!Array.isArray(newValue)) {
			newValue = [newValue];
		}

		return newValue;
	}

	_handleDocumentClicked({target}) {
		const {base} = this.refs;
		const {dropdown} = base.refs;

		if (dropdown) {
			const {menu} = dropdown.refs.portal.refs;
			const {expanded} = this;

			if (
				expanded &&
				!this.element.contains(target) &&
				!dropdown.element.contains(target) &&
				!menu.contains(target)
			) {
				this.setState({
					expanded: false
				});
			}
		}
	}

	_handleExpandedChanged({newVal}) {
		if (newVal) {
			this.emit('fieldFocused', {
				fieldInstance: this,
				originalEvent: window.event
			});
		}
		else {
			this.emit('fieldBlurred', {
				fieldInstance: this,
				originalEvent: window.event
			});
		}

		this.expanded = newVal;
	}

	_handleItemClicked({data, preventDefault}) {
		const {multiple} = this;
		const currentValue = this._getArrayValue(this.value);
		const itemValue = data.item.value;

		let newValue;

		if (multiple) {
			if (currentValue.includes(itemValue)) {
				newValue = this.deleteValue(itemValue);

				if (document.activeElement) {
					document.activeElement.blur();
				}
			}
			else {
				newValue = this.addValue(itemValue);
			}
		}
		else {
			newValue = this.setValue(itemValue);
		}

		preventDefault();

		let selectElement = document.getElementById(this.name);

		if (selectElement && selectElement.parentElement) {
			selectElement = selectElement.parentElement;

			selectElement.focus();
		}

		this.setState(
			{
				expanded: multiple,
				value: newValue
			},
			() =>
				this.emit('fieldEdited', {
					fieldInstance: this,
					value: newValue
				})
		);
	}

	_handleLabelClosed({preventDefault, stopPropagation, target}) {
		const {value} = target.data;

		preventDefault();
		stopPropagation();

		const newValue = this.deleteValue(value);

		this.setState(
			{
				value: newValue
			},
			() =>
				this.emit('fieldEdited', {
					fieldInstance: this,
					value: newValue
				})
		);
	}

	_isEmptyArray(array) {
		return array.some(value => value !== '') === false;
	}

	_prepareOption(option, valueArray) {
		const {multiple} = this;
		const included = valueArray.includes(option.value);

		return {
			...option,
			active: !multiple && included,
			checked: multiple && included,
			label: Liferay.Util.escape(option.label),
			type: multiple ? 'checkbox' : 'item'
		};
	}
}

Select.STATE = {
	/**
	 * @default 'string'
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	dataSourceType: Config.string(),

	/**
	 * @default 'string'
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	dataType: Config.string().value('string'),

	/**
	 * @default false
	 * @memberof Select
	 * @type {?(boolean|undefined)}
	 */

	evaluable: Config.bool().value(false),

	/**
	 * @default false
	 * @memberof Select
	 * @type {?bool}
	 */

	expanded: Config.bool()
		.internal()
		.value(false),

	/**
	 * @default undefined
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	fieldName: Config.string(),

	/**
	 * @default []
	 * @memberof Select
	 * @type {?array<object>}
	 */

	fixedOptions: Config.arrayOf(
		Config.shapeOf({
			active: Config.bool().value(false),
			disabled: Config.bool().value(false),
			id: Config.string(),
			inline: Config.bool().value(false),
			label: Config.string(),
			name: Config.string(),
			showLabel: Config.bool().value(true),
			value: Config.string()
		})
	).value([]),

	/**
	 * @default undefined
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	multiple: Config.bool(),

	/**
	 * @default []
	 * @memberof Select
	 * @type {?array<object>}
	 */

	options: Config.arrayOf(
		Config.shapeOf({
			active: Config.bool().value(false),
			disabled: Config.bool().value(false),
			id: Config.string(),
			inline: Config.bool().value(false),
			label: Config.string(),
			name: Config.string(),
			showLabel: Config.bool().value(true),
			value: Config.string()
		})
	).value([]),

	/**
	 * @default Choose an Option
	 * @memberof Select
	 * @type {?string}
	 */

	placeholder: Config.string(),

	/**
	 * @default undefined
	 * @memberof Select
	 * @type {?string}
	 */

	predefinedValue: Config.oneOfType([
		Config.array(),
		Config.object(),
		Config.string()
	])
		.setter(setJSONArrayValue)
		.value([]),

	/**
	 * @default false
	 * @memberof Select
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default false
	 * @memberof Select
	 * @type {?bool}
	 */

	required: Config.bool().value(false),

	/**
	 * @default true
	 * @memberof Select
	 * @type {?bool}
	 */

	showEmptyOption: Config.bool().value(true),

	/**
	 * @default false
	 * @memberof Select
	 * @type {?bool}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('select'),

	/**
	 * @default undefined
	 * @memberof Select
	 * @type {?(string|undefined)}
	 */

	value: Config.oneOfType([
		Config.array(),
		Config.object(),
		Config.string()
	]).setter(setJSONArrayValue)
};

Soy.register(Select, templates);

export default Select;
