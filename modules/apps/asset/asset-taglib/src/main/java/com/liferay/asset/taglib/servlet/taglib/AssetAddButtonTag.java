/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.servlet.taglib;

import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class AssetAddButtonTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		request.setAttribute(
			AssetWebKeys.ASSET_HELPER, ServletContextUtil.getAssetHelper());

		return super.doStartTag();
	}

	public long[] getAllAssetCategoryIds() {
		return _allAssetCategoryIds;
	}

	public String[] getAllAssetTagNames() {
		return _allAssetTagNames;
	}

	public long[] getClassNameIds() {
		return _classNameIds;
	}

	public long[] getClassTypeIds() {
		return _classTypeIds;
	}

	public long[] getGroupIds() {
		return _groupIds;
	}

	public String getRedirect() {
		return _redirect;
	}

	public boolean isAddDisplayPageParameter() {
		return _addDisplayPageParameter;
	}

	public boolean isUseDialog() {
		return _useDialog;
	}

	public void setAddDisplayPageParameter(boolean addDisplayPageParameter) {
		_addDisplayPageParameter = addDisplayPageParameter;
	}

	public void setAllAssetCategoryIds(long[] allAssetCategoryIds) {
		_allAssetCategoryIds = allAssetCategoryIds;
	}

	public void setAllAssetTagNames(String[] allAssetTagNames) {
		_allAssetTagNames = allAssetTagNames;
	}

	public void setClassNameIds(long[] classNameIds) {
		_classNameIds = classNameIds;
	}

	public void setClassTypeIds(long[] classTypeIds) {
		_classTypeIds = classTypeIds;
	}

	public void setGroupIds(long[] groupIds) {
		_groupIds = groupIds;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	public void setUseDialog(boolean useDialog) {
		_useDialog = useDialog;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_addDisplayPageParameter = false;
		_allAssetCategoryIds = null;
		_allAssetTagNames = null;
		_classNameIds = null;
		_classTypeIds = null;
		_groupIds = null;
		_redirect = null;
		_useDialog = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:addDisplayPageParameter",
			_addDisplayPageParameter);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:allAssetCategoryIds",
			_allAssetCategoryIds);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:allAssetTagNames",
			_allAssetTagNames);

		long[] classNameIds = _classNameIds;

		if (classNameIds == null) {
			classNameIds = AssetRendererFactoryRegistryUtil.getClassNameIds(
				themeDisplay.getCompanyId());
		}

		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:classNameIds", classNameIds);

		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:classTypeIds", _classTypeIds);

		long[] groupIds = _groupIds;

		if (groupIds == null) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:groupIds", groupIds);

		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:redirect", _redirect);
		httpServletRequest.setAttribute(
			"liferay-asset:asset-add-button:useDialog", _useDialog);
	}

	private static final String _PAGE = "/asset_add_button/page.jsp";

	private boolean _addDisplayPageParameter;
	private long[] _allAssetCategoryIds;
	private String[] _allAssetTagNames;
	private long[] _classNameIds;
	private long[] _classTypeIds;
	private long[] _groupIds;
	private String _redirect;
	private boolean _useDialog = true;

}