/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ProductPurchaseResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> productPurchaseResultRows = new ArrayList<>();
		List<ResultRow> detachedResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			if (resultRow.getObject() != null) {
				productPurchaseResultRows.add(resultRow);
			}
			else {
				detachedResultRows.add(resultRow);
			}
		}

		if (!productPurchaseResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(null, productPurchaseResultRows));
		}

		if (!detachedResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("detached", detachedResultRows));
		}

		return resultRowSplitterEntries;
	}

}