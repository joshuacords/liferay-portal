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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewTeamDisplayContext extends ViewAccountDisplayContext {

	public ViewTeamDisplayContext() {
	}

	@Override
	public void addPortletBreadcrumbEntries() throws Exception {
		super.addPortletBreadcrumbEntries();

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter("teamKey", team.getKey());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, team.getName(), portletURL.toString());
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		team = (Team)renderRequest.getAttribute(ProvisioningWebKeys.TEAM);

		teamDisplay = new TeamDisplay(
			renderRequest, renderResponse, team, 0, 0);

		setWindowTitle();
	}

	public SearchContainer getContactsSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		String emptyResultsMessage = "no-contacts-were-found";

		if (!teamDisplay.isSystem()) {
			emptyResultsMessage = "no-team-members-added-yet";
		}

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			emptyResultsMessage);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(true, "teamKeys", team.getKey());

		List<Contact> contacts = contactWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountCustomerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, contact, contactRoles);
				}));

		int count = (int)contactWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public SearchContainer getFLSAssignedAccountsSearchContainer()
		throws Exception {

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-accounts-were-found");

		TeamRole teamRole = teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "assignedTeamKeyTeamRoleKeys",
			team.getKey() + "_" + teamRole.getKey());

		List<Account> accounts = accountWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(),
			StringPool.BLANK);

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					renderRequest, renderResponse, accountReader, account)));

		int count = (int)accountWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public SearchContainer getPartnerAssignedAccountsSearchContainer()
		throws Exception {

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-accounts-were-found");

		TeamRole teamRole = teamRoleWebService.getTeamRole(
			TeamRole.Type.ACCOUNT.toString(), TeamRoleConstants.NAME_PARTNER);

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "assignedTeamKeyTeamRoleKeys",
			team.getKey() + "_" + teamRole.getKey());

		List<Account> accounts = accountWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(),
			StringPool.BLANK);

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					renderRequest, renderResponse, accountReader, account)));

		int count = (int)accountWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter("teamKey", team.getKey());

		return portletURL;
	}

	public Team getTeam() {
		return team;
	}

	public TeamDisplay getTeamDisplay() {
		return teamDisplay;
	}

	public boolean hasOktaGroup() {
		if (ArrayUtil.isNotEmpty(team.getExternalLinks())) {
			for (ExternalLink externalLink : team.getExternalLinks()) {
				String domain = externalLink.getDomain();
				String entityName = externalLink.getEntityName();

				if (domain.equals(ExternalLinkDomain.OKTA) &&
					entityName.equals(ExternalLinkEntityName.OKTA_GROUP)) {

					return true;
				}
			}
		}

		return false;
	}

	@Override
	protected void setWindowTitle() {
		String tabs1 = ParamUtil.getString(
			renderRequest, "tabs1", "team-members");

		renderResponse.setTitle(
			StringBundler.concat(
				account.getCode(), StringPool.SPACE,
				LanguageUtil.get(httpServletRequest, tabs1)));
	}

	protected Team team;
	protected TeamDisplay teamDisplay;

}