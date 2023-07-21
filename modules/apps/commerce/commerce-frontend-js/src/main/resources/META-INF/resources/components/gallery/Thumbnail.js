/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

export default function Thumbnail({
	active = false,
	background,
	onClick,
	src,
	title
}) {
	const cardClasses = classNames(
		'card',
		'card-interactive',
		'card-interactive-primary',
		{active}
	);

	return (
		<div className={cardClasses} onClick={onClick} style={{background}}>
			<div className="aspect-ratio aspect-ratio-4-to-3">
				<img
					alt={title}
					className="aspect-ratio-item-center-middle aspect-ratio-item-fluid aspect-ratio-item-vertical-fluid"
					src={src}
				/>
			</div>
		</div>
	);
}

Thumbnail.propTypes = {
	active: PropTypes.bool,
	onClick: PropTypes.func,
	src: PropTypes.string.isRequired,
	title: PropTypes.string.isRequired
};
