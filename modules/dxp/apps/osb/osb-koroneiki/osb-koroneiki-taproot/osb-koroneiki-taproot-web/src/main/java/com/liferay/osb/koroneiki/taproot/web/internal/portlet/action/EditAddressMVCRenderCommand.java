/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/edit_address"
	},
	service = MVCRenderCommand.class
)
public class EditAddressMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long accountId = ParamUtil.getLong(renderRequest, "accountId");
			long addressId = ParamUtil.getLong(renderRequest, "addressId");

			if (accountId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.ACCOUNT,
					_accountLocalService.getAccount(accountId));
			}

			if (addressId > 0) {
				renderRequest.setAttribute(
					WebKeys.ADDRESS,
					_addressLocalService.getAddress(addressId));
			}

			return "/accounts_admin/edit_address.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			throw new PortletException(exception);
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AddressLocalService _addressLocalService;

}