/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.util;

import com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseDisplay;

import java.util.Comparator;

/**
 * @author Yuanyuan Huang
 */
public class ProductPurchaseDisplayComparator
	implements Comparator<ProductPurchaseDisplay> {

	@Override
	public int compare(
		ProductPurchaseDisplay productPurchaseDisplay1,
		ProductPurchaseDisplay productPurchaseDisplay2) {

		int state1 = _getStateRank(productPurchaseDisplay1.getState());
		int state2 = _getStateRank(productPurchaseDisplay2.getState());

		if (state1 > state2) {
			return 1;
		}

		return -1;
	}

	private int _getStateRank(String state) {
		if (state.equals("active")) {
			return 1;
		}
		else if (state.equals("cancelled")) {
			return 4;
		}
		else if (state.equals("expired")) {
			return 3;
		}
		else if (state.equals("future")) {
			return 2;
		}

		return 0;
	}

}