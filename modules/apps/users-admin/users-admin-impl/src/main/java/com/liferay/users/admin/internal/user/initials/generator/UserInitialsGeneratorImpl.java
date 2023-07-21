/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.user.initials.generator;

import com.liferay.portal.kernel.language.LanguageConstants;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.users.admin.kernel.util.UserInitialsGenerator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pei-Jung Lan
 */
@Component(immediate = true, service = UserInitialsGenerator.class)
public class UserInitialsGeneratorImpl implements UserInitialsGenerator {

	@Override
	public String getInitials(
		Locale locale, String firstName, String middleName, String lastName) {

		String[] userNames = {firstName, middleName, lastName};

		return Stream.of(
			_getUserInitialsFieldNames(locale)
		).map(
			key -> userNames[_userNameIndexes.get(key)]
		).filter(
			name -> Validator.isNotNull(name)
		).limit(
			2
		).map(
			name -> StringUtil.shorten(name, 1)
		).map(
			initial -> StringUtil.toUpperCase(initial)
		).collect(
			Collectors.joining()
		);
	}

	@Override
	public String getInitials(User user) {
		return getInitials(
			user.getLocale(), user.getFirstName(), user.getMiddleName(),
			user.getLastName());
	}

	private String[] _getUserInitialsFieldNames(Locale locale) {
		String[] userInitialsFieldNames = _userInitialsFieldNamesMap.get(
			locale);

		if (userInitialsFieldNames != null) {
			return userInitialsFieldNames;
		}

		userInitialsFieldNames = StringUtil.split(
			LanguageUtil.get(
				locale, LanguageConstants.KEY_USER_INITIALS_FIELD_NAMES, null));

		if (ArrayUtil.isEmpty(userInitialsFieldNames)) {
			userInitialsFieldNames = _DEFAULT_USER_INITIALS_FIELD_NAMES;
		}

		_userInitialsFieldNamesMap.put(locale, userInitialsFieldNames);

		return userInitialsFieldNames;
	}

	private static final String[] _DEFAULT_USER_INITIALS_FIELD_NAMES = {
		LanguageConstants.VALUE_FIRST_NAME, LanguageConstants.VALUE_LAST_NAME
	};

	private static final Map<String, Integer> _userNameIndexes =
		new HashMap<String, Integer>(3) {
			{
				put(LanguageConstants.VALUE_FIRST_NAME, 0);
				put(LanguageConstants.VALUE_LAST_NAME, 2);
				put(LanguageConstants.VALUE_MIDDLE_NAME, 1);
			}
		};

	private final Map<Locale, String[]> _userInitialsFieldNamesMap =
		new HashMap<>();

}