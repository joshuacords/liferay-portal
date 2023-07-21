/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class TeamImpl extends TeamBaseImpl {

	public TeamImpl() {
	}

	public Account getAccount() throws PortalException {
		return AccountLocalServiceUtil.getAccount(getAccountId());
	}

	public String getAccountKey() throws PortalException {
		if (_accountKey != null) {
			return _accountKey;
		}

		Account account = getAccount();

		return account.getAccountKey();
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			Team.class.getName(), getTeamId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public void setAccountKey(String accountKey) {
		_accountKey = accountKey;
	}

	private String _accountKey;

}