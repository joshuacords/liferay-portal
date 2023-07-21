/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.model.listener;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.metrics.internal.petra.executor.WorkflowMetricsPortalExecutor;
import com.liferay.portal.workflow.metrics.internal.search.index.InstanceWorkflowMetricsIndexer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoInstanceModelListener
	extends BaseModelListener<KaleoInstance> {

	@Override
	public void onAfterCreate(KaleoInstance kaleoInstance) {
		_workflowMetricsPortalExecutor.execute(
			() -> _instanceWorkflowMetricsIndexer.addDocument(
				_instanceWorkflowMetricsIndexer.createDocument(kaleoInstance)));
	}

	@Override
	public void onAfterRemove(KaleoInstance kaleoInstance) {
		_workflowMetricsPortalExecutor.execute(
			() -> _instanceWorkflowMetricsIndexer.deleteDocument(
				_instanceWorkflowMetricsIndexer.createDocument(kaleoInstance)));
	}

	@Override
	public void onAfterUpdate(KaleoInstance kaleoInstance) {
		_workflowMetricsPortalExecutor.execute(
			() -> _instanceWorkflowMetricsIndexer.updateDocument(
				_instanceWorkflowMetricsIndexer.createDocument(kaleoInstance)));
	}

	@Reference
	private InstanceWorkflowMetricsIndexer _instanceWorkflowMetricsIndexer;

	@Reference
	private WorkflowMetricsPortalExecutor _workflowMetricsPortalExecutor;

}