/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.web.internal.util.filter;

import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.workflow.web.internal.util.comparator.WorkflowDefinitionModifiedDateComparator;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Adam Brandizzi
 */
public class WorkflowDefinitionModifiedDateComparatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testCompareEqualsAscending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(true);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition2, workflowDefinition1);

		Assert.assertEquals(0, result);
	}

	@Test
	public void testCompareEqualsDescending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(false);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition2, workflowDefinition1);

		Assert.assertEquals(0, result);
	}

	@Test
	public void testCompareNewerOlderAscending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(true);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		calendar.add(Calendar.DATE, 1);

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition2, workflowDefinition1);

		Assert.assertEquals(1, result);
	}

	@Test
	public void testCompareNewerOlderDescending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(false);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		calendar.add(Calendar.DATE, 1);

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition2, workflowDefinition1);

		Assert.assertEquals(-1, result);
	}

	@Test
	public void testCompareOlderNewerAscending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(true);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		calendar.add(Calendar.DATE, 1);

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition1, workflowDefinition2);

		Assert.assertEquals(-1, result);
	}

	@Test
	public void testCompareOlderNewerDescending() {
		WorkflowDefinitionModifiedDateComparator comparator =
			new WorkflowDefinitionModifiedDateComparator(false);

		Calendar calendar = Calendar.getInstance();

		WorkflowDefinition workflowDefinition1 = new WorkflowDefinitionImpl(
			calendar.getTime());

		calendar.add(Calendar.DATE, 1);

		WorkflowDefinition workflowDefinition2 = new WorkflowDefinitionImpl(
			calendar.getTime());

		int result = comparator.compare(
			workflowDefinition1, workflowDefinition2);

		Assert.assertEquals(1, result);
	}

}