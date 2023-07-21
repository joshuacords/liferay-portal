/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as FormSupport from 'dynamic-data-mapping-form-renderer/js/components/FormRenderer/FormSupport.es';
import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';

const handleFieldClicked = (props, state, event) => {
	const {fieldName} = event;
	const {pages} = state;

	const fieldProperties = FormSupport.findFieldByName(pages, fieldName);
	const {settingsContext} = fieldProperties;
	const visitor = new PagesVisitor(settingsContext.pages);

	const focusedField = {
		...fieldProperties,
		settingsContext: {
			...settingsContext,
			pages: visitor.mapFields(field => {
				const {fieldName} = field;

				if (fieldName === 'validation') {
					field = {
						...field,
						validation: {
							...field.validation,
							fieldName: fieldProperties.fieldName
						}
					};
				}

				return field;
			})
		}
	};

	return {
		focusedField,
		previousFocusedField: focusedField
	};
};

export default handleFieldClicked;
