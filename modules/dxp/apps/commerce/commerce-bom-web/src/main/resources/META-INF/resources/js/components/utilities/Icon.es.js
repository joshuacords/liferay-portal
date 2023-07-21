/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export default function Icon(props) {
	return (
		<svg
			aria-hidden="true"
			className="lexicon-icon"
			role="presentation"
			viewBox="0 0 24 24"
		>
			<title>{props.symbol}</title>
			<use xlinkHref={`${props.spritemap}#${props.symbol}`} />
		</svg>
	);
}
