/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.lifecycle;

import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEvent;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleEventFactory;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Daniel Kocsis
 */
@Component(immediate = true, service = ExportImportLifecycleEventFactory.class)
public class ExportImportLifecycleEventFactoryImpl
	implements ExportImportLifecycleEventFactory {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public ExportImportLifecycleEvent create(
		int code, int processFlag, Serializable... attributes) {

		ExportImportLifecycleEvent exportImportLifecycleEvent =
			new ExportImportLifecycleEventImpl();

		exportImportLifecycleEvent.setAttributes(attributes);
		exportImportLifecycleEvent.setCode(code);
		exportImportLifecycleEvent.setProcessFlag(processFlag);
		exportImportLifecycleEvent.setProcessId(
			GetterUtil.getString(processFlag));

		return exportImportLifecycleEvent;
	}

	@Override
	public ExportImportLifecycleEvent create(
		int code, int processFlag, String processId,
		Serializable... attributes) {

		ExportImportLifecycleEvent exportImportLifecycleEvent =
			new ExportImportLifecycleEventImpl();

		exportImportLifecycleEvent.setAttributes(attributes);
		exportImportLifecycleEvent.setCode(code);
		exportImportLifecycleEvent.setProcessFlag(processFlag);
		exportImportLifecycleEvent.setProcessId(processId);

		return exportImportLifecycleEvent;
	}

}