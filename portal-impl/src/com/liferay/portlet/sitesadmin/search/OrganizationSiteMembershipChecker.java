/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.sitesadmin.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Charles May
 */
public class OrganizationSiteMembershipChecker extends EmptyOnClickRowChecker {

	public OrganizationSiteMembershipChecker(
		RenderResponse renderResponse, Group group) {

		super(renderResponse);

		_group = group;
	}

	@Override
	public boolean isChecked(Object obj) {
		Organization organization = (Organization)obj;

		try {
			if (OrganizationLocalServiceUtil.hasGroupOrganization(
					_group.getGroupId(), organization.getOrganizationId()) ||
				(_group.getOrganizationId() ==
					organization.getOrganizationId())) {

				return true;
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return false;
	}

	@Override
	public boolean isDisabled(Object obj) {
		Organization organization = (Organization)obj;

		if (_group.getOrganizationId() == organization.getOrganizationId()) {
			return true;
		}

		return isChecked(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationSiteMembershipChecker.class);

	private final Group _group;

}