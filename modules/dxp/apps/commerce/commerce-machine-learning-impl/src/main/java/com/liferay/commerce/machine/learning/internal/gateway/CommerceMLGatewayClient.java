/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.gateway;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.File;
import java.io.InputStream;

/**
 * @author Riccardo Ferrari
 */
public interface CommerceMLGatewayClient {

	public File downloadCommerceMLJobResult(
			String applicationId, String resourceName,
			UnicodeProperties unicodeProperties)
		throws PortalException;

	public CommerceMLJobState getCommerceMLJobState(
			String applicationId, UnicodeProperties unicodeProperties)
		throws PortalException;

	public CommerceMLJobState startCommerceMLJob(
			UnicodeProperties unicodeProperties)
		throws PortalException;

	public void uploadCommerceMLJobResource(
			String resourceName, InputStream resourceInputStream,
			UnicodeProperties unicodeProperties)
		throws PortalException;

}