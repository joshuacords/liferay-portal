/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
