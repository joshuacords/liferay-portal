/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayToggle} from '@clayui/form';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {
	DASH,
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../utilities/constants';
import {convertDashToEmptyString} from '../utilities/helpers';
import EditableField from './EditableField';
import ExternalSelectField from './ExternalSelectField';

function InlineEdit({
	deleteFn,
	displayAs = 'text',
	displayValue,
	fieldName,
	fieldValue,
	inputStyle = '',
	options = [{label: '', value: ''}],
	saveFn,
	type = FIELD_TYPE_TEXT
}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [showEditor, setShowEditor] = useState(false);
	const [value, setValue] = useState(fieldValue);

	useEffect(() => {
		setShowEditor(false);
	}, [fieldValue]);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function getDisplayValue() {
		return displayValue ? displayValue : value;
	}

	function handleChange(event) {
		setValue(event.currentTarget.value);
	}

	function handleClick() {
		saveFn(value);

		setFieldEditable(false);
		setShowEditor(false);
	}

	function handleDelete() {
		deleteFn();
	}

	function handleReset() {
		setFieldEditable(false);
		setShowEditor(false);
		setValue(fieldValue);
	}

	function handleToggle() {
		setShowEditor(true);
		setValue(!convertDashToEmptyString(value));
	}

	return (
		<div className={`inline-edit ${showEditor ? 'block' : ''}`}>
			{!showEditor && type !== FIELD_TYPE_TOGGLE && (
				<div
					onClick={() => setShowEditor(true)}
					onMouseEnter={() => setFieldEditable(true)}
					onMouseLeave={() => setFieldEditable(false)}
				>
					{fieldEditable && (
						<EditableField value={getDisplayValue()} />
					)}

					{!fieldEditable &&
						(displayAs === 'label' ? (
							<Label inputStyle={inputStyle} value={value} />
						) : (
							getDisplayValue()
						))}
				</div>
			)}

			{showEditor && type !== FIELD_TYPE_TOGGLE && (
				<>
					{type === FIELD_TYPE_EXTERNAL && (
						<ExternalSelectField
							clickFn={handleClick}
							deleteFn={handleDelete}
							id={namespacedFieldName}
							value={value}
						/>
					)}

					{type === FIELD_TYPE_SELECT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<select
								className="form-control"
								disabled={options.length === 0}
								id={namespacedFieldName}
								onChange={handleChange}
								value={value}
							>
								<option key={DASH} value="">
									{DASH}
								</option>

								{options.map(option => (
									<option
										key={option.value}
										value={option.value}
									>
										{option.label}
									</option>
								))}
							</select>
						</label>
					)}

					{type === FIELD_TYPE_TEXT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<input
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={value}
							/>
						</label>
					)}

					{type === FIELD_TYPE_TEXTAREA && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<textarea
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={value}
							/>
						</label>
					)}

					<ButtonControls
						clickHandler={handleClick}
						disabled={fieldValue !== DASH && value === fieldValue}
						resetHandler={handleReset}
					/>
				</>
			)}

			{type === FIELD_TYPE_TOGGLE && (
				<>
					<ClayToggle
						aria-label={fieldName}
						onToggle={handleToggle}
						toggled={value}
					/>

					{showEditor && (
						<ButtonControls
							clickHandler={handleClick}
							disabled={value === fieldValue}
							resetHandler={handleReset}
						/>
					)}
				</>
			)}
		</div>
	);
}

function ButtonControls({clickHandler, disabled, resetHandler}) {
	return (
		<div className="button-holder button-holder-sm" role="group">
			<button
				className="btn btn-primary btn-sm save-btn"
				disabled={disabled}
				onClick={clickHandler}
				role="button"
				type="button"
			>
				{Liferay.Language.get('save')}
			</button>

			<button
				className="btn btn-secondary btn-sm cancel-btn"
				onClick={resetHandler}
				role="button"
				type="button"
			>
				{Liferay.Language.get('cancel')}
			</button>
		</div>
	);
}

function Label({inputStyle, value}) {
	return <span className={`label ${inputStyle}`}>{value}</span>;
}

InlineEdit.propTypes = {
	deleteFn: PropTypes.func,
	displayAs: PropTypes.oneOf(['label', 'text', 'toggle']),
	displayValue: PropTypes.string,
	fieldName: PropTypes.string,
	fieldValue: PropTypes.oneOfType([PropTypes.bool, PropTypes.string]),
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	saveFn: PropTypes.func.isRequired,
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TEXTAREA,
		FIELD_TYPE_TOGGLE
	])
};

export default InlineEdit;
