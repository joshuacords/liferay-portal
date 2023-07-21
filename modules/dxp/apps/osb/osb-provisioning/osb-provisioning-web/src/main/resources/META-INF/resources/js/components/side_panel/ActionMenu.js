/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayTooltipProvider} from '@clayui/tooltip';
import PropTypes from 'prop-types';
import React from 'react';

import {NOTE_TYPE_GENERAL, NOTE_TYPE_SALES} from '../../utilities/constants';
import IconButton from '../IconButton';

function ActionMenu({
	onEdit,
	onPinning,
	pinned = false,
	tabType = NOTE_TYPE_GENERAL
}) {
	return (
		<>
			<ClayTooltipProvider>
				<IconButton
					className="btn-unstyled"
					data-tooltip-align="top"
					labelName="edit-note-icon"
					onClick={onEdit}
					svgId="#edit"
					title={Liferay.Language.get('edit')}
				/>
			</ClayTooltipProvider>

			{tabType === NOTE_TYPE_GENERAL && (
				<Pinning onPinning={onPinning} pinned={pinned} />
			)}
		</>
	);
}

ActionMenu.propTypes = {
	onEdit: PropTypes.func,
	onPinning: PropTypes.func,
	pinned: PropTypes.bool,
	tabType: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES])
};

function Pinning({onPinning, pinned}) {
	return (
		<>
			{pinned ? (
				<ClayTooltipProvider>
					<IconButton
						className="btn-unstyled"
						data-tooltip-align="top"
						labelName="unpin-note-icon"
						onClick={onPinning}
						svgId="#unpin"
						title={Liferay.Language.get('unpin')}
					/>
				</ClayTooltipProvider>
			) : (
				<ClayTooltipProvider>
					<IconButton
						className="btn-unstyled"
						data-tooltip-align="top"
						labelName="pin-note-icon"
						onClick={onPinning}
						svgId="#pin"
						title={Liferay.Language.get('pin')}
					/>
				</ClayTooltipProvider>
			)}
		</>
	);
}

export default ActionMenu;
