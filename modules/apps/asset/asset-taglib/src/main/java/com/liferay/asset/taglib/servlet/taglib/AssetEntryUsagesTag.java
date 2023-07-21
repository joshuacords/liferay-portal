/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.asset.taglib.internal.servlet.taglib.util.AssetEntryUsagesTaglibUtil;
import com.liferay.fragment.constants.FragmentActionKeys;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorWebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryUsagesTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		AssetEntryUsagesTaglibUtil.recordAssetEntryUsage(_className, _classPK);

		request.setAttribute(
			ContentPageEditorWebKeys.FRAGMENT_COLLECTION_CONTRIBUTOR_TRACKER,
			ServletContextUtil.getFragmentCollectionContributorTracker());
		request.setAttribute(
			FragmentActionKeys.FRAGMENT_RENDERER_TRACKER,
			ServletContextUtil.getFragmentRendererTracker());

		return super.doStartTag();
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_className = null;
		_classPK = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-asset:asset-entry-usages:className", _className);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-entry-usages:classPK",
			String.valueOf(_classPK));
	}

	private static final String _PAGE = "/asset_entry_usages/page.jsp";

	private String _className;
	private long _classPK;

}