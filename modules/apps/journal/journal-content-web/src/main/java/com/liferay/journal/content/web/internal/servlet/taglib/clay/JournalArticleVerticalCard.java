/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.web.internal.servlet.taglib.clay;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseVerticalCard;
import com.liferay.journal.model.JournalArticle;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalArticleVerticalCard extends BaseVerticalCard {

	public JournalArticleVerticalCard(
		JournalArticle article, AssetRenderer<JournalArticle> assetRenderer,
		RenderRequest renderRequest) {

		super(article, renderRequest, null);

		_article = article;
		_assetRenderer = assetRenderer;
		_renderRequest = renderRequest;

		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getIcon() {
		return "web-content";
	}

	@Override
	public String getImageSrc() {
		try {
			return HtmlUtil.escapeAttribute(
				_assetRenderer.getThumbnailPath(_renderRequest));
		}
		catch (Exception exception) {
			return null;
		}
	}

	@Override
	public String getTitle() {
		String title = _assetRenderer.getTitle(_themeDisplay.getLocale());

		if (_article.getGroupId() == _themeDisplay.getScopeGroupId()) {
			return title;
		}

		Group articleGroup = GroupLocalServiceUtil.fetchGroup(
			_article.getGroupId());

		if (articleGroup == null) {
			return title;
		}

		try {
			StringBundler sb = new StringBundler(5);

			sb.append(title);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(
				HtmlUtil.escape(
					articleGroup.getDescriptiveName(
						_themeDisplay.getLocale())));
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
		catch (Exception exception) {
			return title;
		}
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private final JournalArticle _article;
	private final AssetRenderer<JournalArticle> _assetRenderer;
	private final RenderRequest _renderRequest;
	private final ThemeDisplay _themeDisplay;

}