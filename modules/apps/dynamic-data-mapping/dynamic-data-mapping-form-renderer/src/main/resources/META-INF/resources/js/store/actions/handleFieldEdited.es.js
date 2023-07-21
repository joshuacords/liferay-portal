/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {PagesVisitor} from '../../util/visitors.es';

const getEditedField = (field, editingLanguageId, name, value) => {
	const editedFieldValue =
		!field.localizable &&
		!field.validation &&
		field.localizedValue &&
		field.localizedValue[editingLanguageId] != undefined &&
		field.required
			? field.localizedValue[editingLanguageId]
			: value;

	return {
		...field,
		localizedValue: {
			...field.localizedValue,
			[editingLanguageId]: editedFieldValue
		},
		value: editedFieldValue
	};
};

const getEditedPages = (pages, editingLanguageId, name, value) => {
	const pageVisitor = new PagesVisitor(pages);

	return pageVisitor.mapFields(field => {
		if (field.name === name) {
			field = getEditedField(field, editingLanguageId, name, value);
		}
		else if (field.nestedFields) {
			field = {
				...field,
				nestedFields: field.nestedFields.map(nestedField => {
					if (nestedField.name === name) {
						nestedField = getEditedField(
							nestedField,
							editingLanguageId,
							name,
							value
						);
					}

					return nestedField;
				})
			};
		}

		return field;
	});
};

export default (evaluatorContext, properties) => {
	const {fieldInstance, value} = properties;
	const {editingLanguageId, pages} = evaluatorContext;

	return getEditedPages(pages, editingLanguageId, fieldInstance.name, value);
};
