/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.flash;

import com.liferay.portal.kernel.util.ArrayUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public class FlashMagicBytesUtil {

	public static Result check(InputStream inputStream) throws IOException {
		PushbackInputStream pushbackInputStream = new PushbackInputStream(
			inputStream, 3);

		byte[] magicBytes = new byte[3];

		int length = pushbackInputStream.read(magicBytes);

		if (length < 0) {
			return new Result(false, inputStream);
		}

		pushbackInputStream.unread(magicBytes, 0, length);

		inputStream = pushbackInputStream;

		if (ArrayUtil.containsAll(_FLASH_MAGIC_BYTES[0], magicBytes) ||
			ArrayUtil.containsAll(_FLASH_MAGIC_BYTES[1], magicBytes) ||
			ArrayUtil.containsAll(_FLASH_MAGIC_BYTES[2], magicBytes)) {

			return new Result(true, inputStream);
		}

		return new Result(false, inputStream);
	}

	public static class Result {

		public InputStream getInputStream() {
			return _inputStream;
		}

		public boolean isFlash() {
			return _flash;
		}

		private Result(boolean flash, InputStream inputStream) {
			_flash = flash;
			_inputStream = inputStream;
		}

		private final boolean _flash;
		private final InputStream _inputStream;

	}

	private static final byte[][] _FLASH_MAGIC_BYTES = {
		{0x46, 0x57, 0x53}, {0x43, 0x57, 0x53}, {0x5a, 0x57, 0x53}
	};

}