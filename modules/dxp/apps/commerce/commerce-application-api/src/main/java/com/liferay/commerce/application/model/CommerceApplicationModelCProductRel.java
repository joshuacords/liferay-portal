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
 * The extended model interface for the CommerceApplicationModelCProductRel service. Represents a row in the &quot;CAModelCProductRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelImpl"
)
@ProviderType
public interface CommerceApplicationModelCProductRel
	extends CommerceApplicationModelCProductRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.application.model.impl.CommerceApplicationModelCProductRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceApplicationModelCProductRel, Long>
		COMMERCE_APPLICATION_MODEL_C_PRODUCT_REL_ID_ACCESSOR =
			new Accessor<CommerceApplicationModelCProductRel, Long>() {

				@Override
				public Long get(
					CommerceApplicationModelCProductRel
						commerceApplicationModelCProductRel) {

					return commerceApplicationModelCProductRel.
						getCommerceApplicationModelCProductRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceApplicationModelCProductRel>
					getTypeClass() {

					return CommerceApplicationModelCProductRel.class;
				}

			};

}