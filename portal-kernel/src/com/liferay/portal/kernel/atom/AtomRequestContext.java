/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.atom;

/**
 * @author Igor Spasic
 */
public interface AtomRequestContext {

	public Object getContainerAttribute(String name);

	public String getHeader(String name);

	public int getIntParameter(String name);

	public int getIntParameter(String name, int defaultValue);

	public long getLongParameter(String name);

	public long getLongParameter(String name, long defaultValue);

	public String getParameter(String name);

	public String getParameter(String name, String defaultValue);

	public Object getRequestAttribute(String name);

	public String getResolvedUri();

	public Object getSessionAttribute(String name);

	public String getTargetBasePath();

	public void setContainerAttribute(String name, Object value);

	public void setRequestAttribute(String name, Object value);

	public void setSessionAttribute(String name, Object value);

}