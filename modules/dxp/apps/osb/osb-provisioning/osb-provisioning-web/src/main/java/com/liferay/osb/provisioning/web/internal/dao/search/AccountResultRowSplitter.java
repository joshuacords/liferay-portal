/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class AccountResultRowSplitter implements ResultRowSplitter {

	public AccountResultRowSplitter(Account account) {
		_account = account;
	}

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> parentAccountResultRows = new ArrayList<>();
		List<ResultRow> siblingAccountResultRows = new ArrayList<>();
		List<ResultRow> childAccountResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			AccountDisplay accountDisplay =
				(AccountDisplay)resultRow.getObject();

			String accountKey = accountDisplay.getKey();

			if (accountKey.equals(_account.getParentAccountKey())) {
				parentAccountResultRows.add(resultRow);

				continue;
			}

			String parentAccountKey = accountDisplay.getParentAccountKey();

			if (Validator.isNotNull(parentAccountKey)) {
				if (parentAccountKey.equals(_account.getParentAccountKey())) {
					siblingAccountResultRows.add(resultRow);
				}
				else if (parentAccountKey.equals(_account.getKey())) {
					childAccountResultRows.add(resultRow);
				}
			}
		}

		if (!parentAccountResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"parent-account", parentAccountResultRows));
		}

		if (!siblingAccountResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"sibling-accounts", siblingAccountResultRows));
		}

		if (!childAccountResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry(
					"child-accounts", childAccountResultRows));
		}

		return resultRowSplitterEntries;
	}

	private final Account _account;

}