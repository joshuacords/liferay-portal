/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SyncDevice service. Represents a row in the &quot;SyncDevice&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceModel
 * @generated
 */
@ImplementationClassName("com.liferay.sync.model.impl.SyncDeviceImpl")
@ProviderType
public interface SyncDevice extends PersistedModel, SyncDeviceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.sync.model.impl.SyncDeviceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SyncDevice, Long> SYNC_DEVICE_ID_ACCESSOR =
		new Accessor<SyncDevice, Long>() {

			@Override
			public Long get(SyncDevice syncDevice) {
				return syncDevice.getSyncDeviceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SyncDevice> getTypeClass() {
				return SyncDevice.class;
			}

		};

	public void checkStatus()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasSetModifiedDate();

	public boolean isSupported();

	public boolean supports(int featureSet);

}