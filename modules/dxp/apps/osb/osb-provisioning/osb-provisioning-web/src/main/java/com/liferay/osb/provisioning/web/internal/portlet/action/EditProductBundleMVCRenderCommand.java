/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCT_BUNDLES,
		"mvc.command.name=/product_bundles/edit_product_bundle"
	},
	service = MVCRenderCommand.class
)
public class EditProductBundleMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long productBundleId = ParamUtil.getLong(
				renderRequest, "productBundleId");

			if (productBundleId > 0) {
				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCT_BUNDLE,
					_productBundleLocalService.getProductBundle(
						productBundleId));

				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS,
					_productBundleProductsLocalService.
						getProductBundleAssignedProducts(productBundleId));
			}

			return "/product_bundles/edit_product_bundle.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			_log.error(exception, exception);

			return "/common/error.jsp";
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductBundleMVCRenderCommand.class);

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

}