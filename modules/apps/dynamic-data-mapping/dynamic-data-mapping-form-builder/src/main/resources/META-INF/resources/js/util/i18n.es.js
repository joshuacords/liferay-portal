/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {capitalize} from './strings.es';

export const setValue = (obj, languageId, prop, val) => {
	if (!obj[prop]) {
		obj[prop] = {};
	}

	obj[prop][languageId] = val;

	return obj;
};

export const setLocalizedValue = (obj, languageId, prop, val) => {
	obj[prop] = val;

	const localizedProperty = `localized${capitalize(prop)}`;

	return setValue(obj, languageId, localizedProperty, val);
};
