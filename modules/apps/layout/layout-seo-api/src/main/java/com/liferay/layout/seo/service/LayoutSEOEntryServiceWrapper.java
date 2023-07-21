/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutSEOEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntryService
 * @generated
 */
public class LayoutSEOEntryServiceWrapper
	implements LayoutSEOEntryService, ServiceWrapper<LayoutSEOEntryService> {

	public LayoutSEOEntryServiceWrapper(
		LayoutSEOEntryService layoutSEOEntryService) {

		_layoutSEOEntryService = layoutSEOEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutSEOEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.layout.seo.model.LayoutSEOEntry updateLayoutSEOEntry(
			long groupId, boolean privateLayout, long layoutId, boolean enabled,
			java.util.Map<java.util.Locale, String> canonicalURLMap,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryService.updateLayoutSEOEntry(
			groupId, privateLayout, layoutId, enabled, canonicalURLMap,
			serviceContext);
	}

	@Override
	public LayoutSEOEntryService getWrappedService() {
		return _layoutSEOEntryService;
	}

	@Override
	public void setWrappedService(LayoutSEOEntryService layoutSEOEntryService) {
		_layoutSEOEntryService = layoutSEOEntryService;
	}

	private LayoutSEOEntryService _layoutSEOEntryService;

}