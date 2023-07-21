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
 * The extended model interface for the OAuthConsumer service. Represents a row in the &quot;OpenSocial_OAuthConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerModel
 * @generated
 */
@ImplementationClassName("com.liferay.opensocial.model.impl.OAuthConsumerImpl")
@ProviderType
public interface OAuthConsumer extends OAuthConsumerModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.opensocial.model.impl.OAuthConsumerImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OAuthConsumer, Long>
		O_AUTH_CONSUMER_ID_ACCESSOR = new Accessor<OAuthConsumer, Long>() {

			@Override
			public Long get(OAuthConsumer oAuthConsumer) {
				return oAuthConsumer.getOAuthConsumerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OAuthConsumer> getTypeClass() {
				return OAuthConsumer.class;
			}

		};

	public String getKeyName();

	public void setKeyName(String keyName);

}