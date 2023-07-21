/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.jaxrs.context;

import java.util.Map;
import java.util.Set;

/**
 * @author Javier de Arcos
 */
public interface ExtensionContext {

	public Map<String, Object> getExtendedProperties(Object object);

	public Set<String> getFilteredPropertyKeys(Object object);

}