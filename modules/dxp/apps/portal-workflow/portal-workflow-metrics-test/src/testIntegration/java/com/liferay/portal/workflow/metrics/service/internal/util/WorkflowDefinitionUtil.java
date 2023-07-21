/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.service.internal.util;

import com.liferay.portal.kernel.util.FileUtil;

/**
 * @author Rafael Praxedes
 */
public class WorkflowDefinitionUtil {

	public static byte[] getBytes() throws Exception {
		return FileUtil.getBytes(
			WorkflowDefinitionUtil.class,
			"dependencies/single-approver-definition.xml");
	}

}