/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../components/Tooltip/Tooltip.es';

import 'clay-icon';
import compose from 'dynamic-data-mapping-form-renderer/js/util/compose.es';
import {getRepeatedIndex} from 'dynamic-data-mapping-form-renderer/js/util/repeatable.es';
import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import withDispatch from '../util/withDispatch.es';
import templates from './FieldBase.soy';
import withRepetitionControls from './withRepetitionControls.es';

class FieldBase extends Component {
	prepareStateForRender(state) {
		const repeatedIndex = getRepeatedIndex(this.name);

		return {
			...state,
			addDuplicateField: Liferay.Util.sub(
				Liferay.Language.get('add-duplicate-field'),
				this.label ? this.label : this.type
			),
			removeDuplicateField: Liferay.Util.sub(
				Liferay.Language.get('remove-duplicate-field'),
				this.label ? this.label : this.type
			),
			requiredText: Liferay.Language.get('required'),
			showRepeatableAddButton: this.repeatable,
			showRepeatableRemoveButton: this.repeatable && repeatedIndex > 0
		};
	}

	_handleFieldClicked(event) {
		const {delegateTarget} = event;
		const {fieldName} = delegateTarget.dataset;

		event.stopPropagation();

		this.dispatch('fieldClicked', {
			fieldName,
			originalEvent: event
		});
	}
}

FieldBase.STATE = {
	/**
	 * @default input
	 * @memberof FieldBase
	 * @type {?html}
	 */

	addDuplicateField: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	contentRenderer: Config.any(),

	/**
	 * @default false
	 * @memberof FieldBase
	 * @type {?boolean}
	 */

	displayErrors: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	id: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	label: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	name: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	removeDuplicateField: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	repeatable: Config.bool(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	required: Config.bool(),

	/**
	 * @default true
	 * @memberof FieldBase
	 * @type {?(bool|undefined)}
	 */

	showLabel: Config.bool().value(true),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	spritemap: Config.string().required(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tip: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	tooltip: Config.string(),

	/**
	 * @default undefined
	 * @memberof FieldBase
	 * @type {?(string|undefined)}
	 */

	type: Config.string()
};

const composed = compose(withDispatch, withRepetitionControls)(FieldBase);

Soy.register(composed, templates);

export default composed;
