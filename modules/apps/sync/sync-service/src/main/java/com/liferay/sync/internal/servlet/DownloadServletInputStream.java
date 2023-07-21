/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.servlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Dennis Ju
 */
public class DownloadServletInputStream extends InputStream {

	public DownloadServletInputStream(InputStream inputStream, long size) {
		this(inputStream, StringPool.BLANK, StringPool.BLANK, size);
	}

	public DownloadServletInputStream(
		InputStream inputStream, String fileName, String mimeType, long size) {

		_inputStream = inputStream;
		_fileName = fileName;
		_mimeType = mimeType;
		_size = size;
	}

	@Override
	public void close() throws IOException {
		_inputStream.close();
	}

	public String getFileName() {
		return _fileName;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public long getSize() {
		return _size;
	}

	@Override
	public int read() throws IOException {
		return _inputStream.read();
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {

		// SYNC-1550

		try {
			return _inputStream.read(b, off, len);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new IOException(exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadServletInputStream.class);

	private final String _fileName;
	private final InputStream _inputStream;
	private final String _mimeType;
	private final long _size;

}