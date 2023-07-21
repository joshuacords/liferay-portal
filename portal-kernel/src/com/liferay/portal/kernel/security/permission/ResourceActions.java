/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.permission;

import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Role;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @author Daeyoung Song
 */
@ProviderType
public interface ResourceActions {

	public void check(Portlet portlet);

	public void check(String portletName);

	public void checkAction(String name, String actionId)
		throws NoSuchResourceActionException;

	public String getAction(
		HttpServletRequest httpServletRequest, String action);

	public String getAction(Locale locale, String action);

	public String getActionNamePrefix();

	public String getCompositeModelName(String... classNames);

	public String getCompositeModelNameSeparator();

	public List<String> getModelNames();

	public List<String> getModelPortletResources(String name);

	public String getModelResource(
		HttpServletRequest httpServletRequest, String name);

	public String getModelResource(Locale locale, String name);

	public List<String> getModelResourceActions(String name);

	public List<String> getModelResourceGroupDefaultActions(String name);

	public List<String> getModelResourceGuestDefaultActions(String name);

	public List<String> getModelResourceGuestUnsupportedActions(String name);

	public String getModelResourceNamePrefix();

	public List<String> getModelResourceOwnerDefaultActions(String name);

	public Double getModelResourceWeight(String name);

	public String[] getOrganizationModelResources();

	public String[] getPortalModelResources();

	public String getPortletBaseResource(String portletName);

	public List<String> getPortletModelResources(String portletName);

	public List<String> getPortletNames();

	public List<String> getPortletResourceActions(Portlet portlet);

	public List<String> getPortletResourceActions(String name);

	public List<String> getPortletResourceGroupDefaultActions(String name);

	public List<String> getPortletResourceGuestDefaultActions(String name);

	public List<String> getPortletResourceGuestUnsupportedActions(String name);

	public List<String> getPortletResourceLayoutManagerActions(String name);

	public String getPortletRootModelResource(String portletName);

	public List<String> getResourceActions(String name);

	public List<String> getResourceActions(
		String portletResource, String modelResource);

	public List<String> getResourceGroupDefaultActions(String name);

	public List<String> getResourceGuestUnsupportedActions(
		String portletResource, String modelResource);

	public List<Role> getRoles(
		long companyId, Group group, String modelResource, int[] roleTypes);

	public String[] getRootModelResources();

	public boolean hasModelResourceActions(String name);

	public boolean isOrganizationModelResource(String modelResource);

	public boolean isPortalModelResource(String modelResource);

	public boolean isRootModelResource(String modelResource);

	public void read(
			String servletContextName, ClassLoader classLoader, String source)
		throws Exception;

	public void read(
			String servletContextName, ClassLoader classLoader,
			String... sources)
		throws Exception;

	public void readAndCheck(
			String servletContextName, ClassLoader classLoader,
			String... sources)
		throws Exception;

	public void removePortletResource(String portletName);

}