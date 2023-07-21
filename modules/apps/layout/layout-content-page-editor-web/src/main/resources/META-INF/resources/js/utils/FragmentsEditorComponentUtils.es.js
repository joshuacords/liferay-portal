/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Returns true if any change is different
 * @param {object} changes
 * @return {boolean}
 * @review
 */
const shouldUpdatePureComponent = changes => {
	return Object.values(changes)
		.filter(change => change.key !== 'events')
		.some(change => change.newVal !== change.prevVal);
};

/**
 * Returns true if any of the given properties has changed
 * @param {object} changes
 * @param {string[]} properties
 * @return {boolean}
 */
const shouldUpdateOnChangeProperties = (changes, properties) => {
	return Object.values(changes)
		.filter(change => properties.indexOf(change.key) !== -1)
		.some(change => change.newVal !== change.prevVal);
};

export {shouldUpdatePureComponent, shouldUpdateOnChangeProperties};
