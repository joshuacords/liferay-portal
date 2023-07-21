/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class ProductPurchaseModelListener
	extends BaseAuditModelListener<ProductPurchase> {

	@Override
	protected long getClassNameId(ProductPurchase productPurchase) {
		return classNameLocalService.getClassNameId(Account.class);
	}

	@Override
	protected long getClassPK(ProductPurchase productPurchase) {
		return productPurchase.getAccountId();
	}

	@Override
	protected String getDescription(ProductPurchase productPurchase)
		throws PortalException {

		ProductEntry productEntry = productPurchase.getProductEntry();

		return productEntry.getName();
	}

	@Override
	protected ProductPurchase getModel(long classPK) throws PortalException {
		return _productPurchaseLocalService.getProductPurchase(classPK);
	}

	@Override
	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (field.equals("endDate") || field.equals("originalEndDate") ||
			field.equals("productPurchaseId") || field.equals("startDate") ||
			field.equals("status")) {

			return false;
		}

		return super.isSkipFieldUpdate(field, oldValue, newValue);
	}

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}