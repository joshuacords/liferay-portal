/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent.selectors;

import com.liferay.portal.fabric.ExceptionProcessCallable;
import com.liferay.portal.fabric.ReturnProcessCallable;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class ProcessCallableAssignmentFabricAgentSelectorTest
	extends SystemPropertiesFilterFabricAgentSelectorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Override
	@Test
	public void testSelect() {
		FabricAgentSelector fabricAgentSelector =
			new ProcessCallableAssignmentFabricAgentSelector();

		FabricAgent fabricAgent1 = createFabricAgent(
			Collections.<String, String>singletonMap(
				ProcessCallableAssignmentFabricAgentSelector.
					PROCESS_CALLABLE_ASSIGNMENT_EXPRESSION_KEY,
				".*ReturnProcessCallable"));
		FabricAgent fabricAgent2 = createFabricAgent(
			Collections.<String, String>emptyMap());

		Collection<FabricAgent> fabricAgents = fabricAgentSelector.select(
			new ArrayList<>(Arrays.asList(fabricAgent1, fabricAgent2)),
			new ReturnProcessCallable<>(null));

		Assert.assertEquals(fabricAgents.toString(), 1, fabricAgents.size());

		Iterator<FabricAgent> iterator = fabricAgents.iterator();

		Assert.assertSame(fabricAgent1, iterator.next());

		fabricAgents = fabricAgentSelector.select(
			new ArrayList<>(Arrays.asList(fabricAgent1)),
			new ExceptionProcessCallable(null));

		Assert.assertTrue(fabricAgents.toString(), fabricAgents.isEmpty());
	}

}