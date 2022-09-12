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

package com.liferay.osb.provisioning.internal.upgrade.v1_0_1;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = UpgradeDXPCloudExternalLinks.class)
public class UpgradeDXPCloudExternalLinks extends UpgradeProcess {

	public void upgrade(Map<String, String> dxpCloudProjectIdMap)
		throws Exception {

		for (Map.Entry<String, String> entry :
				dxpCloudProjectIdMap.entrySet()) {

			FilterQuery filterQuery = new FilterQuery();

			filterQuery.addEquals(false, "code", entry.getKey());

			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

			if (accounts.isEmpty()) {
				_log.error(
					"Unable to find account with code: " + entry.getKey());

				continue;
			}

			Account account = accounts.get(0);

			ExternalLink externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.DXP_CLOUD);
			externalLink.setEntityName(
				ExternalLinkEntityName.DXP_CLOUD_PROJECT);
			externalLink.setEntityId(entry.getValue());

			_externalLinkWebService.addAccountExternalLink(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				externalLink);
		}
	}

	@Override
	protected void doUpgrade() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeDXPCloudExternalLinks.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

}