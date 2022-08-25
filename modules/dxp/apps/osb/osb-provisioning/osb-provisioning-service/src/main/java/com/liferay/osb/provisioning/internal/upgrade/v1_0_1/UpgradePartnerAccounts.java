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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = UpgradePartnerAccounts.class)
public class UpgradePartnerAccounts extends UpgradeProcess {

	public void upgradePartnerAccounts() throws Exception {
		Product product = _productWebService.fetchProductByName(
			"DXP Development");

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			false, "entitlements", EntitlementConstants.PARTNER);

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 10000, null);

		for (Account account : accounts) {
			try {
				addProductPurchase(account, product);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	protected void addProductPurchase(Account account, Product product)
		throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		ProductPurchase productPurchase = new ProductPurchase();

		productPurchase.setAccountKey(account.getKey());
		productPurchase.setEndDate(dateFormat.parse("2023-01-30"));
		productPurchase.setOriginalEndDate(dateFormat.parse("2022-12-31"));
		productPurchase.setProduct(product);

		Map<String, String> properties = new HashMap<>();

		properties.put("licenses", StringPool.TRUE);
		properties.put("sizing", String.valueOf(1));
		properties.put("version", String.valueOf(7));

		productPurchase.setProperties(properties);

		productPurchase.setStartDate(dateFormat.parse("2020-01-01"));
		productPurchase.setStatus(ProductPurchase.Status.APPROVED);
		productPurchase.setQuantity(10000);

		_productPurchaseWebService.addProductPurchase(
			StringPool.BLANK, StringPool.BLANK, account.getKey(),
			productPurchase);
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradePartnerAccounts.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

}