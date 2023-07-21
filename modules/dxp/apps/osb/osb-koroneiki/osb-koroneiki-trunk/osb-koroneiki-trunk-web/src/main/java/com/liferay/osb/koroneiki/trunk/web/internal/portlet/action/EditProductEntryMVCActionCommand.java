/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.web.internal.portlet.action;

import com.liferay.osb.koroneiki.trunk.constants.TrunkPortletKeys;
import com.liferay.osb.koroneiki.trunk.exception.ProductEntryNameException;
import com.liferay.osb.koroneiki.trunk.exception.RequiredProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TrunkPortletKeys.PRODUCTS_ADMIN,
		"mvc.command.name=/products_admin/edit_product_entry"
	},
	service = MVCActionCommand.class
)
public class EditProductEntryMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteProductEntry(ActionRequest actionRequest)
		throws PortalException {

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		_productEntryService.deleteProductEntry(productEntryId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteProductEntry(actionRequest);
			}
			else {
				updateProductEntry(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof ProductEntryNameException) {
				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/products_admin/edit_product_entry");
			}
			else if (exception instanceof RequiredProductEntryException) {
				SessionErrors.add(actionRequest, exception.getClass());

				sendRedirect(
					actionRequest, actionResponse, getRedirect(actionResponse));
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected String getRedirect(ActionResponse actionResponse)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/view");

		return portletURL.toString();
	}

	protected void updateProductEntry(ActionRequest actionRequest)
		throws PortalException {

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		String name = ParamUtil.getString(actionRequest, "name");

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

		if (productEntryId <= 0) {
			_productEntryService.addProductEntry(name, productFields);
		}
		else {
			_productEntryService.updateProductEntry(
				productEntryId, name, productFields);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductEntryMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductEntryService _productEntryService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

}