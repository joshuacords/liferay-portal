/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io.delta;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author Connor McKay
 */
public class DeltaUtil {

	public static final int BUFFER_FACTOR = 16;

	public static final byte DATA_KEY = 1;

	public static final byte EOF_KEY = 0;

	public static final byte PROTOCOL_VERSION = 1;

	public static final byte REFERENCE_KEY = 2;

	public static final byte REFERENCE_RANGE_KEY = 3;

	public static void checksums(
			FileChannel originalFileChannel,
			ByteChannelWriter checksumsByteChannelWriter)
		throws IOException {

		checksums(originalFileChannel, checksumsByteChannelWriter, 512);
	}

	public static void checksums(
			FileChannel originalFileChannel,
			ByteChannelWriter checksumsByteChannelWriter, int blockLength)
		throws IOException {

		RollingChecksum rollingChecksum = new RollingChecksum(
			originalFileChannel, blockLength);

		checksumsByteChannelWriter.resizeBuffer(BUFFER_FACTOR * 20);

		ByteBuffer byteBuffer = checksumsByteChannelWriter.getBuffer();

		int blocksCount = (int)Math.ceil(
			originalFileChannel.size() / (double)blockLength);

		checksumsByteChannelWriter.ensureSpace(9);

		byteBuffer.put(PROTOCOL_VERSION);
		byteBuffer.putInt(blockLength);
		byteBuffer.putInt(blocksCount);

		for (; rollingChecksum.hasNext(); rollingChecksum.nextBlock()) {
			checksumsByteChannelWriter.ensureSpace(20);

			byteBuffer.putInt(rollingChecksum.weakChecksum());
			byteBuffer.put(rollingChecksum.strongChecksum());
		}
	}

	public static void delta(
			ReadableByteChannel modifiedReadableByteChannel,
			ByteChannelReader checksumsByteChannelReader,
			ByteChannelWriter deltaByteChannelWriter)
		throws IOException {

		Differ differ = new Differ();

		differ.delta(
			modifiedReadableByteChannel, checksumsByteChannelReader,
			deltaByteChannelWriter);
	}

	public static void patch(
			FileChannel originalFileChannel,
			WritableByteChannel patchedWritableByteChannel,
			ByteChannelReader deltaByteChannelReader)
		throws IOException {

		Patcher patcher = new Patcher();

		patcher.patch(
			originalFileChannel, patchedWritableByteChannel,
			deltaByteChannelReader);
	}

}