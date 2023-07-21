/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayDropDown from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

export default function DropdownMultiSelect({
	addFn,
	allOptions = [],
	newOptions = [],
	removeFn
}) {
	const [active, setActive] = useState(false);

	const displayOptions = allOptions.filter(
		option => !newOptions.includes(option.key)
	);
	const processedAllOptions = allOptions.reduce(
		(options, option) => ({...options, [option.key]: option}),
		{}
	);

	const triggerElement = (
		<div className="input-group input-group-stacked-sm-down">
			<div className={`input-group-item ${active ? 'input-focus' : ''}`}>
				<div className="form-control form-control-tag-group input-group-inset input-group-inset-after">
					{newOptions.map(
						optionKey =>
							processedAllOptions[optionKey] && (
								<Label
									key={optionKey}
									name={processedAllOptions[optionKey].name}
									removeFn={event => {
										// Stops the click event on the label's close button from propagating up and triggering the dropdown.

										event.stopPropagation();

										removeFn(optionKey);
									}}
								/>
							)
					)}
				</div>

				<div className="input-group-inset-item input-group-inset-item-after">
					<button
						className="btn btn-unstyled"
						onClick={event => {
							event.preventDefault();

							setActive(!active);
						}}
						tabIndex="0"
						title={Liferay.Language.get('add')}
					>
						<svg
							aria-label={Liferay.Language.get('select')}
							className="lexicon-icon lexicon-icon-caret-double"
							role="img"
						>
							<use xlinkHref="#caret-double" />
						</svg>
					</button>
				</div>
			</div>
		</div>
	);

	function handleOnActiveChange(val) {
		const newVal = displayOptions.length ? val : false;

		setActive(newVal);
	}

	return (
		<ClayDropDown
			active={active}
			onActiveChange={handleOnActiveChange}
			trigger={triggerElement}
		>
			<ClayDropDown.ItemList className="multi-select-dropdown">
				<ClayDropDown.Group>
					{displayOptions.map(option => (
						<ClayDropDown.Item
							key={option.key}
							onClick={() => addFn(option.key)}
						>
							{option.name}
						</ClayDropDown.Item>
					))}
				</ClayDropDown.Group>
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);
}

DropdownMultiSelect.propTypes = {
	addFn: PropTypes.func.isRequired,
	allOptions: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	newOptions: PropTypes.arrayOf(PropTypes.string),
	removeFn: PropTypes.func.isRequired
};

function Label({name, removeFn}) {
	return (
		<span className="label label-lg label-secondary">
			<span className="label-item label-item-expand">{name}</span>
			<span className="label-item label-item-after">
				<button
					className="close"
					onClick={removeFn}
					tabIndex="0"
					title={Liferay.Language.get('delete')}
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('close')}
						className="lexicon-icon lexicon-icon-times reference-mark"
						role="img"
					>
						<use xlinkHref="#times" />
					</svg>
				</button>
			</span>
		</span>
	);
}

Label.propTypes = {
	name: PropTypes.string.isRequired,
	removeFn: PropTypes.func.isRequired
};
