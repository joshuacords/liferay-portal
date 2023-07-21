/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.resiliency.spi.provider.SPIProvider;

/**
 * @author Shuyang Zhou
 */
public class MockSPIProvider implements SPIProvider {

	public MockSPIProvider() {
		this(MockSPIProvider.class.getName());
	}

	public MockSPIProvider(String name) {
		_name = name;
	}

	@Override
	public SPI createSPI(SPIConfiguration spiConfiguration) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public String toString() {
		return _name;
	}

	private String _name;

}