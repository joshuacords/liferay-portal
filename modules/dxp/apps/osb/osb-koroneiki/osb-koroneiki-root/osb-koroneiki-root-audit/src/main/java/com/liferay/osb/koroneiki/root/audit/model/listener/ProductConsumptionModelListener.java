/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class ProductConsumptionModelListener
	extends BaseAuditModelListener<ProductConsumption> {

	@Override
	protected long getClassNameId(ProductConsumption productConsumption) {
		return classNameLocalService.getClassNameId(Account.class);
	}

	@Override
	protected long getClassPK(ProductConsumption productConsumption) {
		return productConsumption.getAccountId();
	}

	@Override
	protected String getDescription(ProductConsumption productConsumption)
		throws PortalException {

		ProductEntry productEntry = productConsumption.getProductEntry();

		return productEntry.getName();
	}

	@Override
	protected ProductConsumption getModel(long classPK) throws PortalException {
		return _productConsumptionLocalService.getProductConsumption(classPK);
	}

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

}