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

package com.liferay.osb.provisioning.distributed.messaging.internal.upgrade;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.util.DossieraSubscriberUtil;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = UpgradeAccountGSInvolved.class)
public class UpgradeAccountGSInvolved extends UpgradeProcess {

	public void upgradeAccount(String salesforceProjectKey) throws Exception {
		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.SALESFORCE,
			ExternalLinkEntityName.SALESFORCE_PROJECT, salesforceProjectKey, 1,
			1);

		if (accounts.isEmpty()) {
			_log.error(
				"Unable to find account with Salesforce project key " +
					salesforceProjectKey);

			return;
		}

		Account account = accounts.get(0);

		Map<String, String> properties = account.getProperties();

		properties.put("gsOpportunity", "true");

		_accountWebService.updateAccount(
			StringPool.BLANK, StringPool.BLANK, account.getKey(), account);

		_dossieraSubscriberUtil.updateTickets(account, properties);
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeAccountGSInvolved.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private DossieraSubscriberUtil _dossieraSubscriberUtil;

}