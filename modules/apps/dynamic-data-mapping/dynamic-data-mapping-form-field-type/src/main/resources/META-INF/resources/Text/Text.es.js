/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../FieldBase/FieldBase.es';

import './TextRegister.soy';

import 'clay-autocomplete';
import {normalizeFieldName} from 'dynamic-data-mapping-form-renderer/js/util/fields.es';
import Component from 'metal-component';
import dom from 'metal-dom';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './Text.soy';

class Text extends Component {
	attached() {
		const portalElement = dom.toElement('#clay_dropdown_portal');

		if (portalElement) {
			dom.addClasses(portalElement, 'show');
		}
	}

	dispatchEvent(event, name, value) {
		if (this.isMultilineTextRenderedByIE(event)) {
			value = this.value;
		}

		this.emit(name, {
			fieldInstance: this,
			originalEvent: event,
			value
		});
	}

	getAutocompleteOptions() {
		const {options} = this;

		if (!options) {
			return [];
		}

		return options.map(option => {
			return option.label;
		});
	}

	isMultilineTextRenderedByIE(event) {
		return (
			this.displayStyle === 'multiline' &&
			event.target.nodeName === 'TEXTAREA' &&
			Liferay.Browser.isIe()
		);
	}

	prepareStateForRender(state) {
		const {options} = this;

		return {
			...state,
			options: this.getAutocompleteOptions(options),
			value: this._getValue()
		};
	}

	rendered() {
		const {element} = this;

		const target = element.querySelector('.ddm-field-text');

		if (this.isMultilineTextRenderedByIE({target})) {
			if (this.value === '') {
				const currentTargetValue = target.value;

				target.value = ' ';
				target.value = currentTargetValue;
				target.blur();
			}
		}
	}

	shouldUpdate(changes) {
		return Object.keys(changes || {}).some(key => {
			if (key === 'events') {
				return false;
			}

			if (
				!Liferay.Util.isEqual(changes[key].newVal, changes[key].prevVal)
			) {
				return true;
			}
		});
	}

	_getValue() {
		const {
			context: {store}
		} = this;

		if (
			!store.viewMode &&
			this.predefinedValue !== '' &&
			this.value === ''
		) {
			return this.predefinedValue;
		}

		return this.value;
	}

	_handleAutocompleteFieldChanged(event) {
		const {value} = event.data;

		this.setState(
			{
				value
			},
			() => this.dispatchEvent(event, 'fieldEdited', value)
		);
	}

	_handleAutocompleteFieldFocused(event) {
		this.dispatchEvent('fieldFocused', event, event.target.inputValue);
	}

	_handleAutocompleteFilteredItemsChanged(filteredItemsReceived) {
		const {filteredItems} = this;

		if (filteredItemsReceived.newVal.length != filteredItems.length) {
			this.setState({
				filteredItems: filteredItemsReceived.newVal
			});
		}
	}

	_handleAutocompleteSelected(event) {
		const {value} = event.data.item;

		this.setState(
			{
				filteredItems: [],
				value
			},
			() => {
				this.dispatchEvent(event, 'fieldEdited', value);
			}
		);
	}

	_handleFieldBlurred(event) {
		if (this.isMultilineTextRenderedByIE(event)) {
			event.target.value = this.value;
		}

		this.dispatchEvent(event, 'fieldBlurred', event.target.value);
	}

	_handleFieldChanged(event) {
		const {target} = event;
		let {value} = target;
		const {fieldName} = this;

		if (this.isMultilineTextRenderedByIE(event)) {
			if (this.value === '' && value === '') {
				const currentTargetValue = target.value;

				target.value = ' ';
				target.value = currentTargetValue;
				target.blur();

				return;
			}
			else if (
				this.value != '' &&
				value === '' &&
				target.innerText != ''
			) {
				value = target.innerText;
			}
		}

		if (fieldName === 'name') {
			value = normalizeFieldName(value);

			target.value = value;
		}

		if (this.timeout) {
			clearTimeout(this.timeout);
		}

		if (Liferay.Browser.isEdge()) {
			this.cursor = event.target.selectionStart;
		}

		this.timeout = setTimeout(() => {
			this.setState(
				{
					value
				},
				() => {
					this.dispatchEvent(event, 'fieldEdited', value);

					if (this.cursor) {
						event.target.selectionStart = this.cursor;
					}
				}
			);
		}, 300);
	}

	_handleFieldClicked() {
		if (this.alertMessage) {
			this.showAlertMessage = true;
		}
	}

	_handleFieldFocused(event) {
		if (this.isMultilineTextRenderedByIE(event)) {
			event.target.value = this.value;
		}

		this.dispatchEvent(event, 'fieldFocused', event.target.value);
	}
}

Text.STATE = {
	/**
	 * @default 0
	 * @instance
	 * @memberof Text
	 * @type {number}
	 */

	_cursorPosition: Config.number().value(0),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */
	alertMessage: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	autocompleteEnabled: Config.bool(),

	/**
	 * @default 'string'
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	dataType: Config.string().value('string'),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?(boolean|undefined)}
	 */

	displayErrors: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	displayStyle: Config.string().value('singleline'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	errorMessage: Config.string(),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?bool}
	 */

	evaluable: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	fieldName: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	filteredItems: Config.array()
		.value([])
		.internal(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	name: Config.string().required(),

	/**
	 * @default []
	 * @memberof Text
	 * @type {?array<object>}
	.setter('_loadOptionsFn').
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
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	placeholder: Config.string().value(''),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	predefinedValue: Config.string().value(''),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?(bool|undefined)}
	 */

	required: Config.bool().value(false),

	/**
	 * @default false
	 * @instance
	 * @memberof Text
	 * @type {?bool}
	 */

	showAlertMessage: Config.bool().value(false),

	/**
	 * @default true
	 * @instance
	 * @memberof Text
	 * @type {?(bool|undefined)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tooltip: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	type: Config.string().value('text'),

	/**
	 * @default undefined
	 * @instance
	 * @memberof Text
	 * @type {?(string|undefined)}
	 */

	value: Config.string().value('')
};

Soy.register(Text, templates);

export default Text;
