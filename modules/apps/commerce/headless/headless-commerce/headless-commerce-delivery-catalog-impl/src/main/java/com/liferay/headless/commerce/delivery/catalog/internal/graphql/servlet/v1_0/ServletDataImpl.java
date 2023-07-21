/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.delivery.catalog.internal.graphql.servlet.v1_0;

import com.liferay.headless.commerce.delivery.catalog.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.headless.commerce.delivery.catalog.internal.graphql.query.v1_0.Query;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.AttachmentResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.CategoryResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.ProductOptionResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.ProductResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.ProductSpecificationResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.RelatedProductResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.internal.resource.v1_0.SkuResourceImpl;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.CategoryResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.ProductSpecificationResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.RelatedProductResource;
import com.liferay.headless.commerce.delivery.catalog.resource.v1_0.SkuResource;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Query.setAttachmentResourceComponentServiceObjects(
			_attachmentResourceComponentServiceObjects);
		Query.setCategoryResourceComponentServiceObjects(
			_categoryResourceComponentServiceObjects);
		Query.setProductResourceComponentServiceObjects(
			_productResourceComponentServiceObjects);
		Query.setProductOptionResourceComponentServiceObjects(
			_productOptionResourceComponentServiceObjects);
		Query.setProductSpecificationResourceComponentServiceObjects(
			_productSpecificationResourceComponentServiceObjects);
		Query.setRelatedProductResourceComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects);
		Query.setSkuResourceComponentServiceObjects(
			_skuResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Headless.Commerce.Delivery.Catalog";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-commerce-delivery-catalog-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"query#channelProductAttachments",
						new ObjectValuePair<>(
							AttachmentResourceImpl.class,
							"getChannelProductAttachmentsPage"));
					put(
						"query#channelProductImages",
						new ObjectValuePair<>(
							AttachmentResourceImpl.class,
							"getChannelProductImagesPage"));
					put(
						"query#channelProductCategories",
						new ObjectValuePair<>(
							CategoryResourceImpl.class,
							"getChannelProductCategoriesPage"));
					put(
						"query#channelProducts",
						new ObjectValuePair<>(
							ProductResourceImpl.class,
							"getChannelProductsPage"));
					put(
						"query#channelProduct",
						new ObjectValuePair<>(
							ProductResourceImpl.class, "getChannelProduct"));
					put(
						"query#channelProductOptions",
						new ObjectValuePair<>(
							ProductOptionResourceImpl.class,
							"getChannelProductOptionsPage"));
					put(
						"query#channelProductProductSpecifications",
						new ObjectValuePair<>(
							ProductSpecificationResourceImpl.class,
							"getChannelProductProductSpecificationsPage"));
					put(
						"query#channelProductRelatedProducts",
						new ObjectValuePair<>(
							RelatedProductResourceImpl.class,
							"getChannelProductRelatedProductsPage"));
					put(
						"query#channelProductSkus",
						new ObjectValuePair<>(
							SkuResourceImpl.class,
							"getChannelProductSkusPage"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AttachmentResource>
		_attachmentResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CategoryResource>
		_categoryResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ProductSpecificationResource>
		_productSpecificationResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<RelatedProductResource>
		_relatedProductResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;

}