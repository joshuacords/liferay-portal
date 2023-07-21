/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import javax.portlet.RenderResponse;

/**
 * @author Amos Fong
 */
public class AssignTeamContactRowChecker extends EmptyOnClickRowChecker {

	public AssignTeamContactRowChecker(
		RenderResponse renderResponse, List<String> contactKeys) {

		super(renderResponse);

		_contactKeys = contactKeys;
	}

	@Override
	public boolean isChecked(Object obj) {
		ContactDisplay contactDisplay = (ContactDisplay)obj;

		try {
			return _contactKeys.contains(contactDisplay.getKey());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignTeamContactRowChecker.class);

	private final List<String> _contactKeys;

}