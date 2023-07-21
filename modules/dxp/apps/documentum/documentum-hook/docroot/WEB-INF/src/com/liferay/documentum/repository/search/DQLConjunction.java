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
public class DQLConjunction extends DQLJunction {

	@Override
	public String toQueryFragment() {
		if (isEmpty()) {
			return StringPool.BLANK;
		}

		List<DQLCriterion> dqlCriterions = list();

		StringBundler sb = new StringBundler((dqlCriterions.size() * 2) + 1);

		if (dqlCriterions.size() > 1) {
			sb.append(StringPool.OPEN_PARENTHESIS);
		}

		for (int i = 0; i < dqlCriterions.size(); i++) {
			DQLCriterion dqlCriterion = dqlCriterions.get(i);

			if (i != 0) {
				sb.append(" AND ");
			}

			sb.append(dqlCriterion.toQueryFragment());
		}

		if (dqlCriterions.size() > 1) {
			sb.append(StringPool.CLOSE_PARENTHESIS);
		}

		return sb.toString();
	}

}