/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the OAuthApplication service. Represents a row in the &quot;OAuth_OAuthApplication&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ivica Cardic
 * @see OAuthApplicationModel
 * @generated
 */
@ImplementationClassName("com.liferay.oauth.model.impl.OAuthApplicationImpl")
@ProviderType
public interface OAuthApplication
	extends OAuthApplicationModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.oauth.model.impl.OAuthApplicationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OAuthApplication, Long>
		O_AUTH_APPLICATION_ID_ACCESSOR =
			new Accessor<OAuthApplication, Long>() {

				@Override
				public Long get(OAuthApplication oAuthApplication) {
					return oAuthApplication.getOAuthApplicationId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<OAuthApplication> getTypeClass() {
					return OAuthApplication.class;
				}

			};

	public String getAccessLevelLabel();

}