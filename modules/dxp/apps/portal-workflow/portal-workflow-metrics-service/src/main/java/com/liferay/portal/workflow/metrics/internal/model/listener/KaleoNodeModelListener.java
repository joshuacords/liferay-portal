/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.model.listener;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.metrics.internal.petra.executor.WorkflowMetricsPortalExecutor;
import com.liferay.portal.workflow.metrics.internal.search.index.NodeWorkflowMetricsIndexer;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(immediate = true, service = ModelListener.class)
public class KaleoNodeModelListener extends BaseModelListener<KaleoNode> {

	@Override
	public void onAfterCreate(KaleoNode kaleoNode) {
		if (!Objects.equals(kaleoNode.getType(), NodeType.STATE.name())) {
			return;
		}

		_workflowMetricsPortalExecutor.execute(
			() -> _nodeWorkflowMetricsIndexer.addDocument(
				_nodeWorkflowMetricsIndexer.createDocument(kaleoNode)));
	}

	@Override
	public void onAfterRemove(KaleoNode kaleoNode) {
		if (!Objects.equals(kaleoNode.getType(), NodeType.STATE.name())) {
			return;
		}

		_workflowMetricsPortalExecutor.execute(
			() -> _nodeWorkflowMetricsIndexer.deleteDocument(
				_nodeWorkflowMetricsIndexer.createDocument(kaleoNode)));
	}

	@Reference
	private NodeWorkflowMetricsIndexer _nodeWorkflowMetricsIndexer;

	@Reference
	private WorkflowMetricsPortalExecutor _workflowMetricsPortalExecutor;

}