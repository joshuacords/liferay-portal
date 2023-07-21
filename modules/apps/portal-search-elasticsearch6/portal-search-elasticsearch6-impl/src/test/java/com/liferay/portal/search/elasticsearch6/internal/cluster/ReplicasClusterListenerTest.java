/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.kernel.cluster.ClusterEvent;
import com.liferay.portal.kernel.test.CaptureHandler;
import com.liferay.portal.kernel.test.JDKLoggerTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class ReplicasClusterListenerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		setEmbeddedCluster(true);
		setMasterExecutor(true);

		Mockito.when(
			_replicasClusterContext.getClusterSize()
		).thenReturn(
			_REPLICAS + 1
		);

		Mockito.when(
			_replicasClusterContext.getReplicasManager()
		).thenReturn(
			_replicasManager
		);

		Mockito.when(
			_replicasClusterContext.getTargetIndexNames()
		).thenReturn(
			_INDICES
		);

		_replicasClusterListener = new ReplicasClusterListener(
			_replicasClusterContext);
	}

	@Test
	public void testAHappyDay() {
		processClusterEvent();
		assertReplicasChanged();
	}

	@Test
	public void testLiferayClusterReportsEmpty() {
		Mockito.when(
			_replicasClusterContext.getClusterSize()
		).thenReturn(
			0
		);

		processClusterEvent();

		Mockito.verify(
			_replicasManager
		).updateNumberOfReplicas(
			0, _INDICES
		);
	}

	@Test
	public void testMasterTokenAcquired() {
		masterTokenAcquired();

		assertReplicasChanged();
	}

	@Test
	public void testMasterTokenReleased() {
		masterTokenReleased();

		assertReplicasUnchanged();
	}

	@Test
	public void testNonmasterLiferayNodeDoesNothing() {
		setMasterExecutor(false);

		processClusterEvent();

		assertReplicasUnchanged();
	}

	@Test
	public void testRemoteElasticsearchClusterIsLeftAlone() {
		setEmbeddedCluster(false);

		processClusterEvent();

		assertReplicasUnchanged();
	}

	@Test
	public void testResilientToUpdateFailures() {
		Throwable throwable = new RuntimeException();

		Mockito.doThrow(
			throwable
		).when(
			_replicasManager
		).updateNumberOfReplicas(
			Mockito.anyInt(), (String[])Mockito.anyVararg()
		);

		try (CaptureHandler captureHandler =
				JDKLoggerTestUtil.configureJDKLogger(
					ReplicasClusterListener.class.getName(), Level.WARNING)) {

			masterTokenAcquired();

			List<LogRecord> logRecords = captureHandler.getLogRecords();

			Assert.assertEquals(logRecords.toString(), 1, logRecords.size());

			LogRecord logRecord = logRecords.get(0);

			Assert.assertEquals(
				"Unable to update number of replicas", logRecord.getMessage());
			Assert.assertSame(throwable, logRecord.getThrown());
		}
	}

	protected void assertReplicasChanged() {
		Mockito.verify(
			_replicasManager
		).updateNumberOfReplicas(
			_REPLICAS, _INDICES
		);
	}

	protected void assertReplicasUnchanged() {
		Mockito.verify(
			_replicasManager, Mockito.never()
		).updateNumberOfReplicas(
			Mockito.anyInt(), (String[])Mockito.anyVararg()
		);
	}

	protected void masterTokenAcquired() {
		_replicasClusterListener.masterTokenAcquired();
	}

	protected void masterTokenReleased() {
		_replicasClusterListener.masterTokenReleased();
	}

	protected void processClusterEvent() {
		_replicasClusterListener.processClusterEvent(ClusterEvent.join());
	}

	protected void setEmbeddedCluster(boolean value) {
		Mockito.when(
			_replicasClusterContext.isEmbeddedOperationMode()
		).thenReturn(
			value
		);
	}

	protected void setMasterExecutor(boolean value) {
		Mockito.when(
			_replicasClusterContext.isMaster()
		).thenReturn(
			value
		);
	}

	private static final String[] _INDICES = {
		RandomTestUtil.randomString(), RandomTestUtil.randomString()
	};

	private static final int _REPLICAS = RandomTestUtil.randomInt() - 1;

	@Mock
	private ReplicasClusterContext _replicasClusterContext;

	private ReplicasClusterListener _replicasClusterListener;

	@Mock
	private ReplicasManager _replicasManager;

}