/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.item.selector.resolver;

import com.liferay.item.selector.ItemSelectorReturnTypeResolver;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.escape.WikiEscapeUtil;
import com.liferay.wiki.item.selector.WikiPageURLItemSelectorReturnType;
import com.liferay.wiki.model.WikiPage;

import java.net.URL;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ItemSelectorReturnTypeResolver.class
)
public class WikiPageURLItemSelectorReturnTypeResolver
	implements WikiPageItemSelectorReturnTypeResolver
		<WikiPageURLItemSelectorReturnType, WikiPage> {

	@Override
	public Class<WikiPageURLItemSelectorReturnType>
		getItemSelectorReturnTypeClass() {

		return WikiPageURLItemSelectorReturnType.class;
	}

	@Override
	public Class<WikiPage> getModelClass() {
		return WikiPage.class;
	}

	@Override
	public String getTitle(WikiPage page, ThemeDisplay themeDisplay) {
		return StringPool.BLANK;
	}

	@Override
	public String getValue(WikiPage page, ThemeDisplay themeDisplay)
		throws Exception {

		String layoutFullURL = _portal.getLayoutFullURL(
			page.getGroupId(), WikiPortletKeys.WIKI);

		if (Validator.isNotNull(layoutFullURL)) {
			URL urlObject = new URL(layoutFullURL);

			String path = urlObject.getPath();

			if (Validator.isNotNull(path)) {
				return StringBundler.concat(
					path, Portal.FRIENDLY_URL_SEPARATOR, "wiki/",
					page.getNodeId(), StringPool.SLASH,
					URLCodec.encodeURL(
						WikiEscapeUtil.escapeName(page.getTitle())));
			}
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			themeDisplay.getRequest(), WikiPortletKeys.WIKI_ADMIN,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/wiki/view");
		portletURL.setParameter("nodeId", String.valueOf(page.getNodeId()));
		portletURL.setParameter("title", page.getTitle());

		return _http.removeDomain(portletURL.toString());
	}

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

}