/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.servlet.taglib.clay;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.web.internal.constants.FragmentWebKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.BaseBaseClayCard;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.VerticalCard;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

/**
 * @author Eudaldo Alonso
 */
public abstract class FragmentEntryVerticalCard
	extends BaseBaseClayCard implements VerticalCard {

	public FragmentEntryVerticalCard(
		FragmentEntry fragmentEntry, RenderRequest renderRequest,
		RowChecker rowChecker) {

		super(fragmentEntry, rowChecker);

		this.fragmentEntry = fragmentEntry;
		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getDefaultEventHandler() {
		return FragmentWebKeys.FRAGMENT_ENTRY_DROPDOWN_DEFAULT_EVENT_HANDLER;
	}

	@Override
	public String getIcon() {
		return "code";
	}

	@Override
	public String getImageSrc() {
		return fragmentEntry.getImagePreviewURL(themeDisplay);
	}

	@Override
	public String getStickerCssClass() {
		if (fragmentEntry.getType() == FragmentConstants.TYPE_COMPONENT) {
			return "file-icon-color-4";
		}

		return "file-icon-color-2";
	}

	@Override
	public String getStickerIcon() {
		if (fragmentEntry.getType() == FragmentConstants.TYPE_COMPONENT) {
			return "cards2";
		}

		return "cards-full";
	}

	@Override
	public String getTitle() {
		return HtmlUtil.escape(fragmentEntry.getName());
	}

	protected final FragmentEntry fragmentEntry;
	protected final ThemeDisplay themeDisplay;

}