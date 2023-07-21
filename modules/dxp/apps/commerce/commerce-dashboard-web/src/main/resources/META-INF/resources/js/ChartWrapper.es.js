/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayChart from '@clayui/charts';
import React, {useCallback, useRef} from 'react';
import ResizeObserver from 'resize-observer-polyfill';

export default function ChartWrapper({data, loading, noDataErrorMessage}) {
	const chart = useRef();

	const resize = useCallback(() => chart.current && chart.current.resize(), [
		chart
	]);

	const wrapper = useCallback(
		node => {
			if (node !== null) {
				new ResizeObserver(resize).observe(node);
			}
		},
		[resize]
	);

	if (loading) {
		return <span aria-hidden="true" className="loading-animation" />;
	}
	else if (!data.data.columns.length) {
		return <p>{noDataErrorMessage}</p>;
	}
	else {
		return (
			<div ref={wrapper}>
				<ClayChart {...data} ref={chart} />
			</div>
		);
	}
}
