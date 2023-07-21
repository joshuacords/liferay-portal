/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.base.GadgetServiceBaseImpl;
import com.liferay.opensocial.service.permission.GadgetPermission;
import com.liferay.opensocial.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Brian Wing Shun Chan
 * @author Dennis Ju
 */
public class GadgetServiceImpl extends GadgetServiceBaseImpl {

	@Override
	public Gadget addGadget(
			long companyId, String url, String portletCategoryNames,
			ServiceContext serviceContext)
		throws PortalException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.PUBLISH_GADGET);

		return gadgetLocalService.addGadget(
			companyId, url, portletCategoryNames, serviceContext);
	}

	@Override
	public void deleteGadget(long gadgetId, ServiceContext serviceContext)
		throws PortalException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(), gadgetId,
			ActionKeys.DELETE);

		gadgetLocalService.deleteGadget(gadgetId);
	}

	@Override
	public void updateGadget(
			long gadgetId, String portletCategoryNames,
			ServiceContext serviceContext)
		throws PortalException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(), gadgetId,
			ActionKeys.UPDATE);

		gadgetLocalService.updateGadget(gadgetId, portletCategoryNames);
	}

}