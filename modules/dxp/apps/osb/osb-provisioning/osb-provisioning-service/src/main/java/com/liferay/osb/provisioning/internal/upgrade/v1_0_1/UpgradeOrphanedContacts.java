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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = UpgradeOrphanedContacts.class)
public class UpgradeOrphanedContacts extends UpgradeProcess {

	public void upgrade(String[] emailAddresses) throws Exception {
		for (String emailAddress : emailAddresses) {
			try {
				deleteContact(emailAddress);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	protected void deleteContact(String emailAddress) throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "contactEmailAddresses", emailAddress);

		List<Account> accounts = _accountWebService.search(
			null, filterQuery, 1, 1000, null);

		for (Account account : accounts) {
			_accountWebService.unassignCustomerContact(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				emailAddress);

			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"Removed ", emailAddress, " from account ",
						account.getName()));
			}
		}

		_contactWebService.deleteContact(
			StringPool.BLANK, StringPool.BLANK, emailAddress);

		if (_log.isInfoEnabled()) {
			_log.info("Deleted contact " + emailAddress);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeOrphanedContacts.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactWebService _contactWebService;

}