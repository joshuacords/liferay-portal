/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.web.internal.util.ProductPurchaseDisplayComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewSubscriptionDisplayContext extends ViewAccountDisplayContext {

	public ViewSubscriptionDisplayContext() {
	}

	@Override
	public void addPortletBreadcrumbEntries() throws Exception {
		super.addPortletBreadcrumbEntries();

		PortletURL portletURL = getPortletURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, _productPurchaseViewDisplay.getName(),
			portletURL.toString());
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_productPurchaseView = (ProductPurchaseView)renderRequest.getAttribute(
			ProvisioningWebKeys.PRODUCT_PURCHASE_VIEW);

		_productPurchaseViewDisplay = new ProductPurchaseViewDisplay(
			httpServletRequest, account, _productPurchaseView);

		setWindowTitle();
	}

	@Override
	public String getGenerateLicenseURL() {
		PortletURL portletURL = PortletURLFactoryUtil.create(
			httpServletRequest, ProvisioningPortletKeys.LICENSES,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/add_license_key");
		portletURL.setParameter("redirect", currentURLObj.toString());
		portletURL.setParameter(
			"accountKey", _productPurchaseViewDisplay.getAccountKey());
		portletURL.setParameter(
			"productKey", _productPurchaseViewDisplay.getProductKey());

		return portletURL.toString();
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_subscription");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter(
			"accountKey", _productPurchaseViewDisplay.getAccountKey());
		portletURL.setParameter(
			"productKey", _productPurchaseViewDisplay.getProductKey());

		return portletURL;
	}

	public ProductPurchaseViewDisplay getProductPurchaseViewDisplay() {
		return _productPurchaseViewDisplay;
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-purchases-were-found");

		Map<String, Long> productConsumptionsCount = new HashMap<>();

		ProductConsumption[] productConsumptions =
			_productPurchaseView.getProductConsumptions();

		if (productConsumptions != null) {
			for (ProductConsumption productConsumption : productConsumptions) {
				String productPurchaseKey =
					productConsumption.getProductPurchaseKey();

				if (Validator.isNotNull(productPurchaseKey)) {
					long curProductConsumptionsCount =
						productConsumptionsCount.getOrDefault(
							productPurchaseKey, 0L);

					productConsumptionsCount.put(
						productPurchaseKey, curProductConsumptionsCount + 1);
				}
			}
		}

		ProductPurchase[] productPurchases =
			_productPurchaseView.getProductPurchases();

		if (productPurchases != null) {
			List<ProductPurchaseDisplay> productPurchaseDisplays =
				TransformUtil.transformToList(
					productPurchases,
					productPurchase -> new ProductPurchaseDisplay(
						httpServletRequest, productPurchase,
						productConsumptionsCount.getOrDefault(
							productPurchase.getKey(), 0L)));

			Collections.sort(
				productPurchaseDisplays,
				new ProductPurchaseDisplayComparator());

			searchContainer.setResults(productPurchaseDisplays);

			searchContainer.setTotal(productPurchases.length);
		}

		return searchContainer;
	}

	@Override
	protected void setWindowTitle() {
		String tabs1 = ParamUtil.getString(
			renderRequest, "tabs1", "subscription-terms");

		renderResponse.setTitle(
			StringBundler.concat(
				account.getCode(), StringPool.SPACE,
				LanguageUtil.get(httpServletRequest, tabs1)));
	}

	private ProductPurchaseView _productPurchaseView;
	private ProductPurchaseViewDisplay _productPurchaseViewDisplay;

}