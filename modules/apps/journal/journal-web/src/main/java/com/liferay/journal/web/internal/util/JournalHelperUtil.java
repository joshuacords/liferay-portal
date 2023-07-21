/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.util;

import com.liferay.journal.util.JournalHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tom Wang
 */
@Component(immediate = true, service = {})
public class JournalHelperUtil {

	public static String diffHtml(
			long groupId, String articleId, double sourceVersion,
			double targetVersion, String languageId,
			PortletRequestModel portletRequestModel, ThemeDisplay themeDisplay)
		throws Exception {

		return _journalHelper.diffHtml(
			groupId, articleId, sourceVersion, targetVersion, languageId,
			portletRequestModel, themeDisplay);
	}

	public static String getAbsolutePath(
			PortletRequest portletRequest, long folderId)
		throws PortalException {

		return _journalHelper.getAbsolutePath(portletRequest, folderId);
	}

	public static int getRestrictionType(long folderId) {
		return _journalHelper.getRestrictionType(folderId);
	}

	@Reference(unbind = "-")
	protected void setJournalHelper(JournalHelper journalHelper) {
		_journalHelper = journalHelper;
	}

	private static JournalHelper _journalHelper;

}