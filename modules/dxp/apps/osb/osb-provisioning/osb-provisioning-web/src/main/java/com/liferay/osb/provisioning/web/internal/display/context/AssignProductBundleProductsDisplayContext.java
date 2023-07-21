/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.web.internal.dao.search.AssignProductsRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AssignProductBundleProductsDisplayContext {

	public AssignProductBundleProductsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		ProductWebService productWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_productWebService = productWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_productKeys = ParamUtil.getStringValues(renderRequest, "productKeys");
	}

	public String getClearResultsURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/product_bundles/assign_products");

		return portletURL.toString();
	}

	public String getSearchActionURL() throws Exception {
		return _currentURLObj.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-products-were-found");

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		List<Product> products = _productWebService.search(
			keywords, null, searchContainer.getCur(),
			searchContainer.getDelta(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				products,
				product -> new ProductDisplay(
					_renderRequest, _renderResponse, product)));

		searchContainer.setRowChecker(
			new AssignProductsRowChecker(
				_renderResponse, new long[0], Arrays.asList(_productKeys)));

		int count = (int)_productWebService.searchCount(keywords, null);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final String[] _productKeys;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}