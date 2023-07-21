/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.io;

import com.liferay.petra.string.CharPool;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author     Tina Tian
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class Base64OutputStream extends OutputStream {

	public Base64OutputStream(OutputStream outputStream) {
		_outputStream = outputStream;

		_unitBuffer = new byte[3];
		_unitBufferIndex = 0;
		_outputBuffer = new byte[4];
	}

	@Override
	public void close() throws IOException {
		flush();

		_outputStream.close();
	}

	@Override
	public void flush() throws IOException {
		if (_unitBufferIndex == 1) {
			encodeUnit(_unitBuffer[0]);
		}
		else if (_unitBufferIndex == 2) {
			encodeUnit(_unitBuffer[0], _unitBuffer[1]);
		}

		_unitBufferIndex = 0;

		_outputStream.flush();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		write(bytes, 0, bytes.length);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		if (length <= 0) {
			return;
		}

		while ((_unitBufferIndex != 0) && (length > 0)) {
			write(bytes[offset++]);

			length--;
		}

		if (length <= 0) {
			return;
		}

		int bytesLength = length - (length % 3);

		length -= bytesLength;

		while (bytesLength > 0) {
			encodeUnit(bytes[offset], bytes[offset + 1], bytes[offset + 2]);

			bytesLength -= 3;
			offset += 3;
		}

		while (length > 0) {
			write(bytes[offset++]);

			length--;
		}
	}

	@Override
	public void write(int byteValue) throws IOException {
		_unitBuffer[_unitBufferIndex++] = (byte)byteValue;

		if (_unitBufferIndex == 3) {
			encodeUnit(_unitBuffer[0], _unitBuffer[1], _unitBuffer[2]);

			_unitBufferIndex = 0;
		}
	}

	protected void encodeUnit(byte byteValue) throws IOException {
		int intValue = byteValue & 0xff;

		intValue <<= 4;

		_outputBuffer[3] = (byte)CharPool.EQUAL;
		_outputBuffer[2] = (byte)CharPool.EQUAL;
		_outputBuffer[1] = (byte)getChar(intValue & 0x3f);

		intValue >>= 6;

		_outputBuffer[0] = (byte)getChar(intValue & 0x3f);

		_outputStream.write(_outputBuffer);
	}

	protected void encodeUnit(byte byte1, byte byte2) throws IOException {
		int intValue = byte1 & 0xff;

		intValue <<= 8;
		intValue |= byte2 & 0xff;
		intValue <<= 2;

		_outputBuffer[3] = (byte)CharPool.EQUAL;
		_outputBuffer[2] = (byte)getChar(intValue & 0x3f);

		intValue >>= 6;

		_outputBuffer[1] = (byte)getChar(intValue & 0x3f);

		intValue >>= 6;

		_outputBuffer[0] = (byte)getChar(intValue & 0x3f);

		_outputStream.write(_outputBuffer);
	}

	protected void encodeUnit(byte byte1, byte byte2, byte byte3)
		throws IOException {

		int intVallue = byte1 & 0xff;

		intVallue <<= 8;
		intVallue |= byte2 & 0xff;
		intVallue <<= 8;
		intVallue |= byte3 & 0xff;

		_outputBuffer[3] = (byte)getChar(intVallue & 0x3f);

		intVallue >>= 6;

		_outputBuffer[2] = (byte)getChar(intVallue & 0x3f);

		intVallue >>= 6;

		_outputBuffer[1] = (byte)getChar(intVallue & 0x3f);

		intVallue >>= 6;

		_outputBuffer[0] = (byte)getChar(intVallue & 0x3f);

		_outputStream.write(_outputBuffer);
	}

	protected char getChar(int sixbit) {
		if ((sixbit >= 0) && (sixbit <= 25)) {
			return (char)(65 + sixbit);
		}

		if ((sixbit >= 26) && (sixbit <= 51)) {
			return (char)(97 + (sixbit - 26));
		}

		if ((sixbit >= 52) && (sixbit <= 61)) {
			return (char)(48 + (sixbit - 52));
		}

		if (sixbit == 62) {
			return CharPool.PLUS;
		}

		if (sixbit != 63) {
			return CharPool.QUESTION;
		}

		return CharPool.SLASH;
	}

	private final byte[] _outputBuffer;
	private final OutputStream _outputStream;
	private final byte[] _unitBuffer;
	private int _unitBufferIndex;

}