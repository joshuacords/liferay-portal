/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.uad.anonymizer;

import com.liferay.message.boards.exception.RequiredMessageException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(immediate = true, service = UADAnonymizer.class)
public class MBMessageUADAnonymizer extends BaseMBMessageUADAnonymizer {

	@Override
	public Map<Class, String> getExceptionMessageMap(Locale locale) {
		return HashMapBuilder.<Class, String>put(
			RequiredMessageException.class,
			LanguageUtil.get(
				ResourceBundleUtil.getBundle(
					locale, BaseMBMessageUADAnonymizer.class),
				"root-messages-with-multiple-replies-cannot-be-deleted.-" +
					"delete-the-thread-instead")
		).build();
	}

}