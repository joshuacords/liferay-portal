/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayToggle} from '@clayui/form';
import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE
} from '../utilities/constants';
import {itemSelectorDialogWrapper} from '../utilities/itemSelectorDialogHelper';
import HiddenForm from './HiddenForm';
import InlineEdit from './InlineEdit';

function DetailField({
	displayAs = 'text',
	displayValue,
	externalData,
	fieldLabel,
	fieldName = fieldLabel,
	formAction,
	formData,
	inputStyle,
	options = [],
	type = FIELD_TYPE_NONEDITABLE,
	value
}) {
	const formRef = useRef();
	const [data, setData] = useState(formData);

	useEffect(() => {
		if (formRef.current && data[fieldName] !== formData[fieldName]) {
			if (
				type === FIELD_TYPE_SELECT ||
				type === FIELD_TYPE_TEXT ||
				type === FIELD_TYPE_TEXTAREA ||
				type === FIELD_TYPE_TOGGLE
			) {
				formRef.current.submit();
			}
		}
	}, [data, fieldName, formData, type]);

	function handleDeleteExternal() {
		if (formRef.current) {
			formRef.current.submit();
		}
	}

	function handleOpenExternal() {
		itemSelectorDialogWrapper(externalData);
	}

	function handleSubmit(value) {
		setData({...formData, [fieldName]: value});
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				{fieldLabel && (
					<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>
				)}

				<div className="list-group-text">
					{type === FIELD_TYPE_NONEDITABLE &&
						displayAs === 'text' && <>{value}</>}

					{type === FIELD_TYPE_NONEDITABLE &&
						displayAs === 'label' && (
							<span className={`label ${inputStyle}`}>
								{value}
							</span>
						)}

					{type === FIELD_TYPE_NONEDITABLE &&
						displayAs === 'toggle' && (
							<ClayToggle
								aria-label={fieldName}
								disabled
								toggled={value}
							/>
						)}

					{type === FIELD_TYPE_EXTERNAL && (
						<>
							<HiddenForm
								fields={data}
								formAction={formAction}
								formName={externalData.formName}
								ref={formRef}
							/>

							<InlineEdit
								deleteFn={handleDeleteExternal}
								displayAs={displayAs}
								displayValue={displayValue}
								fieldName={fieldName}
								fieldValue={value}
								inputStyle={inputStyle}
								options={options}
								saveFn={handleOpenExternal}
								type={type}
							/>
						</>
					)}

					{type !== FIELD_TYPE_EXTERNAL &&
						type !== FIELD_TYPE_NONEDITABLE && (
							<>
								<HiddenForm
									fields={data}
									formAction={formAction}
									ref={formRef}
								/>

								<InlineEdit
									displayAs={displayAs}
									displayValue={displayValue}
									fieldName={fieldName}
									fieldValue={value}
									inputStyle={inputStyle}
									options={options}
									saveFn={handleSubmit}
									type={type}
								/>
							</>
						)}
				</div>
			</div>
		</ClayList.Item>
	);
}

DetailField.propTypes = {
	displayAs: PropTypes.oneOf(['label', 'text', 'toggle']),
	displayValue: PropTypes.string,
	externalData: PropTypes.shape({
		formField: PropTypes.string,
		formName: PropTypes.string,
		title: PropTypes.string,
		url: PropTypes.string
	}),
	fieldLabel: PropTypes.string,
	fieldName: PropTypes.string,
	formAction: PropTypes.string,
	formData: PropTypes.object,
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_NONEDITABLE,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TEXTAREA,
		FIELD_TYPE_TOGGLE
	]),
	value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string]).isRequired
};

export default DetailField;
