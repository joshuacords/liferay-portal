/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.util.comparator;

import com.liferay.commerce.model.CommerceShippingOption;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceShippingOptionLabelComparator
	implements Comparator<CommerceShippingOption>, Serializable {

	@Override
	public int compare(
		CommerceShippingOption commerceShippingOption1,
		CommerceShippingOption commerceShippingOption2) {

		String label1 = commerceShippingOption1.getLabel();
		String label2 = commerceShippingOption2.getLabel();

		return label1.compareTo(label2);
	}

}