/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useContext} from 'react';

import Filter from '../../../shared/components/filter/Filter.es';
import {CustomTimeRangeForm} from './CustomTimeRangeForm.es';
import {
	TimeRangeContext,
	getCustomTimeRangeName
} from './store/TimeRangeStore.es';

const TimeRangeFilter = ({
	filterKey = 'timeRange',
	hideControl = false,
	position = 'left',
	showFilterName = true
}) => {
	const {
		defaultTimeRange,
		getSelectedTimeRange,
		setShowCustomForm,
		showCustomForm,
		timeRanges
	} = useContext(TimeRangeContext);

	const isCustomFilter = currentFilter => currentFilter.key === 'custom';

	const onChangeFilter = selectedFilter => {
		const preventDefault = isCustomFilter(selectedFilter);

		return preventDefault;
	};

	const onClickFilter = clickedFilter => {
		if (isCustomFilter(clickedFilter)) {
			setShowCustomForm(true);

			if (clickedFilter.active) {
				document.dispatchEvent(new Event('mousedown'));
			}
		}
		else {
			setShowCustomForm(false);
		}

		return true;
	};

	const selectedTimeRange = getSelectedTimeRange();

	return (
		<Filter
			defaultItem={defaultTimeRange}
			filterKey={filterKey}
			hideControl={hideControl}
			items={[...timeRanges]}
			multiple={false}
			name={getFilterName(selectedTimeRange, showFilterName)}
			onChangeFilter={onChangeFilter}
			onClickFilter={onClickFilter}
			position={position}
		>
			{showCustomForm && <CustomTimeRangeForm filterKey={filterKey} />}
		</Filter>
	);
};

const getFilterName = (selectedTimeRange, showFilterName) => {
	if (showFilterName) {
		return Liferay.Language.get('completion-period');
	}

	if (!selectedTimeRange) {
		return '';
	}

	if (selectedTimeRange.key === 'custom') {
		return getCustomTimeRangeName(selectedTimeRange);
	}

	return selectedTimeRange.name;
};

export {TimeRangeFilter};
