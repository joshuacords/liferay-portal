/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import React from 'react';

export const spritemap =
	window.location.protocol +
	'//' +
	window.location.host +
	'/o/classic-theme/images/lexicon/icons.svg';

const Icon = props => {
	const {symbol, ...otherProps} = props;

	return <ClayIcon spritemap={spritemap} symbol={symbol} {...otherProps} />;
};

export default Icon;
