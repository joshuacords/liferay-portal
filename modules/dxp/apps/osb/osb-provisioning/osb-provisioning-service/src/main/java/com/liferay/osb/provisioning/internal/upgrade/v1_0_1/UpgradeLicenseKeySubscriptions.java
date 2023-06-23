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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.subscription.service.SubscriptionEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;

import java.util.LinkedHashMap;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Will Newbury
 */
@Component(service = UpgradeLicenseKeySubscriptions.class)
public class UpgradeLicenseKeySubscriptions extends UpgradeProcess {

	public void upgradeLicenseKeySubscriptions() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "status", Account.Status.ACTIVE.toString());

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 10000, null);

		for (Account account : accounts) {
			ContactRole contactRole = _contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_WORKER.toString(),
				ContactRoleConstants.NAME_PRIMARY_CONTACT);

			filterQuery = new FilterQuery();

			filterQuery.addLambdaEquals(
				true, "accountKeysContactRoleKeys",
				account.getKey() + "_" + contactRole.getKey());

			List<Contact> contacts = _contactWebService.search(
				StringPool.BLANK, filterQuery, 1, 100, StringPool.BLANK);

			if (contacts.isEmpty()) {
				_log.error(
					"Account with key: " + account.getKey() +
						" does not have any primary contacts");

				continue;
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			List<LicenseKey> licenseKeys = _licenseKeyLocalService.search(
				null, null, null, null, null, null, account.getKey(), null,
				null, null, null, new long[0], new String[0], null, null,
				new String[0], new long[0], null, null, null, null, null, null,
				null, null, null, params, false, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

			long classNameId = _classNameLocalService.getClassNameId(
				LicenseKey.class);

			for (Contact contact : contacts) {
				for (LicenseKey licenseKey : licenseKeys) {
					if (Validator.isNull(licenseKey.getProductPurchaseKey())) {
						continue;
					}

					_subscriptionEntryLocalService.addSubscriptionEntry(
						classNameId, licenseKey.getLicenseKeyId(),
						contact.getUuid());
				}
			}
		}
	}

	@Override
	protected void doUpgrade() {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeLicenseKeySubscriptions.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private ProductConsumptionWebService _productConsumptionWebService;

	@Reference
	private SubscriptionEntryLocalService _subscriptionEntryLocalService;

}