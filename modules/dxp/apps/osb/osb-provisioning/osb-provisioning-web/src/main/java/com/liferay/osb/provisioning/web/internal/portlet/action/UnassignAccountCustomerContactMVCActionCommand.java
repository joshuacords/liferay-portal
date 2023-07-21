/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ContactRequiredException;
import com.liferay.osb.provisioning.exception.RequiredContactRoleException;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.validator.ContactRoleValidator;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.util.ZendeskValidator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/unassign_customer_contact"
	},
	service = MVCActionCommand.class
)
public class UnassignAccountCustomerContactMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");
			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");

			_zendeskValidator.validateCustomerZendeskTickets(
				accountKey, emailAddress);

			Team team = _getDefaultTeam(accountKey);

			_zendeskValidator.validateFLSPartnerZendeskTickets(
				team.getKey(), emailAddress);

			User user = themeDisplay.getUser();

			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, emailAddress, 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_PARTNER_MANAGER) ||
					name.equals(
						ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

					_contactRoleValidator.validateAdminContactRoleUnassignment(
						accountKey, emailAddress);
				}
			}

			_accountWebService.unassignCustomerContact(
				user.getFullName(), user.getUuid(), accountKey, emailAddress);
		}
		catch (Exception exception) {
			if (!(exception instanceof ContactRequiredException) &&
				!(exception instanceof RequiredContactRoleException)) {

				_log.error(exception, exception);
			}

			SessionErrors.add(actionRequest, exception.getClass(), exception);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private Team _getDefaultTeam(String accountKey) throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", accountKey);
		filterQuery.addEquals(true, "system", true);

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

		if (teams.isEmpty()) {
			throw new ContactRequiredException();
		}

		return teams.get(0);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UnassignAccountCustomerContactMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleValidator _contactRoleValidator;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private ZendeskValidator _zendeskValidator;

}