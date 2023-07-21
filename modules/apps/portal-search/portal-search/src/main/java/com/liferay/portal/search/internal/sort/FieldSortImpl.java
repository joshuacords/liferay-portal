/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.sort;

import com.liferay.portal.search.sort.FieldSort;
import com.liferay.portal.search.sort.NestedSort;
import com.liferay.portal.search.sort.SortMode;
import com.liferay.portal.search.sort.SortVisitor;

/**
 * @author Michael C. Han
 */
public class FieldSortImpl extends SortImpl implements FieldSort {

	public FieldSortImpl(String field) {
		_field = field;
	}

	@Override
	public <T> T accept(SortVisitor<T> sortVisitor) {
		return sortVisitor.visit(this);
	}

	public String getField() {
		return _field;
	}

	public Object getMissing() {
		return _missing;
	}

	public NestedSort getNestedSort() {
		return _nestedSort;
	}

	public SortMode getSortMode() {
		return _sortMode;
	}

	public void setMissing(Object missing) {
		_missing = missing;
	}

	public void setNestedSort(NestedSort nestedSort) {
		_nestedSort = nestedSort;
	}

	public void setSortMode(SortMode sortMode) {
		_sortMode = sortMode;
	}

	private final String _field;
	private Object _missing;
	private NestedSort _nestedSort;
	private SortMode _sortMode;

}