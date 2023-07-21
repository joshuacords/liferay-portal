/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AuditEntryDisplay {

	public AuditEntryDisplay(
			HttpServletRequest httpServletRequest, AuditEntry auditEntry)
		throws Exception {

		_httpServletRequest = httpServletRequest;
		_auditEntry = auditEntry;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_timeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("hh:mm a");
	}

	public String getAgentName() {
		return _auditEntry.getAgentName();
	}

	public String getAgentPortraitURL() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return UserConstants.getPortraitURL(
			themeDisplay.getPathImage(), true, 0, StringPool.BLANK);
	}

	public long getAuditSetId() {
		return _auditEntry.getAuditSetId();
	}

	public String getDateCreated() {
		return _dateFormat.format(_auditEntry.getDateCreated());
	}

	public String getDescription() {
		if (Validator.isNotNull(_auditEntry.getDescription())) {
			return _auditEntry.getDescription();
		}

		return StringPool.BLANK;
	}

	public String getField() {
		return _auditEntry.getField();
	}

	public String getKey() {
		return _auditEntry.getKey();
	}

	public String getNewValue() {
		if (Validator.isNotNull(_auditEntry.getNewValue())) {
			return _auditEntry.getNewValue();
		}

		return "N/A";
	}

	public String getOldValue() {
		if (Validator.isNotNull(_auditEntry.getOldValue())) {
			return _auditEntry.getOldValue();
		}

		return "N/A";
	}

	public String getSummary() {
		return _auditEntry.getSummary();
	}

	public String getTimeCreated() {
		return _timeFormat.format(_auditEntry.getDateCreated());
	}

	private final AuditEntry _auditEntry;
	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final Format _timeFormat;

}