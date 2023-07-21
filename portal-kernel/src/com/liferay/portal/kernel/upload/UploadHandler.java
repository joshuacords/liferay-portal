/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upload;

import com.liferay.portal.kernel.exception.PortalException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.upload.UploadHandler}
 */
@Deprecated
public interface UploadHandler {

	public void upload(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException;

}