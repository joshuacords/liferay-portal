/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export function convertString(string) {
	try {
		return window.Liferay.Language.get(string);
	}
	catch (error) {
		return string;
	}
}

function LocalizedText(props) {
	return <>{convertString(props.children)}</>;
}

export default LocalizedText;
