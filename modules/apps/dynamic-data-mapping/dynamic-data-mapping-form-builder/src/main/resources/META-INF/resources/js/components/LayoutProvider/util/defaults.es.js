/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {normalizeFieldName} from 'dynamic-data-mapping-form-renderer/js/util/fields.es';

export const shouldAutoGenerateName = (
	defaultLanguageId,
	editingLanguageId,
	focusedField
) => {
	const {fieldName, label} = focusedField;

	return (
		defaultLanguageId === editingLanguageId &&
		fieldName.indexOf(normalizeFieldName(label)) === 0
	);
};
