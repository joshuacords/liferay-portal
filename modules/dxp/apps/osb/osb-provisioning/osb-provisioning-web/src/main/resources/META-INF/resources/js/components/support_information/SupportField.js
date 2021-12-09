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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import {request} from '../../utilities/helpers';
import InlineEdit from '../InlineEdit';

function SupportField({
	displayValue,
	fieldLabel,
	fieldName = fieldLabel,
	formAction,
	inputStyle,
	options = [],
	type = FIELD_TYPE_NONEDITABLE,
	value,
	updateFormData
}) {
	function handleSave(newValue) {
		request(formAction, updateFormData(newValue), 'formData')
			.then(data => {
				if (data.successMessage) {
					// Refresh the page to mimic the same user experience as DetailField for a consistent behavior across all fields even though the AJAX submission makes it possible to update the field value without refreshing the page.

					location.reload();
				}
			})
			.catch(err =>
				console.error(`Request to update field failed with: ${err}`)
			);
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				{fieldLabel && (
					<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>
				)}

				<div className="list-group-text">
					{type === FIELD_TYPE_NONEDITABLE && (
						<>{displayValue ? displayValue : value}</>
					)}

					{type !== FIELD_TYPE_NONEDITABLE && (
						<InlineEdit
							displayValue={displayValue}
							fieldName={fieldName}
							fieldValue={value}
							inputStyle={inputStyle}
							options={options}
							saveFn={handleSave}
							type={type}
						/>
					)}
				</div>
			</div>
		</ClayList.Item>
	);
}

SupportField.propTypes = {
	displayValue: PropTypes.string,
	fieldLabel: PropTypes.string,
	fieldName: PropTypes.string,
	formAction: PropTypes.string,
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
	updateFormData: PropTypes.func,
	value: PropTypes.string.isRequired
};

export default SupportField;
