/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/extend_license_keys",
		"mvc.command.name=/licenses/extend_license_key"
	},
	service = MVCActionCommand.class
)
public class ExtendLicenseKeysMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String licenseKeys = ParamUtil.getString(
				actionRequest, "licenseKeys");

			if (Validator.isNotNull(licenseKeys)) {
				JSONArray licenseKeysJSONArray =
					JSONFactoryUtil.createJSONArray(licenseKeys);

				extendLicenseKeys(
					actionRequest, actionResponse, licenseKeysJSONArray);
			}
			else {
				extendLicenseKey(actionRequest, actionResponse);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void extendLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");

		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");

		if (Validator.isNull(productPurchaseKey)) {
			LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
				licenseKeyId);

			productPurchaseKey = licenseKey.getProductPurchaseKey();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = ParamUtil.getDate(
			actionRequest, "startDate", dateFormat);
		Date expirationDate = ParamUtil.getDate(
			actionRequest, "expirationDate", dateFormat);

		LicenseKey licenseKey = _licenseKeyService.extendLicenseKey(
			licenseKeyId, productPurchaseKey, startDate, expirationDate);

		sendRedirect(
			actionRequest, actionResponse,
			getRedirect(
				actionRequest, actionResponse, licenseKey.getLicenseKeyId()));
	}

	protected void extendLicenseKeys(
			ActionRequest actionRequest, ActionResponse actionResponse,
			JSONArray jsonArray)
		throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			long licenseKeyId = jsonObject.getLong("licenseKeyId");

			String productPurchaseKey = jsonObject.getString(
				"productPurchaseKey");

			if (Validator.isNull(productPurchaseKey)) {
				LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
					licenseKeyId);

				productPurchaseKey = licenseKey.getProductPurchaseKey();
			}

			Date startDate = dateFormat.parse(
				jsonObject.getString("startDate"));
			Date expirationDate = dateFormat.parse(
				jsonObject.getString("expirationDate"));

			_licenseKeyService.extendLicenseKey(
				licenseKeyId, productPurchaseKey, startDate, expirationDate);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	protected String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long licenseKeyId)
		throws Exception {

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		String portletName = portletConfig.getPortletName();

		if (portletName.equals(ProvisioningPortletKeys.ACCOUNTS)) {
			return redirect;
		}

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/edit_license_key");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter("licenseKeyId", String.valueOf(licenseKeyId));

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExtendLicenseKeysMVCActionCommand.class);

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private Portal _portal;

}