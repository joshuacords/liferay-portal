/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;

/**
 * @author Connor McKay
 */
public class ByteChannelReader {

	public ByteChannelReader(ReadableByteChannel readableByteChannel)
		throws IOException {

		this(readableByteChannel, 1024);
	}

	public ByteChannelReader(
			ReadableByteChannel readableByteChannel, int bufferLength)
		throws IOException {

		_readableByteChannel = readableByteChannel;

		_byteBuffer = ByteBuffer.allocate(bufferLength);

		if (_readableByteChannel.read(_byteBuffer) == -1) {
			_eof = true;
		}
		else {
			_eof = false;
		}

		_byteBuffer.flip();
	}

	public void ensureData(int length) throws IOException {
		if (_byteBuffer.remaining() < length) {
			read(length);

			if (_eof || (_byteBuffer.remaining() < length)) {
				throw new IOException("Unexpected EOF");
			}
		}
	}

	public byte get() {
		return _byteBuffer.get();
	}

	public byte get(int offset) {
		return _byteBuffer.get(_byteBuffer.position() + offset);
	}

	public ByteBuffer getBuffer() {
		return _byteBuffer;
	}

	public boolean hasRemaining() {
		return _byteBuffer.hasRemaining();
	}

	public boolean isSeekable() {
		if (_readableByteChannel instanceof SeekableByteChannel) {
			return true;
		}

		return false;
	}

	public void maybeRead(int length) throws IOException {
		if (!_eof && (_byteBuffer.remaining() < length)) {
			read(length);
		}
	}

	public void position(int position) throws IOException {
		if (isSeekable()) {
			SeekableByteChannel seekableByteChannel =
				(SeekableByteChannel)_readableByteChannel;

			seekableByteChannel.position(position);

			_byteBuffer.clear();
			_byteBuffer.flip();
		}
		else {
			throw new IOException("Byte channel is not seekable");
		}
	}

	public void read(int length) throws IOException {
		if (_eof) {
			return;
		}

		_byteBuffer.compact();

		while (_byteBuffer.position() < length) {
			int bytesRead = _readableByteChannel.read(_byteBuffer);

			if (bytesRead == -1) {
				_eof = true;

				break;
			}
		}

		_byteBuffer.flip();
	}

	public int remaining() {
		return _byteBuffer.remaining();
	}

	public void resizeBuffer(int minBufferLength) {
		if (_byteBuffer.capacity() >= minBufferLength) {
			return;
		}

		ByteBuffer newBuffer = ByteBuffer.allocate(minBufferLength);

		newBuffer.put(_byteBuffer);
		newBuffer.flip();

		_byteBuffer = newBuffer;
	}

	public int skip(int length) {
		length = Math.min(_byteBuffer.remaining(), length);

		_byteBuffer.position(_byteBuffer.position() + length);

		return length;
	}

	private ByteBuffer _byteBuffer;
	private boolean _eof;
	private final ReadableByteChannel _readableByteChannel;

}