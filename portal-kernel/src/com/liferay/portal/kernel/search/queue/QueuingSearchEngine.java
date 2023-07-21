/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.search.queue;

import com.liferay.portal.kernel.search.BaseSearchEngine;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.dummy.DummyIndexSearcher;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.QueuingInvocationHandler;

/**
 * @author Michael C. Han
 */
public class QueuingSearchEngine extends BaseSearchEngine {

	public QueuingSearchEngine(int capacity) {
		_queuingInvocationHandler = new QueuingInvocationHandler(capacity);

		Class<?> clazz = getClass();

		_indexWriter = (IndexWriter)ProxyUtil.newProxyInstance(
			clazz.getClassLoader(), new Class<?>[] {IndexWriter.class},
			_queuingInvocationHandler);
	}

	public void flush() {
		_queuingInvocationHandler.flush();
	}

	@Override
	public IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	@Override
	public IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	public void invokeQueued(IndexWriter indexWriter) throws Exception {
		_queuingInvocationHandler.invokeQueued(indexWriter);
	}

	private final IndexSearcher _indexSearcher = new DummyIndexSearcher();
	private final IndexWriter _indexWriter;
	private final QueuingInvocationHandler _queuingInvocationHandler;

}