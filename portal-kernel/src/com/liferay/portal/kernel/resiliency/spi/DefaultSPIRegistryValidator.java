/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;

/**
 * @author Shuyang Zhou
 */
public class DefaultSPIRegistryValidator implements SPIRegistryValidator {

	@Override
	public SPI validatePortletSPI(String portletId, SPI spi) {
		if (spi != null) {
			spi = MPIHelperUtil.checkSPILiveness(spi);
		}

		return spi;
	}

	@Override
	public SPI validateServletContextSPI(String servletContextName, SPI spi) {
		if (spi != null) {
			spi = MPIHelperUtil.checkSPILiveness(spi);
		}

		return spi;
	}

}