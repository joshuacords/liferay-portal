/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the data column in QueuedMessage.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessage
 * @generated
 */
public class QueuedMessageDataBlobModel {

	public QueuedMessageDataBlobModel() {
	}

	public QueuedMessageDataBlobModel(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public QueuedMessageDataBlobModel(long queuedMessageId, Blob dataBlob) {
		_queuedMessageId = queuedMessageId;
		_dataBlob = dataBlob;
	}

	public long getQueuedMessageId() {
		return _queuedMessageId;
	}

	public void setQueuedMessageId(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public Blob getDataBlob() {
		return _dataBlob;
	}

	public void setDataBlob(Blob dataBlob) {
		_dataBlob = dataBlob;
	}

	private long _queuedMessageId;
	private Blob _dataBlob;

}