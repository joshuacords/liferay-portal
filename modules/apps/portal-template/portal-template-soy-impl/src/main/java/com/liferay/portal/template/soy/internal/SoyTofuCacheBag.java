/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.msgs.SoyMsgBundle;
import com.google.template.soy.tofu.SoyTofu;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Basto
 */
public class SoyTofuCacheBag {

	public SoyTofuCacheBag(SoyFileSet soyFileSet, SoyTofu soyTofu) {
		_soyFileSet = soyFileSet;
		_soyTofu = soyTofu;
	}

	public SoyMsgBundle getMessageBundle(Locale locale) {
		return _soyMsgBundleCache.get(locale);
	}

	public SoyFileSet getSoyFileSet() {
		return _soyFileSet;
	}

	public SoyTofu getSoyTofu() {
		return _soyTofu;
	}

	public void putMessageBundle(Locale locale, SoyMsgBundle soyMsgBundle) {
		_soyMsgBundleCache.put(locale, soyMsgBundle);
	}

	public SoyMsgBundle removeMessageBundle(Locale locale) {
		return _soyMsgBundleCache.remove(locale);
	}

	private final SoyFileSet _soyFileSet;
	private final Map<Locale, SoyMsgBundle> _soyMsgBundleCache =
		new HashMap<>();
	private final SoyTofu _soyTofu;

}