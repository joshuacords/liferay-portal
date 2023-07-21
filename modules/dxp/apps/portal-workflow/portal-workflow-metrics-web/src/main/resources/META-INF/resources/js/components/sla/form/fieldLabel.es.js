/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import Icon from '../../../shared/components/Icon.es';

const FieldLabel = ({fieldId, required, text}) => (
	<label htmlFor={fieldId}>
		{`${text} `}
		{required && (
			<span className="reference-mark">
				<Icon iconName="asterisk" />
			</span>
		)}
	</label>
);

export default FieldLabel;
