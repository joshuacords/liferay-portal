/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const checkInvalidFieldNameCharacter = character => {
	return /[\\~`!@#$%^&*(){}[\];:"'<,.>?/\-+=|]/g.test(character);
};

export function normalizeFieldName(fieldName) {
	let nextUpperCase = false;
	let normalizedFieldName = '';

	fieldName = fieldName.trim();

	for (let i = 0; i < fieldName.length; i++) {
		let item = fieldName[i];

		if (item === ' ') {
			nextUpperCase = true;

			continue;
		}
		else if (checkInvalidFieldNameCharacter(item)) {
			continue;
		}

		if (nextUpperCase) {
			item = item.toUpperCase();

			nextUpperCase = false;
		}

		normalizedFieldName += item;
	}

	if (/^\d/.test(normalizedFieldName)) {
		normalizedFieldName = `_${normalizedFieldName}`;
	}

	return normalizedFieldName;
}
