/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.admin.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.service.GadgetServiceUtil;
import com.liferay.opensocial.service.permission.GadgetPermission;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.ActionKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		GadgetServiceUtil.deleteGadget(gadgetId, serviceContext);
	}

	public void refreshGadgets(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] gadgetIds = ParamUtil.getLongValues(actionRequest, "gadgetId");

		if (gadgetIds.length == 0) {
			List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Gadget gadget : gadgets) {
				ShindigUtil.clearGadgetSpecCache(gadget.getUrl());
			}
		}
		else {
			for (long gadgetId : gadgetIds) {
				Gadget gadget = GadgetLocalServiceUtil.getGadget(gadgetId);

				ShindigUtil.clearGadgetSpecCache(gadget.getUrl());
			}
		}
	}

	public void updateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			doAddGadget(actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			doUpdateGadget(actionRequest, actionResponse);
		}
	}

	public void updateOAuthConsumers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, themeDisplay.getScopeGroupId(), gadgetId,
			ActionKeys.UPDATE);

		ShindigUtil.updateOAuthConsumers(actionRequest, actionResponse);
	}

	protected Gadget doAddGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String url = ParamUtil.getString(actionRequest, "url");
		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		return GadgetServiceUtil.addGadget(
			themeDisplay.getCompanyId(), url, portletCategoryNames,
			serviceContext);
	}

	protected void doUpdateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		GadgetServiceUtil.updateGadget(
			gadgetId, portletCategoryNames, serviceContext);
	}

}