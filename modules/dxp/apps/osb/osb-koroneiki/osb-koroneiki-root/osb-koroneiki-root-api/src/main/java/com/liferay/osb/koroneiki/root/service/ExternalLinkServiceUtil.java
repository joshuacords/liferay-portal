/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for ExternalLink. This utility wraps
 * <code>com.liferay.osb.koroneiki.root.service.impl.ExternalLinkServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkService
 * @generated
 */
public class ExternalLinkServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.root.service.impl.ExternalLinkServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ExternalLink addExternalLink(
			long classNameId, long classPK, String domain, String entityName,
			String entityId)
		throws PortalException {

		return getService().addExternalLink(
			classNameId, classPK, domain, entityName, entityId);
	}

	public static ExternalLink deleteExternalLink(long externalLinkId)
		throws PortalException {

		return getService().deleteExternalLink(externalLinkId);
	}

	public static ExternalLink deleteExternalLink(String externalLinkKey)
		throws PortalException {

		return getService().deleteExternalLink(externalLinkKey);
	}

	public static ExternalLink getExternalLink(long externalLinkId)
		throws PortalException {

		return getService().getExternalLink(externalLinkId);
	}

	public static ExternalLink getExternalLink(String externalLinkKey)
		throws PortalException {

		return getService().getExternalLink(externalLinkKey);
	}

	public static List<ExternalLink> getExternalLinks(
			long classNameId, long classPK, int start, int end)
		throws PortalException {

		return getService().getExternalLinks(classNameId, classPK, start, end);
	}

	public static int getExternalLinksCount(long classNameId, long classPK)
		throws PortalException {

		return getService().getExternalLinksCount(classNameId, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ExternalLink updateExternalLink(
			long externalLinkId, String entityId)
		throws PortalException {

		return getService().updateExternalLink(externalLinkId, entityId);
	}

	public static ExternalLinkService getService() {
		return _service;
	}

	public static void setService(ExternalLinkService service) {
		_service = service;
	}

	private static volatile ExternalLinkService _service;

}