/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index.instant;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexDefinitionsHolderImpl;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexSynchronizationPortalInitializedListener;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexSynchronizer;
import com.liferay.portal.search.elasticsearch7.internal.index.IndexSynchronizerImpl;
import com.liferay.portal.search.elasticsearch7.internal.test.util.microcontainer.Microcontainer;
import com.liferay.portal.search.elasticsearch7.internal.test.util.microcontainer.MicrocontainerImpl;
import com.liferay.portal.search.elasticsearch7.spi.index.IndexRegistrar;
import com.liferay.portal.search.spi.index.IndexDefinition;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;

import org.elasticsearch.action.admin.indices.get.GetIndexAction;
import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class InstantIndexesTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@BeforeClass
	public static void setUpClass() throws Exception {
		_elasticsearchFixture = new ElasticsearchFixture(
			InstantIndexesTest.class.getSimpleName());

		_elasticsearchFixture.setUp();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_elasticsearchFixture.tearDown();
	}

	@Before
	public void setUp() throws Exception {
		IndexDefinitionsHolderImpl indexDefinitionsHolderImpl =
			new IndexDefinitionsHolderImpl();

		IndexSynchronizerImpl indexSynchronizerImpl = createIndexSynchronizer(
			_elasticsearchFixture, indexDefinitionsHolderImpl);

		IndexSynchronizationPortalInitializedListener
			indexSynchronizationPortalInitializedListener =
				createIndexSynchronizationPortalInitializedListener(
					indexSynchronizerImpl);

		Microcontainer microcontainer = new MicrocontainerImpl();

		microcontainer.wire(
			IndexDefinition.class,
			indexDefinitionsHolderImpl::addIndexDefinition,
			indexSynchronizationPortalInitializedListener::addIndexDefinition);

		microcontainer.wire(
			IndexRegistrar.class, indexSynchronizerImpl::addIndexRegistrar,
			indexSynchronizationPortalInitializedListener::addIndexRegistrar);

		_eventsIndexDefinition = new EventsIndexDefinition();
		_indexSynchronizationPortalInitializedListener =
			indexSynchronizationPortalInitializedListener;
		_instancesAndProcessesIndexRegistrar =
			new InstancesAndProcessesIndexRegistrar();
		_microcontainer = microcontainer;
		_tasksIndexDefinition = new TasksIndexDefinition();
	}

	@Test
	public void testAutomaticIndexCreation() throws Exception {
		deployComponents(
			_eventsIndexDefinition,
			_indexSynchronizationPortalInitializedListener,
			_instancesAndProcessesIndexRegistrar, _tasksIndexDefinition);

		startPortal();

		assertIndexesExist(
			EventsIndexDefinition.INDEX_NAME_WORKFLOW_EVENTS,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_INSTANCES,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_PROCESSES,
			TasksIndexDefinition.INDEX_NAME_WORKFLOW_TASKS);
	}

	@Test
	public void testRuntimeIndexCreation() throws Exception {
		deployComponents(
			_indexSynchronizationPortalInitializedListener,
			_instancesAndProcessesIndexRegistrar, _tasksIndexDefinition);

		startPortal();

		assertIndexesExist(
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_INSTANCES,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_PROCESSES,
			TasksIndexDefinition.INDEX_NAME_WORKFLOW_TASKS);

		deployComponents(_eventsIndexDefinition);

		assertIndexesExist(
			EventsIndexDefinition.INDEX_NAME_WORKFLOW_EVENTS,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_INSTANCES,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_PROCESSES,
			TasksIndexDefinition.INDEX_NAME_WORKFLOW_TASKS);
	}

	@Test
	public void testStartTwiceIndexCreation() throws Exception {
		deployComponents(
			_eventsIndexDefinition,
			_indexSynchronizationPortalInitializedListener,
			_instancesAndProcessesIndexRegistrar, _tasksIndexDefinition);

		startPortal();

		assertIndexesExist(
			EventsIndexDefinition.INDEX_NAME_WORKFLOW_EVENTS,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_INSTANCES,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_PROCESSES,
			TasksIndexDefinition.INDEX_NAME_WORKFLOW_TASKS);

		startPortal();

		assertIndexesExist(
			EventsIndexDefinition.INDEX_NAME_WORKFLOW_EVENTS,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_INSTANCES,
			InstancesAndProcessesIndexRegistrar.INDEX_NAME_WORKFLOW_PROCESSES,
			TasksIndexDefinition.INDEX_NAME_WORKFLOW_TASKS);
	}

	protected static IndexSynchronizationPortalInitializedListener
		createIndexSynchronizationPortalInitializedListener(
			IndexSynchronizer indexSynchronizer) {

		return new IndexSynchronizationPortalInitializedListener() {
			{
				setIndexSynchronizer(indexSynchronizer);
			}
		};
	}

	protected static IndexSynchronizerImpl createIndexSynchronizer(
		ElasticsearchFixture elasticsearchFixture,
		IndexDefinitionsHolderImpl indexDefinitionsHolderImpl) {

		return new IndexSynchronizerImpl() {
			{
				setElasticsearchClientResolver(elasticsearchFixture);
				setIndexDefinitionsHolder(indexDefinitionsHolderImpl);
			}
		};
	}

	protected void assertIndexesExist(String... expectedIndices) {
		GetIndexRequestBuilder getIndexRequestBuilder =
			new GetIndexRequestBuilder(
				_elasticsearchFixture.getClient(), GetIndexAction.INSTANCE);

		GetIndexResponse getIndexResponse = getIndexRequestBuilder.addIndices(
			expectedIndices
		).get();

		String[] actualIndices = getIndexResponse.getIndices();

		Assert.assertEquals(
			Arrays.asList(expectedIndices), Arrays.asList(actualIndices));
	}

	protected void deployComponents(Object... components) {
		_microcontainer.deploy(components);
	}

	protected void startPortal() {
		_microcontainer.start();
	}

	private static ElasticsearchFixture _elasticsearchFixture;

	private EventsIndexDefinition _eventsIndexDefinition;
	private IndexSynchronizationPortalInitializedListener
		_indexSynchronizationPortalInitializedListener;
	private InstancesAndProcessesIndexRegistrar
		_instancesAndProcessesIndexRegistrar;
	private Microcontainer _microcontainer;
	private TasksIndexDefinition _tasksIndexDefinition;

}