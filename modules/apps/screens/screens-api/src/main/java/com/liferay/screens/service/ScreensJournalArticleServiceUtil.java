/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for ScreensJournalArticle. This utility wraps
 * <code>com.liferay.screens.service.impl.ScreensJournalArticleServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author José Manuel Navarro
 * @see ScreensJournalArticleService
 * @generated
 */
public class ScreensJournalArticleServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.screens.service.impl.ScreensJournalArticleServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static String getJournalArticleContent(
			long classPK, java.util.Locale locale)
		throws PortalException {

		return getService().getJournalArticleContent(classPK, locale);
	}

	public static String getJournalArticleContent(
			long classPK, long ddmTemplateId, java.util.Locale locale)
		throws PortalException {

		return getService().getJournalArticleContent(
			classPK, ddmTemplateId, locale);
	}

	public static String getJournalArticleContent(
			long groupId, String articleId, long ddmTemplateId,
			java.util.Locale locale)
		throws PortalException {

		return getService().getJournalArticleContent(
			groupId, articleId, ddmTemplateId, locale);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static ScreensJournalArticleService getService() {
		return _service;
	}

	public static void setService(ScreensJournalArticleService service) {
		_service = service;
	}

	private static volatile ScreensJournalArticleService _service;

}