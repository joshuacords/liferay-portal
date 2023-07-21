/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.portlet.action;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.web.internal.util.JournalRSSUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseRSSMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = {
		"auth.token.ignore.mvc.action=true",
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=rss",
		"portlet.add.default.resource.check.whitelist.mvc.action=true"
	},
	service = MVCResourceCommand.class
)
public class RSSMVCResourceCommand extends BaseRSSMVCResourceCommand {

	@Override
	protected byte[] getRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		return _journalRSSUtil.getRSS(resourceRequest, resourceResponse);
	}

	@Reference
	protected void setJournalRSSUtil(JournalRSSUtil journalRSSUtil) {
		_journalRSSUtil = journalRSSUtil;
	}

	protected void unsetJournalRSSUtil(JournalRSSUtil journalRSSUtil) {
		_journalRSSUtil = null;
	}

	private JournalRSSUtil _journalRSSUtil;

}