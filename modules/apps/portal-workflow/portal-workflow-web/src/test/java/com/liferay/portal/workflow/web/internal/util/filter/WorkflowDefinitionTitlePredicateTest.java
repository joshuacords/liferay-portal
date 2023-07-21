/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.web.internal.util.filter;

import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leonardo Barros
 */
public class WorkflowDefinitionTitlePredicateTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testFilterWithoutSpace1() {
		WorkflowDefinitionTitlePredicate predicate =
			new WorkflowDefinitionTitlePredicate("Single");

		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
			null, "Single Approver");

		boolean result = predicate.test(workflowDefinition);

		Assert.assertTrue(result);
	}

	@Test
	public void testFilterWithoutSpace2() {
		WorkflowDefinitionTitlePredicate predicate =
			new WorkflowDefinitionTitlePredicate("Appr");

		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
			null, "Single Approver");

		boolean result = predicate.test(workflowDefinition);

		Assert.assertTrue(result);
	}

	@Test
	public void testFilterWithoutSpace3() {
		WorkflowDefinitionTitlePredicate predicate =
			new WorkflowDefinitionTitlePredicate("Approver");

		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
			null, "A Different Definition");

		boolean result = predicate.test(workflowDefinition);

		Assert.assertFalse(result);
	}

	@Test
	public void testFilterWithSpace1() {
		WorkflowDefinitionTitlePredicate predicate =
			new WorkflowDefinitionTitlePredicate("Single Approver");

		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
			null, "Single Approver Definition");

		boolean result = predicate.test(workflowDefinition);

		Assert.assertTrue(result);
	}

	@Test
	public void testFilterWithSpace2() {
		WorkflowDefinitionTitlePredicate predicate =
			new WorkflowDefinitionTitlePredicate("Single Approver");

		WorkflowDefinition workflowDefinition = new WorkflowDefinitionImpl(
			null, "A Different Definition");

		boolean result = predicate.test(workflowDefinition);

		Assert.assertFalse(result);
	}

}