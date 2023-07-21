/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.lifecycle;

import java.io.Serializable;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Daniel Kocsis
 */
@ProviderType
public interface ExportImportLifecycleEventFactory {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	public ExportImportLifecycleEvent create(
		int code, int processFlag, Serializable... attributes);

	public ExportImportLifecycleEvent create(
		int code, int processFlag, String processId,
		Serializable... attributes);

}