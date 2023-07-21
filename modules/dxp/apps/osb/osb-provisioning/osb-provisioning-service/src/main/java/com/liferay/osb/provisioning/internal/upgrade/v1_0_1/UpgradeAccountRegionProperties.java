/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.internal.upgrade.v1_0_1;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
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
 * @author Jenny Chen
 */
@Component(service = UpgradeAccountRegionProperties.class)
public class UpgradeAccountRegionProperties extends UpgradeProcess {

	public void upgrade(
			String[] regions, String propertyName, String propertyValue,
			boolean removeProperty)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(false, "region", regions);

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Account account : accounts) {
			Map<String, String> properties = account.getProperties();

			if (removeProperty) {
				properties.remove(propertyName);
			}
			else {
				properties.put(propertyName, propertyValue);
			}

			try {
				_accountWebService.updateAccount(
					StringPool.BLANK, StringPool.BLANK, account.getKey(),
					account);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	@Override
	protected void doUpgrade() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeAccountRegionProperties.class);

	@Reference
	private AccountWebService _accountWebService;

}