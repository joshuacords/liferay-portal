/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductFieldException;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductFieldUtil
 * @generated
 */
@ProviderType
public interface ProductFieldPersistence extends BasePersistence<ProductField> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductFieldUtil} to access the product field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching product fields
	 */
	public java.util.List<ProductField> findByC_C(
		long classNameId, long classPK);

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
	public java.util.List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

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
	public java.util.List<ProductField> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public ProductField findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public ProductField fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public ProductField findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public ProductField fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

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
	public ProductField[] findByC_C_PrevAndNext(
			long productFieldId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Removes all the product fields where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of product fields where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching product fields
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the product fields where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching product fields
	 */
	public java.util.List<ProductField> findByC_N(
		long classNameId, String name);

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
	public java.util.List<ProductField> findByC_N(
		long classNameId, String name, int start, int end);

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
	public java.util.List<ProductField> findByC_N(
		long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

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
	public java.util.List<ProductField> findByC_N(
		long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public ProductField findByC_N_First(
			long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Returns the first product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public ProductField fetchByC_N_First(
		long classNameId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field
	 * @throws NoSuchProductFieldException if a matching product field could not be found
	 */
	public ProductField findByC_N_Last(
			long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Returns the last product field in the ordered set where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product field, or <code>null</code> if a matching product field could not be found
	 */
	public ProductField fetchByC_N_Last(
		long classNameId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

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
	public ProductField[] findByC_N_PrevAndNext(
			long productFieldId, long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<ProductField>
				orderByComparator)
		throws NoSuchProductFieldException;

	/**
	 * Removes all the product fields where classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 */
	public void removeByC_N(long classNameId, String name);

	/**
	 * Returns the number of product fields where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching product fields
	 */
	public int countByC_N(long classNameId, String name);

	/**
	 * Caches the product field in the entity cache if it is enabled.
	 *
	 * @param productField the product field
	 */
	public void cacheResult(ProductField productField);

	/**
	 * Caches the product fields in the entity cache if it is enabled.
	 *
	 * @param productFields the product fields
	 */
	public void cacheResult(java.util.List<ProductField> productFields);

	/**
	 * Creates a new product field with the primary key. Does not add the product field to the database.
	 *
	 * @param productFieldId the primary key for the new product field
	 * @return the new product field
	 */
	public ProductField create(long productFieldId);

	/**
	 * Removes the product field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field that was removed
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public ProductField remove(long productFieldId)
		throws NoSuchProductFieldException;

	public ProductField updateImpl(ProductField productField);

	/**
	 * Returns the product field with the primary key or throws a <code>NoSuchProductFieldException</code> if it could not be found.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field
	 * @throws NoSuchProductFieldException if a product field with the primary key could not be found
	 */
	public ProductField findByPrimaryKey(long productFieldId)
		throws NoSuchProductFieldException;

	/**
	 * Returns the product field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productFieldId the primary key of the product field
	 * @return the product field, or <code>null</code> if a product field with the primary key could not be found
	 */
	public ProductField fetchByPrimaryKey(long productFieldId);

	/**
	 * Returns all the product fields.
	 *
	 * @return the product fields
	 */
	public java.util.List<ProductField> findAll();

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
	public java.util.List<ProductField> findAll(int start, int end);

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
	public java.util.List<ProductField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator);

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
	public java.util.List<ProductField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product fields from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product fields.
	 *
	 * @return the number of product fields
	 */
	public int countAll();

}