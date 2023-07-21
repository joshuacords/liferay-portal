/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.parsers.bbcode;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Iliyan Peychev
 * @author Miguel Pastor
 */
public class BBCodeTranslatorUtil {

	public static BBCodeTranslator getBBCodeTranslator() {
		return _bbCodeTranslatorUtil._getBBCodeTranslator();
	}

	public static String[] getEmoticonDescriptions() {
		return getBBCodeTranslator().getEmoticonDescriptions();
	}

	public static String[] getEmoticonFiles() {
		return getBBCodeTranslator().getEmoticonFiles();
	}

	public static String[][] getEmoticons() {
		return getBBCodeTranslator().getEmoticons();
	}

	public static String[] getEmoticonSymbols() {
		return getBBCodeTranslator().getEmoticonSymbols();
	}

	public static String getHTML(String bbcode) {
		return getBBCodeTranslator().getHTML(bbcode);
	}

	public static String parse(String message) {
		return getBBCodeTranslator().parse(message);
	}

	private BBCodeTranslatorUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(BBCodeTranslator.class);

		_serviceTracker.open();
	}

	private BBCodeTranslator _getBBCodeTranslator() {
		return _serviceTracker.getService();
	}

	private static final BBCodeTranslatorUtil _bbCodeTranslatorUtil =
		new BBCodeTranslatorUtil();

	private final ServiceTracker<BBCodeTranslator, BBCodeTranslator>
		_serviceTracker;

}