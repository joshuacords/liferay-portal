/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.action;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.struts.JSONAction;
import com.liferay.portlet.layoutsadmin.util.LayoutsTreeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Zsolt Szabó
 * @author Tibor Lipusz
 */
public class GetLayoutsAction extends JSONAction {

	@Override
	public String getJSON(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		String cmd = ParamUtil.getString(httpServletRequest, Constants.CMD);

		long groupId = ParamUtil.getLong(httpServletRequest, "groupId");
		String treeId = ParamUtil.getString(httpServletRequest, "treeId");

		if (cmd.equals("get")) {
			return getLayoutsJSON(httpServletRequest, groupId, treeId);
		}
		else if (cmd.equals("getAll")) {
			return LayoutsTreeUtil.getLayoutsJSON(
				httpServletRequest, groupId, treeId);
		}
		else if (cmd.equals("getSiblingLayoutsJSON")) {
			return getSiblingLayoutsJSON(httpServletRequest, groupId);
		}

		return null;
	}

	protected String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId, String treeId)
		throws Exception {

		boolean privateLayout = ParamUtil.getBoolean(
			httpServletRequest, "privateLayout");
		long parentLayoutId = ParamUtil.getLong(
			httpServletRequest, "parentLayoutId");
		boolean incomplete = ParamUtil.getBoolean(
			httpServletRequest, "incomplete", true);

		return LayoutsTreeUtil.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, parentLayoutId,
			incomplete, treeId);
	}

	protected String getSiblingLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId)
		throws Exception {

		boolean privateLayout = ParamUtil.getBoolean(
			httpServletRequest, "privateLayout");
		long layoutId = ParamUtil.getLong(httpServletRequest, "layoutId");
		int max = ParamUtil.getInteger(httpServletRequest, "max");

		return LayoutsTreeUtil.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, layoutId, max);
	}

}