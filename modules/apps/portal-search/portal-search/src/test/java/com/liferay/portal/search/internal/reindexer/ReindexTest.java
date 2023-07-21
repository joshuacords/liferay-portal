/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.reindexer;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class ReindexTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_reindex = createReindex();
	}

	@Test
	public void testMustRepeatModelChangedOutsideAfterIndexedInside() {
		_reindex.setNonbulkIndexing(false);
		_reindex.setSynchronousExecution(false);

		doTestMustRepeatModelChangedOutsideAfterIndexedInside();
	}

	@Test
	public void testMustRepeatModelChangedOutsideAfterIndexedInsideNonbulk() {
		_reindex.setNonbulkIndexing(true);
		_reindex.setSynchronousExecution(false);

		doTestMustRepeatModelChangedOutsideAfterIndexedInside();
	}

	@Test
	public void testSynchronousIndexingForIntegrationTests() {
		_reindex.setNonbulkIndexing(false);
		_reindex.setSynchronousExecution(true);

		doTestSynchronousIndexingForIntegrationTests();
	}

	@Test
	public void testSynchronousIndexingForIntegrationTestsNonbulk() {
		_reindex.setNonbulkIndexing(true);
		_reindex.setSynchronousExecution(true);

		doTestSynchronousIndexingForIntegrationTests();
	}

	protected void acquire(Semaphore semaphore, int permits) {
		try {
			if (!semaphore.tryAcquire(permits, 2L, TimeUnit.SECONDS)) {
				Assert.assertEquals(
					"Permits", permits, semaphore.availablePermits());
			}
		}
		catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
	}

	protected void addReindexEndListener(
		Reindex.ReindexEndListener reindexEndListener) {

		_reindex.addReindexEndListener(reindexEndListener);
	}

	protected void assertIndexedValues(List<Integer> list) {
		Assert.assertEquals(list, _indexedValues);
	}

	protected Reindex createReindex() {
		Reindex reindex = new Reindex(
			null, null, Executors.newSingleThreadExecutor(),
			new ReindexRequestsHolder());

		reindex.setCustomReindex(this::_reindex);
		reindex.setCustomReindexBulk(this::_reindexBulk);

		return reindex;
	}

	protected void doTestMustRepeatModelChangedOutsideAfterIndexedInside() {
		Semaphore semaphore1 = new Semaphore(0);

		Semaphore semaphore2 = new Semaphore(0);

		AtomicBoolean atomicBoolean = new AtomicBoolean();

		addReindexEndListener(
			() -> {
				semaphore1.release(1);

				if (!atomicBoolean.getAndSet(true)) {
					acquire(semaphore2, 1);
				}
			});

		reindexAddingValue(101);

		acquire(semaphore1, 1);

		assertIndexedValues(Arrays.asList(101));

		reindexAddingValue(202);
		reindexAddingValue(303);
		reindexAddingValue(404);
		reindexAddingValue(505);

		assertIndexedValues(Arrays.asList(101));

		semaphore2.release(1);

		int multipleRequestsForSameClassPKCollapseIntoOne = 1;

		acquire(semaphore1, multipleRequestsForSameClassPKCollapseIntoOne);

		assertIndexedValues(Arrays.asList(101, 202, 303, 404, 505));
	}

	protected void doTestSynchronousIndexingForIntegrationTests() {
		reindexAddingValue(101);

		assertIndexedValues(Arrays.asList(101));

		reindexAddingValue(202);
		reindexAddingValue(303);
		reindexAddingValue(404);
		reindexAddingValue(505);

		assertIndexedValues(Arrays.asList(101, 202, 303, 404, 505));
	}

	protected void reindexAddingValue(int group) {
		_addedValues.add(group);

		_reindex.reindex(_CLASS_NAME, _CLASS_PK);
	}

	private void _reindex(long classPK) {
		_transferAccumulatedToIndexed();
	}

	private void _reindexBulk(Collection<Long> classPKs) {
		_transferAccumulatedToIndexed();
	}

	private void _transferAccumulatedToIndexed() {
		_indexedValues.clear();

		_indexedValues.addAll(_addedValues);
	}

	private static final String _CLASS_NAME = RandomTestUtil.randomString();

	private static final long _CLASS_PK = RandomTestUtil.randomLong();

	private final List<Integer> _addedValues = new ArrayList<>();
	private final List<Integer> _indexedValues = new ArrayList<>();
	private Reindex _reindex;

}