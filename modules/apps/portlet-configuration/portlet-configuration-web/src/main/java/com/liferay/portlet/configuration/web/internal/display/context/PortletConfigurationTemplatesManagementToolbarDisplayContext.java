/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.configuration.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class PortletConfigurationTemplatesManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public PortletConfigurationTemplatesManagementToolbarDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		HttpServletRequest httpServletRequest,
		PortletConfigurationTemplatesDisplayContext
			portletConfigurationTemplatesDisplayContext) {

		super(
			liferayPortletRequest, liferayPortletResponse, httpServletRequest,
			portletConfigurationTemplatesDisplayContext.
				getArchivedSettingsSearchContainer());

		_portletConfigurationTemplatesDisplayContext =
			portletConfigurationTemplatesDisplayContext;
	}

	@Override
	public List<DropdownItem> getActionDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.putData(
							"action", "deleteArchivedSettings");
						dropdownItem.setIcon("trash");
						dropdownItem.setLabel(
							LanguageUtil.get(request, "delete"));
						dropdownItem.setQuickAction(true);
					});
			}
		};
	}

	@Override
	public String getComponentId() {
		return "archivedSettingsManagementToolbar";
	}

	@Override
	public String getDefaultEventHandler() {
		return "PORTLET_CONFIGURATION_TEMPLATES_MANAGEMENT_TOOLBAR_DEFAULT_" +
			"EVENT_HANDLER";
	}

	@Override
	public String getSearchContainerId() {
		return "archivedSettings";
	}

	@Override
	protected String getDisplayStyle() {
		return _portletConfigurationTemplatesDisplayContext.getDisplayStyle();
	}

	@Override
	protected String[] getDisplayViews() {
		return new String[] {"list", "descriptive", "icon"};
	}

	@Override
	protected String[] getNavigationKeys() {
		return new String[] {"all"};
	}

	@Override
	protected String[] getOrderByKeys() {
		return new String[] {"name", "modified-date"};
	}

	private final PortletConfigurationTemplatesDisplayContext
		_portletConfigurationTemplatesDisplayContext;

}