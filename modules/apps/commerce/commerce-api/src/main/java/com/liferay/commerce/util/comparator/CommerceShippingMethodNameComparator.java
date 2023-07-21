/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.util.comparator;

import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.portal.kernel.util.CollatorUtil;

import java.io.Serializable;

import java.text.Collator;

import java.util.Comparator;
import java.util.Locale;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingMethodNameComparator
	implements Comparator<CommerceShippingMethod>, Serializable {

	public CommerceShippingMethodNameComparator(Locale locale) {
		_locale = locale;
	}

	@Override
	public int compare(
		CommerceShippingMethod commerceShippingMethod1,
		CommerceShippingMethod commerceShippingMethod2) {

		Collator collator = CollatorUtil.getInstance(_locale);

		String name1 = commerceShippingMethod1.getName(_locale);
		String name2 = commerceShippingMethod2.getName(_locale);

		return collator.compare(name1, name2);
	}

	private final Locale _locale;

}