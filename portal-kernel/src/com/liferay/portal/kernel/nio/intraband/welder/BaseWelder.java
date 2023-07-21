/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseWelder implements Welder {

	public BaseWelder() {

		// Assignments have to stay in the constructor because we need to
		// differentiate between a constructor created object and a
		// deserialization created object. Only the constructor created object
		// needs to assign values. The deserialization created object gets its
		// values from the original object.

		server = true;
	}

	@Override
	public synchronized void destroy() throws IOException {
		if (state != State.WELDED) {
			throw new IllegalStateException(
				"Unable to destroy a welder with state " + state);
		}

		registrationReference.cancelRegistration();

		doDestroy();

		state = State.DESTROYED;
	}

	@Override
	public synchronized RegistrationReference weld(Intraband intraband)
		throws IOException {

		if (state != State.CREATED) {
			throw new IllegalStateException(
				"Unable to weld a welder with state " + state);
		}

		if (server) {
			registrationReference = weldServer(intraband);
		}
		else {
			registrationReference = weldClient(intraband);
		}

		state = State.WELDED;

		return registrationReference;
	}

	protected abstract void doDestroy() throws IOException;

	protected abstract RegistrationReference weldClient(Intraband intraband)
		throws IOException;

	protected abstract RegistrationReference weldServer(Intraband intraband)
		throws IOException;

	protected transient RegistrationReference registrationReference;
	protected final transient boolean server;
	protected transient State state = State.CREATED;

	protected static enum State {

		CREATED, DESTROYED, WELDED

	}

	private void readObject(ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		objectInputStream.defaultReadObject();

		state = State.CREATED;
	}

}