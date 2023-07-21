/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PortalPreferences;
import com.liferay.portal.kernel.model.PortletConstants;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.base.PortalPreferencesLocalServiceBaseImpl;
import com.liferay.portlet.PortalPreferencesImpl;
import com.liferay.portlet.PortalPreferencesWrapper;
import com.liferay.portlet.PortalPreferencesWrapperCacheUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Alexander Chow
 */
public class PortalPreferencesLocalServiceImpl
	extends PortalPreferencesLocalServiceBaseImpl {

	@Override
	public PortalPreferences addPortalPreferences(
		long ownerId, int ownerType, String defaultPreferences) {

		PortalPreferences previousPortalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (previousPortalPreferences != null) {
			throw new IllegalArgumentException(
				"Duplicate owner ID and owner type exists in " +
					previousPortalPreferences);
		}

		PortalPreferencesWrapperCacheUtil.remove(ownerId, ownerType);

		long portalPreferencesId = counterLocalService.increment();

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.create(portalPreferencesId);

		portalPreferences.setOwnerId(ownerId);
		portalPreferences.setOwnerType(ownerType);

		if (Validator.isNull(defaultPreferences)) {
			defaultPreferences = PortletConstants.DEFAULT_PREFERENCES;
		}

		portalPreferences.setPreferences(defaultPreferences);

		try {
			portalPreferences = portalPreferencesPersistence.update(
				portalPreferences);
		}
		catch (SystemException systemException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Add failed, fetch {ownerId=", ownerId, ", ownerType=",
						ownerType, "}"));
			}

			portalPreferences = portalPreferencesPersistence.fetchByO_O(
				ownerId, ownerType, false);

			if (portalPreferences == null) {
				throw systemException;
			}
		}

		return portalPreferences;
	}

	@Override
	public PortalPreferences fetchPortalPreferences(
		long ownerId, int ownerType) {

		return portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);
	}

	@Override
	public PortletPreferences getPreferences(long ownerId, int ownerType) {
		return getPreferences(ownerId, ownerType, null);
	}

	@Override
	public PortletPreferences getPreferences(
		long ownerId, int ownerType, String defaultPreferences) {

		PortalPreferencesWrapper portalPreferencesWrapper =
			PortalPreferencesWrapperCacheUtil.get(ownerId, ownerType);

		if (portalPreferencesWrapper != null) {
			return portalPreferencesWrapper.clone();
		}

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (portalPreferences == null) {
			portalPreferences =
				portalPreferencesLocalService.addPortalPreferences(
					ownerId, ownerType, defaultPreferences);
		}

		PortalPreferencesImpl portalPreferencesImpl = new PortalPreferencesImpl(
			portalPreferences, false);

		portalPreferencesWrapper = new PortalPreferencesWrapper(
			portalPreferencesImpl);

		PortalPreferencesWrapperCacheUtil.put(
			ownerId, ownerType, portalPreferencesWrapper);

		return portalPreferencesWrapper.clone();
	}

	@Override
	public PortalPreferences updatePreferences(
		long ownerId, int ownerType,
		com.liferay.portal.kernel.portlet.PortalPreferences portalPreferences) {

		String xml = PortletPreferencesFactoryUtil.toXML(portalPreferences);

		return updatePreferences(ownerId, ownerType, xml);
	}

	@Override
	public PortalPreferences updatePreferences(
		long ownerId, int ownerType, String xml) {

		PortalPreferencesWrapperCacheUtil.remove(ownerId, ownerType);

		PortalPreferences portalPreferences =
			portalPreferencesPersistence.fetchByO_O(ownerId, ownerType);

		if (portalPreferences == null) {
			long portalPreferencesId = counterLocalService.increment();

			portalPreferences = portalPreferencesPersistence.create(
				portalPreferencesId);

			portalPreferences.setOwnerId(ownerId);
			portalPreferences.setOwnerType(ownerType);
		}

		portalPreferences.setPreferences(xml);

		return portalPreferencesPersistence.update(portalPreferences);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalPreferencesLocalServiceImpl.class);

}