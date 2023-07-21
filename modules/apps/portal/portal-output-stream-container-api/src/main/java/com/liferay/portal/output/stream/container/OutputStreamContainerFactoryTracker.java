/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.output.stream.container;

import java.io.OutputStream;

import java.util.Set;

/**
 * @author Matthew Tambara
 */
public interface OutputStreamContainerFactoryTracker {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *			   #getOutputStreamContainerFactory(String)}
	 */
	@Deprecated
	public OutputStreamContainerFactory getOutputStreamContainerFactory();

	public OutputStreamContainerFactory getOutputStreamContainerFactory(
		String outputStreamContainerFactoryName);

	public Set<String> getOutputStreamContainerFactoryNames();

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *			   #runWithSwappedLog(Runnable, String, OutputStream)}
	 */
	@Deprecated
	public void runWithSwappedLog(Runnable runnable, String outputStreamHint);

	public void runWithSwappedLog(
		Runnable runnable, String outputStreamName, OutputStream outputStream);

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *			   #runWithSwappedLog(Runnable, String, OutputStream)}
	 */
	@Deprecated
	public void runWithSwappedLog(
		Runnable runnable, String outputStreamHint,
		String outputStreamContainerName);

}