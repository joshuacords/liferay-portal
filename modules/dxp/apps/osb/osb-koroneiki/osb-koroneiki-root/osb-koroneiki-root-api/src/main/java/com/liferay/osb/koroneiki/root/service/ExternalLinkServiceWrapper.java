/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExternalLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkService
 * @generated
 */
public class ExternalLinkServiceWrapper
	implements ExternalLinkService, ServiceWrapper<ExternalLinkService> {

	public ExternalLinkServiceWrapper(ExternalLinkService externalLinkService) {
		_externalLinkService = externalLinkService;
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink addExternalLink(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.addExternalLink(
			classNameId, classPK, domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink deleteExternalLink(
			long externalLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.deleteExternalLink(externalLinkId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink deleteExternalLink(
			String externalLinkKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.deleteExternalLink(externalLinkKey);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink getExternalLink(
			long externalLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.getExternalLink(externalLinkId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink getExternalLink(
			String externalLinkKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.getExternalLink(externalLinkKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
			getExternalLinks(long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.getExternalLinks(
			classNameId, classPK, start, end);
	}

	@Override
	public int getExternalLinksCount(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.getExternalLinksCount(classNameId, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _externalLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink updateExternalLink(
			long externalLinkId, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkService.updateExternalLink(
			externalLinkId, entityId);
	}

	@Override
	public ExternalLinkService getWrappedService() {
		return _externalLinkService;
	}

	@Override
	public void setWrappedService(ExternalLinkService externalLinkService) {
		_externalLinkService = externalLinkService;
	}

	private ExternalLinkService _externalLinkService;

}