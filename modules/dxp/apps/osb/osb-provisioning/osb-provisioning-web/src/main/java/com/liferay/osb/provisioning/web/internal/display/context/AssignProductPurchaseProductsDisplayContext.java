/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.web.internal.dao.search.AssignProductsRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
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
public class AssignProductPurchaseProductsDisplayContext {

	public AssignProductPurchaseProductsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		ProductBundleLocalService productBundleLocalService,
		ProductWebService productWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_productBundleLocalService = productBundleLocalService;
		_productWebService = productWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_accountKey = ParamUtil.getString(renderRequest, "accountKey");
		_productBundleIds = ParamUtil.getLongValues(
			renderRequest, "productBundleIds");
		_productKeys = ParamUtil.getStringValues(renderRequest, "productKeys");
	}

	public String getClearResultsURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_products");
		portletURL.setParameter("accountKey", _accountKey);

		return portletURL.toString();
	}

	public String getSearchActionURL() throws Exception {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_products");
		portletURL.setParameter("accountKey", _accountKey);
		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-products-were-found");

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		int count = (int)_productWebService.searchCount(keywords, null);

		List<Object> results = new ArrayList<>();

		Hits hits = _productBundleLocalService.search(
			_themeDisplay.getCompanyId(), keywords, searchContainer.getStart(),
			searchContainer.getEnd(), new Sort(Field.NAME, false));

		int previousPageCount =
			(searchContainer.getCur() - 1) * searchContainer.getDelta();

		if ((hits.getLength() > 0) && (hits.getLength() > previousPageCount)) {
			for (Document document : hits.getDocs()) {
				long productBundleId = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));

				results.add(
					_productBundleLocalService.getProductBundle(
						productBundleId));
			}

			if (results.size() < searchContainer.getDelta()) {
				List<Product> products = _productWebService.search(
					keywords, null, 1,
					searchContainer.getDelta() - results.size(), "name");

				results.addAll(
					TransformUtil.transform(
						products,
						product -> new ProductDisplay(
							_renderRequest, _renderResponse, product)));
			}
		}
		else {
			int start = previousPageCount - hits.getLength();

			int end = start + searchContainer.getDelta();

			if (end > count) {
				end = count;
			}

			if (end > 0) {
				int size = searchContainer.getDelta();

				int startPage = (int)Math.ceil((start + 1) * 1.0 / size);

				int endPage = (int)Math.ceil(end * 1.0 / size);

				while (startPage != endPage) {
					size += 1;

					startPage = (int)Math.ceil((start + 1) * 1.0 / size);

					endPage = (int)Math.ceil(end * 1.0 / size);
				}

				List<Product> products = _productWebService.search(
					keywords, null, endPage, size, "name");

				start = start % size;

				end = start + searchContainer.getDelta();

				if (end > products.size()) {
					end = products.size();
				}

				results.addAll(
					TransformUtil.transform(
						products.subList(start, end),
						product -> new ProductDisplay(
							_renderRequest, _renderResponse, product)));
			}
		}

		count += hits.getLength();

		searchContainer.setResults(results);

		searchContainer.setRowChecker(
			new AssignProductsRowChecker(
				_renderResponse, _productBundleIds,
				Arrays.asList(_productKeys)));

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private final String _accountKey;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final long[] _productBundleIds;
	private final ProductBundleLocalService _productBundleLocalService;
	private final String[] _productKeys;
	private final ProductWebService _productWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}