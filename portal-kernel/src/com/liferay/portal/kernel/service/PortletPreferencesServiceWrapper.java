/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link PortletPreferencesService}.
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferencesService
 * @generated
 */
public class PortletPreferencesServiceWrapper
	implements PortletPreferencesService,
			   ServiceWrapper<PortletPreferencesService> {

	public PortletPreferencesServiceWrapper(
		PortletPreferencesService portletPreferencesService) {

		_portletPreferencesService = portletPreferencesService;
	}

	@Override
	public void deleteArchivedPreferences(long portletItemId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_portletPreferencesService.deleteArchivedPreferences(portletItemId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _portletPreferencesService.getOSGiServiceIdentifier();
	}

	@Override
	public void restoreArchivedPreferences(
			long groupId, com.liferay.portal.kernel.model.Layout layout,
			java.lang.String portletId, long portletItemId,
			javax.portlet.PortletPreferences jxPortletPreferences)
		throws com.liferay.portal.kernel.exception.PortalException {

		_portletPreferencesService.restoreArchivedPreferences(
			groupId, layout, portletId, portletItemId, jxPortletPreferences);
	}

	@Override
	public void restoreArchivedPreferences(
			long groupId, com.liferay.portal.kernel.model.Layout layout,
			java.lang.String portletId,
			com.liferay.portal.kernel.model.PortletItem portletItem,
			javax.portlet.PortletPreferences jxPortletPreferences)
		throws com.liferay.portal.kernel.exception.PortalException {

		_portletPreferencesService.restoreArchivedPreferences(
			groupId, layout, portletId, portletItem, jxPortletPreferences);
	}

	@Override
	public void restoreArchivedPreferences(
			long groupId, java.lang.String name,
			com.liferay.portal.kernel.model.Layout layout,
			java.lang.String portletId,
			javax.portlet.PortletPreferences jxPortletPreferences)
		throws com.liferay.portal.kernel.exception.PortalException {

		_portletPreferencesService.restoreArchivedPreferences(
			groupId, name, layout, portletId, jxPortletPreferences);
	}

	@Override
	public void updateArchivePreferences(
			long userId, long groupId, java.lang.String name,
			java.lang.String portletId,
			javax.portlet.PortletPreferences jxPortletPreferences)
		throws com.liferay.portal.kernel.exception.PortalException {

		_portletPreferencesService.updateArchivePreferences(
			userId, groupId, name, portletId, jxPortletPreferences);
	}

	@Override
	public PortletPreferencesService getWrappedService() {
		return _portletPreferencesService;
	}

	@Override
	public void setWrappedService(
		PortletPreferencesService portletPreferencesService) {

		_portletPreferencesService = portletPreferencesService;
	}

	private PortletPreferencesService _portletPreferencesService;

}