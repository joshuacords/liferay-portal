/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

const FieldSelect = React.forwardRef(
	({changeHandler, fieldDisabled, fieldName, options, value}, ref) => (
		<label htmlFor={fieldName} ref={ref}>
			<select
				aria-label={fieldName}
				className="form-control form-control-sm"
				disabled={fieldDisabled}
				id={fieldName}
				onChange={changeHandler}
				value={value}
			>
				{options.map(option => (
					<option key={option} value={option}>
						{option}
					</option>
				))}
			</select>
		</label>
	)
);

export default FieldSelect;
