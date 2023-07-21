/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.servlet.taglib.clay;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.journal.web.internal.display.context.JournalSelectDDMTemplateDisplayContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.Map;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class JournalSelectDDMTemplateVerticalCard implements VerticalCard {

	public JournalSelectDDMTemplateVerticalCard(
		BaseModel<?> baseModel, RenderRequest renderRequest,
		JournalSelectDDMTemplateDisplayContext
			journalSelectDDMTemplateDisplayContext) {

		_journalSelectDDMTemplateDisplayContext =
			journalSelectDDMTemplateDisplayContext;

		_ddmTemplate = (DDMTemplate)baseModel;
		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
	}

	@Override
	public Map<String, String> getData() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HashMapBuilder.put(
			"ddmtemplateid", String.valueOf(_ddmTemplate.getTemplateId())
		).put(
			"ddmtemplatekey", _ddmTemplate.getTemplateKey()
		).put(
			"description", _ddmTemplate.getDescription(themeDisplay.getLocale())
		).put(
			"imageurl", _ddmTemplate.getTemplateImageURL(themeDisplay)
		).put(
			"name", _ddmTemplate.getName(themeDisplay.getLocale())
		).build();
	}

	@Override
	public String getElementClasses() {
		if (_ddmTemplate.getTemplateId() !=
				_journalSelectDDMTemplateDisplayContext.getDDMTemplateId()) {

			return "card-interactive card-interactive-secondary " +
				"selector-button";
		}

		return StringPool.BLANK;
	}

	@Override
	public String getIcon() {
		return "page-template";
	}

	@Override
	public String getImageSrc() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return HtmlUtil.escapeAttribute(
			_ddmTemplate.getTemplateImageURL(themeDisplay));
	}

	@Override
	public String getSubtitle() {
		Date createDate = _ddmTemplate.getModifiedDate();

		String modifiedDateDescription = LanguageUtil.getTimeDescription(
			_httpServletRequest,
			System.currentTimeMillis() - createDate.getTime(), true);

		return LanguageUtil.format(
			_httpServletRequest, "modified-x-ago", modifiedDateDescription);
	}

	@Override
	public String getTitle() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _ddmTemplate.getName(themeDisplay.getLocale());
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private final DDMTemplate _ddmTemplate;
	private final HttpServletRequest _httpServletRequest;
	private final JournalSelectDDMTemplateDisplayContext
		_journalSelectDDMTemplateDisplayContext;

}