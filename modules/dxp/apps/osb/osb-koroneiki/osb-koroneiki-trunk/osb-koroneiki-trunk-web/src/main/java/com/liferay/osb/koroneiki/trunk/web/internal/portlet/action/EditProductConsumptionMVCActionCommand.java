/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountException;
import com.liferay.osb.koroneiki.trunk.constants.TrunkPortletKeys;
import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TrunkPortletKeys.PRODUCTS_ADMIN,
		"mvc.command.name=/products_admin/edit_product_consumption"
	},
	service = MVCActionCommand.class
)
public class EditProductConsumptionMVCActionCommand
	extends BaseMVCActionCommand {

	protected void addProductConsumption(ActionRequest actionRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountId = ParamUtil.getLong(actionRequest, "accountId");
		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		long productPurchaseId = ParamUtil.getLong(
			actionRequest, "productPurchaseId");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		Date startDate = _portal.getDate(
			startDateMonth, startDateDay, startDateYear,
			themeDisplay.getTimeZone(), null);

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		Date endDate = _portal.getDate(
			endDateMonth, endDateDay, endDateYear, themeDisplay.getTimeZone(),
			null);

		List<ProductField> productFields = new ArrayList<>();

		int[] productFieldIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "productFieldIndexes"), 0);

		for (int productFieldIndex : productFieldIndexes) {
			String productFieldName = ParamUtil.getString(
				actionRequest, "productFieldName_" + productFieldIndex);
			String productFieldValue = ParamUtil.getString(
				actionRequest, "productFieldValue_" + productFieldIndex);

			if (Validator.isNull(productFieldName) ||
				Validator.isNull(productFieldValue)) {

				continue;
			}

			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName(productFieldName);
			productField.setValue(productFieldValue);

			productFields.add(productField);
		}

		_productConsumptionService.addProductConsumption(
			accountId, productEntryId, productPurchaseId, startDate, endDate,
			productFields);
	}

	protected void deleteProductConsumption(ActionRequest actionRequest)
		throws PortalException {

		long productConsumptionId = ParamUtil.getLong(
			actionRequest, "productConsumptionId");

		_productConsumptionService.deleteProductConsumption(
			productConsumptionId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteProductConsumption(actionRequest);
			}
			else {
				addProductConsumption(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchAccountException ||
				exception instanceof NoSuchProductEntryException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/products_admin/edit_product_consumption");
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductConsumptionMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}