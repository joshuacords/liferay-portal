/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * The Resize RTL Component.
 *
 * @deprecated since 7.2, unused
 * @module liferay-resize-rtl
 */

AUI.add(
	'liferay-resize-rtl',
	A => {
		var RULES = A.Resize.RULES;

		var tmpL = RULES.l;

		RULES.l = RULES.r;
		RULES.r = tmpL;
	},
	'',
	{
		requires: ['resize-base']
	}
);
