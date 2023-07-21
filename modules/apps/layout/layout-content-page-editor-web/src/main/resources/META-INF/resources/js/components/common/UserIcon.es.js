/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint no-unused-vars: "warn" */

import ClayIcon from '@clayui/icon';
import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React from 'react';

const UserIcon = props => {
	const stickerColor = parseInt(props.userId, 10) % 10;

	return (
		<ClaySticker
			className={`flex-shrink-0 sticker-use-icon user-icon-color-${stickerColor}`}
			displayType="secondary"
			shape="circle"
			size="lg"
		>
			{props.portraitURL ? (
				<div className="sticker-overlay">
					<img
						alt={`${props.fullName}.`}
						className="sticker-img"
						src={props.portraitURL}
					/>
				</div>
			) : (
				<ClayIcon symbol="user" />
			)}
		</ClaySticker>
	);
};

UserIcon.defaultProps = {
	fullName: '',
	portraitURL: ''
};

UserIcon.propTypes = {
	fullName: PropTypes.string,
	portraitURL: PropTypes.string,
	userId: PropTypes.string.isRequired
};

export {UserIcon};
export default UserIcon;
