/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.nonblocking;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.nio.channels.SelectionKey;

/**
 * @author Shuyang Zhou
 */
public class SelectionKeyRegistrationReference
	implements RegistrationReference {

	@Override
	public void cancelRegistration() {
		readSelectionKey.cancel();
		writeSelectionKey.cancel();
	}

	@Override
	public Intraband getIntraband() {
		return intraband;
	}

	@Override
	public boolean isValid() {
		return writeSelectionKey.isValid();
	}

	protected SelectionKeyRegistrationReference(
		Intraband intraband, SelectionKey readSelectionKey,
		SelectionKey writeSelectionKey) {

		this.intraband = intraband;
		this.readSelectionKey = readSelectionKey;
		this.writeSelectionKey = writeSelectionKey;
	}

	protected final Intraband intraband;
	protected final SelectionKey readSelectionKey;
	protected final SelectionKey writeSelectionKey;

}