/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.uad.display;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalFolder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.user.associated.data.display.UADDisplay;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Balázs Sáfrány-Kovalik
 */
@Component(immediate = true, service = UADDisplay.class)
public class JournalFolderUADDisplay extends BaseJournalFolderUADDisplay {

	@Override
	public String getEditURL(
			JournalFolder journalFolder,
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		if (journalFolder.isInTrash()) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			liferayPortletRequest, JournalPortletKeys.JOURNAL,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/edit_folder.jsp");
		portletURL.setParameter(
			"redirect", _portal.getCurrentURL(liferayPortletRequest));
		portletURL.setParameter(
			"groupId", String.valueOf(journalFolder.getGroupId()));
		portletURL.setParameter(
			"folderId", String.valueOf(journalFolder.getFolderId()));

		return portletURL.toString();
	}

	@Reference
	private Portal _portal;

}