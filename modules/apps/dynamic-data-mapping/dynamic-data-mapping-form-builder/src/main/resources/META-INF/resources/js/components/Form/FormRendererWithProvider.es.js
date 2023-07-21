/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {PagesVisitor} from 'dynamic-data-mapping-form-builder/js/util/visitors.es';

import FormRenderer from './FormRenderer.es';

class FormRendererWithProvider extends FormRenderer {
	_handleFieldEdited(properties) {
		const pageVisitor = new PagesVisitor(this.pages);

		const pages = pageVisitor.mapFields(field => {
			if (field.fieldName === properties.fieldInstance.fieldName) {
				return {
					...field,
					value: properties.value
				};
			}
		});

		this.pages = pages;
	}

	created() {
		this.on('fieldEdited', this._handleFieldEdited);
	}
}

export default FormRendererWithProvider;
