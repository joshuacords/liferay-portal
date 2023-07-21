/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/", "mvc.command.name=/accounts/view"
	},
	service = MVCRenderCommand.class
)
public class AccountsViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			String[] keywords = StringUtil.split(
				ParamUtil.getString(renderRequest, "accountSearchKeywords"),
				StringPool.SPACE);

			if ((keywords.length == 1) && Validator.isNotNull(keywords[0]) &&
				StringUtil.isUpperCase(keywords[0])) {

				FilterQuery filterQuery = new FilterQuery();

				filterQuery.addEquals(true, "code", keywords[0]);

				List<Account> accounts = _accountWebService.search(
					StringPool.BLANK, filterQuery, 0, 1, null);

				if (!accounts.isEmpty()) {
					renderRequest.setAttribute(
						ProvisioningWebKeys.ACCOUNT, accounts.get(0));

					return "/accounts/view_account.jsp";
				}
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception, exception);
			}
		}

		return "/accounts/view.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountsViewMVCRenderCommand.class);

	@Reference
	private AccountWebService _accountWebService;

}