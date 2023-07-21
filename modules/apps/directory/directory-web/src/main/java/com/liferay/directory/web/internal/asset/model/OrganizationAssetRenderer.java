/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.directory.web.internal.asset.model;

import com.liferay.asset.kernel.model.BaseAssetRenderer;
import com.liferay.portal.kernel.model.Organization;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ricardo Couso
 */
public class OrganizationAssetRenderer extends BaseAssetRenderer<Organization> {

	public OrganizationAssetRenderer(Organization organization) {
		_organization = organization;
	}

	@Override
	public Organization getAssetObject() {
		return _organization;
	}

	@Override
	public String getClassName() {
		return Organization.class.getName();
	}

	@Override
	public long getClassPK() {
		return _organization.getPrimaryKey();
	}

	@Override
	public long getGroupId() {
		return _organization.getGroupId();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return _organization.getComments();
	}

	@Override
	public String getTitle(Locale locale) {
		return _organization.getName();
	}

	@Override
	public long getUserId() {
		return _organization.getUserId();
	}

	@Override
	public String getUserName() {
		return _organization.getUserName();
	}

	@Override
	public String getUuid() {
		return _organization.getUuid();
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		return false;
	}

	private final Organization _organization;

}