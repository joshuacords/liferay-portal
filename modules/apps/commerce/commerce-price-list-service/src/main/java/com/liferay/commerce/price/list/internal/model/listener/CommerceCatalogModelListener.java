/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.model.listener;

import com.liferay.commerce.price.list.internal.helper.CommerceBasePriceListHelper;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceCatalogModelListener
	extends BaseModelListener<CommerceCatalog> {

	@Override
	public void onAfterCreate(CommerceCatalog commerceCatalog) {
		try {
			_commerceBasePriceListHelper.addCatalogBaseCommercePriceList(
				commerceCatalog);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}
		}
	}

	@Override
	public void onBeforeRemove(CommerceCatalog commerceCatalog) {
		try {
			_commerceBasePriceListHelper.deleteCatalogBaseCommercePriceList(
				commerceCatalog);
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(portalException, portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCatalogModelListener.class);

	@Reference
	private CommerceBasePriceListHelper _commerceBasePriceListHelper;

}