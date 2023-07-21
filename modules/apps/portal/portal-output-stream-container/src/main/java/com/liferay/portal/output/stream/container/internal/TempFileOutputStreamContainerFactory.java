/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.output.stream.container.internal;

import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.output.stream.container.OutputStreamContainer;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Carlos Sierra Andrés
 */
public class TempFileOutputStreamContainerFactory
	implements OutputStreamContainerFactory {

	@Override
	public OutputStreamContainer create(String hint) {
		try {
			Path tempDirectoryPath = Files.createTempDirectory(
				"com_liferay_portal_output_stream_container_internal_" +
					"TempFileOutputStreamContainerFactory");

			final Path tempFilePath = Files.createTempFile(
				tempDirectoryPath, hint, ".log");

			return new OutputStreamContainer() {

				@Override
				public String getDescription() {
					Path absolutePath = tempFilePath.toAbsolutePath();

					return absolutePath.toString();
				}

				@Override
				public OutputStream getOutputStream() {
					try {
						return StreamUtil.uncloseable(
							new FileOutputStream(tempFilePath.toFile()));
					}
					catch (FileNotFoundException fileNotFoundException) {
						throw new RuntimeException(fileNotFoundException);
					}
				}

			};
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

}