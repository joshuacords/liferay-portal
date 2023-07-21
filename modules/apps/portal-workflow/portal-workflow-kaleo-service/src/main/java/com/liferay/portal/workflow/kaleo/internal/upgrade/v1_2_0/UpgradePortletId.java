/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Sam Ziemer
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"151", _SITE_ADMINISTRATION_WORKFLOW}, {"158", _USER_WORKFLOW}
		};
	}

	/**
	 * @see com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys
	 */
	private static final String _SITE_ADMINISTRATION_WORKFLOW =
		"com_liferay_portal_workflow_web_internal_portlet_" +
			"SiteAdministrationWorkflowPortlet";

	/**
	 * @see com.liferay.portal.workflow.web.internal.constants.WorkflowPortletKeys
	 */
	private static final String _USER_WORKFLOW =
		"com_liferay_portal_workflow_web_internal_portlet_UserWorkflowPortlet";

}