/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.yaml.openapi;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author     Javier de Arcos
 * @deprecated As of Athanasius (7.3.x)
 */
@Deprecated
public class ResponseCode {

	public ResponseCode(String code) {
		if (StringUtil.equals(code, "default")) {
			_defaultResponse = true;
			_httpCode = null;
		}
		else {
			_defaultResponse = false;
			_httpCode = GetterUtil.getInteger(code);
		}
	}

	public Integer getHttpCode() {
		return _httpCode;
	}

	public boolean isDefaultResponse() {
		return _defaultResponse;
	}

	private final boolean _defaultResponse;
	private final Integer _httpCode;

}