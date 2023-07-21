/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

const FieldNumberWithLabel = React.forwardRef(
	(
		{
			changeHandler,
			fieldDisabled,
			fieldName,
			labelName,
			max = null,
			min = null,
			value
		},
		ref
	) => (
		<label htmlFor={fieldName}>
			<div className="input-group" id="endDateBulkInput">
				<div className="input-group-item">
					<input
						aria-label={fieldName}
						className="form-control form-control-sm input-group-inset input-group-inset-after"
						disabled={fieldDisabled}
						id={fieldName}
						max={max}
						min={min}
						onChange={changeHandler}
						ref={ref}
						type="number"
						value={value}
					/>
				</div>
				<div
					className={`${
						fieldDisabled ? 'disabled' : ''
					} input-group-inset-item input-group-inset-item-after`}
				>
					{labelName}
				</div>
			</div>
		</label>
	)
);

export default FieldNumberWithLabel;
