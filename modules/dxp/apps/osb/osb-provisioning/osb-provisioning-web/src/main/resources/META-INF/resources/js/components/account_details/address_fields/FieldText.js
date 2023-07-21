/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {NAMESPACE} from '../../../utilities/constants';

function AddressTextInput({fieldName, onChangeFn, required = false, value}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange(event) {
		onChangeFn(fieldName, event.currentTarget.value);
	}

	return (
		<label className="form-control-label" htmlFor={namespacedFieldName}>
			<input
				className="form-control"
				id={namespacedFieldName}
				name={namespacedFieldName}
				onChange={handleOnChange}
				required={required}
				type="text"
				value={value}
			/>
		</label>
	);
}

AddressTextInput.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	required: PropTypes.bool,
	value: PropTypes.string.isRequired
};

export default AddressTextInput;
