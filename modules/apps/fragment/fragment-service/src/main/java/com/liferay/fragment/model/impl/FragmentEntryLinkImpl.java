/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model.impl;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

/**
 * @author Eudaldo Alonso
 */
public class FragmentEntryLinkImpl extends FragmentEntryLinkBaseImpl {

	@Override
	public boolean isLatestVersion() throws PortalException {
		FragmentEntry fragmentEntry =
			FragmentEntryLocalServiceUtil.getFragmentEntry(
				getFragmentEntryId());

		Date fragmentEntryModifiedDate = fragmentEntry.getModifiedDate();

		return fragmentEntryModifiedDate.before(getLastPropagationDate());
	}

}