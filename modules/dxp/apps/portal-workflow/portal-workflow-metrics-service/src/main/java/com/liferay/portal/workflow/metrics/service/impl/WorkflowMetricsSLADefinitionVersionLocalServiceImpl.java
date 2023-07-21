/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.workflow.metrics.exception.NoSuchSLADefinitionVersionException;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;
import com.liferay.portal.workflow.metrics.service.base.WorkflowMetricsSLADefinitionVersionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowMetricsSLADefinitionVersionLocalServiceImpl
	extends WorkflowMetricsSLADefinitionVersionLocalServiceBaseImpl {

	@Override
	public WorkflowMetricsSLADefinitionVersion
			getWorkflowMetricsSLADefinitionVersion(
				long workflowMetricsSLADefinitionId, String version)
		throws NoSuchSLADefinitionVersionException {

		return workflowMetricsSLADefinitionVersionPersistence.findByV_WMSLAD(
			version, workflowMetricsSLADefinitionId);
	}

	@Override
	public List<WorkflowMetricsSLADefinitionVersion>
		getWorkflowMetricsSLADefinitionVersions(
			long workflowMetricsSLADefinitionId) {

		return workflowMetricsSLADefinitionVersionPersistence.
			findByWorkflowMetricsSLADefinitionId(
				workflowMetricsSLADefinitionId);
	}

	@Override
	public List<WorkflowMetricsSLADefinitionVersion>
		getWorkflowMetricsSLADefinitionVersions(
			long companyId, Date createDate, int status) {

		return workflowMetricsSLADefinitionVersionFinder.findByC_WMSLAD_V(
			companyId, createDate, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

}