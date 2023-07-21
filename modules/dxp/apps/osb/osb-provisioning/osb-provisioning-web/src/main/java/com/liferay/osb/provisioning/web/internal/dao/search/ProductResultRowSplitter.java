/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuanyuan Huang
 */
public class ProductResultRowSplitter implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> productBundleResultRows = new ArrayList<>();
		List<ResultRow> productResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			Object object = resultRow.getObject();

			if (object instanceof ProductBundle) {
				productBundleResultRows.add(resultRow);
			}
			else if (object instanceof ProductDisplay) {
				productResultRows.add(resultRow);
			}
		}

		if (!productBundleResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("bundle", productBundleResultRows));
		}

		if (!productResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("individual", productResultRows));
		}

		return resultRowSplitterEntries;
	}

}