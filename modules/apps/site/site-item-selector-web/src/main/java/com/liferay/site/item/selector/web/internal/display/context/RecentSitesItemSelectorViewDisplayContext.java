/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.item.selector.web.internal.display.context;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;
import com.liferay.site.util.RecentGroupManager;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class RecentSitesItemSelectorViewDisplayContext
	extends BaseSitesItemSelectorViewDisplayContext
	implements SitesItemSelectorViewDisplayContext {

	public RecentSitesItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest,
		SiteItemSelectorCriterion siteItemSelectorCriterion,
		String itemSelectedEventName, PortletURL portletURL,
		RecentGroupManager recentGroupManager) {

		super(
			httpServletRequest, siteItemSelectorCriterion,
			itemSelectedEventName, portletURL);

		_recentGroupManager = recentGroupManager;
	}

	@Override
	public String getGroupName(Group group) throws PortalException {
		String groupName = super.getGroupName(group);

		if (group.isStaged() && group.isStagingGroup()) {
			StringBundler sb = new StringBundler(5);

			sb.append(groupName);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(LanguageUtil.get(request, "staging"));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			groupName = sb.toString();
		}

		return groupName;
	}

	@Override
	public GroupSearch getGroupSearch() throws Exception {
		GroupSearch groupSearch = new GroupSearch(
			getPortletRequest(), getPortletURL());

		groupSearch.setEmptyResultsMessage(
			"you-have-not-visited-any-sites-recently");

		List<Group> results = _recentGroupManager.getRecentGroups(request);

		groupSearch.setTotal(results.size());

		results = ListUtil.subList(
			results, groupSearch.getStart(), groupSearch.getEnd());

		groupSearch.setResults(results);

		return groupSearch;
	}

	@Override
	public boolean isShowSearch() {
		return false;
	}

	private final RecentGroupManager _recentGroupManager;

}