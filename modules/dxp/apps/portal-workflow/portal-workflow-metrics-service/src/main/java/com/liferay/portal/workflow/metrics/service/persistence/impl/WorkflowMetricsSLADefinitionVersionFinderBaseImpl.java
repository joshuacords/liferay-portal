/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;
import com.liferay.portal.workflow.metrics.service.persistence.WorkflowMetricsSLADefinitionVersionPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WorkflowMetricsSLADefinitionVersionFinderBaseImpl
	extends BasePersistenceImpl<WorkflowMetricsSLADefinitionVersion> {

	public WorkflowMetricsSLADefinitionVersionFinderBaseImpl() {
		setModelClass(WorkflowMetricsSLADefinitionVersion.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"workflowMetricsSLADefinitionVersionId",
			"wmSLADefinitionVersionId");
		dbColumnNames.put("active", "active_");
		dbColumnNames.put(
			"workflowMetricsSLADefinitionId", "wmSLADefinitionId");

		setDBColumnNames(dbColumnNames);
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getWorkflowMetricsSLADefinitionVersionPersistence().
			getBadColumnNames();
	}

	/**
	 * Returns the workflow metrics sla definition version persistence.
	 *
	 * @return the workflow metrics sla definition version persistence
	 */
	public WorkflowMetricsSLADefinitionVersionPersistence
		getWorkflowMetricsSLADefinitionVersionPersistence() {

		return workflowMetricsSLADefinitionVersionPersistence;
	}

	/**
	 * Sets the workflow metrics sla definition version persistence.
	 *
	 * @param workflowMetricsSLADefinitionVersionPersistence the workflow metrics sla definition version persistence
	 */
	public void setWorkflowMetricsSLADefinitionVersionPersistence(
		WorkflowMetricsSLADefinitionVersionPersistence
			workflowMetricsSLADefinitionVersionPersistence) {

		this.workflowMetricsSLADefinitionVersionPersistence =
			workflowMetricsSLADefinitionVersionPersistence;
	}

	@BeanReference(type = WorkflowMetricsSLADefinitionVersionPersistence.class)
	protected WorkflowMetricsSLADefinitionVersionPersistence
		workflowMetricsSLADefinitionVersionPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		WorkflowMetricsSLADefinitionVersionFinderBaseImpl.class);

}