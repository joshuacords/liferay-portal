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

package com.liferay.osb.provisioning.license.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.osb.provisioning.subscription.service.SubscriptionEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.provisioning.license.model.LicenseKey",
	service = ModelDocumentContributor.class
)
public class LicenseKeyModelDocumentContributor
	implements ModelDocumentContributor<LicenseKey> {

	@Override
	public void contribute(Document document, LicenseKey licenseKey) {
		try {
			_contribute(document, licenseKey);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _contribute(Document document, LicenseKey licenseKey)
		throws Exception {

		document.addKeyword(Field.COMPANY_ID, licenseKey.getCompanyId());
		document.addDate(Field.CREATE_DATE, licenseKey.getCreateDate());
		document.addText(Field.DESCRIPTION, licenseKey.getDescription());
		document.addDate(Field.EXPIRATION_DATE, licenseKey.getExpirationDate());
		document.addDate(Field.MODIFIED_DATE, licenseKey.getModifiedDate());
		document.addText(Field.NAME, licenseKey.getName());

		Account account = _accountWebService.fetchAccount(
			licenseKey.getAccountKey());

		if (account != null) {
			document.addText("accountCode", account.getCode());
			document.addText("accountName", account.getName());
		}

		document.addKeyword("accountKey", licenseKey.getAccountKey());
		document.addKeyword("active", licenseKey.isActive());
		document.addText("additionalInfo", licenseKey.getAdditionalInfo());
		document.addKeyword(
			"assetReceiptLicenseUuid", licenseKey.getAssetReceiptLicenseUuid());
		document.addKeyword("complimentary", licenseKey.isComplimentary());
		document.addKeyword("hostName", licenseKey.getHostName());
		document.addKeyword(
			"ipAddresses", StringUtil.split(licenseKey.getIpAddresses()));
		document.addKeyword("key", licenseKey.getKey());
		document.addKeyword("licenseEntryId", licenseKey.getLicenseEntryId());

		LicenseEntry licenseEntry = _licenseEntryLocalService.fetchLicenseEntry(
			licenseKey.getLicenseEntryId());

		if (licenseEntry != null) {
			document.addKeyword("licenseEntryName", licenseEntry.getName());
			document.addKeyword("licenseEntryType", licenseEntry.getType());
		}

		document.addKeyword(
			"macAddresses", StringUtil.split(licenseKey.getMacAddresses()));
		document.addKeyword("maxClusterNodes", licenseKey.getMaxClusterNodes());
		document.addKeyword(
			"maxConcurrentUsers", licenseKey.getMaxConcurrentUsers());
		document.addKeyword("maxHttpSessions", licenseKey.getMaxHttpSessions());
		document.addKeyword("maxServers", licenseKey.getMaxServers());
		document.addKeyword("maxUsers", licenseKey.getMaxUsers());
		document.addKeyword(
			"modifiedUserUuid", licenseKey.getModifiedUserUuid());
		document.addKeyword("owner", licenseKey.getOwner());
		document.addKeyword("productId", licenseKey.getProductId());
		document.addKeyword("productKey", licenseKey.getProductKey());
		document.addKeyword("productName", licenseKey.getProductName());
		document.addKeyword(
			"productPurchaseKey", licenseKey.getProductPurchaseKey());
		document.addKeyword("productVersion", licenseKey.getProductVersion());
		document.addKeyword("serverId", licenseKey.getServerId());
		document.addKeyword("sizing", licenseKey.getSizing());
		document.addDate("startDate", licenseKey.getStartDate());
		document.addKeyword("userUuid", licenseKey.getUserUuid());

		document.addDateSortable(Field.CREATE_DATE, licenseKey.getCreateDate());
		document.addDateSortable(
			Field.MODIFIED_DATE, licenseKey.getModifiedDate());
		document.addTextSortable("productName", licenseKey.getProductName());

		_contributeSubscriptions(document, licenseKey.getLicenseKeyId());
	}

	private void _contributeSubscriptions(
		Document document, long licenseKeyId) {

		Set<String> subscriptionContactUuids = new HashSet<>();

		long classNameId = _classNameLocalService.getClassNameId(
			LicenseKey.class.getName());

		List<SubscriptionEntry> subscriptionEntries =
			_subscriptionEntryLocalService.getSubscriptionEntries(
				classNameId, licenseKeyId);

		for (SubscriptionEntry subscriptionEntry : subscriptionEntries) {
			subscriptionContactUuids.add(subscriptionEntry.getContactUuid());
		}

		document.addKeyword(
			"subscriptionContactUuids",
			ArrayUtil.toStringArray(subscriptionContactUuids.toArray()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyModelDocumentContributor.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private SubscriptionEntryLocalService _subscriptionEntryLocalService;

}