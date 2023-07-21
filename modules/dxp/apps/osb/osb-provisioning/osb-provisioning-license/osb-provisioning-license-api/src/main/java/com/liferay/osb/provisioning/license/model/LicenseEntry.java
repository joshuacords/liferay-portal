/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the LicenseEntry service. Represents a row in the &quot;Provisioning_LicenseEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.license.model.impl.LicenseEntryImpl"
)
@ProviderType
public interface LicenseEntry extends LicenseEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.license.model.impl.LicenseEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LicenseEntry, Long> LICENSE_ENTRY_ID_ACCESSOR =
		new Accessor<LicenseEntry, Long>() {

			@Override
			public Long get(LicenseEntry licenseEntry) {
				return licenseEntry.getLicenseEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LicenseEntry> getTypeClass() {
				return LicenseEntry.class;
			}

		};

	public String getDisplayName();

}