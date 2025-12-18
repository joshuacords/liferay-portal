/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.background.task;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.search.background.task.ReindexBackgroundTaskConstants;
import com.liferay.portal.kernel.search.background.task.ReindexStatusMessageSender;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.search.spi.reindexer.IndexReindexer;
import com.liferay.portal.search.spi.reindexer.IndexReindexerRegistry;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class ReindexIndexReindexerBackgroundTaskExecutorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
        public void setUp() {
                _reindexIndexReindexerBackgroundTaskExecutor =
                        new ReindexIndexReindexerBackgroundTaskExecutor();

                Collection<IndexReindexer> indexReindexers = Arrays.asList(
                        _indexReindexer1, _indexReindexer2);

		Mockito.when(
			_indexReindexerRegistry.getIndexReindexers()
		).thenReturn(
			indexReindexers
		);

		Mockito.when(
			_indexReindexerRegistry.getIndexReindexer(Mockito.anyString())
		).thenReturn(
			_indexReindexer1
		);

		ReflectionTestUtil.setFieldValue(
			_reindexIndexReindexerBackgroundTaskExecutor,
			"_indexReindexerRegistry", _indexReindexerRegistry);
		ReflectionTestUtil.setFieldValue(
			_reindexIndexReindexerBackgroundTaskExecutor,
			"_reindexStatusMessageSender", _reindexStatusMessageSender);
	}

        @Test
        public void testBackgroundTaskCompanyIdsSerialization() throws Exception {
                long[] companyIds = {0L, 42184964271267L};

                Map<String, Object> taskContextMap = HashMapBuilder.<String, Object>put(
                        ReindexBackgroundTaskConstants.CLASS_NAME, "className"
                ).put(
                        ReindexBackgroundTaskConstants.COMPANY_IDS, companyIds
                ).put(
                        ReindexBackgroundTaskConstants.EXECUTION_MODE, _EXECUTION_MODE
                ).build();

                @SuppressWarnings("unchecked")
                Map<String, Object> deserializedTaskContextMap =
                        (Map<String, Object>)JSONFactoryUtil.looseDeserialize(
                                JSONFactoryUtil.looseSerialize(taskContextMap));

                BackgroundTask backgroundTask = Mockito.mock(BackgroundTask.class);

                Mockito.when(
                        backgroundTask.getTaskContextMap()
                ).thenReturn(
                        (Map<String, Serializable>)(Map)deserializedTaskContextMap
                );

                AtomicInteger atomicInteger = new AtomicInteger();

                Mockito.doAnswer(
                        invocation -> {
                                Assert.assertEquals(
                                        companyIds[atomicInteger.getAndIncrement()],
                                        invocation.getArgument(0));

                                return null;
                        }
                ).when(
                        _indexReindexer1
                ).reindex(
                        Mockito.anyLong(), Mockito.anyString()
                );

                _reindexIndexReindexerBackgroundTaskExecutor.execute(
                        backgroundTask);

                Assert.assertEquals(companyIds.length, atomicInteger.get());
        }

        @Test
        public void testDatabasePartitioning() throws Exception {
                Mockito.doAnswer(
                        invocation -> {
				Assert.assertEquals(
					invocation.getArgument(0),
					CompanyThreadLocal.getCompanyId());

				return null;
			}
		).when(
			_indexReindexer1
                ).reindex(
                        Mockito.anyLong(), Mockito.anyString()
                );

                _reindexIndexReindexerBackgroundTaskExecutor.reindex(
                        "", _COMPANY_IDS, _EXECUTION_MODE);
        }

	@Test
	public void testReindexAllClasses() throws Exception {
                _reindexIndexReindexerBackgroundTaskExecutor.reindex(
                        "", _COMPANY_IDS, _EXECUTION_MODE);

                _verifyIndexerCalledOnceForEachCompany(_indexReindexer1);
                _verifyIndexerCalledOnceForEachCompany(_indexReindexer2);
        }

	@Test
	public void testReindexSingleClass() throws Exception {
                _reindexIndexReindexerBackgroundTaskExecutor.reindex(
                        "className", _COMPANY_IDS, _EXECUTION_MODE);

		_verifyIndexerCalledOnceForEachCompany(_indexReindexer1);

		Mockito.verify(
			_indexReindexer2, Mockito.never()
                ).reindex(
                        _COMPANY_IDS_ARRAY[0], _EXECUTION_MODE
                );

		Mockito.verify(
			_indexReindexer2, Mockito.never()
                ).reindex(
                        _COMPANY_IDS_ARRAY[1], _EXECUTION_MODE
                );
        }

	private void _verifyIndexerCalledOnceForEachCompany(
			IndexReindexer indexReindexer)
		throws Exception {

                for (long companyId : _COMPANY_IDS) {
                        Mockito.verify(
                                indexReindexer
                        ).reindex(
                                companyId, _EXECUTION_MODE
			);
		}
	}

        private static final long[] _COMPANY_IDS_ARRAY = {11111L, 22222L};

        private static final List<Long> _COMPANY_IDS = Arrays.stream(
                _COMPANY_IDS_ARRAY
        ).boxed(
        ).collect(
                Collectors.toList()
        );

        private static final String _EXECUTION_MODE = "full";

	private final IndexReindexer _indexReindexer1 = Mockito.mock(
		IndexReindexer.class);
	private final IndexReindexer _indexReindexer2 = Mockito.mock(
		IndexReindexer.class);
	private final IndexReindexerRegistry _indexReindexerRegistry = Mockito.mock(
		IndexReindexerRegistry.class);
	private ReindexIndexReindexerBackgroundTaskExecutor
		_reindexIndexReindexerBackgroundTaskExecutor;
	private final ReindexStatusMessageSender _reindexStatusMessageSender =
		Mockito.mock(ReindexStatusMessageSender.class);

}