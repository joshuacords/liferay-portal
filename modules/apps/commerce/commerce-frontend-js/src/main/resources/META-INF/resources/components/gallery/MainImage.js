/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import PropTypes from 'prop-types';
import React from 'react';

import Arrows from './Arrows';

export default function MainImage({
	background,
	loading = false,
	onNext,
	onPrev,
	onZoom,
	src,
	title
}) {
	return (
		<div className="card main-image" onClick={onZoom} style={{background}}>
			<div className="aspect-ratio aspect-ratio-4-to-3">
				<img
					alt={title}
					className="aspect-ratio-item-center-middle aspect-ratio-item-fluid aspect-ratio-item-vertical-fluid"
					src={src}
				/>
			</div>
			<Arrows onNext={onNext} onPrev={onPrev} />
			{loading ? <ClayLoadingIndicator /> : null}
		</div>
	);
}

MainImage.propTypes = {
	background: PropTypes.string,
	loading: PropTypes.bool,
	onNext: PropTypes.func,
	onPrev: PropTypes.func,
	onZoom: PropTypes.func,
	src: PropTypes.string.isRequired,
	title: PropTypes.string.isRequired
};
