/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.factory.MessageFactory;
import com.liferay.osb.koroneiki.xylem.distributed.messaging.model.listener.PublishingTasksThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseXylemModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	@Override
	public void onAfterCreate(final T model) throws ModelListenerException {
		try {
			PublishingTasksThreadLocal.addPublishingTask(
				getCreateKey(model), getCreateTopic(model), getCallable(model));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterRemove(final T model) throws ModelListenerException {
		try {
			PublishingTasksThreadLocal.addPublishingTask(
				getRemoveKey(model), getRemoveTopic(model), getCallable(model));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onAfterUpdate(final T model) throws ModelListenerException {
		try {
			PublishingTasksThreadLocal.addPublishingTask(
				getUpdateKey(model), getUpdateTopic(model), getCallable(model));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ModelListenerException(exception);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_createTopic = (String)properties.get("create.topic");
		_removeTopic = (String)properties.get("remove.topic");
		_updateTopic = (String)properties.get("update.topic");
	}

	protected abstract Callable<Message> getCallable(T model) throws Exception;

	protected String getCreateKey(T model) throws PortalException {
		return getCreateTopic(model) + StringPool.POUND + getPrimaryKey(model);
	}

	protected String getCreateTopic(T model) throws PortalException {
		return _createTopic;
	}

	protected String getPrimaryKey(T model) {
		return String.valueOf(model.getPrimaryKeyObj());
	}

	protected String getRemoveKey(T model) throws PortalException {
		return getRemoveTopic(model) + StringPool.POUND + getPrimaryKey(model);
	}

	protected String getRemoveTopic(T model) throws PortalException {
		return _removeTopic;
	}

	protected String getUpdateKey(T model) throws PortalException {
		return getUpdateTopic(model) + StringPool.POUND + getPrimaryKey(model);
	}

	protected String getUpdateTopic(T model) throws PortalException {
		return _updateTopic;
	}

	@Reference
	protected MessageFactory messageFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseXylemModelListener.class);

	private String _createTopic;
	private String _removeTopic;
	private String _updateTopic;

}