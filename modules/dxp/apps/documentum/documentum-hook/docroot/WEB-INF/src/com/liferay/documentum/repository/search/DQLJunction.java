/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public abstract class DQLJunction implements DQLCriterion {

	public DQLJunction() {
		_dqlCriterions = new ArrayList<>();
	}

	public void add(DQLCriterion dqlCriterion) {
		_dqlCriterions.add(dqlCriterion);
	}

	public boolean isEmpty() {
		return _dqlCriterions.isEmpty();
	}

	public List<DQLCriterion> list() {
		return _dqlCriterions;
	}

	@Override
	public abstract String toQueryFragment();

	private final List<DQLCriterion> _dqlCriterions;

}