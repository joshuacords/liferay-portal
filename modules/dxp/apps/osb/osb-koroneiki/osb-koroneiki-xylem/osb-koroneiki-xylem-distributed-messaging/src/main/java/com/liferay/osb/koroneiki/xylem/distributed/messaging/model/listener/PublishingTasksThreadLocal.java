/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.NewTransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionAttribute;
import com.liferay.portal.kernel.transaction.TransactionLifecycleListener;
import com.liferay.portal.kernel.transaction.TransactionLifecycleManager;
import com.liferay.portal.kernel.transaction.TransactionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = PublishingTasksThreadLocal.class)
public class PublishingTasksThreadLocal {

	public static final TransactionLifecycleListener
		TRANSACTION_LIFECYCLE_LISTENER = new NewTransactionLifecycleListener() {

			@Override
			protected void doCommitted(
				TransactionAttribute transactionAttribute,
				TransactionStatus transactionStatus) {

				Map<String, PublishingTask> publishingTasksMap =
					_popPublishingTasksMapList();

				Map<String, PublishingTask> parentPublishingTasksMap =
					_peekPublishingTasksMapList();

				if (parentPublishingTasksMap == null) {
					_flushPublishingTasks(publishingTasksMap);
				}
				else {
					parentPublishingTasksMap.putAll(publishingTasksMap);
				}
			}

			@Override
			protected void doCreated(
				TransactionAttribute transactionAttribute,
				TransactionStatus transactionStatus) {

				_pushPublishingTasksMapList();
			}

			@Override
			protected void doRollbacked(
				TransactionAttribute transactionAttribute,
				TransactionStatus transactionStatus, Throwable throwable) {

				_popPublishingTasksMapList();
			}

		};

	public static void addPublishingTask(
		String key, String topic, Callable<Message> callable) {

		if (isImportInProcess()) {
			return;
		}

		Map<String, PublishingTask> publishingTasksMap =
			_peekPublishingTasksMapList();

		if (publishingTasksMap == null) {
			try {
				callable.call();

				return;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		}

		if (publishingTasksMap ==
				Collections.<String, PublishingTask>emptyMap()) {

			publishingTasksMap = new LinkedHashMap<>();

			_setPublishingTasksMap(publishingTasksMap);
		}

		PublishingTask publishingTask = publishingTasksMap.get(key);

		if (publishingTask == null) {
			publishingTasksMap.put(key, new PublishingTask(topic, callable));
		}
		else {
			publishingTask.setCallable(callable);
		}
	}

	public static boolean isImportInProcess() {
		return _importInProcess.get();
	}

	public static void setImportInProcess(boolean importInProcess) {
		_importInProcess.set(importInProcess);
	}

	@Activate
	public void activate() {
		TransactionLifecycleManager.register(TRANSACTION_LIFECYCLE_LISTENER);
	}

	@Reference(unbind = "-")
	protected void setMessagePublisher(MessagePublisher messagePublisher) {
		_messagePublisher = messagePublisher;
	}

	private static void _flushPublishingTasks(
		Map<String, PublishingTask> publishingTasksMap) {

		for (PublishingTask publishingTask : publishingTasksMap.values()) {
			try {
				Message message = publishingTask.createMessage();

				if (message != null) {
					_messagePublisher.publish(
						publishingTask.getTopic(), message);
				}
			}
			catch (Exception exception) {
				_log.error("Unable to publish message", exception);
			}
		}
	}

	private static Map<String, PublishingTask> _peekPublishingTasksMapList() {
		List<Map<String, PublishingTask>> publishingTasksMapList =
			_publishingTasksMapListThreadLocal.get();

		if (publishingTasksMapList.isEmpty()) {
			return null;
		}

		return publishingTasksMapList.get(publishingTasksMapList.size() - 1);
	}

	private static Map<String, PublishingTask> _popPublishingTasksMapList() {
		List<Map<String, PublishingTask>> publishingTasksMapList =
			_publishingTasksMapListThreadLocal.get();

		return publishingTasksMapList.remove(publishingTasksMapList.size() - 1);
	}

	private static void _pushPublishingTasksMapList() {
		List<Map<String, PublishingTask>> publishingTasksMapList =
			_publishingTasksMapListThreadLocal.get();

		publishingTasksMapList.add(
			Collections.<String, PublishingTask>emptyMap());
	}

	private static void _setPublishingTasksMap(
		Map<String, PublishingTask> publishingTasksMap) {

		List<Map<String, PublishingTask>> publishingTasksMapList =
			_publishingTasksMapListThreadLocal.get();

		publishingTasksMapList.set(
			publishingTasksMapList.size() - 1, publishingTasksMap);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PublishingTasksThreadLocal.class);

	private static final ThreadLocal<Boolean> _importInProcess =
		new CentralizedThreadLocal<>(
			PublishingTasksThreadLocal.class + "._importInProcess",
			() -> Boolean.FALSE);
	private static MessagePublisher _messagePublisher;
	private static final ThreadLocal<List<Map<String, PublishingTask>>>
		_publishingTasksMapListThreadLocal = new CentralizedThreadLocal<>(
			PublishingTasksThreadLocal.class +
				"._publishingTasksMapListThreadLocal",
			ArrayList::new);

	private static class PublishingTask {

		public PublishingTask(String topic, Callable<Message> callable) {
			_topic = topic;
			_callable = callable;
		}

		public Message createMessage() throws Exception {
			if (_callable == null) {
				return null;
			}

			return _callable.call();
		}

		public String getTopic() {
			return _topic;
		}

		public void setCallable(Callable<Message> callable) {
			_callable = callable;
		}

		private Callable<Message> _callable;
		private final String _topic;

	}

}