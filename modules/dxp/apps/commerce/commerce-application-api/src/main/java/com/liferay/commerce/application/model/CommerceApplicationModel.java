/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceApplicationModel service. Represents a row in the &quot;CommerceApplicationModel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl"
)
@ProviderType
public interface CommerceApplicationModel
	extends CommerceApplicationModelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.application.model.impl.CommerceApplicationModelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceApplicationModel, Long>
		COMMERCE_APPLICATION_MODEL_ID_ACCESSOR =
			new Accessor<CommerceApplicationModel, Long>() {

				@Override
				public Long get(
					CommerceApplicationModel commerceApplicationModel) {

					return commerceApplicationModel.
						getCommerceApplicationModelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceApplicationModel> getTypeClass() {
					return CommerceApplicationModel.class;
				}

			};

}