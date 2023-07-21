/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {DASH, NAMESPACE} from '../../../utilities/constants';

function FieldSelect({
	fieldName,
	onChangeFn,
	options,
	required = false,
	value
}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange(event) {
		onChangeFn(fieldName, event.currentTarget.value);
	}

	return (
		<label className="form-control-label" htmlFor={namespacedFieldName}>
			<select
				className="form-control"
				disabled={options.length === 0}
				id={namespacedFieldName}
				name={namespacedFieldName}
				onChange={handleOnChange}
				required={required}
				value={value}
			>
				<option value="">{DASH}</option>
				{options.map((option, index) => (
					<option key={option.name || index} value={option.name}>
						{option.name}
					</option>
				))}
			</select>
		</label>
	);
}

FieldSelect.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	options: PropTypes.arrayOf(PropTypes.shape({name: PropTypes.string}))
		.isRequired,
	required: PropTypes.bool,
	value: PropTypes.string.isRequired
};

export default FieldSelect;
