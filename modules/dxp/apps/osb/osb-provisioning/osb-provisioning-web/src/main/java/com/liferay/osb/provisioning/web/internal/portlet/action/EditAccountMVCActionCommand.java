/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.AccountCodeException;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.util.DataRegionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_account"
	},
	service = MVCActionCommand.class
)
public class EditAccountMVCActionCommand extends BaseMVCActionCommand {

	protected String addAccount(
			ActionRequest actionRequest, ActionResponse actionResponse,
			User user)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		String tier = ParamUtil.getString(actionRequest, "tier");
		String region = ParamUtil.getString(actionRequest, "region");

		validate(code);

		Account account = new Account();

		account.setName(name);

		if (Validator.isNotNull(code)) {
			account.setCode(code);
		}

		if (Validator.isNotNull(tier)) {
			account.setTier(Account.Tier.create(tier));
		}

		if (Validator.isNotNull(region)) {
			Account.Region accountRegion = Account.Region.create(region);

			account.setRegion(accountRegion);

			PostalAddress[] postalAddresses = account.getPostalAddresses();

			String country = null;

			if (postalAddresses != null) {
				for (PostalAddress postalAddress : postalAddresses) {
					if ((postalAddress.getPrimary() != null) &&
						postalAddress.getPrimary()) {

						country = postalAddress.getAddressCountry();
					}
				}
			}

			account.setDataRegion(
				DataRegionUtil.getDataRegion(accountRegion, country));
		}

		Account newAccount = _accountWebService.addAccount(
			user.getFullName(), user.getUuid(), account);

		return newAccount.getKey();
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		String tabs1 = ParamUtil.getString(actionRequest, "tabs1", "details");

		try {
			if (Validator.isNotNull(accountKey)) {
				updateAccount(actionRequest, actionResponse, accountKey, user);
			}
			else {
				accountKey = addAccount(actionRequest, actionResponse, user);
			}

			sendRedirect(
				actionRequest, actionResponse,
				getRedirect(actionResponse, accountKey, tabs1));
		}
		catch (Exception exception) {
			if (exception instanceof AccountCodeException ||
				exception instanceof Problem.ProblemException) {

				if (_log.isDebugEnabled()) {
					_log.debug(exception, exception);
				}

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);

				if (Validator.isNotNull(accountKey)) {
					sendRedirect(
						actionRequest, actionResponse,
						getRedirect(actionResponse, accountKey, tabs1));
				}
				else {
					actionResponse.setRenderParameter(
						"mvcRenderCommandName", "/accounts/add_account");
				}
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected String getRedirect(
			ActionResponse actionResponse, String accountKey, String tabs1)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("tabs1", tabs1);
		portletURL.setParameter("accountKey", accountKey);

		return portletURL.toString();
	}

	protected void updateAccount(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String accountKey, User user)
		throws Exception {

		boolean updateAccount = ParamUtil.getBoolean(
			actionRequest, "updateAccount");

		if (updateAccount) {
			String name = ParamUtil.getString(actionRequest, "name");
			String code = ParamUtil.getString(actionRequest, "code");
			String tier = ParamUtil.getString(actionRequest, "tier");
			String region = ParamUtil.getString(actionRequest, "region");
			String dataRegion = ParamUtil.getString(
				actionRequest, "dataRegion");
			String liferayVersion = ParamUtil.getString(
				actionRequest, "liferayVersion");
			boolean allowComplimentary = ParamUtil.getBoolean(
				actionRequest, "allowComplimentary");
			boolean allowPermanentLicenses = ParamUtil.getBoolean(
				actionRequest, "allowPermanentLicenses");
			boolean allowSelfProvisioning = ParamUtil.getBoolean(
				actionRequest, "allowSelfProvisioning");

			validate(code);

			Account account = _accountWebService.getAccount(accountKey);

			account.setName(name);

			if (Validator.isNotNull(code)) {
				account.setCode(code);
			}

			if (Validator.isNotNull(tier)) {
				account.setTier(Account.Tier.create(tier));
			}

			if (Validator.isNotNull(region)) {
				account.setRegion(Account.Region.create(region));
			}

			if (Validator.isNotNull(dataRegion)) {
				account.setDataRegion(Account.DataRegion.create(dataRegion));
			}

			Map<String, String> properties = account.getProperties();

			if (properties == null) {
				properties = new HashMap<>();
			}

			if (Validator.isNotNull(liferayVersion)) {
				properties.put("liferayVersion", liferayVersion);
			}
			else {
				properties.remove("liferayVersion");
			}

			if (allowComplimentary) {
				properties.put("allowComplimentary", StringPool.TRUE);
			}
			else {
				properties.remove("allowComplimentary");
			}

			if (!allowPermanentLicenses) {
				properties.put("allowPermanentLicenses", StringPool.FALSE);
			}
			else {
				properties.remove("allowPermanentLicenses");
			}

			if (!allowSelfProvisioning) {
				properties.put("allowSelfProvisioning", StringPool.FALSE);
			}
			else {
				properties.remove("allowSelfProvisioning");
			}

			account.setProperties(properties);

			_accountWebService.updateAccount(
				user.getFullName(), user.getUuid(), accountKey, account);
		}

		boolean updatePartner = ParamUtil.getBoolean(
			actionRequest, "updatePartner");

		if (updatePartner) {
			String partnerTeamKey = ParamUtil.getString(
				actionRequest, "partnerTeamKey");

			updateAssignedTeam(
				user, accountKey, partnerTeamKey,
				TeamRoleConstants.NAME_PARTNER);
		}

		boolean updateFirstLineSupport = ParamUtil.getBoolean(
			actionRequest, "updateFirstLineSupport");

		if (updateFirstLineSupport) {
			String firstLineSupportTeamKey = ParamUtil.getString(
				actionRequest, "firstLineSupportTeamKey");

			updateAssignedTeam(
				user, accountKey, firstLineSupportTeamKey,
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);
		}
	}

	protected void updateAssignedTeam(
			User user, String accountKey, String teamKey, String teamRoleName)
		throws Exception {

		TeamRole teamRole = _teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), teamRoleName);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "accountKeyTeamRoleKeys",
			accountKey + "_" + teamRole.getKey());

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Team team : teams) {
			if (teamKey.equals(team.getKey())) {
				return;
			}

			_accountWebService.unassignTeamRoles(
				user.getFullName(), user.getUuid(), accountKey, team.getKey(),
				new String[] {teamRole.getKey()});
		}

		if (Validator.isNotNull(teamKey)) {
			_accountWebService.assignTeamRoles(
				user.getFullName(), user.getUuid(), accountKey, teamKey,
				new String[] {teamRole.getKey()});
		}
	}

	protected void validate(String code) throws PortalException {
		if (Validator.isNull(code)) {
			throw new AccountCodeException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private Portal _portal;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

}