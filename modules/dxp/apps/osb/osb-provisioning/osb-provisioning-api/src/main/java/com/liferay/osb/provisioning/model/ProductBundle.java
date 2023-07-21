/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ProductBundle service. Represents a row in the &quot;Provisioning_ProductBundle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.model.impl.ProductBundleImpl"
)
@ProviderType
public interface ProductBundle extends PersistedModel, ProductBundleModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.model.impl.ProductBundleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProductBundle, Long>
		PRODUCT_BUNDLE_ID_ACCESSOR = new Accessor<ProductBundle, Long>() {

			@Override
			public Long get(ProductBundle productBundle) {
				return productBundle.getProductBundleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ProductBundle> getTypeClass() {
				return ProductBundle.class;
			}

		};

}