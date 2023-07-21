/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.internal.model.listener;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ExternalLinkModelListener extends BaseModelListener<ExternalLink> {

	@Override
	public void onAfterCreate(ExternalLink externalLink)
		throws ModelListenerException {

		try {
			_reindex(externalLink);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterRemove(ExternalLink externalLink)
		throws ModelListenerException {

		try {
			_reindex(externalLink);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(ExternalLink externalLink)
		throws ModelListenerException {

		try {
			_reindex(externalLink);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	private void _reindex(ExternalLink externalLink) throws PortalException {
		if (externalLink.getClassNameId() ==
				_classNameLocalService.getClassNameId(
					ProductConsumption.class)) {

			_productConsumptionLocalService.reindex(externalLink.getClassPK());
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(ProductEntry.class)) {

			_productEntryLocalService.reindex(externalLink.getClassPK());
		}
		else if (externalLink.getClassNameId() ==
					_classNameLocalService.getClassNameId(
						ProductPurchase.class)) {

			_productPurchaseLocalService.reindex(externalLink.getClassPK());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExternalLinkModelListener.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}