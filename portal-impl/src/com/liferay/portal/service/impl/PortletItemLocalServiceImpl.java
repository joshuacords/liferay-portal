/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.NoSuchPortletItemException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.PortletItemNameException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortletItem;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.base.PortletItemLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class PortletItemLocalServiceImpl
	extends PortletItemLocalServiceBaseImpl {

	@Override
	public PortletItem addPortletItem(
			long userId, long groupId, String name, String portletId,
			String className)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		long classNameId = classNameLocalService.getClassNameId(className);

		validate(name);

		long portletItemId = counterLocalService.increment();

		PortletItem portletItem = portletItemPersistence.create(portletItemId);

		portletItem.setGroupId(groupId);
		portletItem.setCompanyId(user.getCompanyId());
		portletItem.setUserId(user.getUserId());
		portletItem.setUserName(user.getFullName());
		portletItem.setName(name);
		portletItem.setPortletId(portletId);
		portletItem.setClassNameId(classNameId);

		return portletItemPersistence.update(portletItem);
	}

	@Override
	public PortletItem getPortletItem(
			long groupId, String name, String portletId, String className)
		throws PortalException {

		return portletItemPersistence.findByG_N_P_C(
			groupId, name, portletId,
			classNameLocalService.getClassNameId(className));
	}

	@Override
	public List<PortletItem> getPortletItems(long groupId, String className) {
		return portletItemPersistence.findByG_C(
			groupId, classNameLocalService.getClassNameId(className));
	}

	@Override
	public List<PortletItem> getPortletItems(
		long groupId, String portletId, String className) {

		return portletItemPersistence.findByG_P_C(
			groupId, portletId,
			classNameLocalService.getClassNameId(className));
	}

	@Override
	public PortletItem updatePortletItem(
			long userId, long groupId, String name, String portletId,
			String className)
		throws PortalException {

		PortletItem portletItem = null;

		try {
			User user = userPersistence.findByPrimaryKey(userId);

			portletItem = getPortletItem(
				groupId, name, portletId, PortletPreferences.class.getName());

			portletItem.setUserId(userId);
			portletItem.setUserName(user.getFullName());

			portletItem = portletItemPersistence.update(portletItem);
		}
		catch (NoSuchPortletItemException noSuchPortletItemException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(
					noSuchPortletItemException, noSuchPortletItemException);
			}

			portletItem = addPortletItem(
				userId, groupId, name, portletId,
				PortletPreferences.class.getName());
		}

		return portletItem;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new PortletItemNameException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletItemLocalServiceImpl.class);

}