/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {NAMESPACE} from '../../../utilities/constants';

function AddressToggle({fieldName, onChangeFn, value = false}) {
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleOnChange() {
		onChangeFn(fieldName, !value);
	}

	return (
		<label
			className="simple-toggle-switch toggle-switch"
			htmlFor={namespacedFieldName}
		>
			<span className="toggle-switch-check-bar">
				<input
					aria-label={fieldName}
					checked={value}
					className="toggle-switch-check"
					id={namespacedFieldName}
					name={namespacedFieldName}
					onChange={handleOnChange}
					type="checkbox"
					value={value}
				/>
				<span aria-hidden="true" className="toggle-switch-bar">
					<span className="toggle-switch-handle"></span>
				</span>
			</span>
		</label>
	);
}

AddressToggle.propTypes = {
	fieldName: PropTypes.string.isRequired,
	onChangeFn: PropTypes.func.isRequired,
	value: PropTypes.bool.isRequired
};

export default AddressToggle;
