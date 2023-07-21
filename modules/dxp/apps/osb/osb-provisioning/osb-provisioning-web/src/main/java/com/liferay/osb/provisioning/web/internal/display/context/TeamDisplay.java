/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class TeamDisplay {

	public TeamDisplay(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Team team, long flsAssignedAccountsCount,
		long partnerAssignedAccountsCount) {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_team = team;
		_flsAssignedAccountsCount = flsAssignedAccountsCount;
		_partnerAssignedAccountsCount = partnerAssignedAccountsCount;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a");
		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
	}

	public Account getAccount() {
		return _team.getAccount();
	}

	public List<String> getContactNames() {
		List<String> contactNames = new ArrayList<>();

		Contact[] contacts = _team.getContacts();

		if (contacts != null) {
			for (Contact contact : contacts) {
				ContactDisplay contactDisplay = new ContactDisplay(
					_httpServletRequest, contact, null);

				contactNames.add(contactDisplay.getFullName());
			}
		}

		return contactNames;
	}

	public String getDateCreated() {
		return _dateFormat.format(_team.getDateCreated());
	}

	public String getDateModified() {
		return _dateFormat.format(_team.getDateModified());
	}

	public String getDeleteTeamURL() {
		PortletURL deleteTeamURL = _liferayPortletResponse.createActionURL();

		deleteTeamURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_team");
		deleteTeamURL.setParameter(Constants.CMD, Constants.DELETE);

		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", _team.getAccountKey());
		portletURL.setParameter("tabs1", "teams");

		deleteTeamURL.setParameter("redirect", portletURL.toString());

		deleteTeamURL.setParameter("teamKey", _team.getKey());

		return deleteTeamURL.toString();
	}

	public String getFLSAssignedAccountsCount() {
		return String.valueOf(_flsAssignedAccountsCount);
	}

	public String getKey() {
		return _team.getKey();
	}

	public String getName() {
		return _team.getName();
	}

	public String getPartnerAssignedAccountsCount() {
		return String.valueOf(_partnerAssignedAccountsCount);
	}

	public boolean isSystem() {
		return _team.getSystem();
	}

	private final Format _dateFormat;
	private final long _flsAssignedAccountsCount;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final long _partnerAssignedAccountsCount;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private final Team _team;

}