/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Returns a prefixed id
 * if the value is not undefined
 *
 * @export
 * @param {string|number} [segmentsExperienceId]
 * @returns {string}
 */
export function prefixSegmentsExperienceId(segmentsExperienceId) {
	return segmentsExperienceId === undefined || segmentsExperienceId === ''
		? undefined
		: SEGMENT_EXPERIENCE_ID_PREFIX + segmentsExperienceId;
}

const SEGMENT_EXPERIENCE_ID_PREFIX = 'segments-experience-id-';
