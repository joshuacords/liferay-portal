/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.cluster;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.engine.adapter.cluster.ClusterRequestExecutor;
import com.liferay.portal.search.engine.adapter.cluster.HealthClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.StateClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.StatsClusterRequest;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Dylan Rebelak
 */
public class ElasticsearchClusterRequestExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_clusterRequestExecutor = new ElasticsearchClusterRequestExecutor() {
			{
				setHealthClusterRequestExecutor(_healthClusterRequestExecutor);
				setStateClusterRequestExecutor(_stateClusterRequestExecutor);
				setStatsClusterRequestExecutor(_statsClusterRequestExecutor);
			}
		};
	}

	@Test
	public void testExecuteHealthClusterRequest() {
		HealthClusterRequest healthClusterRequest = new HealthClusterRequest(
			new String[] {RandomTestUtil.randomString()});

		_clusterRequestExecutor.execute(healthClusterRequest);

		Mockito.verify(
			_healthClusterRequestExecutor
		).execute(
			healthClusterRequest
		);
	}

	@Test
	public void testExecuteStateClusterRequest() {
		StateClusterRequest stateClusterRequest = new StateClusterRequest(
			new String[] {RandomTestUtil.randomString()});

		_clusterRequestExecutor.execute(stateClusterRequest);

		Mockito.verify(
			_stateClusterRequestExecutor
		).execute(
			stateClusterRequest
		);
	}

	@Test
	public void testExecuteStatsClusterRequest() {
		StatsClusterRequest statsClusterRequest = new StatsClusterRequest(
			new String[] {RandomTestUtil.randomString()});

		_clusterRequestExecutor.execute(statsClusterRequest);

		Mockito.verify(
			_statsClusterRequestExecutor
		).execute(
			statsClusterRequest
		);
	}

	private ClusterRequestExecutor _clusterRequestExecutor;

	@Mock
	private HealthClusterRequestExecutor _healthClusterRequestExecutor;

	@Mock
	private StateClusterRequestExecutor _stateClusterRequestExecutor;

	@Mock
	private StatsClusterRequestExecutor _statsClusterRequestExecutor;

}