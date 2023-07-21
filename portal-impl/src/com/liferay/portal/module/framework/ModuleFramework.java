/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.module.framework;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

import java.net.URL;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public interface ModuleFramework {

	public long addBundle(String location) throws PortalException;

	public long addBundle(String location, InputStream inputStream)
		throws PortalException;

	public URL getBundleResource(long bundleId, String name);

	public Object getFramework();

	public String getState(long bundleId) throws PortalException;

	public void initFramework() throws Exception;

	public void registerContext(Object context);

	public void setBundleStartLevel(long bundleId, int startLevel)
		throws PortalException;

	public void startBundle(long bundleId) throws PortalException;

	public void startBundle(long bundleId, int options) throws PortalException;

	public void startFramework() throws Exception;

	public void startRuntime() throws Exception;

	public void stopBundle(long bundleId) throws PortalException;

	public void stopBundle(long bundleId, int options) throws PortalException;

	public void stopFramework(long timeout) throws Exception;

	public void stopRuntime() throws Exception;

	public void uninstallBundle(long bundleId) throws PortalException;

	public void unregisterContext(Object context);

	public void updateBundle(long bundleId) throws PortalException;

	public void updateBundle(long bundleId, InputStream inputStream)
		throws PortalException;

}