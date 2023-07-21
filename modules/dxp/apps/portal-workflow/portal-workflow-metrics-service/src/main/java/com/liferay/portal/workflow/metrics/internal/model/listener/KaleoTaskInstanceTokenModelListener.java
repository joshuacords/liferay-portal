/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.model.listener;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.metrics.internal.petra.executor.WorkflowMetricsPortalExecutor;
import com.liferay.portal.workflow.metrics.internal.search.index.TokenWorkflowMetricsIndexer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoTaskInstanceTokenModelListener
	extends BaseModelListener<KaleoTaskInstanceToken> {

	@Override
	public void onAfterCreate(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_workflowMetricsPortalExecutor.execute(
			() -> _tokenWorkflowMetricsIndexer.addDocument(
				_tokenWorkflowMetricsIndexer.createDocument(
					kaleoTaskInstanceToken)));
	}

	@Override
	public void onAfterRemove(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_workflowMetricsPortalExecutor.execute(
			() -> _tokenWorkflowMetricsIndexer.deleteDocument(
				_tokenWorkflowMetricsIndexer.createDocument(
					kaleoTaskInstanceToken)));
	}

	@Override
	public void onAfterUpdate(KaleoTaskInstanceToken kaleoTaskInstanceToken) {
		_workflowMetricsPortalExecutor.execute(
			() -> _tokenWorkflowMetricsIndexer.updateDocument(
				_tokenWorkflowMetricsIndexer.createDocument(
					kaleoTaskInstanceToken)));
	}

	@Reference
	private TokenWorkflowMetricsIndexer _tokenWorkflowMetricsIndexer;

	@Reference
	private WorkflowMetricsPortalExecutor _workflowMetricsPortalExecutor;

}