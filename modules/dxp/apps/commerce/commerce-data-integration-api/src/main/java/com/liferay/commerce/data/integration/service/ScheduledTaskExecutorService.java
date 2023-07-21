/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

/**
 * @author guywandji
 * @author Alessio Antonio Rendina
 */
public interface ScheduledTaskExecutorService {

	/**
	 * This method returns the name of the process type
	 */
	public String getName();

	/**
	 * This method execute the selected process
	 *
	 * @param commerceDataIntegrationProcessId
	 * @throws IOException
	 * @throws PortalException
	 */
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException;

}