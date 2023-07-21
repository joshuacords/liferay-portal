/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the product field service. This utility wraps <code>com.liferay.osb.koroneiki.trunk.service.persistence.impl.ProductFieldPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductFieldPersistence
 * @generated
 */
public class ProductFieldUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ProductField productField) {
		getPersistence().clearCache(productField);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ProductField> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductField> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductField update(ProductField productField) {
		return getPersistence().update(productField);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductField update(
		ProductField productField, ServiceContext serviceContext) {

		return getPersistence().update(productField, serviceContext);
	}

	/**
	 * Returns all the product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching product fields
	 */
	public static List<ProductField> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @return the range of matching product fields
	 */
	public static List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product fields
	 */
	public static List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product fields
	 */
	public static List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ProductField> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public static ProductField findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public static ProductField fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public static ProductField findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public static ProductField fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the product fields before and after the current product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param productFieldId the primary key of the current product field
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product field
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public static ProductField[] findByC_C_PrevAndNext(
			long productFieldId, long classNameId, long classPK,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_C_PrevAndNext(
			productFieldId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the product fields where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching product fields
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the product fields where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching product fields
	 */
	public static List<ProductField> findByC_N(long classNameId, String name) {
		return getPersistence().findByC_N(classNameId, name);
	}

	/**
	 * Returns a range of all the product fields where classNameId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @return the range of matching product fields
	 */
	public static List<ProductField> findByC_N(
		long classNameId, String name, int start, int end) {

		return getPersistence().findByC_N(classNameId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the product fields where classNameId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product fields
	 */
	public static List<ProductField> findByC_N(
		long classNameId, String name, int start, int end,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().findByC_N(
			classNameId, name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product fields where classNameId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product fields
	 */
	public static List<ProductField> findByC_N(
		long classNameId, String name, int start, int end,
		OrderByComparator<ProductField> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_N(
			classNameId, name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public static ProductField findByC_N_First(
			long classNameId, String name,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_N_First(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public static ProductField fetchByC_N_First(
		long classNameId, String name,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().fetchByC_N_First(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public static ProductField findByC_N_Last(
			long classNameId, String name,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_N_Last(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public static ProductField fetchByC_N_Last(
		long classNameId, String name,
		OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().fetchByC_N_Last(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the product fields before and after the current product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param productFieldId the primary key of the current product field
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product field
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public static ProductField[] findByC_N_PrevAndNext(
			long productFieldId, long classNameId, String name,
			OrderByComparator<ProductField> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByC_N_PrevAndNext(
			productFieldId, classNameId, name, orderByComparator);
	}

	/**
	 * Removes all the product fields where classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 */
	public static void removeByC_N(long classNameId, String name) {
		getPersistence().removeByC_N(classNameId, name);
	}

	/**
	 * Returns the number of product fields where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching product fields
	 */
	public static int countByC_N(long classNameId, String name) {
		return getPersistence().countByC_N(classNameId, name);
	}

	/**
	 * Caches the product field in the entity cache if it is enabled.
	 *
	 * @param productField the product field
	 */
	public static void cacheResult(ProductField productField) {
		getPersistence().cacheResult(productField);
	}

	/**
	 * Caches the product fields in the entity cache if it is enabled.
	 *
	 * @param productFields the product fields
	 */
	public static void cacheResult(List<ProductField> productFields) {
		getPersistence().cacheResult(productFields);
	}

	/**
	 * Creates a new product field with the primary key. Does not add the product field to the database.
	 *
	 * @param productFieldId the primary key for the new product field
	 * @return the new product field
	 */
	public static ProductField create(long productFieldId) {
		return getPersistence().create(productFieldId);
	}

	/**
	 * Removes the product field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field that was removed
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public static ProductField remove(long productFieldId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().remove(productFieldId);
	}

	public static ProductField updateImpl(ProductField productField) {
		return getPersistence().updateImpl(productField);
	}

	/**
	 * Returns the product field with the primary key or throws a <code>NoSuchProductFieldException</code> if it could not be found.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public static ProductField findByPrimaryKey(long productFieldId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductFieldException {

		return getPersistence().findByPrimaryKey(productFieldId);
	}

	/**
	 * Returns the product field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field, or <code>null</code> if a product field with the primary key could not be found
	 */
	public static ProductField fetchByPrimaryKey(long productFieldId) {
		return getPersistence().fetchByPrimaryKey(productFieldId);
	}

	/**
	 * Returns all the product fields.
	 *
	 * @return the product fields
	 */
	public static List<ProductField> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @return the range of product fields
	 */
	public static List<ProductField> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product fields
	 */
	public static List<ProductField> findAll(
		int start, int end, OrderByComparator<ProductField> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product fields
	 * @param end the upper bound of the range of product fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product fields
	 */
	public static List<ProductField> findAll(
		int start, int end, OrderByComparator<ProductField> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product fields from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product fields.
	 *
	 * @return the number of product fields
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProductFieldPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ProductFieldPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ProductFieldPersistence _persistence;

}