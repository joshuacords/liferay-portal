/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.xsl;

import java.io.Serializable;

import javax.xml.transform.URIResolver;

/**
 * @author Tina Tian
 */
public interface XSLURIResolver extends Serializable, URIResolver {

	public String getLanguageId();

}