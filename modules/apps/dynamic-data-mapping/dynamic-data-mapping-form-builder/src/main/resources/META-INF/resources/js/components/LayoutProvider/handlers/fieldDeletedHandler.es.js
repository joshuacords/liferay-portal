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

import * as FormSupport from 'dynamic-data-mapping-form-renderer/js/components/FormRenderer/FormSupport.es';
import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';

import RulesSupport from '../../RuleBuilder/RulesSupport.es';
import {updateField} from '../util/settingsContext.es';

export const removeField = (props, pages, fieldName) => {
	const visitor = new PagesVisitor(pages);

	const filter = fields =>
		fields
			.filter(field => field.fieldName !== fieldName)
			.map(field => {
				const pages = [{rows: field.rows}];
				const visitor = new PagesVisitor(pages);

				const nestedFields = field.nestedFields
					? filter(field.nestedFields)
					: [];

				field = updateField(props, field, 'nestedFields', nestedFields);

				const rows = field.rows
					? FormSupport.removeEmptyRows(
							visitor.mapColumns(column => ({
								...column,
								fields: column.fields.filter(
									nestedFieldName =>
										fieldName !== nestedFieldName
								)
							})),
							0
					  )
					: [];

				field = updateField(props, field, 'rows', rows);

				return {
					...field,
					nestedFields,
					rows
				};
			});

	return visitor.mapColumns(column => ({
		...column,
		fields: filter(column.fields)
	}));
};

const removeEmptyRow = (pages, source) => {
	const {pageIndex, rowIndex} = source;

	if (!FormSupport.rowHasFields(pages, pageIndex, rowIndex)) {
		pages = FormSupport.removeRow(pages, pageIndex, rowIndex);
	}

	return pages;
};

export const handleFieldDeleted = (state, indexes) => {
	const {columnIndex, pageIndex, rowIndex} = indexes;
	const {pages} = state;
	let newContext = FormSupport.removeFields(
		pages,
		pageIndex,
		rowIndex,
		columnIndex
	);

	newContext = removeEmptyRow(newContext, {
		columnIndex,
		pageIndex,
		rowIndex
	});

	return {
		focusedField: {},
		pages: newContext,
		rules: RulesSupport.formatRules(newContext, state.rules)
	};
};

export default handleFieldDeleted;
