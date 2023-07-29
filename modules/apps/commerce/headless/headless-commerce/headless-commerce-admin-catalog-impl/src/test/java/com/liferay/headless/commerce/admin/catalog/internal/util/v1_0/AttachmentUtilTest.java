/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.catalog.internal.util.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentBase64;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentUrl;
import com.liferay.headless.commerce.admin.catalog.internal.jaxrs.exception.MethodRequiredParameterMissingException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@PrepareForTest(LanguageUtil.class)
@RunWith(PowerMockRunner.class)
public class AttachmentUtilTest extends PowerMockito {

	@Before
	public void setUp() {
		_setUpLanguageUtil();
	}

	private void _setUpLanguageUtil() {
		LanguageUtil languageUtil = new LanguageUtil();

		_whenLanguageIsAvailableLocale(new Locale("hr", "HR"));
		_whenLanguageIsAvailableLocale(LocaleUtil.ITALY);
		_whenLanguageIsAvailableLocale(LocaleUtil.US);

		languageUtil.setLanguage(_language);
	}

	private void _whenLanguageIsAvailableLocale(Locale locale) {
		Mockito.when(
			_language.isAvailableLocale(Mockito.eq(locale))
		).thenReturn(
			true
		);

		Mockito.when(
			_language.isAvailableLocale(
				Mockito.eq(LocaleUtil.toLanguageId(locale)))
		).thenReturn(
			true
		);
	}

	private final Language _language = Mockito.mock(Language.class);

	@Test
	public void testGetTitleMapIfCPAttachmentFileEntryIsNull()
		throws Exception {

		Map<Locale, String> titleMap = AttachmentUtil.getTitleMap(
			null, _titleMap);

		Assert.assertEquals(
			"Title map size", _titleMap.size(), titleMap.size());

		titleMap.forEach(
			(key, value) -> Assert.assertTrue(
				"title map contains " + key.toString(),
				_titleMap.containsKey(key.toString())));
	}

	@Test(expected = MethodRequiredParameterMissingException.class)
	public void testUpsertCPAttachmentFileEntryWithInvalidAttachment()
		throws Exception {

		AttachmentUtil.upsertCPAttachmentFileEntry(
			0, null, null, new Attachment(), 0, 0, 0, new ServiceContext());
	}

	@Test(expected = MethodRequiredParameterMissingException.class)
	public void testUpsertCPAttachmentFileEntryWithInvalidAttachmentBase64()
		throws Exception {

		AttachmentUtil.upsertCPAttachmentFileEntry(
			null, null, new AttachmentBase64(), 0, 0, 0, new ServiceContext());
	}

	@Test(expected = MethodRequiredParameterMissingException.class)
	public void testUpsertCPAttachmentFileEntryWithInvalidAttachmentUrl()
		throws Exception {

		AttachmentUtil.upsertCPAttachmentFileEntry(
			null, null, new AttachmentUrl(), 0, 0, 0, new ServiceContext());
	}

	private static final Map<String, String> _titleMap = HashMapBuilder.put(
		"en_US", "Written in English"
	).put(
		"hr_HR", "Napisano na Hrvatskom"
	).put(
		"it_IT", "Scritto nel Italiano"
	).build();

}