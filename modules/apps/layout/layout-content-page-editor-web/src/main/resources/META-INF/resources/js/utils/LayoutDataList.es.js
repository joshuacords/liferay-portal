/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	getFragmentRowIndex,
	getRowFragmentEntryLinkIds
} from './FragmentsEditorGetUtils.es';

/**
 * Tells if a fragmentEntryLink is referenced in any (but the current one)
 * LayoutData inside LayoutDataList
 * @param {Array<{ segmentsExperienceId: string, layoutData: {structure: Array} }>} LayoutDataList
 * @param {string} fragmentEntryLinkId
 * @param {string} [skipSegmentsExperienceId] - allows to skip searching in layoutData by segmentsExperienceId
 * @returns {boolean}
 */
function containsFragmentEntryLinkId(
	LayoutDataList,
	fragmentEntryLinkId,
	skipSegmentsExperienceId
) {
	return LayoutDataList.filter(function _avoidCurrentExperienceLayoutDataItem(
		LayoutDataItem
	) {
		return LayoutDataItem.segmentsExperienceId !== skipSegmentsExperienceId;
	}).some(function _getFragmentRowIndexWrapper(LayoutDataItem) {
		const index = getFragmentRowIndex(
			LayoutDataItem.layoutData.structure,
			fragmentEntryLinkId
		);

		return index !== -1;
	});
}

/**
 * Utility to get a layoutData object
 *
 * @returns {object}
 */
function getEmptyLayoutData() {
	return {
		nextColumnId: 0,
		nextRowId: 0,
		structure: []
	};
}

/**
 * Utility to get a fragment entry links ids from layout data
 *
 * @param layoutData {LayoutDataShape}
 * @returns {Array}
 */
function getLayoutDataFragmentEntryLinkIds(layoutData) {
	let fragmentEntryLinkIds = [];

	layoutData.structure.forEach(row => {
		fragmentEntryLinkIds = fragmentEntryLinkIds.concat(
			getRowFragmentEntryLinkIds(row)
		);
	});

	return fragmentEntryLinkIds;
}

export {
	containsFragmentEntryLinkId,
	getEmptyLayoutData,
	getLayoutDataFragmentEntryLinkIds
};
