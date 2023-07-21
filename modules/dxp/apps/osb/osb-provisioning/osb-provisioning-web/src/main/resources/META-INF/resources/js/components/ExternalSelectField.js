/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

import {FIELD_SIZE_DEFAULT, FIELD_SIZE_SMALL} from '../utilities/constants';

function ExternalSelectField({
	clickFn,
	deleteFn,
	id,
	inputSize = FIELD_SIZE_DEFAULT,
	value,
	...otherProps
}) {
	function handleClick() {
		clickFn();
	}

	function handleDelete() {
		deleteFn();
	}

	return (
		<div className="external-select-field">
			{!!id && (
				<label className="form-control-label" htmlFor={id}>
					<input
						className={`form-control ${
							inputSize === FIELD_SIZE_SMALL
								? 'form-control-sm'
								: ''
						}`}
						disabled
						id={id}
						type="text"
						value={value}
						{...otherProps}
					/>
				</label>
			)}

			<button
				className="btn btn-secondary btn-sm"
				onClick={handleClick}
				role="button"
				title={Liferay.Language.get('select')}
				type="button"
			>
				{Liferay.Language.get('select')}
			</button>

			{!!deleteFn && (
				<button
					className="btn btn-icon btn-sm"
					onClick={handleDelete}
					role="button"
					title={Liferay.Language.get('delete')}
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('delete-field-icon')}
						className="delete-icon"
						role="img"
					>
						<use xlinkHref="#trash" />
					</svg>
				</button>
			)}
		</div>
	);
}

ExternalSelectField.propTypes = {
	clickFn: PropTypes.func.isRequired,
	deleteFn: PropTypes.func,
	id: PropTypes.string,
	inputSize: PropTypes.oneOf([FIELD_SIZE_DEFAULT, FIELD_SIZE_SMALL]),
	value: PropTypes.string
};

export default ExternalSelectField;
