/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.model.impl;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.reports.engine.console.service.permission.ReportsActionKeys;
import com.liferay.portal.reports.engine.constants.ReportsEngineDestinationNames;

/**
 * @author Brian Wing Shun Chan
 * @author Gavin Wan
 */
public class EntryImpl extends EntryBaseImpl {

	public EntryImpl() {
	}

	@Override
	public String getAttachmentsDir() {
		return "reports/".concat(String.valueOf(getEntryId()));
	}

	@Override
	public String[] getAttachmentsFiles() throws PortalException {
		return DLStoreUtil.getFileNames(
			getCompanyId(), CompanyConstants.SYSTEM, getAttachmentsDir());
	}

	@Override
	public String getJobName() {
		return ReportsActionKeys.ADD_REPORT.concat(
			String.valueOf(getEntryId()));
	}

	@Override
	public TZSRecurrence getRecurrenceObj() {
		return (TZSRecurrence)JSONFactoryUtil.deserialize(getRecurrence());
	}

	@Override
	public String getSchedulerRequestName() {
		return ReportsEngineDestinationNames.REPORT_REQUEST.concat(
			StringPool.SLASH
		).concat(
			String.valueOf(getEntryId())
		);
	}

}