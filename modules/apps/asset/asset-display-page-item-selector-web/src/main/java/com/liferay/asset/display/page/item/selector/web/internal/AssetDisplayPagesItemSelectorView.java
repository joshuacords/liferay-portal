/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.item.selector.web.internal;

import com.liferay.asset.display.page.item.selector.criterion.AssetDisplayPageSelectorCriterion;
import com.liferay.asset.display.page.item.selector.web.internal.constants.AssetDisplayPageItemSelectorWebKeys;
import com.liferay.asset.display.page.item.selector.web.internal.display.context.AssetDisplayPagesItemSelectorViewDisplayContext;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.display.contributor.InfoDisplayContributorTracker;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = ItemSelectorView.class)
public class AssetDisplayPagesItemSelectorView
	implements ItemSelectorView<AssetDisplayPageSelectorCriterion> {

	@Override
	public Class<AssetDisplayPageSelectorCriterion>
		getItemSelectorCriterionClass() {

		return AssetDisplayPageSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, AssetDisplayPagesItemSelectorView.class);

		return ResourceBundleUtil.getString(
			resourceBundle, "display-page-templates");
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			AssetDisplayPageSelectorCriterion assetDisplayPageSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)servletRequest;

		AssetDisplayPagesItemSelectorViewDisplayContext
			assetDisplayPagesItemSelectorViewDisplayContext =
				new AssetDisplayPagesItemSelectorViewDisplayContext(
					httpServletRequest, assetDisplayPageSelectorCriterion,
					itemSelectedEventName, portletURL);

		servletRequest.setAttribute(
			AssetDisplayPageItemSelectorWebKeys.
				ASSET_DISPLAY_PAGES_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			assetDisplayPagesItemSelectorViewDisplayContext);

		servletRequest.setAttribute(
			InfoDisplayWebKeys.INFO_DISPLAY_CONTRIBUTOR_TRACKER,
			_infoDisplayContributorTracker);

		ServletContext servletContext = _servletContext;

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/display_pages.jsp");

		requestDispatcher.include(servletRequest, servletResponse);
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(new UUIDItemSelectorReturnType()));

	@Reference
	private InfoDisplayContributorTracker _infoDisplayContributorTracker;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.display.page.item.selector.web)"
	)
	private ServletContext _servletContext;

}