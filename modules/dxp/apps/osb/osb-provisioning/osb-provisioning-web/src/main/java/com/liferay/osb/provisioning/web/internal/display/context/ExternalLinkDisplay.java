/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ExternalLinkDisplay {

	public ExternalLinkDisplay(
		HttpServletRequest httpServletRequest, ExternalLink externalLink) {

		_httpServletRequest = httpServletRequest;
		_externalLink = externalLink;
	}

	public String getDomain() {
		return _externalLink.getDomain();
	}

	public String getEntityId() {
		return _externalLink.getEntityId();
	}

	public String getEntityName() {
		return _externalLink.getEntityName();
	}

	public String getKey() {
		return _externalLink.getKey();
	}

	public String getLabel() {
		StringBundler sb = new StringBundler(3);

		sb.append(_externalLink.getDomain());
		sb.append(StringPool.DASH);
		sb.append(_externalLink.getEntityName());

		return LanguageUtil.get(_httpServletRequest, sb.toString());
	}

	public String getUrl() {
		return _externalLink.getUrl();
	}

	private final ExternalLink _externalLink;
	private final HttpServletRequest _httpServletRequest;

}