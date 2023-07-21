/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.test;

import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.workflow.UserWorkflowHandler;
import com.liferay.portlet.usersadmin.util.ContactIndexer;
import com.liferay.portlet.usersadmin.util.OrganizationIndexer;

/**
 * @author     Roberto Díaz
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class PortalRegisterTestUtil {

	protected static void registerIndexers() {
		if (_indexersRegistered) {
			return;
		}

		IndexerRegistryUtil.register(new ContactIndexer());
		IndexerRegistryUtil.register(new OrganizationIndexer());

		_indexersRegistered = true;
	}

	protected static void registerWorkflowHandlers() {
		if (_workflowHandlersRegistered) {
			return;
		}

		WorkflowHandlerRegistryUtil.register(new UserWorkflowHandler());

		_workflowHandlersRegistered = true;
	}

	private static boolean _indexersRegistered;
	private static boolean _workflowHandlersRegistered;

}