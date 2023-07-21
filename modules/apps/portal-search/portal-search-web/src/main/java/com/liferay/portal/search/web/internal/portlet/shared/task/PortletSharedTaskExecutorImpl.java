/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.portlet.shared.task;

import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.search.web.portlet.shared.task.PortletSharedTask;
import com.liferay.portal.search.web.portlet.shared.task.PortletSharedTaskExecutor;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.portlet.RenderRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = PortletSharedTaskExecutor.class)
public class PortletSharedTaskExecutorImpl
	implements PortletSharedTaskExecutor {

	@Override
	public <T> T executeOnlyOnce(
		PortletSharedTask<T> portletSharedTask, String attributeSuffix,
		RenderRequest renderRequest) {

		String attributeName = _requestSharedAttribute.concat(attributeSuffix);

		Optional<FutureTask<T>> oldFutureTaskOptional;
		FutureTask<T> futureTask;

		synchronized (renderRequest) {
			oldFutureTaskOptional = portletSharedRequestHelper.getAttribute(
				attributeName, renderRequest);

			futureTask = oldFutureTaskOptional.orElseGet(
				() -> {
					FutureTask<T> newFutureTask = new FutureTask<>(
						portletSharedTask::execute);

					portletSharedRequestHelper.setAttribute(
						attributeName, newFutureTask, renderRequest);

					return newFutureTask;
				});
		}

		if (!oldFutureTaskOptional.isPresent()) {
			futureTask.run();
		}

		try {
			return futureTask.get();
		}
		catch (ExecutionException | InterruptedException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Activate
	protected void activate() {
		String[] requestSharedAttributes = props.getArray(
			PropsKeys.REQUEST_SHARED_ATTRIBUTES);

		Arrays.sort(requestSharedAttributes);

		_requestSharedAttribute = requestSharedAttributes[0];
	}

	@Reference
	protected PortletSharedRequestHelper portletSharedRequestHelper;

	@Reference
	protected Props props;

	private String _requestSharedAttribute;

}