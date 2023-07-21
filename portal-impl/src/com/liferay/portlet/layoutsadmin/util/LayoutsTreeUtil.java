/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.layoutsadmin.util;

import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Akos Thurzo
 */
public class LayoutsTreeUtil {

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long parentLayoutId, boolean incomplete,
			String treeId)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, parentLayoutId,
			incomplete, treeId);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long parentLayoutId, boolean incomplete,
			String treeId, LayoutSetBranch layoutSetBranch)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, parentLayoutId,
			incomplete, treeId, layoutSetBranch);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long layoutId, int max)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, layoutId, max);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long layoutId, int max,
			LayoutSetBranch layoutSetBranch)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, layoutId, max,
			layoutSetBranch);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long parentLayoutId,
			long[] expandedLayoutIds, boolean incomplete, String treeId)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, parentLayoutId,
			expandedLayoutIds, incomplete, treeId);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId,
			boolean privateLayout, long parentLayoutId,
			long[] expandedLayoutIds, boolean incomplete, String treeId,
			LayoutSetBranch layoutSetBranch)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, privateLayout, parentLayoutId,
			expandedLayoutIds, incomplete, treeId, layoutSetBranch);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId, String treeId)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(httpServletRequest, groupId, treeId);
	}

	public static String getLayoutsJSON(
			HttpServletRequest httpServletRequest, long groupId, String treeId,
			LayoutSetBranch layoutSetBranch)
		throws Exception {

		return _layoutsTree.getLayoutsJSON(
			httpServletRequest, groupId, treeId, layoutSetBranch);
	}

	private static volatile LayoutsTree _layoutsTree =
		ServiceProxyFactory.newServiceTrackedInstance(
			LayoutsTree.class, LayoutsTreeUtil.class, "_layoutsTree", false);

}