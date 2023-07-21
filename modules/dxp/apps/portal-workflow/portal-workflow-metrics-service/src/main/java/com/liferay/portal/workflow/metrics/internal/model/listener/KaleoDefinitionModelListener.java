/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.metrics.internal.petra.executor.WorkflowMetricsPortalExecutor;
import com.liferay.portal.workflow.metrics.internal.search.index.ProcessWorkflowMetricsIndexer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoDefinitionModelListener
	extends BaseModelListener<KaleoDefinition> {

	@Override
	public void onAfterCreate(KaleoDefinition kaleoDefinition)
		throws ModelListenerException {

		_workflowMetricsPortalExecutor.execute(
			() -> _processWorkflowMetricsIndexer.addDocument(
				_processWorkflowMetricsIndexer.createDocument(
					kaleoDefinition)));
	}

	@Override
	public void onAfterUpdate(KaleoDefinition kaleoDefinition)
		throws ModelListenerException {

		_workflowMetricsPortalExecutor.execute(
			() -> _processWorkflowMetricsIndexer.updateDocument(
				_processWorkflowMetricsIndexer.createDocument(
					kaleoDefinition)));
	}

	@Override
	public void onBeforeRemove(KaleoDefinition kaleoDefinition)
		throws ModelListenerException {

		_workflowMetricsPortalExecutor.execute(
			() -> _processWorkflowMetricsIndexer.deleteDocument(
				_processWorkflowMetricsIndexer.createDocument(
					kaleoDefinition)));
	}

	@Reference
	private ProcessWorkflowMetricsIndexer _processWorkflowMetricsIndexer;

	@Reference
	private WorkflowMetricsPortalExecutor _workflowMetricsPortalExecutor;

}