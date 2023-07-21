/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;
import com.liferay.portal.workflow.metrics.service.WorkflowMetricsSLADefinitionVersionLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class WorkflowMetricsSLADefinitionVersionLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_company = CompanyTestUtil.addCompany();
	}

	@Test
	public void testGetWorkflowMetricsSLADefinitionVersions1()
		throws Exception {

		_addWorkflowMetricsSLADefinitionVersion(
			"Abc", 1, WorkflowConstants.STATUS_APPROVED, 1);

		_addWorkflowMetricsSLADefinitionVersion(
			"Cdf", 1, WorkflowConstants.STATUS_APPROVED, 1);

		List<WorkflowMetricsSLADefinitionVersion>
			workflowMetricsSLADefinitionVersions =
				WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
					getWorkflowMetricsSLADefinitionVersions(
						_company.getCompanyId(), new Date(),
						WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			workflowMetricsSLADefinitionVersions.toString(), 1,
			workflowMetricsSLADefinitionVersions.size());

		WorkflowMetricsSLADefinitionVersion
			workflowMetricsSLADefinitionVersion =
				workflowMetricsSLADefinitionVersions.get(0);

		Assert.assertEquals(
			"Cdf", workflowMetricsSLADefinitionVersion.getName());
	}

	@Test
	public void testGetWorkflowMetricsSLADefinitionVersions2()
		throws Exception {

		_addWorkflowMetricsSLADefinitionVersion(
			"Abc", 1, WorkflowConstants.STATUS_APPROVED, 1);

		_addWorkflowMetricsSLADefinitionVersion(
			"Cdf", 1, WorkflowConstants.STATUS_DRAFT, 1);

		List<WorkflowMetricsSLADefinitionVersion>
			workflowMetricsSLADefinitionVersions =
				WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
					getWorkflowMetricsSLADefinitionVersions(
						_company.getCompanyId(), new Date(),
						WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			workflowMetricsSLADefinitionVersions.toString(), 1,
			workflowMetricsSLADefinitionVersions.size());

		WorkflowMetricsSLADefinitionVersion
			workflowMetricsSLADefinitionVersion =
				workflowMetricsSLADefinitionVersions.get(0);

		Assert.assertEquals(
			"Abc", workflowMetricsSLADefinitionVersion.getName());
	}

	@Test
	public void testGetWorkflowMetricsSLADefinitionVersions3()
		throws Exception {

		WorkflowMetricsSLADefinitionVersion
			workflowMetricsSLADefinitionVersion1 =
				_addWorkflowMetricsSLADefinitionVersion(
					"Abc", 1, WorkflowConstants.STATUS_APPROVED, 1);

		WorkflowMetricsSLADefinitionVersion
			workflowMetricsSLADefinitionVersion2 =
				_addWorkflowMetricsSLADefinitionVersion(
					"Cdf", 1, WorkflowConstants.STATUS_APPROVED, 2);

		List<WorkflowMetricsSLADefinitionVersion>
			workflowMetricsSLADefinitionVersions =
				WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
					getWorkflowMetricsSLADefinitionVersions(
						_company.getCompanyId(), new Date(),
						WorkflowConstants.STATUS_APPROVED);

		Assert.assertEquals(
			workflowMetricsSLADefinitionVersions.toString(), 2,
			workflowMetricsSLADefinitionVersions.size());
		Assert.assertTrue(
			workflowMetricsSLADefinitionVersions.contains(
				workflowMetricsSLADefinitionVersion1));
		Assert.assertTrue(
			workflowMetricsSLADefinitionVersions.contains(
				workflowMetricsSLADefinitionVersion2));
	}

	private WorkflowMetricsSLADefinitionVersion
			_addWorkflowMetricsSLADefinitionVersion(
				String name, long processId, int status,
				long workflowMetricsSLADefinitionId)
		throws Exception {

		WorkflowMetricsSLADefinitionVersion
			workflowMetricsSLADefinitionVersion =
				WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
					createWorkflowMetricsSLADefinitionVersion(
						CounterLocalServiceUtil.increment());

		Date now = new Date();

		workflowMetricsSLADefinitionVersion.setCreateDate(now);
		workflowMetricsSLADefinitionVersion.setModifiedDate(now);

		workflowMetricsSLADefinitionVersion.setCompanyId(
			_company.getCompanyId());
		workflowMetricsSLADefinitionVersion.setCreateDate(now);
		workflowMetricsSLADefinitionVersion.setModifiedDate(now);
		workflowMetricsSLADefinitionVersion.setName(name);
		workflowMetricsSLADefinitionVersion.setProcessId(processId);
		workflowMetricsSLADefinitionVersion.setWorkflowMetricsSLADefinitionId(
			workflowMetricsSLADefinitionId);
		workflowMetricsSLADefinitionVersion.setStatus(status);

		workflowMetricsSLADefinitionVersion =
			WorkflowMetricsSLADefinitionVersionLocalServiceUtil.
				addWorkflowMetricsSLADefinitionVersion(
					workflowMetricsSLADefinitionVersion);

		_workflowMetricsSLADefinitionVersions.add(
			workflowMetricsSLADefinitionVersion);

		return workflowMetricsSLADefinitionVersion;
	}

	@DeleteAfterTestRun
	private Company _company;

	@DeleteAfterTestRun
	private final List<WorkflowMetricsSLADefinitionVersion>
		_workflowMetricsSLADefinitionVersions = new ArrayList<>();

}