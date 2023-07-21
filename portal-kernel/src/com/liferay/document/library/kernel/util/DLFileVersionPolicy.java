/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.util;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface DLFileVersionPolicy {

	public boolean isKeepFileVersionLabel(
			DLFileVersion lastDLFileVersion, DLFileVersion latestDLFileVersion,
			boolean majorVersion, ServiceContext serviceContext)
		throws PortalException;

}