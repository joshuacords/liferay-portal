/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.sort;

import com.liferay.portal.search.script.Script;
import com.liferay.portal.search.sort.NestedSort;
import com.liferay.portal.search.sort.ScriptSort;
import com.liferay.portal.search.sort.SortMode;
import com.liferay.portal.search.sort.SortVisitor;

/**
 * @author Michael C. Han
 */
public class ScriptSortImpl extends SortImpl implements ScriptSort {

	public ScriptSortImpl(Script script, ScriptSortType scriptSortType) {
		_script = script;
		_scriptSortType = scriptSortType;
	}

	@Override
	public <T> T accept(SortVisitor<T> sortVisitor) {
		return sortVisitor.visit(this);
	}

	public NestedSort getNestedSort() {
		return _nestedSort;
	}

	public Script getScript() {
		return _script;
	}

	public ScriptSortType getScriptSortType() {
		return _scriptSortType;
	}

	public SortMode getSortMode() {
		return _sortMode;
	}

	public void setNestedSort(NestedSort nestedSort) {
		_nestedSort = nestedSort;
	}

	public void setSortMode(SortMode sortMode) {
		_sortMode = sortMode;
	}

	private NestedSort _nestedSort;
	private final Script _script;
	private final ScriptSortType _scriptSortType;
	private SortMode _sortMode;

}