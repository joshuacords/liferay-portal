/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.web.internal.permission.ProductPermissionChecker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ProductDisplay {

	public ProductDisplay(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Product product) {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_product = product;

		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
	}

	public String getDeleteProductURL() {
		PortletURL deleteProductURL = _liferayPortletResponse.createActionURL();

		deleteProductURL.setParameter(
			ActionRequest.ACTION_NAME, "/products/edit_product");
		deleteProductURL.setParameter(Constants.CMD, Constants.DELETE);

		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/products/view");

		deleteProductURL.setParameter("redirect", portletURL.toString());

		deleteProductURL.setParameter("productKey", _product.getKey());

		return deleteProductURL.toString();
	}

	public String getKey() {
		return _product.getKey();
	}

	public String getName() {
		return _product.getName();
	}

	public String getType() {
		Map<String, String> properties = _product.getProperties();

		if (properties != null) {
			String type = properties.get("type");

			if (Validator.isNotNull(type)) {
				return LanguageUtil.get(_httpServletRequest, type);
			}
		}

		return StringPool.DASH;
	}

	public boolean hasEditPermission() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return ProductPermissionChecker.contains(
			themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_PRODUCTS);
	}

	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private final Product _product;

}