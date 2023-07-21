/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import {ClayRadio, ClayRadioGroup, ClayToggle} from '@clayui/form';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

function formatValue(itemValue, items, exclude) {
	return (
		(exclude ? `(${Liferay.Language.get('exclude')}) ` : '') +
		items.find(item => item.value === itemValue).label
	);
}

function getOdataString(value, key, exclude) {
	return `${key} ${exclude ? 'ne' : 'eq'} ${
		typeof value === 'string' ? `'${value}'` : value
	}`;
}

function isDefined(itemValue) {
	return itemValue !== undefined && itemValue !== null;
}
function RadioFilter(props) {
	const [itemValue, setItemValue] = useState(
		props.value && props.value.itemValue
	);
	const [exclude, setExclude] = useState(props.value && props.value.exclude);

	const actionType = props.value ? 'edit' : 'add';

	let submitDisabled = true;

	if (
		(!props.value && isDefined(itemValue)) ||
		(props.value && props.value.itemValue !== itemValue) ||
		(props.value && isDefined(itemValue) && props.value.exclude !== exclude)
	) {
		submitDisabled = false;
	}

	return (
		<>
			<ClayDropDown.Caption className="pb-0">
				<div className="row">
					<div className="col">
						<label htmlFor={`autocomplete-exclude-${props.id}`}>
							{Liferay.Language.get('exclude')}
						</label>
					</div>
					<div className="col-auto">
						<ClayToggle
							id={`autocomplete-exclude-${props.id}`}
							onToggle={() => setExclude(!exclude)}
							toggled={exclude}
						/>
					</div>
				</div>
			</ClayDropDown.Caption>
			<ClayDropDown.Divider />
			<ClayDropDown.Caption>
				<div className="inline-scroller mb-n2 mx-n2 px-2">
					<ClayRadioGroup
						onSelectedValueChange={setItemValue}
						selectedValue={isDefined(itemValue) ? itemValue : ''}
					>
						{props.items.map(item => (
							<ClayRadio
								key={item.value}
								label={item.label}
								value={item.value}
							/>
						))}
					</ClayRadioGroup>
				</div>
			</ClayDropDown.Caption>
			<ClayDropDown.Divider />
			<ClayDropDown.Caption>
				<ClayButton
					disabled={submitDisabled}
					onClick={() =>
						actionType !== 'delete'
							? props.actions.updateFilterState(
									props.id,
									{
										exclude,
										itemValue
									},
									formatValue(
										itemValue,
										props.items,
										exclude
									),
									getOdataString(itemValue, props.id, exclude)
							  )
							: props.actions.updateFilterState(props.id)
					}
					small
				>
					{actionType === 'add' && Liferay.Language.get('add-filter')}
					{actionType === 'edit' &&
						Liferay.Language.get('edit-filter')}
				</ClayButton>
			</ClayDropDown.Caption>
		</>
	);
}

RadioFilter.propTypes = {
	id: PropTypes.string.isRequired,
	invisible: PropTypes.bool,
	items: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
		})
	),
	label: PropTypes.string.isRequired,
	type: PropTypes.oneOf(['radio']).isRequired,
	value: PropTypes.shape({
		exclude: PropTypes.bool,
		itemValue: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
	})
};

export default RadioFilter;
