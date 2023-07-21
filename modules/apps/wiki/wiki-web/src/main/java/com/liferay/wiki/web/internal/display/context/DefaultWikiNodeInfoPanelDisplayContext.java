/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.display.context;

import com.liferay.wiki.display.context.WikiNodeInfoPanelDisplayContext;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.wiki.web.internal.display.context.util.WikiNodeInfoPanelRequestHelper;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Roberto Díaz
 */
public class DefaultWikiNodeInfoPanelDisplayContext
	implements WikiNodeInfoPanelDisplayContext {

	public DefaultWikiNodeInfoPanelDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		_wikiNodeInfoPanelRequestHelper = new WikiNodeInfoPanelRequestHelper(
			httpServletRequest);
	}

	@Override
	public WikiNode getFirstNode() {
		List<WikiNode> nodes = _wikiNodeInfoPanelRequestHelper.getNodes();

		if (nodes.isEmpty()) {
			return null;
		}

		return nodes.get(0);
	}

	@Override
	public int getNodesCount() {
		return WikiNodeLocalServiceUtil.getNodesCount(
			_wikiNodeInfoPanelRequestHelper.getScopeGroupId());
	}

	@Override
	public int getSelectedNodesCount() {
		List<?> items = getSelectedNodes();

		return items.size();
	}

	@Override
	public UUID getUuid() {
		return _UUID;
	}

	@Override
	public boolean isMultipleNodeSelection() {
		List<?> items = getSelectedNodes();

		if (items.size() > 1) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSingleNodeSelection() {
		List<WikiNode> nodes = _wikiNodeInfoPanelRequestHelper.getNodes();

		if (nodes.size() == 1) {
			return true;
		}

		return false;
	}

	protected List<?> getSelectedNodes() {
		return _wikiNodeInfoPanelRequestHelper.getNodes();
	}

	private static final UUID _UUID = UUID.fromString(
		"A91E44F1-686A-4FC5-8877-43C2532543D3");

	private final WikiNodeInfoPanelRequestHelper
		_wikiNodeInfoPanelRequestHelper;

}