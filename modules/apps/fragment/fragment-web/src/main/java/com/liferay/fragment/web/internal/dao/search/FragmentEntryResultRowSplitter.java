/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.dao.search;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jürgen Kappler
 */
public class FragmentEntryResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> fragmentEntrySectionsResultRows = new ArrayList<>();

		List<ResultRow> fragmentEntryComponentsResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			FragmentEntry fragmentEntry = (FragmentEntry)resultRow.getObject();

			if (fragmentEntry.getType() == FragmentConstants.TYPE_COMPONENT) {
				fragmentEntryComponentsResultRows.add(resultRow);
			}
			else {
				fragmentEntrySectionsResultRows.add(resultRow);
			}
		}

		if (!fragmentEntrySectionsResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"sections", fragmentEntrySectionsResultRows));
		}

		if (!fragmentEntryComponentsResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"components", fragmentEntryComponentsResultRows));
		}

		return resultRowSplitterEntries;
	}

}