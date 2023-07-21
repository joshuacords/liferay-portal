/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.reader;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;

import java.util.List;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
public interface AccountReader {

	public List<Account> getAncestorAccounts(Account account) throws Exception;

	public Team getFirstLineSupportTeam(Account account) throws Exception;

	public int getMaxSupportSeatCount(Account account);

	public Team getPartnerTeam(Account account) throws Exception;

	public ProductPurchase getSLAProductPurchase(Account account);

	public List<ProductPurchase> getSLAProductPurchases(Account account);

	public String getSubscriptionState(Account account);

	public int getSupportSeatCount(Account account);

	public boolean isEWSA(Account account) throws Exception;

}