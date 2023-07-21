/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.internal.search.index.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.service.internal.search.index.test.base.BaseWorkflowMetricsIndexerTestCase;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Rafael Praxedes
 */
@RunWith(Arquillian.class)
public class ProcessWorkflowMetricsIndexerTest
	extends BaseWorkflowMetricsIndexerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddProcess() throws Exception {
		KaleoDefinition kaleoDefinition = getKaleoDefinition();

		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version", "1.0");
		retryAssertCount(
			_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsInstanceType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "instanceId", 0);
		retryAssertCount(
			_slaProcessResultWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsSLAProcessResultType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "slaDefinitionId", 0);
	}

	@Test
	public void testDeleteProcess() throws Exception {
		KaleoDefinition kaleoDefinition = getKaleoDefinition();

		undeployWorkflowDefinition();

		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", true, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version", "1.0");
	}

	@Test
	public void testReindex() throws Exception {
		KaleoDefinition kaleoDefinition = getKaleoDefinition();

		assertReindex(
			_processWorkflowMetricsIndexer,
			new String[] {
				_processWorkflowMetricsIndexNameBuilder.getIndexName(
					kaleoDefinition.getCompanyId()),
				_instanceWorkflowMetricsIndexNameBuilder.getIndexName(
					kaleoDefinition.getCompanyId()),
				_slaProcessResultWorkflowMetricsIndexNameBuilder.getIndexName(
					kaleoDefinition.getCompanyId())
			},
			new String[] {
				"WorkflowMetricsProcessType", "WorkflowMetricsInstanceType",
				"WorkflowMetricsSLAProcessResultType"
			},
			"companyId", kaleoDefinition.getCompanyId(), "processId",
			kaleoDefinition.getKaleoDefinitionId());
	}

	@Test
	public void testUpdateProcess() throws Exception {
		KaleoDefinition kaleoDefinition = getKaleoDefinition();

		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version", "1.0");

		updateKaleoDefinition();

		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version", "2.0");
	}

	@Inject(filter = "workflow.metrics.index.entity.name=instance")
	private static WorkflowMetricsIndexNameBuilder
		_instanceWorkflowMetricsIndexNameBuilder;

	@Inject(filter = "workflow.metrics.index.entity.name=process")
	private static WorkflowMetricsIndexNameBuilder
		_processWorkflowMetricsIndexNameBuilder;

	@Inject(filter = "workflow.metrics.index.entity.name=sla-process-result")
	private static WorkflowMetricsIndexNameBuilder
		_slaProcessResultWorkflowMetricsIndexNameBuilder;

	@Inject(
		filter = "(&(objectClass=com.liferay.portal.workflow.metrics.internal.search.index.ProcessWorkflowMetricsIndexer))"
	)
	private Indexer<Object> _processWorkflowMetricsIndexer;

}