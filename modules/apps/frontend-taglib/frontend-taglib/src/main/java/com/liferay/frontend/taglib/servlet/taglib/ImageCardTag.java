/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Roberto Díaz
 */
public class ImageCardTag extends IncludeTag {

	public String getCssClass() {
		return _cssClass;
	}

	public Map<String, Object> getData() {
		return _data;
	}

	public String getImageCSSClass() {
		return _imageCSSClass;
	}

	public String getImageUrl() {
		return _imageUrl;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setData(Map<String, Object> data) {
		_data = data;
	}

	public void setImageCSSClass(String imageCSSClass) {
		_imageCSSClass = imageCSSClass;
	}

	public void setImageUrl(String imageUrl) {
		_imageUrl = imageUrl;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = null;
		_data = null;
		_imageCSSClass = null;
		_imageUrl = null;
	}

	@Override
	protected String getPage() {
		return "/card/image_card/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest.setAttribute(
			"liferay-frontend:image-card:cssClass", _cssClass);
		httpServletRequest.setAttribute(
			"liferay-frontend:image-card:data", _data);
		httpServletRequest.setAttribute(
			"liferay-frontend:image-card:imageCSSClass", _imageCSSClass);
		httpServletRequest.setAttribute(
			"liferay-frontend:image-card:imageUrl", _imageUrl);
	}

	private String _cssClass;
	private Map<String, Object> _data;
	private String _imageCSSClass;
	private String _imageUrl;

}