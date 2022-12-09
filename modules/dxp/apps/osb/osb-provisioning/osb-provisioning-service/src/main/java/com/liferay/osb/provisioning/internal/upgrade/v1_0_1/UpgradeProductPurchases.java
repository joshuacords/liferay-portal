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
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = UpgradeProductPurchases.class)
public class UpgradeProductPurchases extends UpgradeProcess {

	public void upgradeProductPurchases(
			List<String> accountCodes, List<String> productKeys)
		throws Exception {

		for (String accountCode : accountCodes) {
			FilterQuery filterQuery = new FilterQuery();

			filterQuery.addEquals(true, "code", accountCode);

			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK, filterQuery, 1, 1, null);

			if (accounts.isEmpty()) {
				continue;
			}

			for (String productKey : productKeys) {
				try {
					addProductPurchase(accounts.get(0), productKey);
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}
		}
	}

	protected void addProductPurchase(Account account, String productKey)
		throws Exception {

		ProductPurchase productPurchase = new ProductPurchase();

		productPurchase.setAccountKey(account.getKey());
		productPurchase.setProductKey(productKey);
		productPurchase.setStatus(ProductPurchase.Status.APPROVED);
		productPurchase.setQuantity(1);

		Map<String, String> properties = new HashMap<>();

		properties.put("licenses", StringPool.TRUE);
		properties.put("sizing", String.valueOf(1));
		properties.put("version", String.valueOf(0));

		productPurchase.setProperties(properties);

		Date now = new Date();

		ProductPurchase[] productPurchases = account.getProductPurchases();

		for (ProductPurchase curProductPurchase : productPurchases) {
			if (now.after(curProductPurchase.getEndDate())) {
				continue;
			}

			Product product = curProductPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_GOLD) ||
				name.equals(ProductConstants.NAME_PLATINUM)) {

				productPurchase.setEndDate(curProductPurchase.getEndDate());
				productPurchase.setExternalLinks(
					curProductPurchase.getExternalLinks());
				productPurchase.setOriginalEndDate(
					curProductPurchase.getOriginalEndDate());
				productPurchase.setStartDate(curProductPurchase.getStartDate());

				break;
			}
		}

		_productPurchaseWebService.addProductPurchase(
			StringPool.BLANK, StringPool.BLANK, account.getKey(),
			productPurchase);
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeProductPurchases.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

}