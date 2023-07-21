/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelWriter {

	public ByteChannelWriter(WritableByteChannel writableByteChannel) {
		this(writableByteChannel, 1024);
	}

	public ByteChannelWriter(
		WritableByteChannel writableByteChannel, int bufferLength) {

		_writableByteChannel = writableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);
	}

	public void ensureSpace(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			write();
		}
	}

	public void finish() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		_byteBuffer.flip();

		newBuffer.put(_byteBuffer);

		_byteBuffer = newBuffer;
	}

	protected void write() throws IOException {
		_byteBuffer.flip();

		_writableByteChannel.write(_byteBuffer);

		_byteBuffer.clear();
	}

	private ByteBuffer _byteBuffer;
	private final WritableByteChannel _writableByteChannel;

}