/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
