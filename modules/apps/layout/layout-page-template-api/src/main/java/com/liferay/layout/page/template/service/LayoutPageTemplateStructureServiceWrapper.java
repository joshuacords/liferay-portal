/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutPageTemplateStructureService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateStructureService
 * @generated
 */
public class LayoutPageTemplateStructureServiceWrapper
	implements LayoutPageTemplateStructureService,
			   ServiceWrapper<LayoutPageTemplateStructureService> {

	public LayoutPageTemplateStructureServiceWrapper(
		LayoutPageTemplateStructureService layoutPageTemplateStructureService) {

		_layoutPageTemplateStructureService =
			layoutPageTemplateStructureService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutPageTemplateStructureService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.layout.page.template.model.LayoutPageTemplateStructure
			updateLayoutPageTemplateStructure(
				long groupId, long classNameId, long classPK,
				long segmentsExperienceId, String data)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutPageTemplateStructureService.
			updateLayoutPageTemplateStructure(
				groupId, classNameId, classPK, segmentsExperienceId, data);
	}

	@Override
	public LayoutPageTemplateStructureService getWrappedService() {
		return _layoutPageTemplateStructureService;
	}

	@Override
	public void setWrappedService(
		LayoutPageTemplateStructureService layoutPageTemplateStructureService) {

		_layoutPageTemplateStructureService =
			layoutPageTemplateStructureService;
	}

	private LayoutPageTemplateStructureService
		_layoutPageTemplateStructureService;

}