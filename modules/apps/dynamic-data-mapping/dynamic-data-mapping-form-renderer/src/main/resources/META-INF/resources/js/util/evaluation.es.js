/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {debounce} from 'frontend-js-web';

import {convertToFormData, makeFetch} from './fetch.es';
import {PagesVisitor} from './visitors.es';

const EVALUATOR_URL =
	themeDisplay.getPathContext() +
	'/o/dynamic-data-mapping-form-context-provider/';

let controller = null;

const doEvaluate = debounce((fieldName, evaluatorContext, callback) => {
	const {
		defaultLanguageId,
		editingLanguageId,
		pages,
		portletNamespace
	} = evaluatorContext;

	if (controller) {
		controller.abort();
	}

	if (window.AbortController) {
		controller = new AbortController();
	}

	makeFetch({
		body: convertToFormData({
			languageId: editingLanguageId,
			p_auth: Liferay.authToken,
			portletNamespace,
			serializedFormContext: JSON.stringify({
				...evaluatorContext,
				groupId: themeDisplay.getScopeGroupId(),
				portletNamespace
			}),
			trigger: fieldName
		}),
		signal: controller && controller.signal,
		url: EVALUATOR_URL
	})
		.then(newPages => {
			const mergedPages = mergePages(
				defaultLanguageId,
				editingLanguageId,
				fieldName,
				newPages,
				pages
			);

			callback(null, mergedPages);
		})
		.catch(error => callback(error));
}, 600);

export const evaluate = (fieldName, evaluatorContext) => {
	return new Promise((resolve, reject) => {
		doEvaluate(fieldName, evaluatorContext, (error, pages) => {
			if (error) {
				return reject(error);
			}

			resolve(pages);
		});
	});
};

export const mergeFieldOptions = (field, newField) => {
	let newValue = {...newField.value};

	Object.keys(newValue).forEach(languageId => {
		newValue = {
			...newValue,
			[languageId]: newValue[languageId].map(option => {
				const existingOption = field.value[languageId].find(
					({value}) => value === option.value
				);

				return {
					...option,
					edited: existingOption && existingOption.edited
				};
			})
		};
	});

	return newValue;
};

export const mergePages = (
	defaultLanguageId,
	editingLanguageId,
	fieldName,
	newPages,
	sourcePages
) => {
	const visitor = new PagesVisitor(newPages);

	return visitor.mapFields(
		(field, fieldIndex, columnIndex, rowIndex, pageIndex) => {
			const sourceField =
				sourcePages[pageIndex].rows[rowIndex].columns[columnIndex]
					.fields[fieldIndex];

			const displayErrors =
				sourceField.displayErrors || field.fieldName === fieldName;

			let newField = {
				...sourceField,
				...field,
				defaultLanguageId,
				displayErrors,
				editingLanguageId,
				valid: field.valid !== false,
				value: field.valueChanged ? field.value : sourceField.value
			};

			if (newField.type === 'captcha') {
				return sourceField;
			}

			if (sourceField.nestedFields && newField.nestedFields) {
				newField = {
					...newField,
					nestedFields: sourceField.nestedFields.map(nestedField => {
						return {
							...nestedField,
							...(newField.nestedFields.find(({fieldName}) => {
								return fieldName === nestedField.fieldName;
							}) || {})
						};
					})
				};
			}

			if (newField.type === 'options') {
				newField = {
					...newField,
					value: mergeFieldOptions(sourceField, newField)
				};
			}

			if (newField.localizable) {
				newField = {
					...newField,
					localizedValue: {
						...sourceField.localizedValue
					}
				};
			}

			return newField;
		}
	);
};
