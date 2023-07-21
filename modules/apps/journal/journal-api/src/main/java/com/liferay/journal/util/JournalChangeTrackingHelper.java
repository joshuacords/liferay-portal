/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.util;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides Change Tracking related information about Journal entities.
 *
 * @author     Zoltan Csaszi
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
@ProviderType
public interface JournalChangeTrackingHelper {

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public String getJournalArticleCTCollectionName(long userId, long classPK);

	public String getJournalArticleCTCollectionName(
		long companyId, long userId, long classPK);

	public PortletURL getJournalArticleCTCollectionURL(
		PortletRequest portletRequest, long companyId, long userId, long id);

	public boolean hasActiveCTCollection(long companyId, long userId);

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	public boolean isJournalArticleInChangeList(long userId, long classPK);

	public boolean isJournalArticleInChangeList(
		long companyId, long userId, long classPK);

}