/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.util;

import java.text.DecimalFormat;

import java.util.Locale;

/**
 * @author Rafael Praxedes
 * @author Guilherme Camacho
 */
public class NumericDDMFormFieldUtil {

	public static DecimalFormat getDecimalFormat(Locale locale) {
		DecimalFormat decimalFormat = (DecimalFormat)DecimalFormat.getInstance(
			locale);

		decimalFormat.setGroupingUsed(false);
		decimalFormat.setMaximumFractionDigits(Integer.MAX_VALUE);
		decimalFormat.setParseBigDecimal(true);

		return decimalFormat;
	}

}