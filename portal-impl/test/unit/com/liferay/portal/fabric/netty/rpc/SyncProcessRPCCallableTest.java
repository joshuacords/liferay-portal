/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc;

import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SyncProcessRPCCallableTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testCallWithException() throws Throwable {
		final RuntimeException runtimeException = new RuntimeException();

		RPCCallable<String> rpcCallable = new SyncProcessRPCCallable<String>(
			new ProcessCallable<String>() {

				@Override
				public String call() {
					throw runtimeException;
				}

			});

		NoticeableFuture<String> noticeableFuture = rpcCallable.call();

		try {
			noticeableFuture.get();

			Assert.fail();
		}
		catch (ExecutionException executionException) {
			Assert.assertEquals(
				runtimeException, executionException.getCause());
		}
	}

	@Test
	public void testCallWithResult() throws Throwable {
		final String result = "result";

		RPCCallable<String> rpcCallable = new SyncProcessRPCCallable<String>(
			new ProcessCallable<String>() {

				@Override
				public String call() {
					return result;
				}

			});

		NoticeableFuture<String> noticeableFuture = rpcCallable.call();

		Assert.assertEquals(result, noticeableFuture.get());
	}

}