/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {updateField} from './fieldEditedHandler.es';

export const handleFieldBlurred = (state, event) => {
	const {propertyName, propertyValue} = event;
	let newState = {pages: state.pages};

	if (propertyName === 'name' && propertyValue === '') {
		newState = updateField(state, propertyName, propertyValue);
	}

	return newState;
};

export default handleFieldBlurred;
