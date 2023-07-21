/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.importer;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.wiki.model.WikiNode;

import java.io.InputStream;

import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public interface WikiImporter {

	public void importPages(
			long userId, WikiNode node, InputStream[] inputStream,
			Map<String, String[]> options)
		throws PortalException;

}