/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.permission.ContactPermissionChecker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewContactDisplayContext {

	public ViewContactDisplayContext() {
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, LanguageUtil.get(httpServletRequest, "users"),
			accountsPortletURL.toString());

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/users/view_contact");
		portletURL.setParameter(
			"contactEmailAddress", contact.getEmailAddress());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, contactDisplay.getFullName(),
			portletURL.toString());
	}

	public SearchContainer getContactAccountsSearchContainer(String type)
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"this-user-is-not-assigned-to-any-" + type + "-roles-yet");

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, type + "ContactUuids", contact.getUuid());

		List<Account> accounts = accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, "name");

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					renderRequest, renderResponse, accountReader, account)));

		return searchContainer;
	}

	public List<TeamDisplay> getContactAccountTeamDisplays(String accountKey)
		throws Exception {

		List<TeamDisplay> teamDisplays = new ArrayList<>();

		Team[] teams = contact.getTeams();

		for (Team team : teams) {
			if (accountKey.equals(team.getAccountKey())) {
				teamDisplays.add(
					new TeamDisplay(renderRequest, renderResponse, team, 0, 0));
			}
		}

		return teamDisplays;
	}

	public ContactDisplay getContactDisplay() {
		return contactDisplay;
	}

	public String getCurrentURL() {
		return currentURLObj.toString();
	}

	public List<String> getCustomerContactRoleNames(String accountKey)
		throws Exception {

		List<String> names = new ArrayList<>();

		List<ContactRole> contactRoles =
			contactRoleWebService.getAccountCustomerContactRoles(
				accountKey, contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			names.add(contactRole.getName());
		}

		return names;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/users/view_contact");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter(
			"contactEmailAddress", contact.getEmailAddress());

		return portletURL;
	}

	public List<String> getWorkerContactRoleNames(String accountKey)
		throws Exception {

		List<String> names = new ArrayList<>();

		List<ContactRole> contactRoles =
			contactRoleWebService.getAccountWorkerContactRoles(
				accountKey, contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			names.add(contactRole.getName());
		}

		return names;
	}

	public boolean hasManageContactsPermission() throws Exception {
		return ContactPermissionChecker.contains(
			themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_CONTACTS);
	}

	public void init(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			AccountWebService accountWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService)
		throws Exception {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.httpServletRequest = httpServletRequest;
		this.accountReader = accountReader;
		this.accountWebService = accountWebService;
		this.contactRoleWebService = contactRoleWebService;
		this.contactWebService = contactWebService;

		doInit();
	}

	protected void doInit() throws Exception {
		contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);
		themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<ContactRole> contactRoles = null;

		if (contact.getContactRoles() != null) {
			contactRoles = new ArrayList<>(
				Arrays.asList(contact.getContactRoles()));
		}

		contactDisplay = new ContactDisplay(
			httpServletRequest, contact, contactRoles);

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);
	}

	protected AccountReader accountReader;
	protected long accountsCount;
	protected AccountWebService accountWebService;
	protected Contact contact;
	protected ContactDisplay contactDisplay;
	protected ContactRoleWebService contactRoleWebService;
	protected ContactWebService contactWebService;
	protected PortletURL currentURLObj;
	protected HttpServletRequest httpServletRequest;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;
	protected ThemeDisplay themeDisplay;

}