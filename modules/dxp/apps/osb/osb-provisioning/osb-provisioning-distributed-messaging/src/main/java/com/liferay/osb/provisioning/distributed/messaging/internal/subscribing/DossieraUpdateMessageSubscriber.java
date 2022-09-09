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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.distributed.messaging.internal.subscribing.util.DossieraSubscriberUtil;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.distributed.messaging.internal.configuration.DistributedMessagingConfiguration",
	immediate = true, property = "topic.pattern=dossiera.provisioning.update",
	service = DossieraUpdateMessageSubscriber.class
)
public class DossieraUpdateMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Parsing message: " + jsonObject.toString());
		}

		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String salesforceProjectKey = projectJSONObject.getString(
			"_salesforceProjectKey");

		if (Validator.isNull(salesforceProjectKey)) {
			return;
		}

		String accountKey = _dossieraSubscriberUtil.getAccountKey(jsonObject);

		if (accountKey != null) {
			Account account = _accountWebService.getAccount(accountKey);

			Map<String, String> properties =
				_dossieraSubscriberUtil.getAccountProperties(
					account, jsonObject);

			account.setProperties(properties);

			_accountWebService.updateAccount(
				StringPool.BLANK, StringPool.BLANK, accountKey, account);

			_dossieraSubscriberUtil.updateTickets(account, properties);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraUpdateMessageSubscriber.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private DossieraSubscriberUtil _dossieraSubscriberUtil;

}