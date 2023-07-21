/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.model;

import java.io.Serializable;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class QueuedMessageSoap implements Serializable {

	public static QueuedMessageSoap toSoapModel(QueuedMessage model) {
		QueuedMessageSoap soapModel = new QueuedMessageSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setQueuedMessageId(model.getQueuedMessageId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setMessageBrokerClassName(model.getMessageBrokerClassName());
		soapModel.setTopic(model.getTopic());
		soapModel.setMessageObject(model.getMessageObject());

		return soapModel;
	}

	public static QueuedMessageSoap[] toSoapModels(QueuedMessage[] models) {
		QueuedMessageSoap[] soapModels = new QueuedMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static QueuedMessageSoap[][] toSoapModels(QueuedMessage[][] models) {
		QueuedMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new QueuedMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new QueuedMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static QueuedMessageSoap[] toSoapModels(List<QueuedMessage> models) {
		List<QueuedMessageSoap> soapModels = new ArrayList<QueuedMessageSoap>(
			models.size());

		for (QueuedMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new QueuedMessageSoap[soapModels.size()]);
	}

	public QueuedMessageSoap() {
	}

	public long getPrimaryKey() {
		return _queuedMessageId;
	}

	public void setPrimaryKey(long pk) {
		setQueuedMessageId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getQueuedMessageId() {
		return _queuedMessageId;
	}

	public void setQueuedMessageId(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getMessageBrokerClassName() {
		return _messageBrokerClassName;
	}

	public void setMessageBrokerClassName(String messageBrokerClassName) {
		_messageBrokerClassName = messageBrokerClassName;
	}

	public String getTopic() {
		return _topic;
	}

	public void setTopic(String topic) {
		_topic = topic;
	}

	public Blob getMessageObject() {
		return _messageObject;
	}

	public void setMessageObject(Blob messageObject) {
		_messageObject = messageObject;
	}

	private long _mvccVersion;
	private long _queuedMessageId;
	private Date _createDate;
	private String _messageBrokerClassName;
	private String _topic;
	private Blob _messageObject;

}