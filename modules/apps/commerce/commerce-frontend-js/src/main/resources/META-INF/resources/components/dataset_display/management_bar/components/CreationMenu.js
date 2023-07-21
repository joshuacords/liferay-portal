/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayButtonWithIcon} from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React, {useContext, useState} from 'react';

import {triggerAction} from '../../../../utilities/actionItems/index';
import DatasetDisplayContext from '../../DatasetDisplayContext';

function CreationMenu(props) {
	const [active, setActive] = useState(false);
	const datasetContext = useContext(DatasetDisplayContext);

	return (
		props.items &&
		props.items.length && (
			<ul className="navbar-nav">
				<li className="nav-item">
					{props.items.length > 1 ? (
						<ClayDropDown
							active={active}
							onActiveChange={setActive}
							trigger={<ClayButtonWithIcon symbol="plus" />}
						>
							<ClayDropDown.ItemList>
								{props.items.map((item, i) => (
									<ClayDropDown.Item
										key={i}
										onClick={e => {
											e.preventDefault();
											setActive(false);
											triggerAction(item, datasetContext);
										}}
									>
										{item.label}
									</ClayDropDown.Item>
								))}
							</ClayDropDown.ItemList>
						</ClayDropDown>
					) : (
						<ClayButtonWithIcon
							onClick={() =>
								triggerAction(props.items[0], datasetContext)
							}
							symbol="plus"
						/>
					)}
				</li>
			</ul>
		)
	);
}

CreationMenu.propTypes = {
	items: PropTypes.arrayOf(
		PropTypes.shape({
			href: PropTypes.string.isRequired,
			label: PropTypes.string.isRequired,
			target: PropTypes.oneOf(['modal', 'sidePanel', 'event', 'link'])
		})
	).isRequired
};

export default CreationMenu;
