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
