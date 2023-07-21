/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal;

import com.liferay.journal.kernel.util.JournalConverterManager;
import com.liferay.journal.util.JournalConverter;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = JournalConverterManager.class)
public class JournalConverterManagerImpl implements JournalConverterManager {

	@Override
	public String getDDMXSD(String journalXSD, Locale defaultLocale)
		throws Exception {

		return _journalConverter.getDDMXSD(journalXSD, defaultLocale);
	}

	@Reference(unbind = "-")
	protected void setJournalConverter(JournalConverter journalConverter) {
		_journalConverter = journalConverter;
	}

	private JournalConverter _journalConverter;

}