/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ProductPurchaseQuantityException;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
	service = MVCActionCommand.class
)
public class EditProductPurchasesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateProductPurchases(actionRequest);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof Problem.ProblemException ||
				exception instanceof ProductPurchaseQuantityException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/accounts/edit_product_purchases");
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateProductPurchases(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			ParamUtil.getString(actionRequest, "data"));

		ProductPurchase[] productPurchases = ProductPurchaseSerDes.toDTOs(
			jsonArray.toString());

		for (ProductPurchase productPurchase : productPurchases) {
			if ((productPurchase.getQuantity() == null) ||
				(productPurchase.getQuantity() <= 0)) {

				throw new ProductPurchaseQuantityException();
			}

			String productPurchaseKey = productPurchase.getKey();

			Map<String, String> properties = new HashMap<>();

			if (Validator.isNotNull(productPurchaseKey)) {
				ProductPurchase curProductPurchase =
					_productPurchaseWebService.getProductPurchase(
						productPurchaseKey);

				Map<String, String> curProperties =
					curProductPurchase.getProperties();

				if (curProperties != null) {
					for (Map.Entry<String, String> entry :
							curProperties.entrySet()) {

						properties.put(entry.getKey(), entry.getValue());
					}
				}
			}

			Map<String, String> newProperties = productPurchase.getProperties();

			if ((newProperties != null) &&
				(newProperties.get("sizing") != null)) {

				properties.put(
					"sizing", String.valueOf(newProperties.get("sizing")));
			}

			productPurchase.setProperties(properties);

			if (Validator.isNull(productPurchaseKey)) {
				if (productPurchase.getOriginalEndDate() != null) {
					Calendar calendar = CalendarFactoryUtil.getCalendar();

					calendar.setTime(productPurchase.getOriginalEndDate());

					calendar.add(Calendar.DATE, 30);

					productPurchase.setEndDate(calendar.getTime());
				}

				_productPurchaseWebService.addProductPurchase(
					user.getFullName(), user.getUuid(), accountKey,
					productPurchase);
			}
			else {
				_productPurchaseWebService.updateProductPurchase(
					user.getFullName(), user.getUuid(), productPurchaseKey,
					productPurchase);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductPurchasesMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

}