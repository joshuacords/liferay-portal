/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '../../containers/Pagination/Pagination.es';

import '../../containers/PaginationControls/PaginationControls.es';

import '../../containers/Tabs/Tabs.es';

import '../../containers/Wizard/Wizard.es';

import '../PageRenderer/PageRenderer.es';

import 'clay-button';
import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './FormRenderer.soy';

/**
 * FormRenderer.
 * @extends Component
 */

class FormRenderer extends Component {
	attached() {
		Liferay.fire(`${this.portletNamespace}simplecaptcha_attachEvent`);
	}

	created() {
		if (!this._getDisplayable()) {
			this.render = () => {};
		}
	}

	_defaultLanguageIdValueFn() {
		return themeDisplay.getLanguageId();
	}

	_editingLanguageIdValueFn() {
		const {defaultLanguageId} = this;

		return defaultLanguageId;
	}

	_getDisplayable() {
		const {containerId, readOnly, viewMode} = this;

		return (
			readOnly ||
			!viewMode ||
			document.getElementById(containerId) !== null
		);
	}

	_handleFieldBlurred(event) {
		this.emit('fieldBlurred', event);
	}

	_handleFieldClicked(event) {
		this.emit('fieldClicked', event);
	}

	_handleFieldEdited(event) {
		this.emit('fieldEdited', event);
	}

	_handleFieldFocused(event) {
		this.emit('fieldFocused', event);
	}
}

FormRenderer.STATE = {
	/**
	 * @default
	 * @instance
	 * @memberof FormRenderer
	 * @type {?number}
	 */

	activePage: Config.number().value(0),

	/**
	 * @default undefined
	 * @memberof FormRenderer
	 * @type {string}
	 * @required
	 */

	containerId: Config.string().value(''),

	/**
	 * @default undefined
	 * @memberof FormRenderer
	 * @type {string}
	 * @required
	 */

	defaultLanguageId: Config.string().valueFn('_defaultLanguageIdValueFn'),

	/**
	 * @default false
	 * @instance
	 * @memberof FormRenderer
	 * @type {?bool}
	 */

	displayable: Config.bool().valueFn('_getDisplayable'),

	/**
	 * @default false
	 * @instance
	 * @memberof FormRenderer
	 * @type {?bool}
	 */

	editable: Config.bool().value(false),

	/**
	 * @default undefined
	 * @memberof FormRenderer
	 * @type {string}
	 * @required
	 */

	editingLanguageId: Config.string().valueFn('_editingLanguageIdValueFn'),

	/**
	 * @default []
	 * @instance
	 * @memberof FormRenderer
	 * @type {?array<object>}
	 */

	pages: Config.array().value([]),

	/**
	 * @instance
	 * @memberof FormRenderer
	 * @type {string}
	 */

	portletNamespace: Config.string().required(),

	/**
	 * @default false
	 * @instance
	 * @memberof FormRenderer
	 * @type {?bool}
	 */

	readOnly: Config.bool().value(false),

	/**
	 * @default []
	 * @instance
	 * @memberof FormRenderer
	 * @type {?array<object>}
	 */

	rules: Config.array().value([]),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormRenderer
	 * @type {!string}
	 */

	spritemap: Config.string().required(),

	/**
	 * @default false
	 * @instance
	 * @memberof FormRenderer
	 * @type {?bool}
	 */

	viewMode: Config.bool().value(false)
};

Soy.register(FormRenderer, templates);

export default FormRenderer;
