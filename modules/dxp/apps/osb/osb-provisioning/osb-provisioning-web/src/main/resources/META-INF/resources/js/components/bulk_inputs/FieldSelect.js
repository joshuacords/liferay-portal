/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
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
