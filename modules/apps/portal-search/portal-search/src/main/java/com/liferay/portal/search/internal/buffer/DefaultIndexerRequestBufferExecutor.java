/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.buffer;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.search.buffer.IndexerRequest;
import com.liferay.portal.search.buffer.IndexerRequestBuffer;
import com.liferay.portal.search.buffer.IndexerRequestBufferExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "buffered.execution.mode=DEFAULT",
	service = IndexerRequestBufferExecutor.class
)
public class DefaultIndexerRequestBufferExecutor
	extends BaseIndexerRequestBufferExecutor
	implements IndexerRequestBufferExecutor {

	@Override
	public void execute(
		IndexerRequestBuffer indexerRequestBuffer, int numRequests) {

		Set<String> searchEngineIds = new HashSet<>();

		Collection<IndexerRequest> completedIndexerRequests = new ArrayList<>();

		if (_log.isDebugEnabled()) {
			Collection<IndexerRequest> indexerRequests =
				indexerRequestBuffer.getIndexerRequests();

			_log.debug(
				StringBundler.concat(
					"Indexer request buffer size ", indexerRequests.size(),
					" to execute ", numRequests, " requests"));
		}

		int i = 0;

		for (IndexerRequest indexerRequest :
				indexerRequestBuffer.getIndexerRequests()) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Executing indexer request ", i++, ": ",
						indexerRequest));
			}

			executeIndexerRequest(searchEngineIds, indexerRequest);

			completedIndexerRequests.add(indexerRequest);

			if (completedIndexerRequests.size() == numRequests) {
				break;
			}
		}

		for (IndexerRequest indexerRequest : completedIndexerRequests) {
			indexerRequestBuffer.remove(indexerRequest);
		}

		if (!BufferOverflowThreadLocal.isOverflowMode()) {
			commit(searchEngineIds);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_indexWriterHelperServiceTracker = new ServiceTracker<>(
			bundleContext, IndexWriterHelper.class, null);

		_indexWriterHelperServiceTracker.open();
	}

	@Deactivate
	protected void deactivate() {
		_indexWriterHelperServiceTracker.close();
	}

	@Override
	protected IndexWriterHelper getIndexWriterHelper() {
		return _indexWriterHelperServiceTracker.getService();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultIndexerRequestBufferExecutor.class);

	private ServiceTracker<IndexWriterHelper, IndexWriterHelper>
		_indexWriterHelperServiceTracker;

}