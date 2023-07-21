/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.portlet.action;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	configurationPid = "com.liferay.layout.admin.web.internal.configuration.LayoutConverterConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + LayoutAdminPortletKeys.GROUP_PAGES,
		"mvc.command.name=/layout/move_layout"
	},
	service = MVCActionCommand.class
)
public class MoveLayoutMVCActionCommand
	extends GetLayoutChildrenMVCActionCommand {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		super.activate(properties);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long plid = ParamUtil.getLong(actionRequest, "plid");

		long parentPlid = ParamUtil.getLong(actionRequest, "parentPlid");
		int priority = ParamUtil.getInteger(actionRequest, "priority");

		Layout layout = layoutLocalService.fetchLayout(plid);

		if (layout.getParentPlid() == parentPlid) {
			_layoutService.updatePriority(plid, priority);
		}
		else {
			_layoutService.updatePriority(plid, Integer.MAX_VALUE);

			_layoutService.updateParentLayoutIdAndPriority(
				plid, parentPlid, priority);
		}

		long checkPlid = ParamUtil.getLong(actionRequest, "checkPlid");

		writeChildLayoutsAsJSON(actionRequest, actionResponse, checkPlid);
	}

	@Reference
	private LayoutService _layoutService;

}