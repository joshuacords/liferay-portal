/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.internal.executor;

import com.liferay.portal.output.stream.container.OutputStreamContainer;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactory;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactoryTracker;

import java.io.IOException;
import java.io.OutputStream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mariano Álvaro Sáiz
 */
@Component(immediate = true, service = SwappedLogExecutor.class)
public class SwappedLogExecutor {

	public void execute(
		String bundleSymbolicName, Runnable runnable,
		String outputStreamContainerFactoryName) {

		OutputStreamContainerFactory outputStreamContainerFactory =
			_outputStreamContainerFactoryTracker.
				getOutputStreamContainerFactory(
					outputStreamContainerFactoryName);

		OutputStreamContainer outputStreamContainer =
			outputStreamContainerFactory.create(
				"upgrade-" + bundleSymbolicName);

		try (OutputStream outputStream =
				outputStreamContainer.getOutputStream()) {

			_outputStreamThreadLocal.set(outputStream);

			_outputStreamContainerFactoryTracker.runWithSwappedLog(
				runnable, outputStreamContainer.getDescription(), outputStream);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
		finally {
			_outputStreamThreadLocal.remove();
		}
	}

	public OutputStream getOutputStream() {
		return _outputStreamThreadLocal.get();
	}

	@Reference
	private OutputStreamContainerFactoryTracker
		_outputStreamContainerFactoryTracker;

	private final ThreadLocal<OutputStream> _outputStreamThreadLocal =
		new ThreadLocal<>();

}