/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.RequiredProductException;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCTS,
		"mvc.command.name=/products/edit_product"
	},
	service = MVCActionCommand.class
)
public class EditProductMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteProduct(ActionRequest actionRequest, User user)
		throws Exception {

		String productKey = ParamUtil.getString(actionRequest, "productKey");

		int count =
			_productBundleProductsLocalService.getProductBundleProductsCount(
				productKey);

		if (count == 0) {
			_productWebService.deleteProduct(
				user.getFullName(), user.getUuid(), productKey);
		}
		else {
			throw new RequiredProductException();
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteProduct(actionRequest, user);
			}
			else {
				updateProduct(actionRequest, user);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof Problem.ProblemException ||
				exception instanceof RequiredProductException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				if (cmd.equals(Constants.DELETE)) {
					sendRedirect(actionRequest, actionResponse);
				}
				else {
					actionResponse.setRenderParameter(
						"mvcRenderCommandName", "/products/edit_product");
				}
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateDossieraMapping(
			User user, String productKey, ExternalLink externalLink)
		throws Exception {

		Product oldProduct = _productWebService.getProduct(productKey);

		ExternalLink[] externalLinks = oldProduct.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink curExternalLink : externalLinks) {
				String domain = curExternalLink.getDomain();
				String entityName = curExternalLink.getEntityName();

				if (domain.equals(ExternalLinkDomain.DOSSIERA) &&
					entityName.equals(
						ExternalLinkEntityName.DOSSIERA_PRODUCT)) {

					if (externalLink == null) {
						_externalLinkWebService.deleteExternalLink(
							user.getFullName(), user.getUuid(),
							curExternalLink.getKey());
					}
					else {
						_externalLinkWebService.updateExternalLink(
							user.getFullName(), user.getUuid(),
							curExternalLink.getKey(), externalLink);
					}

					return;
				}
			}
		}

		if (externalLink != null) {
			_externalLinkWebService.addProductExternalLink(
				user.getFullName(), user.getUuid(), productKey, externalLink);
		}
	}

	protected void updateProduct(ActionRequest actionRequest, User user)
		throws Exception {

		String productKey = ParamUtil.getString(actionRequest, "productKey");

		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");
		String dossieraIdMapping = ParamUtil.getString(
			actionRequest, "dossieraIdMapping");

		Product product = new Product();

		product.setName(name);

		Map<String, String> properties = new HashMap<>();

		if (Validator.isNotNull(type)) {
			properties.put("type", type);
		}

		ExternalLink externalLink = null;

		if (Validator.isNotNull(dossieraIdMapping)) {
			externalLink = new ExternalLink();

			externalLink.setDomain(ExternalLinkDomain.DOSSIERA);
			externalLink.setEntityId(dossieraIdMapping);
			externalLink.setEntityName(ExternalLinkEntityName.DOSSIERA_PRODUCT);

			product.setExternalLinks(new ExternalLink[] {externalLink});
		}

		product.setProperties(properties);

		if (Validator.isNull(productKey)) {
			product = _productWebService.addProduct(
				user.getFullName(), user.getUuid(), product);

			productKey = product.getKey();
		}
		else {
			updateDossieraMapping(user, productKey, externalLink);

			_productWebService.updateProduct(
				user.getFullName(), user.getUuid(), productKey, product);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductMVCActionCommand.class);

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

	@Reference
	private ProductWebService _productWebService;

}