/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';

export const createField = (props, event) => {
	const {
		defaultLanguageId,
		editingLanguageId,
		fieldNameGenerator,
		spritemap
	} = props;
	const {fieldType, skipFieldNameGeneration = false} = event;

	let newFieldName;

	if (skipFieldNameGeneration) {
		const {settingsContext} = fieldType;
		const visitor = new PagesVisitor(settingsContext.pages);

		visitor.mapFields(({fieldName, value}) => {
			if (fieldName === 'name') {
				newFieldName = value;
			}
		});
	}
	else {
		newFieldName = fieldNameGenerator(fieldType.label);
	}

	const newField = {
		...fieldType,
		fieldName: newFieldName,
		name: newFieldName,
		settingsContext: {
			...fieldType.settingsContext,
			pages: normalizeSettingsContextPages(
				fieldType.settingsContext.pages,
				editingLanguageId,
				fieldType,
				newFieldName
			),
			type: fieldType.name
		}
	};

	const {fieldName, name, settingsContext} = newField;

	return {
		...getFieldProperties(
			settingsContext,
			defaultLanguageId,
			editingLanguageId
		),
		fieldName,
		instanceId: generateInstanceId(8),
		name,
		settingsContext,
		spritemap,
		type: fieldType.name
	};
};

export const formatFieldName = (instanceId, languageId, value) => {
	return `ddm$$${value}$${instanceId}$0$$${languageId}`;
};

export const generateInstanceId = length => {
	let text = '';

	const possible =
		'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

	for (let i = 0; i < length; i++) {
		text += possible.charAt(Math.floor(Math.random() * possible.length));
	}

	return text;
};

export const normalizeSettingsContextPages = (
	pages,
	editingLanguageId,
	fieldType,
	generatedFieldName
) => {
	const visitor = new PagesVisitor(pages);

	return visitor.mapFields(field => {
		const {fieldName} = field;

		if (fieldName === 'name') {
			field = {
				...field,
				value: generatedFieldName,
				visible: true
			};
		}
		else if (fieldName === 'label') {
			field = {
				...field,
				localizedValue: {
					...field.localizedValue,
					[editingLanguageId]: fieldType.label
				},
				type: 'text',
				value: fieldType.label
			};
		}
		else if (fieldName === 'type') {
			field = {
				...field,
				value: fieldType.name
			};
		}
		else if (fieldName === 'validation') {
			field = {
				...field,
				validation: {
					...field.validation,
					fieldName: generatedFieldName
				}
			};
		}

		return {
			...field
		};
	});
};

export const getFieldProperties = (
	{pages},
	defaultLanguageId,
	editingLanguageId
) => {
	const properties = {};
	const visitor = new PagesVisitor(pages);

	visitor.mapFields(
		({fieldName, localizable, localizedValue, type, value}) => {
			if (
				localizable &&
				(fieldName == 'predefinedValue' ||
					localizedValue[editingLanguageId])
			) {
				properties[fieldName] = localizedValue[editingLanguageId];
			}
			else if (localizable && localizedValue[defaultLanguageId]) {
				properties[fieldName] = localizedValue[defaultLanguageId];
			}
			else if (type == 'options') {
				if (!value[editingLanguageId] && value[defaultLanguageId]) {
					properties[fieldName] = value[defaultLanguageId];
				}
				else {
					properties[fieldName] = value[editingLanguageId];
				}
			}
			else if (type == 'validation') {
				if (!value.errorMessage[editingLanguageId]) {
					value.errorMessage[editingLanguageId] =
						value.errorMessage[defaultLanguageId];
				}

				if (!value.parameter[editingLanguageId]) {
					value.parameter[editingLanguageId] =
						value.parameter[defaultLanguageId];
				}

				properties[fieldName] = value;
			}
			else {
				properties[fieldName] = value;
			}
		}
	);

	return properties;
};

export const getFieldIndexes = (pages, fieldName) => {
	const visitor = new PagesVisitor(pages);
	let indexes = {};

	visitor.mapFields((field, fieldIndex, columnIndex, rowIndex, pageIndex) => {
		if (field.fieldName === fieldName) {
			indexes = {columnIndex, pageIndex, rowIndex};
		}
	});

	return indexes;
};

export const localizeField = (field, defaultLanguageId, editingLanguageId) => {
	let value = field.value;

	if (field.dataType === 'json' && typeof value === 'object') {
		value = JSON.stringify(value);
	}

	if (field.localizable && field.localizedValue) {
		let localizedValue = field.localizedValue[editingLanguageId];

		if (localizedValue === undefined) {
			localizedValue = field.localizedValue[defaultLanguageId];
		}

		if (localizedValue !== undefined) {
			value = localizedValue;
		}
	}
	else if (
		field.dataType === 'ddm-options' &&
		value[editingLanguageId] === undefined
	) {
		value = {
			...value,
			[editingLanguageId]: value[defaultLanguageId]
		};
	}

	return {
		...field,
		defaultLanguageId,
		editingLanguageId,
		localizedValue: {
			...(field.localizedValue || {}),
			[editingLanguageId]: value
		},
		value
	};
};
