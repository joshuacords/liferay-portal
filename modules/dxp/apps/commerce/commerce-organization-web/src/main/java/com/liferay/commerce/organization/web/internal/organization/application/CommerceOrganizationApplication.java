/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.application;

import com.liferay.commerce.organization.web.internal.organization.application.context.provider.PaginationContextProvider;
import com.liferay.commerce.organization.web.internal.organization.application.context.provider.SortContextProvider;
import com.liferay.commerce.organization.web.internal.organization.application.context.provider.ThemeDisplayContextProvider;
import com.liferay.commerce.organization.web.internal.organization.resource.CommerceOrganizationResource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-organization",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CommerceOrganization.Application",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true", "liferay.oauth2=false"
	},
	service = Application.class
)
public class CommerceOrganizationApplication extends Application {

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		singletons.add(_commerceOrganizationResource);
		singletons.add(_paginationContextProvider);
		singletons.add(_sortContextProvider);
		singletons.add(_themeDisplayContextProvider);

		return singletons;
	}

	@Reference
	private CommerceOrganizationResource _commerceOrganizationResource;

	@Reference
	private PaginationContextProvider _paginationContextProvider;

	@Reference
	private SortContextProvider _sortContextProvider;

	@Reference
	private ThemeDisplayContextProvider _themeDisplayContextProvider;

}