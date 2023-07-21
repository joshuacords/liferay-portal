/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ServiceProducer service. Represents a row in the &quot;Koroneiki_ServiceProducer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerImpl"
)
@ProviderType
public interface ServiceProducer extends PersistedModel, ServiceProducerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ServiceProducer, Long>
		SERVICE_PRODUCER_ID_ACCESSOR = new Accessor<ServiceProducer, Long>() {

			@Override
			public Long get(ServiceProducer serviceProducer) {
				return serviceProducer.getServiceProducerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ServiceProducer> getTypeClass() {
				return ServiceProducer.class;
			}

		};

}