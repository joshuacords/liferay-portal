/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export default function Icon(props) {
	const {className, spritemap, symbol, ...otherProps} = props;

	return (
		<svg
			{...otherProps}
			className={`lexicon-icon lexicon-icon-${symbol}${
				className ? ` ${className}` : ``
			}`}
			role="presentation"
		>
			<use xlinkHref={`${spritemap}#${symbol}`} />
		</svg>
	);
}
