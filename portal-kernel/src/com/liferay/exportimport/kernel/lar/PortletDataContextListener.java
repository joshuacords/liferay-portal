/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.lar;

/**
 * @author     Raymond Augé
 * @deprecated As of Wilberforce (7.0.x), see {@link
 *             com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent}
 */
@Deprecated
public interface PortletDataContextListener {

	public void onAddZipEntry(String path);

	public void onGetZipEntry(String path);

}