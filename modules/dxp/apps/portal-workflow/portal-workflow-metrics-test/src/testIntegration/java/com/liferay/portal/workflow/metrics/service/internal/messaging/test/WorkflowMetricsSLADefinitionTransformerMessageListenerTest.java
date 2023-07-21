/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.internal.messaging.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;
import com.liferay.portal.workflow.metrics.search.index.name.WorkflowMetricsIndexNameBuilder;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionLocalService;
import com.liferay.portal.workflow.metrics.service.internal.util.WorkflowDefinitionUtil;
import com.liferay.portal.workflow.metrics.service.test.base.BaseWorkflowMetricsTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Rafael Praxedes
 */
@RunWith(Arquillian.class)
public class WorkflowMetricsSLADefinitionTransformerMessageListenerTest
	extends BaseWorkflowMetricsTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				StringUtil.randomId(), StringUtil.randomId(),
				WorkflowDefinitionUtil.getBytes());
	}

	@Test
	public void testTransform() throws Exception {
		KaleoDefinition kaleoDefinition =
			_kaleoDefinitionLocalService.getKaleoDefinition(
				_workflowDefinition.getName(),
				ServiceContextTestUtil.getServiceContext());

		retryAssertCount(
			4,
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsNodeType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version",
			kaleoDefinition.getVersion() + ".0");
		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version",
			kaleoDefinition.getVersion() + ".0");

		_workflowMetricsSLADefinition =
			_workflowMetricsSLADefinitionLocalService.
				addWorkflowMetricsSLADefinition(
					StringPool.BLANK, StringPool.BLANK, 5000, "Abc",
					new String[0], kaleoDefinition.getKaleoDefinitionId(),
					new String[] {getInitialNodeKey(kaleoDefinition)},
					new String[] {getTerminalNodeKey(kaleoDefinition)},
					ServiceContextTestUtil.getServiceContext());

		_workflowDefinitionManager.deployWorkflowDefinition(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			kaleoDefinition.getTitle(), kaleoDefinition.getName(),
			WorkflowDefinitionUtil.getBytes());

		kaleoDefinition = _kaleoDefinitionLocalService.getKaleoDefinition(
			_workflowDefinition.getName(),
			ServiceContextTestUtil.getServiceContext());

		retryAssertCount(
			_processWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsProcessType", "companyId",
			kaleoDefinition.getCompanyId(), "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version",
			kaleoDefinition.getVersion() + ".0");
		retryAssertCount(
			4,
			_nodeWorkflowMetricsIndexNameBuilder.getIndexName(
				kaleoDefinition.getCompanyId()),
			"WorkflowMetricsNodeType", "companyId",
			kaleoDefinition.getCompanyId(), "deleted", false, "processId",
			kaleoDefinition.getKaleoDefinitionId(), "version",
			kaleoDefinition.getVersion() + ".0");

		_workflowMetricsSLADefinitionTransformerMessageListener.receive(
			new Message());

		_workflowMetricsSLADefinition =
			_workflowMetricsSLADefinitionLocalService.
				getWorkflowMetricsSLADefinition(
					_workflowMetricsSLADefinition.
						getWorkflowMetricsSLADefinitionId());

		Assert.assertEquals(
			kaleoDefinition.getKaleoDefinitionId(),
			_workflowMetricsSLADefinition.getProcessId());
		Assert.assertEquals(
			getInitialNodeKey(kaleoDefinition),
			_workflowMetricsSLADefinition.getStartNodeKeys());
		Assert.assertEquals(
			getTerminalNodeKey(kaleoDefinition),
			_workflowMetricsSLADefinition.getStopNodeKeys());
	}

	@Inject(filter = "workflow.metrics.index.entity.name=node")
	private static WorkflowMetricsIndexNameBuilder
		_nodeWorkflowMetricsIndexNameBuilder;

	@Inject(filter = "workflow.metrics.index.entity.name=process")
	private static WorkflowMetricsIndexNameBuilder
		_processWorkflowMetricsIndexNameBuilder;

	@Inject
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	private WorkflowDefinition _workflowDefinition;

	@Inject
	private WorkflowDefinitionManager _workflowDefinitionManager;

	@DeleteAfterTestRun
	private WorkflowMetricsSLADefinition _workflowMetricsSLADefinition;

	@Inject
	private WorkflowMetricsSLADefinitionLocalService
		_workflowMetricsSLADefinitionLocalService;

	@Inject(
		filter = "(&(objectClass=com.liferay.portal.workflow.metrics.internal.messaging.WorkflowMetricsSLADefinitionTransformerMessageListener))"
	)
	private MessageListener
		_workflowMetricsSLADefinitionTransformerMessageListener;

}