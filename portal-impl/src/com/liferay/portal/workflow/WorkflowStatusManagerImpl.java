/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.workflow.WorkflowStatusManager;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Bruno Farache
 */
public class WorkflowStatusManagerImpl implements WorkflowStatusManager {

	@Override
	public void updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws WorkflowException {

		try {
			WorkflowHandlerRegistryUtil.updateStatus(status, workflowContext);
		}
		catch (Exception exception) {
			throw new WorkflowException(exception);
		}
	}

}