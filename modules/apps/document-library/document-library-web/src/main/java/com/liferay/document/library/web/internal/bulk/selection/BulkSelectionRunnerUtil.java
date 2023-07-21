/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.bulk.selection;

import com.liferay.bulk.selection.BulkSelectionRunner;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class BulkSelectionRunnerUtil {

	public static BulkSelectionRunner getBulkSelectionRunner() {
		return _bulkSelectionRunner;
	}

	@Reference(unbind = "-")
	protected void setBulkSelectionRunner(
		BulkSelectionRunner bulkSelectionRunner) {

		_bulkSelectionRunner = bulkSelectionRunner;
	}

	private static BulkSelectionRunner _bulkSelectionRunner;

}