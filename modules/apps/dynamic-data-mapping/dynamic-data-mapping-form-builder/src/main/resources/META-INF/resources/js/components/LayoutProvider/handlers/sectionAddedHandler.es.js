/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as FormSupport from 'dynamic-data-mapping-form-renderer/js/components/FormRenderer/FormSupport.es';
import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';

import {createField} from '../../../util/fieldSupport.es';
import {updateField} from '../util/settingsContext.es';
import handleFieldDeleted from './fieldDeletedHandler.es';

const addNestedField = ({field, indexes, nestedField, props}) => {
	const layout = FormSupport.addFieldToColumn(
		[{rows: field.rows}],
		indexes.pageIndex,
		indexes.rowIndex,
		indexes.columnIndex,
		nestedField.fieldName
	);
	const nestedFields = [...field.nestedFields, nestedField];

	field = updateField(props, field, 'nestedFields', nestedFields);

	const {rows} = layout[indexes.pageIndex];

	field = updateField(props, field, 'rows', rows);

	return {
		...field,
		nestedFields,
		rows
	};
};

const addNestedFields = ({field, indexes, nestedFields, props}) => {
	let layout = [{rows: field.rows}];
	const visitor = new PagesVisitor(layout);

	visitor.mapFields((field, fieldIndex, columnIndex, rowIndex, pageIndex) => {
		if (
			!nestedFields.some(
				nestedField => nestedField.fieldName === field.fieldName
			)
		) {
			layout = FormSupport.removeFields(
				layout,
				pageIndex,
				rowIndex,
				columnIndex
			);
		}
	});

	[...nestedFields].reverse().forEach(nestedField => {
		layout = FormSupport.addFieldToColumn(
			layout,
			indexes.pageIndex,
			indexes.rowIndex,
			indexes.columnIndex,
			nestedField.fieldName
		);
	});

	field = updateField(props, field, 'nestedFields', nestedFields);

	const {rows} = layout[indexes.pageIndex];

	return {
		...updateField(props, field, 'rows', rows),
		nestedFields,
		rows
	};
};

const createSection = (props, event, nestedFields) => {
	const {fieldTypes} = props;
	const fieldType = fieldTypes.find(fieldType => {
		return fieldType.name === 'fieldset';
	});
	const sectionField = createField(props, {...event, fieldType});

	return addNestedFields({
		field: {
			...sectionField,
			rows: [{columns: [{fields: [], size: 12}]}]
		},
		indexes: {
			columnIndex: 0,
			pageIndex: 0,
			rowIndex: 0
		},
		nestedFields,
		props
	});
};

export default (props, state, event) => {
	const {data, indexes} = event;
	const {fieldName, parentFieldName} = data;
	const {pages} = state;

	const newField = event.newField || createField(props, event);
	const existingField = FormSupport.findFieldByName(pages, fieldName);
	const sectionField = createSection(props, event, [existingField, newField]);

	const visitor = new PagesVisitor(pages);

	let newPages;

	if (parentFieldName) {
		newPages = visitor.mapFields(
			field => {
				if (field.fieldName === parentFieldName) {
					const updatedParentField = FormSupport.findFieldByName(
						handleFieldDeleted(props, state, {
							fieldName
						}).pages,
						parentFieldName
					);

					return addNestedField({
						field: updatedParentField,
						indexes,
						nestedField: sectionField,
						props
					});
				}

				return field;
			},
			false,
			true
		);
	}
	else {
		newPages = visitor.mapFields(field => {
			if (field.fieldName === fieldName) {
				return sectionField;
			}

			return field;
		});
	}

	return {
		focusedField: {
			...newField
		},
		pages: newPages,
		previousFocusedField: sectionField
	};
};
