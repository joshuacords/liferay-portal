/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.talend.job.deployer;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

/**
 * @author Danny Situ
 */
public interface TalendJobFileProvider {

	public List<InputStream> getJobFileInputStreams() throws IOException;

}