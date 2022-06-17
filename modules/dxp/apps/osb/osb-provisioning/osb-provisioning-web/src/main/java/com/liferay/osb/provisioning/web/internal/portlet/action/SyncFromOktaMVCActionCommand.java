/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		"mvc.command.name=/team/sync_from_okta"
	},
	service = MVCActionCommand.class
)
public class SyncFromOktaMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			String teamKey = ParamUtil.getString(actionRequest, "teamKey");

			Team team = _teamWebService.getTeam(teamKey);

			String oktaGroupId = StringPool.BLANK;

			if (ArrayUtil.isNotEmpty(team.getExternalLinks())) {
				for (ExternalLink externalLink : team.getExternalLinks()) {
					String domain = externalLink.getDomain();
					String entityName = externalLink.getEntityName();

					if (domain.equals(ExternalLinkDomain.OKTA) &&
						entityName.equals(ExternalLinkEntityName.OKTA_GROUP)) {

						oktaGroupId = externalLink.getEntityId();
					}
				}
			}

			if (Validator.isNull(oktaGroupId)) {
				return;
			}

			Set<String> oktaContactUuids = _getContactUuids(
				_contactIdentityProvider.getGroupContacts(oktaGroupId));
			Set<String> contactUuids = _getContactUuids(
				_contactWebService.getTeamContacts(teamKey, 1, 1000));

			for (String oktaContactUuid : oktaContactUuids) {
				if (!contactUuids.contains(oktaContactUuid)) {
					_teamWebService.assignContactsByUuid(
						user.getFullName(), user.getUuid(), teamKey,
						new String[] {oktaContactUuid});
				}
			}

			for (String contactUuid : contactUuids) {
				if (!oktaContactUuids.contains(contactUuid)) {
					_teamWebService.unassignContactsByUuid(
						user.getFullName(), user.getUuid(), teamKey,
						new String[] {contactUuid});
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass());
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private Set<String> _getContactUuids(List<Contact> contacts) {
		Set<String> contactUuids = new HashSet<>();

		for (Contact contact : contacts) {
			if (Validator.isNull(contact.getUuid())) {
				continue;
			}

			contactUuids.add(contact.getUuid());
		}

		return contactUuids;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SyncFromOktaMVCActionCommand.class);

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private TeamWebService _teamWebService;

}