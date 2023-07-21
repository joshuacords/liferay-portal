/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.search.index;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.search.engine.adapter.document.BulkDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.workflow.metrics.internal.sla.processor.WorkflowMetricsSLATaskResult;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.sla.processor.WorkfowMetricsSLAStatus;

import java.sql.Timestamp;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(
	immediate = true,
	service = {Indexer.class, SLATaskResultWorkflowMetricsIndexer.class}
)
public class SLATaskResultWorkflowMetricsIndexer
	extends BaseSLAWorkflowMetricsIndexer {

	public void addDocuments(
		List<WorkflowMetricsSLATaskResult> workflowMetricsSLATaskResults) {

		if (searchEngineAdapter == null) {
			return;
		}

		BulkDocumentRequest bulkDocumentRequest = new BulkDocumentRequest();

		workflowMetricsSLATaskResults.forEach(
			workflowMetricsSLATaskResult -> {
				Document document = createDocument(
					workflowMetricsSLATaskResult);

				bulkDocumentRequest.addBulkableDocumentRequest(
					new IndexDocumentRequest(
						getIndexName(
							GetterUtil.getLong(document.get("companyId"))),
						document.getUID(), document) {

						{
							setType(getIndexType());
						}
					});
			});

		if (ListUtil.isNotEmpty(
				bulkDocumentRequest.getBulkableDocumentRequests())) {

			searchEngineAdapter.execute(bulkDocumentRequest);
		}
	}

	public Document createDocument(
		WorkflowMetricsSLATaskResult workflowMetricsSLATaskResult) {

		Document document = new DocumentImpl();

		document.addUID(
			"WorkflowMetricsSLATaskResult",
			digest(
				workflowMetricsSLATaskResult.getCompanyId(),
				workflowMetricsSLATaskResult.getInstanceId(),
				workflowMetricsSLATaskResult.getProcessId(),
				workflowMetricsSLATaskResult.getSLADefinitionId(),
				workflowMetricsSLATaskResult.getTaskId(),
				workflowMetricsSLATaskResult.getTokenId()));

		document.addKeyword(
			"companyId", workflowMetricsSLATaskResult.getCompanyId());
		document.addKeyword(
			"breached", workflowMetricsSLATaskResult.isBreached());
		document.addKeyword("deleted", false);
		document.addKeyword(
			"instanceId", workflowMetricsSLATaskResult.getInstanceId());
		document.addDateSortable(
			"lastCheckDate",
			Timestamp.valueOf(
				workflowMetricsSLATaskResult.getLastCheckLocalDateTime()));
		document.addKeyword("onTime", workflowMetricsSLATaskResult.isOnTime());
		document.addKeyword(
			"processId", workflowMetricsSLATaskResult.getProcessId());
		document.addKeyword(
			"slaDefinitionId",
			workflowMetricsSLATaskResult.getSLADefinitionId());

		WorkfowMetricsSLAStatus workfowMetricsSLAStatus =
			workflowMetricsSLATaskResult.getWorkfowMetricsSLAStatus();

		document.addKeyword("status", workfowMetricsSLAStatus.name());

		document.addKeyword("taskId", workflowMetricsSLATaskResult.getTaskId());
		document.addKeyword(
			"taskName", workflowMetricsSLATaskResult.getTaskName());
		document.addKeyword(
			"tokenId", workflowMetricsSLATaskResult.getTokenId());

		return document;
	}

	@Override
	public void reindex(long companyId) {
	}

	@Override
	protected String getIndexName(long companyId) {
		return _slaTaskResultWorkflowMetricsIndexNameBuilder.getIndexName(
			companyId);
	}

	@Override
	protected String getIndexType() {
		return "WorkflowMetricsSLATaskResultType";
	}

	@Reference(target = "(workflow.metrics.index.entity.name=sla-task-result)")
	private WorkflowMetricsIndexNameBuilder
		_slaTaskResultWorkflowMetricsIndexNameBuilder;

}