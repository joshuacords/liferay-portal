/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.web.internal.display.context.util;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationWebKeys;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.portal.kernel.display.context.util.BaseRequestHelper;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guywandji
 */
public class CommerceDataIntegrationRequestHelper extends BaseRequestHelper {

	public CommerceDataIntegrationRequestHelper(RenderRequest renderRequest) {
		super(PortalUtil.getHttpServletRequest(renderRequest));
	}

	public CommerceDataIntegrationProcess getCommerceDataIntegrationProcess() {
		HttpServletRequest httpServletRequest = getRequest();

		return (CommerceDataIntegrationProcess)httpServletRequest.getAttribute(
			CommerceDataIntegrationWebKeys.COMMERCE_DATA_INTEGRATION_PROCESS);
	}

}