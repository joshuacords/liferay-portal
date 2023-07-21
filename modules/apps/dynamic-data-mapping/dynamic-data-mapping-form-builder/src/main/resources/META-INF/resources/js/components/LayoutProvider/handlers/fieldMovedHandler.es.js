/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as FormSupport from 'dynamic-data-mapping-form-renderer/js/components/FormRenderer/FormSupport.es';

import RulesSupport from '../../RuleBuilder/RulesSupport.es';
import {addField} from './fieldAddedHandler.es';
import handleFieldDeleted from './fieldDeletedHandler.es';
import handleSectionAdded from './sectionAddedHandler.es';

export default (props, state, event) => {
	const {
		sourceFieldName,
		targetFieldName,
		targetIndexes,
		targetParentFieldName
	} = event;

	const sourceField = FormSupport.findFieldByName(
		state.pages,
		sourceFieldName
	);

	if (sourceFieldName === targetFieldName) {
		return {
			focusedField: sourceField,
			pages: state.pages,
			previousFocusedField: sourceField
		};
	}

	const previousRules = JSON.parse(JSON.stringify(state.rules));

	const deletedState = handleFieldDeleted(props, state, {
		fieldName: sourceFieldName
	});

	if (targetFieldName) {
		const addedSection = handleSectionAdded(
			props,
			{
				...state,
				pages: deletedState.pages
			},
			{
				data: {
					fieldName: targetFieldName,
					parentFieldName: targetParentFieldName
				},
				indexes: targetIndexes,
				newField: sourceField
			}
		);

		return {
			...addedSection,
			rules: RulesSupport.formatRules(addedSection.pages, previousRules)
		};
	}

	return {
		...addField(props, {
			indexes: targetIndexes,
			newField: sourceField,
			pages: deletedState.pages,
			parentFieldName: targetParentFieldName
		}),
		rules: RulesSupport.formatRules(state.pages, previousRules)
	};
};
