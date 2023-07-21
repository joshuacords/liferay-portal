/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export const pipe = (...fns) => initialVal =>
	fns.reduce((prevVal, currentFn) => currentFn(prevVal), initialVal);
export const compose = (...fns) => initialVal =>
	fns.reduceRight((prevVal, currentFn) => currentFn(prevVal), initialVal);
