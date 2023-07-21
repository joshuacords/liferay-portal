/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayToggle} from '@clayui/form';
import ClayList from '@clayui/list';
import React, {useState} from 'react';

import {DASH} from '../../../utilities/constants';
import EditableField from '../../EditableField';
import RequiredFieldMarker from '../../RequiredFieldMarker';
import FieldSelect from './FieldSelect';
import FieldText from './FieldText';
import FieldToggle from './FieldToggle';

export const Select = props => FieldWrapper(FieldSelect, {...props});
export const Text = props => FieldWrapper(FieldText, {...props});
export const Toggle = props => FieldWrapper(FieldToggle, {...props});

function FieldWrapper(AddressField, props) {
	const {
		editable,
		fieldLabel,
		readOnly,
		readOnlyValue,
		required = false,
		setEditableFn,
		value
	} = props;
	const [fieldEditable, setFieldEditable] = useState(false);

	const displayValue = getDisplayValue();

	function getDisplayValue() {
		if (readOnlyValue) {
			return readOnlyValue;
		}

		if (typeof value === 'boolean') {
			return null;
		}

		if (!value) {
			return DASH;
		}

		return value;
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>
					{fieldLabel} {required && <RequiredFieldMarker />}
				</ClayList.ItemTitle>

				{readOnly && displayValue && (
					<div className="list-group-text">{displayValue}</div>
				)}

				{readOnly && !displayValue && (
					<ClayToggle
						aria-label={fieldLabel}
						disabled
						toggled={value}
					/>
				)}

				{!readOnly && (
					<div className="list-group-text">
						{!editable && displayValue && (
							<div className="inline-edit">
								<div
									onClick={() => setEditableFn(true)}
									onMouseEnter={() => setFieldEditable(true)}
									onMouseLeave={() => setFieldEditable(false)}
								>
									{fieldEditable ? (
										<EditableField value={displayValue} />
									) : (
										displayValue
									)}
								</div>
							</div>
						)}

						{!editable && !displayValue && (
							<ClayToggle
								aria-label={fieldLabel}
								onToggle={() => setEditableFn(true)}
								toggled={value}
							/>
						)}

						{editable && <AddressField {...props} />}
					</div>
				)}
			</div>
		</ClayList.Item>
	);
}
