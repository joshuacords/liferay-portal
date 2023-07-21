/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.process.local.LocalProcessLauncher;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Shuyang Zhou
 */
public class SPIUtil {

	public static SPI getSPI() {
		if (_spi == null) {
			throw new IllegalStateException(
				"Current process is not an SPI instance");
		}

		return _spi;
	}

	public static boolean isSPI() {
		if (_spi == null) {
			return false;
		}

		return true;
	}

	private static final SPI _spi;

	static {
		ConcurrentMap<String, Object> attributes =
			LocalProcessLauncher.ProcessContext.getAttributes();

		_spi = (SPI)attributes.remove(SPI.SPI_INSTANCE_PUBLICATION_KEY);
	}

}