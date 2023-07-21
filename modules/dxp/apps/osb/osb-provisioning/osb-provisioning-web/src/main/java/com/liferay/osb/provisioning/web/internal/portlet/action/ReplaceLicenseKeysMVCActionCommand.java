/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/replace_license_keys",
		"mvc.command.name=/licenses/replace_license_key"
	},
	service = MVCActionCommand.class
)
public class ReplaceLicenseKeysMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long[] licenseKeyIds = ParamUtil.getLongValues(
				actionRequest, "licenseKeyIds");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date startDate = ParamUtil.getDate(
				actionRequest, "startDate", dateFormat);
			Date expirationDate = ParamUtil.getDate(
				actionRequest, "expirationDate", dateFormat);

			if (ArrayUtil.isNotEmpty(licenseKeyIds)) {
				for (long licenseKeyId : licenseKeyIds) {
					_licenseKeyService.replaceLicenseKey(
						licenseKeyId, startDate, expirationDate);
				}

				sendRedirect(
					actionRequest, actionResponse,
					getRedirect(actionRequest, actionResponse, 0));
			}
			else {
				long licenseKeyId = ParamUtil.getLong(
					actionRequest, "licenseKeyId");

				LicenseKey licenseKey = _licenseKeyService.replaceLicenseKey(
					licenseKeyId, startDate, expirationDate);

				sendRedirect(
					actionRequest, actionResponse,
					getRedirect(
						actionRequest, actionResponse,
						licenseKey.getLicenseKeyId()));
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			long licenseKeyId)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		if (licenseKeyId > 0) {
			String redirect = ParamUtil.getString(actionRequest, "redirect");

			portletURL.setParameter(
				"mvcRenderCommandName", "/licenses/edit_license_key");
			portletURL.setParameter("redirect", redirect);
			portletURL.setParameter(
				"licenseKeyId", StringUtil.valueOf(licenseKeyId));
		}
		else {
			String productKey = ParamUtil.getString(
				actionRequest, "productKey");

			if (Validator.isNull(productKey)) {
				portletURL.setParameter(
					"mvcRenderCommandName", "/accounts/view_account");
			}
			else {
				portletURL.setParameter(
					"mvcRenderCommandName", "/accounts/view_subscription");
				portletURL.setParameter("productKey", productKey);
			}

			portletURL.setParameter("tabs1", "licenses");

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			portletURL.setParameter("accountKey", accountKey);
		}

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReplaceLicenseKeysMVCActionCommand.class);

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private Portal _portal;

}