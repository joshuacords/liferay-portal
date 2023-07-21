/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.checkout.web.internal.portlet.action;

import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStepServicesTracker;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + CommercePortletKeys.COMMERCE_CHECKOUT,
		"mvc.command.name=saveStep"
	},
	service = MVCActionCommand.class
)
public class SaveStepMVCActionCommand extends BaseMVCActionCommand {

	public String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String checkoutStepName)
		throws Exception {

		String redirect = GetterUtil.getString(
			actionRequest.getAttribute(WebKeys.REDIRECT));

		if (Validator.isNotNull(redirect)) {
			return redirect;
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return getPortletURL(
				actionRequest, actionResponse, checkoutStepName);
		}

		CommerceCheckoutStep commerceCheckoutStep =
			_commerceCheckoutStepServicesTracker.getNextCommerceCheckoutStep(
				checkoutStepName, _portal.getHttpServletRequest(actionRequest),
				_portal.getHttpServletResponse(actionResponse));

		if (commerceCheckoutStep == null) {
			return ParamUtil.getString(actionRequest, "redirect");
		}

		return getPortletURL(
			actionRequest, actionResponse, commerceCheckoutStep.getName());
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String checkoutStepName = ParamUtil.getString(
			actionRequest, "checkoutStepName");

		CommerceCheckoutStep commerceCheckoutStep =
			_commerceCheckoutStepServicesTracker.getCommerceCheckoutStep(
				checkoutStepName);

		commerceCheckoutStep.processAction(actionRequest, actionResponse);

		hideDefaultSuccessMessage(actionRequest);

		String redirect = getRedirect(
			actionRequest, actionResponse, checkoutStepName);

		sendRedirect(actionRequest, actionResponse, redirect);
	}

	protected String getPortletURL(
		ActionRequest actionRequest, ActionResponse actionResponse,
		String checkoutStepName) {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String commerceOrderUuid = ParamUtil.getString(
			actionRequest, "commerceOrderUuid");

		portletURL.setParameter("commerceOrderUuid", commerceOrderUuid);

		portletURL.setParameter("checkoutStepName", checkoutStepName);

		return portletURL.toString();
	}

	@Reference
	private CommerceCheckoutStepServicesTracker
		_commerceCheckoutStepServicesTracker;

	@Reference
	private Portal _portal;

}