/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Config} from 'metal-state';

import {focusedFieldStructure, pageStructure} from '../../util/config.es';

export default {
	/**
	 * @default
	 * @instance
	 * @memberof FormBuilder
	 * @type {?number}
	 */

	activePage: Config.number().value(0),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormBuilder
	 * @type {?string}
	 */

	defaultLanguageId: Config.string(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormBuilder
	 * @type {?string}
	 */

	editingLanguageId: Config.string(),

	/**
	 * @default []
	 * @instance
	 * @memberof FormBuilder
	 * @type {?(array|undefined)}
	 */

	fieldActions: Config.array().value([]),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormBuilder
	 * @type {?string}
	 */

	fieldSetDefinitionURL: Config.string(),

	/**
	 * @default []
	 * @instance
	 * @memberof FormBuilder
	 * @type {?(array|undefined)}
	 */

	fieldSets: Config.array().value([]),

	/**
	 * @default []
	 * @instance
	 * @memberof FormBuilder
	 * @type {?(array|undefined)}
	 */

	fieldTypes: Config.array().value([]),

	/**
	 * @default {}
	 * @instance
	 * @memberof FormBuilder
	 * @type {?object}
	 */

	focusedField: focusedFieldStructure.value({}),

	/**
	 * @default []
	 * @instance
	 * @memberof FormBuilder
	 * @type {?array<object>}
	 */

	pages: Config.arrayOf(pageStructure).value([]),

	/**
	 * @instance
	 * @memberof FormBuilder
	 * @type {string}
	 */

	paginationMode: Config.string().required(),

	/**
	 * @instance
	 * @memberof FormBuilder
	 * @type {string}
	 */

	portletNamespace: Config.string().required(),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormRenderer
	 * @type {!string}
	 */

	spritemap: Config.string().required(),

	/**
	 * @instance
	 * @memberof FormBuilder
	 * @type {object}
	 */

	successPageSettings: Config.shapeOf({
		body: Config.object(),
		enabled: Config.bool(),
		title: Config.object()
	}).value({}),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormBuilder
	 * @type {?string}
	 */

	view: Config.string()
};
