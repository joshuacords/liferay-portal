/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

/**
 * @author Mika Koivisto
 */
public class DQLNotExpression implements DQLCriterion {

	public DQLNotExpression(DQLCriterion dqlCriterion) {
		_dqlCriterion = dqlCriterion;
	}

	@Override
	public String toQueryFragment() {
		return "NOT(".concat(
			_dqlCriterion.toQueryFragment()
		).concat(
			")"
		);
	}

	private final DQLCriterion _dqlCriterion;

}