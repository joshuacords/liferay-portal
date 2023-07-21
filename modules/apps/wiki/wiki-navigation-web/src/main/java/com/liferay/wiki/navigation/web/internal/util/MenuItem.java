/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.navigation.web.internal.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageServiceUtil;

import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletURL;

/**
 * @author Thiago Moreira
 * @author Peter Shin
 */
public class MenuItem implements Serializable {

	public static List<MenuItem> fromWikiNode(
		long nodeId, int depth, PortletURL portletURL) {

		List<WikiPage> wikiPages = null;

		try {
			wikiPages = WikiPageServiceUtil.getNodePages(
				nodeId, WikiNavigationConstants.MAX_PAGES);
		}
		catch (Exception exception) {
			return new LinkedList<>();
		}

		return _fromWikiNode(wikiPages, 1, depth, portletURL);
	}

	public static List<MenuItem> fromWikiPage(
		WikiPage wikiPage, PortletURL portletURL) {

		return _fromWikiPage(wikiPage, portletURL);
	}

	public MenuItem() {
		_children = new LinkedList<>();
	}

	public void addChild(MenuItem child) {
		_children.add(child);
	}

	public void addChildren(List<MenuItem> children) {
		_children.addAll(children);
	}

	public List<MenuItem> getChildren() {
		return _children;
	}

	public boolean getExternalURL() {
		return _externalURL;
	}

	public String getLabel() {
		return _label;
	}

	public String getURL() {
		return _url;
	}

	public void setChildren(List<MenuItem> children) {
		_children = children;
	}

	public void setExternalURL(boolean externalURL) {
		_externalURL = externalURL;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setURL(String url) {
		_url = url;
	}

	private static List<MenuItem> _fromWikiNode(
		List<WikiPage> wikiPages, int curDepth, int depth,
		PortletURL portletURL) {

		List<MenuItem> menuItems = new LinkedList<>();

		for (WikiPage wikiPage : wikiPages) {
			if ((wikiPage.fetchParentPage() != null) && (curDepth == 1)) {
				continue;
			}

			String title = wikiPage.getTitle();

			WikiNode wikiNode = wikiPage.getNode();

			portletURL.setParameter(
				"nodeId", String.valueOf(wikiNode.getNodeId()));

			portletURL.setParameter("title", title);

			MenuItem menuItem = new MenuItem();

			menuItem.setLabel(title);
			menuItem.setURL(portletURL.toString());

			if ((depth >= curDepth) ||
				(depth == WikiNavigationConstants.DEPTH_ALL)) {

				List<MenuItem> children = _fromWikiNode(
					wikiPage.getViewableChildPages(), curDepth + 1, depth,
					portletURL);

				menuItem.addChildren(children);
			}

			menuItems.add(menuItem);
		}

		return menuItems;
	}

	private static List<MenuItem> _fromWikiPage(
		WikiPage wikiPage, PortletURL portletURL) {

		List<MenuItem> menuItems = new LinkedList<>();

		Matcher matcher = _pattern.matcher(wikiPage.getContent());

		while (matcher.find()) {
			String title = GetterUtil.getString(matcher.group(1));

			MenuItem menuItem = new MenuItem();

			menuItem.setLabel(title);

			menuItems.add(menuItem);

			String s = matcher.group(2);

			if (s != null) {
				MenuItem childMenuItem = new MenuItem();

				int index = s.indexOf(StringPool.PIPE);

				String label = null;
				String url = null;

				if (index != -1) {
					label = s.substring(index + 1);
					url = s.substring(0, index);
				}
				else {
					label = s;
					url = s;
				}

				if (!url.startsWith(Http.HTTP)) {
					portletURL.setParameter("title", url);
					portletURL.setParameter(
						"nodeId", String.valueOf(wikiPage.getNodeId()));

					url = portletURL.toString();
				}
				else {
					childMenuItem.setExternalURL(true);
				}

				childMenuItem.setLabel(label);
				childMenuItem.setURL(url);

				menuItem.addChild(childMenuItem);
			}
		}

		return menuItems;
	}

	private static final Pattern _pattern = Pattern.compile(
		"(?:(?:==\\s(.*?)\\s==)*(?:\\Q[[\\E(.*?)\\Q]]\\E)*)*");

	private List<MenuItem> _children;
	private boolean _externalURL;
	private String _label;
	private String _url;

}