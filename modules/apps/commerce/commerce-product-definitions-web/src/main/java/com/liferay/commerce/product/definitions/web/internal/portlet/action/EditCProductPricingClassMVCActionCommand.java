/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.portlet.action;

import com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelService;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=editCProductPricingClass"
	},
	service = MVCActionCommand.class
)
public class EditCProductPricingClassMVCActionCommand
	extends BaseMVCActionCommand {

	protected void deleteCommercePricingClassCPDefinitionRel(
			ActionRequest actionRequest)
		throws Exception {

		long[] commercePricingClassCPDefinitionRelIds = null;

		long commercePricingClassCPDefinitionRelId = ParamUtil.getLong(
			actionRequest, "commercePricingClassCPDefinitionRelId");

		if (commercePricingClassCPDefinitionRelId > 0) {
			commercePricingClassCPDefinitionRelIds = new long[] {
				commercePricingClassCPDefinitionRelId
			};
		}
		else {
			commercePricingClassCPDefinitionRelIds = ParamUtil.getLongValues(
				actionRequest, "commercePricingClassCPDefinitionRelIds");
		}

		for (long deleteCommercePricingClassCPDefinitionRelId :
				commercePricingClassCPDefinitionRelIds) {

			_commercePricingClassCPDefinitionRelService.
				deleteCommercePricingClassCPDefinitionRel(
					deleteCommercePricingClassCPDefinitionRelId);
		}
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteCommercePricingClassCPDefinitionRel(actionRequest);
			}
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	@Reference
	private CommercePricingClassCPDefinitionRelService
		_commercePricingClassCPDefinitionRelService;

}