/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

export const handleFieldDeleted = (props, state, {fieldName}) => {
	const {activePage, pages} = state;
	const newPages = pages.map((page, pageIndex) => {
		if (activePage === pageIndex) {
			return {
				...page,
				rows: FormSupport.removeEmptyRows(
					removeField(props, pages, fieldName),
					pageIndex
				)
			};
		}

		return page;
	});

	return {
		focusedField: {},
		pages: newPages,
		rules: RulesSupport.formatRules(newPages, state.rules)
	};
};

export default handleFieldDeleted;
