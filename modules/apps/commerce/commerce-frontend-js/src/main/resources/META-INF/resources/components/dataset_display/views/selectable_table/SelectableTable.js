/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayCheckbox} from '@clayui/form';
import ClayTable from '@clayui/table';
import PropTypes from 'prop-types';
import React, {useContext, useEffect, useState} from 'react';

import DatasetDisplayContext from '../../DatasetDisplayContext';

function SelectableTable(props) {
	const {namespace} = useContext(DatasetDisplayContext);
	const {selectedItemsKey} = useContext(DatasetDisplayContext);

	const [items, updateItems] = useState(props.items);

	useEffect(() => {
		updateItems(props.items);
	}, [props.items]);

	function handleCheckboxChange(itemField, itemId, value) {
		const updatedItems = items.map(item => {
			const currentItemId = item[selectedItemsKey];
			if (!itemId || currentItemId === itemId) {
				return {
					...item,
					fields: item.fields.map(currentField => {
						if (itemField !== currentField.name) {
							return currentField;
						}

						return {
							...currentField,
							value:
								typeof value === 'boolean'
									? value
									: !currentField.value
						};
					})
				};
			}

			return item;
		});

		updateItems(updatedItems);
	}

	return (
		<div className={`table-style-${props.style}`}>
			<ClayTable borderless hover={false} responsive={false}>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTable.Cell
							className="table-cell-expand-smaller"
							headingCell
							headingTitle
						>
							{props.schema.firstColumnLabel}
						</ClayTable.Cell>
						{items[0].fields.map(columnField => {
							const checkedItems = items.reduce(
								(checked, item) => {
									const field = item.fields.find(
										itemField =>
											itemField.name === columnField.name
									);

									return checked + (field.value ? 1 : 0);
								},
								0
							);

							return (
								<ClayTable.Cell
									className="table-cell-expand-smaller"
									headingCell
									headingTitle
									key={columnField.name}
								>
									<ClayCheckbox
										checked={checkedItems === items.length}
										className="mr-2"
										indeterminate={
											checkedItems > 0 &&
											checkedItems < items.length
										}
										label={columnField.label}
										name={`${columnField.name}_column`}
										onChange={() =>
											handleCheckboxChange(
												columnField.name,
												null,
												checkedItems === items.length
													? false
													: true
											)
										}
									/>
								</ClayTable.Cell>
							);
						})}
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					{items.map((item, i) => {
						const itemId = item[selectedItemsKey];

						return (
							<ClayTable.Row key={i}>
								<ClayTable.Cell>
									{item[props.schema.firstColumnName]}
								</ClayTable.Cell>
								{item.fields.map(field => {
									return (
										<ClayTable.Cell key={field.name}>
											<ClayCheckbox
												checked={field.value}
												name={namespace + itemId}
												onChange={() => {
													handleCheckboxChange(
														field.name,
														itemId
													);
												}}
												value={field.name}
											/>
										</ClayTable.Cell>
									);
								})}
							</ClayTable.Row>
						);
					})}
				</ClayTable.Body>
			</ClayTable>
		</div>
	);
}

SelectableTable.propTypes = {
	items: PropTypes.arrayOf(PropTypes.object),
	itemsActions: PropTypes.array,
	schema: PropTypes.shape({
		firstColumnLabel: PropTypes.string.isRequired,
		firstColumnName: PropTypes.string.isRequired
	}).isRequired,
	style: PropTypes.string.isRequired
};

SelectableTable.defaultProps = {
	items: []
};

export default SelectableTable;
