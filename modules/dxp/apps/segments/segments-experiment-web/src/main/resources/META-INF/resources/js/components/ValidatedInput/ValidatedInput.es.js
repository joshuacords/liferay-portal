/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import getCN from 'classnames';
import PropTypes from 'prop-types';
import React, {useRef, useState} from 'react';

function _isValueValid(value) {
	const noSpacesValue = value.replace(/\s/g, '');

	return !!noSpacesValue;
}

function ValidatedInput(props) {
	const {
		errorMessage,
		label,
		onBlur = () => {},
		onChange = () => {},
		onFocus = () => {},
		onValidationChange = () => {},
		value = ''
	} = props;

	const [invalid, setInvalid] = useState(false);
	const node = useRef();

	const formGroupClasses = getCN('form-group w-100', {
		'has-error': invalid
	});

	return (
		<label className={formGroupClasses}>
			{label && (
				<>
					{label}
					<ClayIcon
						className="ml-1 reference-mark text-warning"
						symbol="asterisk"
					/>
				</>
			)}

			<input
				className="form-control mt-1"
				maxLength="75"
				onBlur={_handleNameInputBlur}
				onChange={onChange}
				onFocus={_handleNameInputFocus}
				ref={node}
				type="text"
				value={value}
			/>
			{invalid && errorMessage && (
				<div className="form-feedback-group">
					<div className="form-feedback-item">{errorMessage}</div>
				</div>
			)}
		</label>
	);

	function _handleNameInputBlur(event) {
		if (!_isValueValid(value)) {
			_setInvalid(true);
		}
		onBlur(event);
	}
	function _handleNameInputFocus(event) {
		_setInvalid(false);
		onFocus(event);
	}

	function _setInvalid(newInvalid) {
		setInvalid(newInvalid);
		if (newInvalid !== invalid) {
			onValidationChange(newInvalid);
		}
	}
}

ValidatedInput.propTypes = {
	errorMessage: PropTypes.string,
	label: PropTypes.string,
	onBlur: PropTypes.func,
	onChange: PropTypes.func,
	onFocus: PropTypes.func,
	onValidationChange: PropTypes.func,
	value: PropTypes.string
};

export default ValidatedInput;
