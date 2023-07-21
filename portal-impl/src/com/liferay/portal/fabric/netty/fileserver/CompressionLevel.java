/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.fileserver;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Deflater;

/**
 * @author Shuyang Zhou
 */
public enum CompressionLevel {

	BEST_COMPRESSION(Deflater.BEST_COMPRESSION),
	BEST_SPEED(Deflater.BEST_SPEED),
	DEFAULT_COMPRESSION(Deflater.DEFAULT_COMPRESSION), LEVEL_2(2), LEVEL_3(3),
	LEVEL_4(4), LEVEL_5(5), LEVEL_6(6), LEVEL_7(7), LEVEL_8(8),
	NO_COMPRESSION(Deflater.NO_COMPRESSION);

	public static CompressionLevel getCompressionLevel(int level) {
		CompressionLevel compressionLevel = _compressionLevels.get(level);

		if (compressionLevel == null) {
			throw new IllegalArgumentException(
				"Compression level " + level +
					" is not within the range of -1 and 9");
		}

		return compressionLevel;
	}

	public int getLevel() {
		return _level;
	}

	private CompressionLevel(int level) {
		_level = level;
	}

	private static final Map<Integer, CompressionLevel> _compressionLevels =
		new HashMap<Integer, CompressionLevel>() {
			{
				for (CompressionLevel compressionLevel :
						EnumSet.allOf(CompressionLevel.class)) {

					put(compressionLevel._level, compressionLevel);
				}
			}
		};

	private final int _level;

}