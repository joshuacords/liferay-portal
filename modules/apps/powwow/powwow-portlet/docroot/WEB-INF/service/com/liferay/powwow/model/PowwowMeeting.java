/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the PowwowMeeting service. Represents a row in the &quot;PowwowMeeting&quot; database table, with each column mapped to a property of this class.
 *
 * @author Shinn Lok
 * @see PowwowMeetingModel
 * @generated
 */
@ImplementationClassName("com.liferay.powwow.model.impl.PowwowMeetingImpl")
@ProviderType
public interface PowwowMeeting extends PersistedModel, PowwowMeetingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.powwow.model.impl.PowwowMeetingImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PowwowMeeting, Long>
		POWWOW_MEETING_ID_ACCESSOR = new Accessor<PowwowMeeting, Long>() {

			@Override
			public Long get(PowwowMeeting powwowMeeting) {
				return powwowMeeting.getPowwowMeetingId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PowwowMeeting> getTypeClass() {
				return PowwowMeeting.class;
			}

		};

	public java.util.Map<String, java.io.Serializable>
		getProviderTypeMetadataMap();

}