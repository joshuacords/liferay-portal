/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;

import java.util.List;

/**
 * @author     Tobias Kaefer
 * @author     Douglas Wong
 * @author     Matthew Kong
 * @author     Raymond Augé
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class VerifyPermission extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		List<String> modelNames = ResourceActionsUtil.getModelNames();

		for (String modelName : modelNames) {
			List<String> actionIds =
				ResourceActionsUtil.getModelResourceActions(modelName);

			ResourceActionLocalServiceUtil.checkResourceActions(
				modelName, actionIds, true);
		}

		List<String> portletNames = ResourceActionsUtil.getPortletNames();

		for (String portletName : portletNames) {
			List<String> actionIds =
				ResourceActionsUtil.getPortletResourceActions(portletName);

			ResourceActionLocalServiceUtil.checkResourceActions(
				portletName, actionIds, true);
		}
	}

}