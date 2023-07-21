/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal.search.engine.adapter.document;

import com.liferay.portal.search.engine.adapter.document.BulkDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.BulkDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.DeleteByQueryDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.DeleteDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.DeleteDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.DocumentRequestExecutor;
import com.liferay.portal.search.engine.adapter.document.GetDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.GetDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.IndexDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateByQueryDocumentResponse;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(
	immediate = true, property = "search.engine.impl=Solr",
	service = DocumentRequestExecutor.class
)
public class SolrDocumentRequestExecutor implements DocumentRequestExecutor {

	@Override
	public BulkDocumentResponse executeBulkDocumentRequest(
		BulkDocumentRequest bulkDocumentRequest) {

		return _bulkDocumentRequestExecutor.execute(bulkDocumentRequest);
	}

	@Override
	public DeleteByQueryDocumentResponse executeDocumentRequest(
		DeleteByQueryDocumentRequest deleteByQueryDocumentRequest) {

		return _deleteByQueryDocumentRequestExecutor.execute(
			deleteByQueryDocumentRequest);
	}

	@Override
	public DeleteDocumentResponse executeDocumentRequest(
		DeleteDocumentRequest deleteDocumentRequest) {

		return _deleteDocumentRequestExecutor.execute(deleteDocumentRequest);
	}

	@Override
	public GetDocumentResponse executeDocumentRequest(
		GetDocumentRequest getDocumentRequest) {

		return _getDocumentRequestExecutor.execute(getDocumentRequest);
	}

	@Override
	public IndexDocumentResponse executeDocumentRequest(
		IndexDocumentRequest indexDocumentRequest) {

		return _indexDocumentRequestExecutor.execute(indexDocumentRequest);
	}

	@Override
	public UpdateByQueryDocumentResponse executeDocumentRequest(
		UpdateByQueryDocumentRequest updateByQueryDocumentRequest) {

		return _updateByQueryDocumentRequestExecutor.execute(
			updateByQueryDocumentRequest);
	}

	@Override
	public UpdateDocumentResponse executeDocumentRequest(
		UpdateDocumentRequest updateDocumentRequest) {

		return _updateDocumentRequestExecutor.execute(updateDocumentRequest);
	}

	@Reference(unbind = "-")
	protected void setBulkDocumentRequestExecutor(
		BulkDocumentRequestExecutor bulkDocumentRequestExecutor) {

		_bulkDocumentRequestExecutor = bulkDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setDeleteByQueryDocumentRequestExecutor(
		DeleteByQueryDocumentRequestExecutor
			deleteByQueryDocumentRequestExecutor) {

		_deleteByQueryDocumentRequestExecutor =
			deleteByQueryDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setDeleteDocumentRequestExecutor(
		DeleteDocumentRequestExecutor deleteDocumentRequestExecutor) {

		_deleteDocumentRequestExecutor = deleteDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setGetDocumentRequestExecutor(
		GetDocumentRequestExecutor getDocumentRequestExecutor) {

		_getDocumentRequestExecutor = getDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setIndexDocumentRequestExecutor(
		IndexDocumentRequestExecutor indexDocumentRequestExecutor) {

		_indexDocumentRequestExecutor = indexDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setUpdateByQueryDocumentRequestExecutor(
		UpdateByQueryDocumentRequestExecutor
			updateByQueryDocumentRequestExecutor) {

		_updateByQueryDocumentRequestExecutor =
			updateByQueryDocumentRequestExecutor;
	}

	@Reference(unbind = "-")
	protected void setUpdateDocumentRequestExecutor(
		UpdateDocumentRequestExecutor updateDocumentRequestExecutor) {

		_updateDocumentRequestExecutor = updateDocumentRequestExecutor;
	}

	private BulkDocumentRequestExecutor _bulkDocumentRequestExecutor;
	private DeleteByQueryDocumentRequestExecutor
		_deleteByQueryDocumentRequestExecutor;
	private DeleteDocumentRequestExecutor _deleteDocumentRequestExecutor;
	private GetDocumentRequestExecutor _getDocumentRequestExecutor;
	private IndexDocumentRequestExecutor _indexDocumentRequestExecutor;
	private UpdateByQueryDocumentRequestExecutor
		_updateByQueryDocumentRequestExecutor;
	private UpdateDocumentRequestExecutor _updateDocumentRequestExecutor;

}