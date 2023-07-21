/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.link.to.page.internal.controller;

import com.liferay.item.selector.ItemSelector;
import com.liferay.layout.type.controller.link.to.page.internal.constants.LinkToPageLayoutTypeControllerWebKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.impl.BaseLayoutTypeControllerImpl;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.servlet.PipingServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = "layout.type=" + LayoutConstants.TYPE_LINK_TO_LAYOUT,
	service = LayoutTypeController.class
)
public class LinkToPageLayoutTypeController
	extends BaseLayoutTypeControllerImpl {

	@Override
	public String getType() {
		return LayoutConstants.TYPE_LINK_TO_LAYOUT;
	}

	@Override
	public String getURL() {
		return _URL;
	}

	@Override
	public String includeEditContent(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Layout layout)
		throws Exception {

		httpServletRequest.setAttribute(
			LinkToPageLayoutTypeControllerWebKeys.ITEM_SELECTOR, _itemSelector);
		httpServletRequest.setAttribute(WebKeys.SEL_LAYOUT, layout);

		return super.includeEditContent(
			httpServletRequest, httpServletResponse, layout);
	}

	@Override
	public boolean isFirstPageable() {
		return false;
	}

	@Override
	public boolean isParentable() {
		return true;
	}

	@Override
	public boolean isSitemapable() {
		return false;
	}

	@Override
	public boolean isURLFriendliable() {
		return true;
	}

	@Override
	public boolean isWorkflowEnabled() {
		return false;
	}

	@Override
	protected ServletResponse createServletResponse(
		HttpServletResponse httpServletResponse,
		UnsyncStringWriter unsyncStringWriter) {

		return new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);
	}

	@Override
	protected String getEditPage() {
		return _EDIT_PAGE;
	}

	@Override
	protected String getViewPage() {
		return StringPool.BLANK;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.type.controller.link.to.page)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private static final String _EDIT_PAGE = "/layout/edit/link_to_layout.jsp";

	private static final String _URL =
		"${liferay:mainPath}/portal/layout?p_v_l_s_g_id=${liferay:pvlsgid}&" +
			"groupId=${liferay:groupId}&privateLayout=${privateLayout}&" +
				"layoutId=${linkToLayoutId}";

	@Reference
	private ItemSelector _itemSelector;

}