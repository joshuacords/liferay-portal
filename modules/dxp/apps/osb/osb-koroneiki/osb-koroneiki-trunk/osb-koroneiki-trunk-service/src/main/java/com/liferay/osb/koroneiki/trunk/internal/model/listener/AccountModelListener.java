/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.model.listener;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountModelListener extends BaseModelListener<Account> {

	@Override
	public void onBeforeRemove(Account account) throws ModelListenerException {
		try {

			// Product consumptions

			List<ProductConsumption> productConsumptions =
				_productConsumptionLocalService.getAccountProductConsumptions(
					account.getAccountId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (ProductConsumption productConsumption : productConsumptions) {
				_productConsumptionLocalService.deleteProductConsumption(
					productConsumption.getProductConsumptionId());
			}

			// Product purchases

			List<ProductPurchase> productPurchases =
				_productPurchaseLocalService.getAccountProductPurchases(
					account.getAccountId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			for (ProductPurchase productPurchase : productPurchases) {
				_productPurchaseLocalService.deleteProductPurchase(
					productPurchase.getProductPurchaseId());
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}