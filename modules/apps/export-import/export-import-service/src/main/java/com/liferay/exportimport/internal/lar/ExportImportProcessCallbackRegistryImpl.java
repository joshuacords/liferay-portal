/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.lar;

import com.liferay.exportimport.kernel.lar.ExportImportProcessCallbackRegistry;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true, service = ExportImportProcessCallbackRegistry.class
)
public class ExportImportProcessCallbackRegistryImpl
	implements ExportImportProcessCallbackRegistry {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void registerCallback(Callable<?> callable) {
	}

	@Override
	public void registerCallback(String processId, Callable<?> callable) {
		ExportImportProcessCallbackUtil.registerCallback(processId, callable);
	}

}