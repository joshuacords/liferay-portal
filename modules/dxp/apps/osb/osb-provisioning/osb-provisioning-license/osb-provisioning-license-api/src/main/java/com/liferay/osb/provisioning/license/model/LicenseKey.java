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
 * The extended model interface for the LicenseKey service. Represents a row in the &quot;Provisioning_LicenseKey&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.license.model.impl.LicenseKeyImpl"
)
@ProviderType
public interface LicenseKey extends LicenseKeyModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.license.model.impl.LicenseKeyImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LicenseKey, Long> LICENSE_KEY_ID_ACCESSOR =
		new Accessor<LicenseKey, Long>() {

			@Override
			public Long get(LicenseKey licenseKey) {
				return licenseKey.getLicenseKeyId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LicenseKey> getTypeClass() {
				return LicenseKey.class;
			}

		};

	public LicenseEntry fetchLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public LicenseEntry getLicenseEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	@com.liferay.portal.kernel.json.JSON
	public String getProductEntryName();

	@com.liferay.portal.kernel.json.JSON
	public String getProductVersionLabel();

	public boolean isExpired();

}