/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.directory.web.internal.asset.model;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.PortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ricardo Couso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.DIRECTORY,
		"javax.portlet.name=" + PortletKeys.FRIENDS_DIRECTORY,
		"javax.portlet.name=" + PortletKeys.MY_SITES_DIRECTORY,
		"javax.portlet.name=" + PortletKeys.SITE_MEMBERS_DIRECTORY
	},
	service = AssetRendererFactory.class
)
public class OrganizationAssetRendererFactory
	extends BaseAssetRendererFactory<Organization> {

	public static final String TYPE = "organization";

	public OrganizationAssetRendererFactory() {
		setSearchable(false);
		setSelectable(false);
	}

	@Override
	public AssetRenderer<Organization> getAssetRenderer(long classPK, int type)
		throws PortalException {

		OrganizationAssetRenderer organizationAssetRenderer =
			new OrganizationAssetRenderer(
				_organizationLocalService.getOrganization(classPK));

		organizationAssetRenderer.setAssetRendererType(type);

		return organizationAssetRenderer;
	}

	@Override
	public String getClassName() {
		return Organization.class.getName();
	}

	@Override
	public String getIconCssClass() {
		return "organization";
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	private OrganizationLocalService _organizationLocalService;

}