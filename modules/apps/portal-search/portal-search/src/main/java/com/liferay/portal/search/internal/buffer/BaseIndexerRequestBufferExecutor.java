/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.buffer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.search.buffer.IndexerRequest;
import com.liferay.portal.search.buffer.IndexerRequestBuffer;
import com.liferay.portal.search.buffer.IndexerRequestBufferExecutor;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class BaseIndexerRequestBufferExecutor
	implements IndexerRequestBufferExecutor {

	@Override
	public void execute(IndexerRequestBuffer indexerRequestBuffer) {
		execute(indexerRequestBuffer, indexerRequestBuffer.size());
	}

	protected void commit(Set<String> searchEngineIds) {
		IndexWriterHelper indexWriterHelper = getIndexWriterHelper();

		if (indexWriterHelper == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Index writer helper is null");
			}

			return;
		}

		for (String searchEngineId : searchEngineIds) {
			try {
				indexWriterHelper.commit(searchEngineId);
			}
			catch (SearchException searchException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to commit search engine", searchException);
				}
			}
		}
	}

	protected void executeIndexerRequest(
		Set<String> searchEngineIds, IndexerRequest indexerRequest) {

		try {
			indexerRequest.execute();

			searchEngineIds.add(indexerRequest.getSearchEngineId());
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to execute index request " + indexerRequest,
					exception);
			}
		}
	}

	protected abstract IndexWriterHelper getIndexWriterHelper();

	private static final Log _log = LogFactoryUtil.getLog(
		BaseIndexerRequestBufferExecutor.class);

}