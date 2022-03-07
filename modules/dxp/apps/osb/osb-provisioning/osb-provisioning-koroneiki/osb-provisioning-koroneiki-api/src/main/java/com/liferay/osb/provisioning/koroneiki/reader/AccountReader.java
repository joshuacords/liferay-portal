/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

	public String getSubscriptionState(Account account);

	public int getSupportSeatCount(Account account);

	public boolean isEWSA(Account account) throws Exception;

}