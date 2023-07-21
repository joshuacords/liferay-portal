/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.util.DDMDisplayTabItem;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "javax.portlet.name=" + PortletKeys.DOCUMENT_LIBRARY,
	service = {DDMDisplayTabItem.class, DocumentTypesDDMDisplayTabItem.class}
)
public class DocumentTypesDDMDisplayTabItem implements DDMDisplayTabItem {

	@Override
	public String getTitle(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			liferayPortletRequest.getLocale(),
			DocumentTypesDDMDisplayTabItem.class);

		return LanguageUtil.get(resourceBundle, "document-types");
	}

	@Override
	public String getURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			liferayPortletRequest, PortletKeys.DOCUMENT_LIBRARY_ADMIN,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("navigation", "file_entry_types");

		return portletURL.toString();
	}

	@Reference
	private Portal _portal;

}