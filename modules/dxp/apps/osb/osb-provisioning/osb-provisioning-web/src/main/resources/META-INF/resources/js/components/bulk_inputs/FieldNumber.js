/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

const FieldNumber = React.forwardRef(
	({changeHandler, fieldName, max = null, min = null, value}, ref) => (
		<label htmlFor={fieldName} ref={ref}>
			<input
				aria-label={fieldName}
				className="form-control form-control-sm"
				id={fieldName}
				max={max}
				min={min}
				onChange={changeHandler}
				type="number"
				value={value}
			/>
		</label>
	)
);

export default FieldNumber;
