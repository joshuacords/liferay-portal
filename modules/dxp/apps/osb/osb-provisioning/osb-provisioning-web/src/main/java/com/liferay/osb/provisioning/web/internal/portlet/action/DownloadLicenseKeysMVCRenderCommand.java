/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
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
		"mvc.command.name=/accounts/download_license_keys"
	},
	service = MVCRenderCommand.class
)
public class DownloadLicenseKeysMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		List<LicenseKey> licenseKeys = new ArrayList<>();

		try {
			String accountKey = ParamUtil.getString(
				renderRequest, "accountKey");

			long[] licenseKeyIds = ParamUtil.getLongValues(
				renderRequest, "licenseKeyIds");

			for (long licenseKeyId : licenseKeyIds) {
				licenseKeys.add(_licenseKeyService.getLicenseKey(licenseKeyId));
			}

			renderRequest.setAttribute(
				ProvisioningWebKeys.ACCOUNT,
				_accountWebService.getAccount(accountKey));
			renderRequest.setAttribute(
				ProvisioningWebKeys.LICENSE_KEYS, licenseKeys);

			return "/accounts/download_license_keys.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/common/error.jsp";
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LicenseKeyService _licenseKeyService;

}