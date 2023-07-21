/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Align, ClayDropDownWithItems} from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React from 'react';

import {
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../../utilities/constants';

function PanelDropdownMenu({
	id,
	onArchive,
	onEdit,
	onPinning,
	pinned = false,
	status = NOTE_STATUS_APPROVED,
	tabType = NOTE_TYPE_GENERAL
}) {
	const generateDropdownItems = () => {
		const dropdownItems = [];

		if (status === NOTE_STATUS_APPROVED) {
			dropdownItems.push({
				label: Liferay.Language.get('edit'),
				onClick: onEdit
			});

			if (tabType === NOTE_TYPE_GENERAL) {
				dropdownItems.push({
					label: pinned
						? Liferay.Language.get('unpin')
						: Liferay.Language.get('pin'),
					onClick: onPinning
				});
			}

			dropdownItems.push({
				label: Liferay.Language.get('archive'),
				onClick: onArchive
			});
		}
		else {
			dropdownItems.push({
				label: Liferay.Language.get('unarchive'),
				onClick: onArchive
			});
		}

		return dropdownItems;
	};

	return (
		// Clay drop-down expects a HTMLButtonElement as a trigger

		<ClayDropDownWithItems
			alignmentPosition={Align.BottomRight}
			className="panel-dropdown-menu"
			items={generateDropdownItems()}
			key={`dropdownMenu-${id}`}
			trigger={
				<button
					className="btn btn-unstyled"
					role="button"
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('action-menu-icon')}
						role="img"
					>
						<use xlinkHref="#three-dot" />
					</svg>
				</button>
			}
		/>
	);
}

PanelDropdownMenu.propTypes = {
	id: PropTypes.string.isRequired,
	onEdit: PropTypes.func.isRequired,
	pinned: PropTypes.bool,
	status: PropTypes.oneOf([NOTE_STATUS_APPROVED, NOTE_STATUS_ARCHIVED]),
	tabType: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES])
};

export default PanelDropdownMenu;
