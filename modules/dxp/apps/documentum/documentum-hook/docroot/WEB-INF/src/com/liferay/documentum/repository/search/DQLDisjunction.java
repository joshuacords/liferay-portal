/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class DQLDisjunction extends DQLJunction {

	@Override
	public String toQueryFragment() {
		if (isEmpty()) {
			return StringPool.BLANK;
		}

		List<DQLCriterion> dqlCriterions = list();

		StringBundler sb = new StringBundler((dqlCriterions.size() * 2) + 1);

		sb.append("(");

		for (int i = 0; i < dqlCriterions.size(); i++) {
			DQLCriterion dqlCriterion = dqlCriterions.get(i);

			if (i != 0) {
				sb.append(" OR ");
			}

			sb.append(dqlCriterion.toQueryFragment());
		}

		sb.append(")");

		return sb.toString();
	}

}