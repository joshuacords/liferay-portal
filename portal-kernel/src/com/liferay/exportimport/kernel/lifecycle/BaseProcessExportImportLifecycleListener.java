/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.lifecycle;

import java.io.Serializable;

import java.util.List;

/**
 * @author     Daniel Kocsis
 * @deprecated As of Judson (7.1.x)
 */
@Deprecated
public abstract class BaseProcessExportImportLifecycleListener
	implements ExportImportLifecycleListener {

	@Override
	public abstract boolean isParallel();

	@Override
	public void onExportImportLifecycleEvent(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {

		int code = exportImportLifecycleEvent.getCode();
		int processFlag = exportImportLifecycleEvent.getProcessFlag();

		if (processFlag ==
				ExportImportLifecycleConstants.
					PROCESS_FLAG_LAYOUT_EXPORT_IN_PROCESS) {

			if (code ==
					ExportImportLifecycleConstants.EVENT_LAYOUT_EXPORT_FAILED) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_LAYOUT_EXPORT_STARTED) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_LAYOUT_EXPORT_SUCCEEDED) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
		else if (processFlag ==
					ExportImportLifecycleConstants.
						PROCESS_FLAG_LAYOUT_IMPORT_IN_PROCESS) {

			if (code ==
					ExportImportLifecycleConstants.EVENT_LAYOUT_IMPORT_FAILED) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_LAYOUT_IMPORT_STARTED) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_LAYOUT_IMPORT_SUCCEEDED) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
		else if (processFlag ==
					ExportImportLifecycleConstants.
						PROCESS_FLAG_LAYOUT_STAGING_IN_PROCESS) {

			if ((code ==
					ExportImportLifecycleConstants.
						EVENT_PUBLICATION_LAYOUT_LOCAL_FAILED) ||
				(code ==
					ExportImportLifecycleConstants.
						EVENT_PUBLICATION_LAYOUT_REMOTE_FAILED)) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if ((code ==
						ExportImportLifecycleConstants.
							EVENT_PUBLICATION_LAYOUT_LOCAL_STARTED) ||
					 (code ==
						 ExportImportLifecycleConstants.
							 EVENT_PUBLICATION_LAYOUT_REMOTE_STARTED)) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if ((code ==
						ExportImportLifecycleConstants.
							EVENT_PUBLICATION_LAYOUT_LOCAL_SUCCEEDED) ||
					 (code ==
						 ExportImportLifecycleConstants.
							 EVENT_PUBLICATION_LAYOUT_REMOTE_SUCCEEDED)) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
		else if (processFlag ==
					ExportImportLifecycleConstants.
						PROCESS_FLAG_PORTLET_EXPORT_IN_PROCESS) {

			if (code ==
					ExportImportLifecycleConstants.
						EVENT_PORTLET_EXPORT_FAILED) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PORTLET_EXPORT_STARTED) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PORTLET_EXPORT_SUCCEEDED) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
		else if (processFlag ==
					ExportImportLifecycleConstants.
						PROCESS_FLAG_PORTLET_IMPORT_IN_PROCESS) {

			if (code ==
					ExportImportLifecycleConstants.
						EVENT_PORTLET_IMPORT_FAILED) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PORTLET_IMPORT_STARTED) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PORTLET_IMPORT_SUCCEEDED) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
		else if (processFlag ==
					ExportImportLifecycleConstants.
						PROCESS_FLAG_PORTLET_STAGING_IN_PROCESS) {

			if (code ==
					ExportImportLifecycleConstants.
						EVENT_PUBLICATION_PORTLET_LOCAL_FAILED) {

				onProcessFailed(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PUBLICATION_PORTLET_LOCAL_STARTED) {

				onProcessStarted(exportImportLifecycleEvent.getAttributes());
			}
			else if (code ==
						ExportImportLifecycleConstants.
							EVENT_PUBLICATION_PORTLET_LOCAL_SUCCEEDED) {

				onProcessSucceeded(exportImportLifecycleEvent.getAttributes());
			}
		}
	}

	protected void onProcessFailed(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void onProcessFailed(List<Serializable> attributes)
		throws Exception {
	}

	protected void onProcessStarted(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void onProcessStarted(List<Serializable> attributes)
		throws Exception {
	}

	protected void onProcessSucceeded(
			ExportImportLifecycleEvent exportImportLifecycleEvent)
		throws Exception {
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void onProcessSucceeded(List<Serializable> attributes)
		throws Exception {
	}

}