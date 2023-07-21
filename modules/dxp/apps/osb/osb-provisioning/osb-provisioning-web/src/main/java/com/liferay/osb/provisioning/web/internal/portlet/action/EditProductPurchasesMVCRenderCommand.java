/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_product_purchases"
	},
	service = MVCRenderCommand.class
)
public class EditProductPurchasesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String accountKey = ParamUtil.getString(
				renderRequest, "accountKey");

			long[] productBundleIds = ParamUtil.getLongValues(
				renderRequest, "productBundleIds");
			String[] productKeys = ParamUtil.getStringValues(
				renderRequest, "productKeys");
			String[] productPurchaseKeys = ParamUtil.getStringValues(
				renderRequest, "productPurchaseKeys");
			String[] productPurchaseViewKeys = ParamUtil.getStringValues(
				renderRequest, "productPurchaseViewKeys");

			if ((productKeys.length > 0) || (productBundleIds.length > 0)) {
				List<Product> products = new ArrayList<>();

				for (long productBundleId : productBundleIds) {
					List<Product> productBundleProducts =
						_productBundleProductsLocalService.
							getProductBundleAssignedProducts(productBundleId);

					products.addAll(productBundleProducts);
				}

				for (String productKey : productKeys) {
					Product product = _productWebService.fetchProduct(
						productKey);

					if (product != null) {
						products.add(product);
					}
				}

				renderRequest.setAttribute(
					ProvisioningWebKeys.ACCOUNT,
					_accountWebService.getAccount(accountKey));
				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCTS, new ArrayList<>(products));

				return "/accounts/edit_product_purchases_edit_details.jsp";
			}

			if (productPurchaseKeys.length > 0) {
				List<ProductPurchase> productPurchases = new ArrayList<>();

				for (String productPurchaseKey : productPurchaseKeys) {
					ProductPurchase productPurchase =
						_productPurchaseWebService.getProductPurchase(
							productPurchaseKey);

					productPurchases.add(productPurchase);
				}

				renderRequest.setAttribute(
					ProvisioningWebKeys.ACCOUNT,
					_accountWebService.getAccount(accountKey));
				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCT_PURCHASES, productPurchases);

				return "/accounts/edit_product_purchases_edit_details.jsp";
			}

			List<ProductPurchaseView> productPurchaseViews = new ArrayList<>();

			for (String productKey : productPurchaseViewKeys) {
				ProductPurchaseView productPurchaseView =
					_productPurchaseViewWebService.getProductPurchaseView(
						accountKey, productKey);

				productPurchaseViews.add(productPurchaseView);
			}

			renderRequest.setAttribute(
				ProvisioningWebKeys.ACCOUNT,
				_accountWebService.getAccount(accountKey));
			renderRequest.setAttribute(
				ProvisioningWebKeys.PRODUCT_PURCHASE_VIEWS,
				productPurchaseViews);

			return "/accounts/edit_product_purchases_select_terms.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/common/error.jsp";
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

}