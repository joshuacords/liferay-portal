/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './PaginatedPageRenderer.soy';

import './SimplePageRenderer.soy';

import './TabbedPageRenderer.soy';

import './WizardPageRenderer.soy';

import 'clay-button';

import 'clay-dropdown';

import 'clay-modal';

import 'clay-icon';
import core from 'metal';
import Component from 'metal-component';
import dom from 'metal-dom';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import * as FormSupport from '../FormRenderer/FormSupport.es';
import templates from './PageRenderer.soy';

const ADMIN_PORTLET_NAMESPACE =
	'_com_liferay_dynamic_data_mapping_form_web_portlet_DDMFormAdminPortlet_';

class PageRenderer extends Component {
	getPage(page) {
		const {editingLanguageId} = this;

		if (core.isObject(page.description)) {
			page = {
				...page,
				description: page.description[editingLanguageId]
			};
		}
		if (core.isObject(page.title)) {
			page = {
				...page,
				title: page.title[editingLanguageId]
			};
		}

		return page;
	}

	isEmptyPage({rows}) {
		let empty = false;

		if (!rows || !rows.length) {
			empty = true;
		}
		else {
			empty = !rows.some(({columns}) => {
				let hasFields = true;

				if (!columns) {
					hasFields = false;
				}
				else {
					hasFields = columns.some(column => column.fields.length);
				}

				return hasFields;
			});
		}

		return empty;
	}

	hasFieldRequired({rows}) {
		let hasFieldRequired = false;

		if (rows && rows.length) {
			hasFieldRequired = rows.some(({columns}) => {
				if (columns && columns.length) {
					return columns.some(column => {
						if (column && column.fields.length) {
							return column.fields.some(field => field.required);
						}

						return false;
					});
				}

				return false;
			});
		}

		return hasFieldRequired;
	}

	prepareStateForRender(states) {
		return {
			...states,
			empty: this.isEmptyPage(states.page),
			hasFieldRequired: this.hasFieldRequired(states.page),
			indicatesRequiredFields: Liferay.Language.get(
				'indicates-required-fields'
			),
			isAdminPortletNamespace:
				ADMIN_PORTLET_NAMESPACE === states.portletNamespace,
			page: this.getPage(states.page)
		};
	}

	_handleFieldBlurred(event) {
		this.emit('fieldBlurred', event);
	}

	_handleFieldClicked(event) {
		const {delegateTarget} = event;
		const {fieldName} = delegateTarget.dataset;

		event.stopPropagation();

		this.emit('fieldClicked', {
			...FormSupport.getIndexes(dom.closest(delegateTarget, '.col-ddm')),
			fieldName,
			originalEvent: event
		});
	}

	_handleFieldEdited(event) {
		this.emit('fieldEdited', event);
	}

	_handleFieldFocused(event) {
		this.emit('fieldFocused', event);
	}
}

PageRenderer.STATE = {
	/**
	 * @instance
	 * @memberof FormPage
	 * @type {?number}
	 */

	activePage: Config.number().value(0),

	/**
	 * @instance
	 * @memberof FormPage
	 * @type {?boolean}
	 */
	editable: Config.bool().value(false),

	/**
	 * @default []
	 * @instance
	 * @memberof FormRenderer
	 * @type {?array<object>}
	 */

	page: Config.object(),

	/**
	 * @default 1
	 * @instance
	 * @memberof FormPage
	 * @type {?number}
	 */

	pageIndex: Config.number().value(0),

	/**
	 * @default undefined
	 * @instance
	 * @memberof FormRenderer
	 * @type {!string}
	 */

	spritemap: Config.string().required()
};

Soy.register(PageRenderer, templates);

export default PageRenderer;
