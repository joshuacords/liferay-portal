/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useEffect, useRef, useState} from 'react';

import {pipe} from '../../utilities/index.es';
import Icon from '../utilities/Icon.es';
import List from './List.es';

export function SelectedData(props) {
	return (
		<div className="commerce-datalist__values">
			{props.data &&
				props.data.map((selectedElement, i) => (
					<span className="commerce-datalist__value" key={i}>
						{selectedElement.label}
						<button
							className="commerce-datalist__delete-value"
							onClick={e =>
								props.updateElementState(
									e,
									{
										label: selectedElement.label,
										value: selectedElement.value
									},
									false
								)
							}
							type="button"
						>
							<Icon spritemap={props.spritemap} symbol="close" />
						</button>
					</span>
				))}
		</div>
	);
}

export function BaseDatalist(props) {
	const [dropdownState, setDropdownState] = useState('collapsed');
	const [selectedData, updateSelectedData] = useState([]);
	const [selectedValues, updateSelectedValues] = useState([]);
	const [query, setQuery] = useState(props.value || null);

	const containerClasses = `commerce-datalist${
		props.additionalClasses ? ` ${props.additionalClasses}` : ''
	}${props.disabled ? ` is-disabled` : ''}`;
	const inputWrapperClasses = `commerce-datalist__input-wrapper${
		dropdownState ? ` is-${dropdownState}` : ''
	}`;
	const dropdownClasses = `commerce-datalist__dropdown${
		dropdownState ? ` is-${dropdownState}` : ''
	}`;

	const datalistRef = useRef();
	const dropdownRef = useRef();
	const addedDataContainer = useRef();

	const handleChangeAndUpdateData = pipe(
		handleValueChange,
		updateSelectedData
	);

	function handleValueChange(newValues) {
		props.emit('selectedValuesChanged', newValues);

		return newValues;
	}

	function handleOutsideClick(e) {
		e.preventDefault();
		if (!datalistRef.current.contains(e.target)) {
			collapseDropdown();
		}
	}

	function updateElementState(e, listElement, toBeAdded = true) {
		if (props.multiselect) {
			handleChangeAndUpdateData(
				toBeAdded
					? [...selectedData, listElement]
					: selectedData.filter(el => el.label !== listElement.label)
			);
		}
		else {
			handleChangeAndUpdateData(listElement.value ? [listElement] : []);
			setQuery(listElement.label);
		}
		collapseDropdown();
	}

	function handleInputChange(e) {
		updateQuery(e.target.value);
	}

	function updateQuery(value = null) {
		setQuery(value);
		props.emit('queryUpdated', value);
	}

	function resetState() {
		updateQuery();
		handleChangeAndUpdateData([]);
		collapseDropdown();
	}

	function resetQueryAndCollapse() {
		updateQuery();
		collapseDropdown();
	}

	function expandDropdown() {
		setDropdownState('expanding');
		dropdownRef.current.addEventListener(
			'animationend',
			handleOpeningAnimationEnd,
			{
				once: true
			}
		);
	}

	function handleOpeningAnimationEnd() {
		setDropdownState('expanded');
		window.addEventListener('click', handleOutsideClick, {once: true});
	}

	function collapseDropdown() {
		setDropdownState('collapsing');
		dropdownRef.current.addEventListener(
			'animationend',
			handleClosingnimationEnd,
			{
				once: true
			}
		);
	}

	function handleClosingnimationEnd() {
		setDropdownState('collapsed');
	}

	useEffect(() => {
		const newSelectedValues = selectedData
			? selectedData.map(el => el.value)
			: [];
		if (
			JSON.stringify(selectedValues) !== JSON.stringify(newSelectedValues)
		) {
			updateSelectedValues(newSelectedValues);
			scrollAddedDataContainer();
		}
	}, [selectedData, selectedValues]);

	function scrollAddedDataContainer() {
		if (addedDataContainer.current) {
			setTimeout(() => {
				addedDataContainer.current.scrollTo({
					behaviour: 'smooth',
					left: Number.MAX_SAFE_INTEGER,
					top: 0
				});
			}, 50);
		}
	}

	return (
		<div className={containerClasses} ref={datalistRef}>
			{props.label && (
				<label className="commerce-modal__label" htmlFor={props.Id}>
					{props.label}
				</label>
			)}
			<div
				className={inputWrapperClasses}
				onClick={e =>
					dropdownState === 'collapsed' && expandDropdown(e)
				}
			>
				<div className="commerce-datalist__mask">
					<div
						className="commerce-datalist__query-wrapper"
						ref={addedDataContainer}
					>
						{props.multiselect && (
							<SelectedData
								data={selectedData}
								spritemap={props.spritemap}
								updateElementState={updateElementState}
							/>
						)}
						<input
							autoComplete="off"
							className="commerce-datalist__query-input"
							id={props.inputId}
							onChange={handleInputChange}
							onFocus={e =>
								dropdownState === 'collapsed' &&
								expandDropdown(e)
							}
							placeholder={props.placeholder}
							type="text"
							value={query || ''}
						/>
					</div>
				</div>
				<span className="commerce-datalist__icon-wrapper">
					<button
						className="commerce-datalist__icon commerce-datalist__icon--reset"
						onClick={resetQueryAndCollapse}
					>
						<Icon spritemap={props.spritemap} symbol="reset" />
					</button>
					<span className="commerce-datalist__icon commerce-datalist__icon--default">
						<Icon spritemap={props.spritemap} symbol="chevron" />
					</span>
				</span>
			</div>

			<div className={dropdownClasses} ref={dropdownRef}>
				<div className="commerce-datalist__data">
					<List
						disabled
						query={query}
						resetState={resetState}
						selectedData={selectedData}
						updateElementState={updateElementState}
						{...props}
					/>
				</div>
			</div>
		</div>
	);
}

export default BaseDatalist;
