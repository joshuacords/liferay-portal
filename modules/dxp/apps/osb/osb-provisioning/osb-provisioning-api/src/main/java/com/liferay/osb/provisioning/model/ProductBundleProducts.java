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
 * The extended model interface for the ProductBundleProducts service. Represents a row in the &quot;Provisioning_ProductBundleProducts&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl"
)
@ProviderType
public interface ProductBundleProducts
	extends PersistedModel, ProductBundleProductsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProductBundleProducts, Long>
		PRODUCT_BUNDLE_ID_ACCESSOR =
			new Accessor<ProductBundleProducts, Long>() {

				@Override
				public Long get(ProductBundleProducts productBundleProducts) {
					return productBundleProducts.getProductBundleId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ProductBundleProducts> getTypeClass() {
					return ProductBundleProducts.class;
				}

			};
	public static final Accessor<ProductBundleProducts, String>
		PRODUCT_KEY_ACCESSOR = new Accessor<ProductBundleProducts, String>() {

			@Override
			public String get(ProductBundleProducts productBundleProducts) {
				return productBundleProducts.getProductKey();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ProductBundleProducts> getTypeClass() {
				return ProductBundleProducts.class;
			}

		};

}