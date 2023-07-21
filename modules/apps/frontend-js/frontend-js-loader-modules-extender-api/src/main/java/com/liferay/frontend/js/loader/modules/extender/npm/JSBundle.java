/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.npm;

import java.net.URL;

import java.util.Collection;

/**
 * Represents an OSGi bundle containing NPM packages and modules.
 *
 * @author Iván Zaera
 */
public interface JSBundle extends JSBundleObject {

	/**
	 * Returns the NPM packages provided by the OSGi bundle.
	 *
	 * @return the NPM packages
	 */
	public Collection<JSPackage> getJSPackages();

	/**
	 * Returns the {@link URL} of an OSGi bundle's resource.
	 *
	 * @return the URL of an OSGi bundle's resource
	 */
	public URL getResourceURL(String location);

	/**
	 * Returns the bundle's OSGi version.
	 *
	 * @return the bundle's OSGi version
	 */
	public String getVersion();

}