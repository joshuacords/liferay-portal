/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.LayoutSetPrototypePermissionUtil;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.base.LayoutSetPrototypeServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class LayoutSetPrototypeServiceImpl
	extends LayoutSetPrototypeServiceBaseImpl {

	@Override
	public LayoutSetPrototype addLayoutSetPrototype(
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			boolean active, boolean layoutsUpdateable,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_LAYOUT_PROTOTYPE);

		User user = getUser();

		return layoutSetPrototypeLocalService.addLayoutSetPrototype(
			user.getUserId(), user.getCompanyId(), nameMap, descriptionMap,
			active, layoutsUpdateable, serviceContext);
	}

	@Override
	public void deleteLayoutSetPrototype(long layoutSetPrototypeId)
		throws PortalException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.DELETE);

		layoutSetPrototypeLocalService.deleteLayoutSetPrototype(
			layoutSetPrototypeId);
	}

	@Override
	public LayoutSetPrototype fetchLayoutSetPrototype(long layoutSetPrototypeId)
		throws PortalException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.VIEW);

		return layoutSetPrototypeLocalService.fetchLayoutSetPrototype(
			layoutSetPrototypeId);
	}

	@Override
	public LayoutSetPrototype getLayoutSetPrototype(long layoutSetPrototypeId)
		throws PortalException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.VIEW);

		return layoutSetPrototypeLocalService.getLayoutSetPrototype(
			layoutSetPrototypeId);
	}

	@Override
	public List<LayoutSetPrototype> search(
			long companyId, Boolean active,
			OrderByComparator<LayoutSetPrototype> obc)
		throws PortalException {

		List<LayoutSetPrototype> filteredLayoutSetPrototypes =
			new ArrayList<>();

		List<LayoutSetPrototype> layoutSetPrototypes =
			layoutSetPrototypeLocalService.search(
				companyId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (LayoutSetPrototypePermissionUtil.contains(
					getPermissionChecker(),
					layoutSetPrototype.getLayoutSetPrototypeId(),
					ActionKeys.VIEW)) {

				filteredLayoutSetPrototypes.add(layoutSetPrototype);
			}
		}

		return filteredLayoutSetPrototypes;
	}

	@Override
	public LayoutSetPrototype updateLayoutSetPrototype(
			long layoutSetPrototypeId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, boolean active,
			boolean layoutsUpdateable, ServiceContext serviceContext)
		throws PortalException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.UPDATE);

		return layoutSetPrototypeLocalService.updateLayoutSetPrototype(
			layoutSetPrototypeId, nameMap, descriptionMap, active,
			layoutsUpdateable, serviceContext);
	}

	@Override
	public LayoutSetPrototype updateLayoutSetPrototype(
			long layoutSetPrototypeId, String settings)
		throws PortalException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.UPDATE);

		return layoutSetPrototypeLocalService.updateLayoutSetPrototype(
			layoutSetPrototypeId, settings);
	}

}