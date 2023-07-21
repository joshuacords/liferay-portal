/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.util.DossieraSubscriberUtil;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration",
	immediate = true,
	property = "topic.pattern=dossiera.provisioning.project.update",
	service = DossieraUpdateMessageSubscriber.class
)
public class DossieraUpdateMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Parsing message: " + jsonObject.toString());
		}

		String salesforceProjectKey = jsonObject.getString(
			"salesforceProjectKey");

		if (Validator.isNull(salesforceProjectKey)) {
			return;
		}

		String accountKey = _dossieraSubscriberUtil.getAccountKey(
			salesforceProjectKey);

		if (accountKey != null) {
			Account account = _accountWebService.getAccount(accountKey);

			Map<String, String> oldProperties = new HashMap<>();

			MapUtil.copy(account.getProperties(), oldProperties);

			Map<String, String> properties =
				_dossieraSubscriberUtil.getAccountProperties(
					account, jsonObject);

			account.setProperties(properties);

			_accountWebService.updateAccount(
				StringPool.BLANK, StringPool.BLANK, accountKey, account);

			if (!oldProperties.equals(properties)) {
				_dossieraSubscriberUtil.updateTickets(account, properties);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraUpdateMessageSubscriber.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private DossieraSubscriberUtil _dossieraSubscriberUtil;

}