/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.web.internal.portlet.action;

import com.liferay.osb.koroneiki.root.constants.RootPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + RootPortletKeys.EXTERNAL_LINKS_ADMIN,
		"mvc.command.name=/external_links_admin/edit_external_link_mappings"
	},
	service = MVCActionCommand.class
)
public class EditExternalLinkMappingsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		Set<String> keys = new HashSet<>();

		int[] externalLinkIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "externalLinkIndexes"), 0);

		for (int externalLinkIndex : externalLinkIndexes) {
			String domain = ParamUtil.getString(
				actionRequest, "domain_" + externalLinkIndex);
			String entityName = ParamUtil.getString(
				actionRequest, "entityName_" + externalLinkIndex);
			String url = ParamUtil.getString(
				actionRequest, "url_" + externalLinkIndex);

			if (Validator.isNotNull(domain) &&
				Validator.isNotNull(entityName) && Validator.isNotNull(url)) {

				String key = domain + StringPool.UNDERLINE + entityName;

				keys.add(key);

				portletPreferences.setValue(key, url);
			}
		}

		Enumeration<String> names = portletPreferences.getNames();

		while (names.hasMoreElements()) {
			String name = names.nextElement();

			if (!keys.contains(name)) {
				portletPreferences.reset(name);
			}
		}

		portletPreferences.store();
	}

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}