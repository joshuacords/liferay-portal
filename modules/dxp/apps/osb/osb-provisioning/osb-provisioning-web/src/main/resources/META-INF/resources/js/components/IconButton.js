/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

function IconButton({cssClass, labelName, onClick, svgId, ...otherProps}) {
	return (
		<button
			className={`btn ${cssClass}`}
			onClick={onClick}
			role="button"
			type="button"
			{...otherProps}
		>
			<svg aria-label={labelName} className="lexicon-icon" role="img">
				<use xlinkHref={svgId} />
			</svg>
		</button>
	);
}

IconButton.propTypes = {
	cssClass: PropTypes.string,
	labelName: PropTypes.string.isRequired,
	onClick: PropTypes.func.isRequired,
	svgId: PropTypes.string.isRequired
};

export default IconButton;
