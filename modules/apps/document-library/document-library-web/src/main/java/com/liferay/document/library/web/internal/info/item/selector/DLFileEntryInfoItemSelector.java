/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.info.item.selector;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.info.item.selector.InfoItemSelector;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletMode;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(service = InfoItemSelector.class)
public class DLFileEntryInfoItemSelector
	implements InfoItemSelector<DLFileEntry> {

	@Override
	public PortletURL getInfoItemSelectorPortletURL(
			HttpServletRequest httpServletRequest)
		throws Exception {

		PortletURL infoItemSelectorPortletURL =
			PortletProviderUtil.getPortletURL(
				httpServletRequest, DLFileEntry.class.getName(),
				PortletProvider.Action.BROWSE);

		if (infoItemSelectorPortletURL == null) {
			return null;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		infoItemSelectorPortletURL.setParameter(
			"groupId", String.valueOf(themeDisplay.getScopeGroupId()));
		infoItemSelectorPortletURL.setParameter(
			"selectedGroupIds", String.valueOf(themeDisplay.getScopeGroupId()));

		infoItemSelectorPortletURL.setParameter(
			"typeSelection", DLFileEntry.class.getName());
		infoItemSelectorPortletURL.setParameter(
			"showNonindexable", String.valueOf(Boolean.TRUE));
		infoItemSelectorPortletURL.setParameter(
			"showScheduled", String.valueOf(Boolean.TRUE));
		infoItemSelectorPortletURL.setPortletMode(PortletMode.VIEW);
		infoItemSelectorPortletURL.setWindowState(LiferayWindowState.POP_UP);

		return infoItemSelectorPortletURL;
	}

}