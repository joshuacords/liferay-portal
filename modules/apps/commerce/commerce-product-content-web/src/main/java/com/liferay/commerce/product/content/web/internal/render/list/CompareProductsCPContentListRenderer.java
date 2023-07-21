/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.content.web.internal.render.list;

import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRenderer;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.content.list.renderer.key=" + CPPortletKeys.CP_COMPARE_CONTENT_WEB,
		"commerce.product.content.list.renderer.order=" + Integer.MIN_VALUE,
		"commerce.product.content.list.renderer.portlet.name=" + CPPortletKeys.CP_COMPARE_CONTENT_WEB
	},
	service = CPContentListRenderer.class
)
public class CompareProductsCPContentListRenderer
	implements CPContentListRenderer {

	@Override
	public String getKey() {
		return CPPortletKeys.CP_COMPARE_CONTENT_WEB;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "default");
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		CPDataSourceResult cpDataSourceResult =
			(CPDataSourceResult)httpServletRequest.getAttribute(
				CPWebKeys.CP_DATA_SOURCE_RESULT);

		List<String> skus = new ArrayList<>();

		for (CPCatalogEntry cpCatalogEntry :
				cpDataSourceResult.getCPCatalogEntries()) {

			List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

			if (cpSkus.size() != 1) {
				continue;
			}

			CPSku cpSku = cpSkus.get(0);

			skus.add(cpSku.getSku());
		}

		long commerceChannelGroupId =
			_commerceChannelLocalService.getCommerceChannelGroupIdBySiteGroupId(
				_portal.getScopeGroupId(httpServletRequest));

		Map<String, Integer> stockQuantities =
			_commerceInventoryEngine.getStockQuantities(
				_portal.getCompanyId(httpServletRequest),
				commerceChannelGroupId, skus);

		httpServletRequest.setAttribute("stockQuantities", stockQuantities);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/compare_products/render/view.jsp");
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceInventoryEngine _commerceInventoryEngine;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

}