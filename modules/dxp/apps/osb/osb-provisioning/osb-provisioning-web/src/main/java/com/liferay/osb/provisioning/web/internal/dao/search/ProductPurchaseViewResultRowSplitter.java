/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.web.internal.display.context.ProductPurchaseViewDisplay;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanyuan Huang
 */
public class ProductPurchaseViewResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> primaryProductResultRows = new ArrayList<>();
		List<ResultRow> addOnProductResultRows = new ArrayList<>();
		List<ResultRow> regularProductResultRows = new ArrayList<>();
		List<ResultRow> otherProductResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			ProductPurchaseViewDisplay productPurchaseViewDisplay =
				(ProductPurchaseViewDisplay)resultRow.getObject();

			String type = productPurchaseViewDisplay.getType();

			if (type.equals("primary")) {
				primaryProductResultRows.add(resultRow);
			}
			else if (type.equals("add-on")) {
				addOnProductResultRows.add(resultRow);
			}
			else if (type.equals("regular")) {
				regularProductResultRows.add(resultRow);
			}
			else {
				otherProductResultRows.add(resultRow);
			}
		}

		if (!primaryProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"primary", primaryProductResultRows));
		}

		if (!addOnProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("add-on", addOnProductResultRows));
		}

		if (!regularProductResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"regular", regularProductResultRows));
		}

		if (!otherProductResultRows.isEmpty()) {
			if (!resultRowSplitterEntries.isEmpty()) {
				resultRowSplitterEntries.add(
					new ResultRowSplitterEntry(
						"other", otherProductResultRows));
			}
			else {
				resultRowSplitterEntries.add(
					new ResultRowSplitterEntry(
						StringPool.BLANK, otherProductResultRows));
			}
		}

		return resultRowSplitterEntries;
	}

}