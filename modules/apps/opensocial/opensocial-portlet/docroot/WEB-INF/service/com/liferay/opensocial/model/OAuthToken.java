/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the OAuthToken service. Represents a row in the &quot;OpenSocial_OAuthToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthTokenModel
 * @generated
 */
@ImplementationClassName("com.liferay.opensocial.model.impl.OAuthTokenImpl")
@ProviderType
public interface OAuthToken extends OAuthTokenModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.opensocial.model.impl.OAuthTokenImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OAuthToken, Long> O_AUTH_TOKEN_ID_ACCESSOR =
		new Accessor<OAuthToken, Long>() {

			@Override
			public Long get(OAuthToken oAuthToken) {
				return oAuthToken.getOAuthTokenId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OAuthToken> getTypeClass() {
				return OAuthToken.class;
			}

		};

}