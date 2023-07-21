/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.util;

import com.liferay.journal.configuration.JournalGroupServiceConfiguration;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Juergen Kappler
 */
@Component(immediate = true, service = {})
public class JournalServiceComponentProvider {

	public static JournalServiceComponentProvider
		getJournalServiceComponentProvider() {

		return _journalServiceComponentProvider;
	}

	@Activate
	public void activate() {
		_journalServiceComponentProvider = this;
	}

	@Deactivate
	public void deactivate() {
		_journalServiceComponentProvider = null;
	}

	public JournalGroupServiceConfiguration
		getJournalGroupServiceConfiguration() {

		return _journalGroupServiceConfiguration;
	}

	@Reference
	public void setJournalGroupServiceConfiguration(
		JournalGroupServiceConfiguration journalGroupServiceConfiguration) {

		_journalGroupServiceConfiguration = journalGroupServiceConfiguration;
	}

	protected void unsetJournalGroupServiceConfiguration(
		JournalGroupServiceConfiguration journalGroupServiceConfiguration) {

		_journalGroupServiceConfiguration = null;
	}

	private static JournalServiceComponentProvider
		_journalServiceComponentProvider;

	private JournalGroupServiceConfiguration _journalGroupServiceConfiguration;

}