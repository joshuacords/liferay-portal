/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.internal.upgrade.v1_0_1;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
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
@Component(service = UpgradeAnalyticsCloudExternalLinks.class)
public class UpgradeAnalyticsCloudExternalLinks extends UpgradeProcess {

	public void upgrade(Map<String, Integer> acWorkspaceIdMap)
		throws Exception {

		for (Map.Entry<String, Integer> entry : acWorkspaceIdMap.entrySet()) {
			String corpProjectUuid = entry.getKey();

			Account account = null;

			if (corpProjectUuid.startsWith("KOR-")) {
				account = _accountWebService.fetchAccount(corpProjectUuid);
			}
			else {
				List<Account> accounts = _accountWebService.getAccounts(
					ExternalLinkDomain.WEB,
					ExternalLinkEntityName.WEB_CORP_PROJECT, corpProjectUuid, 1,
					1);

				if (!accounts.isEmpty()) {
					account = accounts.get(0);
				}
			}

			if (account == null) {
				_log.error(
					"Unable to find account with key: " + corpProjectUuid);

				continue;
			}

			ExternalLink externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.ANALYTICS_CLOUD);
			externalLink.setEntityName(
				ExternalLinkEntityName.ANALYTICS_CLOUD_GROUP);
			externalLink.setEntityId(String.valueOf(entry.getValue()));

			_externalLinkWebService.addAccountExternalLink(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				externalLink);
		}
	}

	@Override
	protected void doUpgrade() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeAnalyticsCloudExternalLinks.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

}