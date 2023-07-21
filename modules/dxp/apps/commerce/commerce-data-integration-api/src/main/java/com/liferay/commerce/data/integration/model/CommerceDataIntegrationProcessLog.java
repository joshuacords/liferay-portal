/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceDataIntegrationProcessLog service. Represents a row in the &quot;CDataIntegrationProcessLog&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogImpl"
)
@ProviderType
public interface CommerceDataIntegrationProcessLog
	extends CommerceDataIntegrationProcessLogModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.data.integration.model.impl.CommerceDataIntegrationProcessLogImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceDataIntegrationProcessLog, Long>
		COMMERCE_DATA_INTEGRATION_PROCESS_LOG_ID_ACCESSOR =
			new Accessor<CommerceDataIntegrationProcessLog, Long>() {

				@Override
				public Long get(
					CommerceDataIntegrationProcessLog
						commerceDataIntegrationProcessLog) {

					return commerceDataIntegrationProcessLog.
						getCommerceDataIntegrationProcessLogId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceDataIntegrationProcessLog> getTypeClass() {
					return CommerceDataIntegrationProcessLog.class;
				}

			};

}