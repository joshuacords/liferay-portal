/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayCheckbox} from '@clayui/form';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';

const BOOTSTRAP_GRID_COL_NUM = 12;

function CheckboxGroups({
	columns = 1,
	fieldValues,
	inputName,
	namespace = NAMESPACE
}) {
	const [values, setValues] = useState(getDefaultValues());

	function getDefaultValues() {
		const defaultValues = [];

		fieldValues.forEach(field => {
			if (field.checked) {
				defaultValues.push(field.value);
			}
		});

		return defaultValues;
	}

	function handleOnChange(event) {
		const currentValue = event.currentTarget.value;

		if (!values.includes(currentValue)) {
			setValues([...values, currentValue]);
		}
		else {
			setValues(values.filter(value => value !== currentValue));
		}
	}

	return (
		<>
			<input
				name={`${namespace}${inputName}`}
				type="hidden"
				value={values.join()}
			/>

			{!!fieldValues && (
				<div className="row">
					{fieldValues.map(field => (
						<div
							className={`col-md-${BOOTSTRAP_GRID_COL_NUM /
								columns}`}
							key={field.value}
						>
							<Checkbox field={field} updateFn={handleOnChange} />
						</div>
					))}
				</div>
			)}
		</>
	);
}

function Checkbox({field, updateFn}) {
	const [checked, setChecked] = useState(!!field.checked);

	function handleOnChange(event) {
		setChecked(!checked);

		updateFn(event);
	}

	return (
		<ClayCheckbox
			aria-label={field.label}
			checked={checked}
			label={field.label}
			onChange={handleOnChange}
			value={field.value}
		/>
	);
}

CheckboxGroups.propTypes = {
	columns: PropTypes.number,
	fieldValues: PropTypes.arrayOf(
		PropTypes.shape({
			checked: PropTypes.bool,
			label: PropTypes.string,
			value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string])
		})
	).isRequired,
	inputName: PropTypes.string.isRequired,
	namespace: PropTypes.string
};

export default CheckboxGroups;
