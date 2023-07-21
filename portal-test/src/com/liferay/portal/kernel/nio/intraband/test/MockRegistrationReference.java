/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.test;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;

import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author Shuyang Zhou
 */
public class MockRegistrationReference implements RegistrationReference {

	public MockRegistrationReference(Intraband intraband) {
		this(intraband, null, null);
	}

	public MockRegistrationReference(
		ScatteringByteChannel scatteringByteChannel,
		GatheringByteChannel gatheringByteChannel) {

		this(null, scatteringByteChannel, gatheringByteChannel);
	}

	@Override
	public void cancelRegistration() {
		_cancelled = true;
	}

	public GatheringByteChannel getGatheringByteChannel() {
		return _gatheringByteChannel;
	}

	@Override
	public Intraband getIntraband() {
		return _intraband;
	}

	public ScatteringByteChannel getScatteringByteChannel() {
		return _scatteringByteChannel;
	}

	@Override
	public boolean isValid() {
		return !_cancelled;
	}

	private MockRegistrationReference(
		Intraband intraband, ScatteringByteChannel scatteringByteChannel,
		GatheringByteChannel gatherByteChannel) {

		_intraband = intraband;
		_scatteringByteChannel = scatteringByteChannel;

		_gatheringByteChannel = gatherByteChannel;
	}

	private boolean _cancelled;
	private final GatheringByteChannel _gatheringByteChannel;
	private final Intraband _intraband;
	private final ScatteringByteChannel _scatteringByteChannel;

}