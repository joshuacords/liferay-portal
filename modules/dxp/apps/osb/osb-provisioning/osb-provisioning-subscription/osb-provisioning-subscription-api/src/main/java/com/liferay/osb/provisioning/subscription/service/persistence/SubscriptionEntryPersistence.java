/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service.persistence;

import com.liferay.osb.provisioning.subscription.exception.NoSuchSubscriptionEntryException;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntryUtil
 * @generated
 */
@ProviderType
public interface SubscriptionEntryPersistence
	extends BasePersistence<SubscriptionEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubscriptionEntryUtil} to access the subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByContactUuid(
		String contactUuid);

	/**
	 * Returns a range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end);

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByContactUuid_First(
			String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByContactUuid_First(
		String contactUuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByContactUuid_Last(
			String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByContactUuid_Last(
		String contactUuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry[] findByContactUuid_PrevAndNext(
			long subscriptionEntryId, String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Removes all the subscription entries where contactUuid = &#63; from the database.
	 *
	 * @param contactUuid the contact uuid
	 */
	public void removeByContactUuid(String contactUuid);

	/**
	 * Returns the number of subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	public int countByContactUuid(String contactUuid);

	/**
	 * Returns all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK);

	/**
	 * Returns a range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end);

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry[] findByC_C_PrevAndNext(
			long subscriptionEntryId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Removes all the subscription entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching subscription entries
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid);

	/**
	 * Returns a range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end);

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	public java.util.List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByC_CU_First(
			long classNameId, String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_CU_First(
		long classNameId, String contactUuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByC_CU_Last(
			long classNameId, String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_CU_Last(
		long classNameId, String contactUuid,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry[] findByC_CU_PrevAndNext(
			long subscriptionEntryId, long classNameId, String contactUuid,
			com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
				orderByComparator)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Removes all the subscription entries where classNameId = &#63; and contactUuid = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 */
	public void removeByC_CU(long classNameId, String contactUuid);

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	public int countByC_CU(long classNameId, String contactUuid);

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	public SubscriptionEntry findByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid);

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	public SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid,
		boolean useFinderCache);

	/**
	 * Removes the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the subscription entry that was removed
	 */
	public SubscriptionEntry removeByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	public int countByC_C_CU(
		long classNameId, long classPK, String contactUuid);

	/**
	 * Caches the subscription entry in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntry the subscription entry
	 */
	public void cacheResult(SubscriptionEntry subscriptionEntry);

	/**
	 * Caches the subscription entries in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntries the subscription entries
	 */
	public void cacheResult(
		java.util.List<SubscriptionEntry> subscriptionEntries);

	/**
	 * Creates a new subscription entry with the primary key. Does not add the subscription entry to the database.
	 *
	 * @param subscriptionEntryId the primary key for the new subscription entry
	 * @return the new subscription entry
	 */
	public SubscriptionEntry create(long subscriptionEntryId);

	/**
	 * Removes the subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry remove(long subscriptionEntryId)
		throws NoSuchSubscriptionEntryException;

	public SubscriptionEntry updateImpl(SubscriptionEntry subscriptionEntry);

	/**
	 * Returns the subscription entry with the primary key or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry findByPrimaryKey(long subscriptionEntryId)
		throws NoSuchSubscriptionEntryException;

	/**
	 * Returns the subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry, or <code>null</code> if a subscription entry with the primary key could not be found
	 */
	public SubscriptionEntry fetchByPrimaryKey(long subscriptionEntryId);

	/**
	 * Returns all the subscription entries.
	 *
	 * @return the subscription entries
	 */
	public java.util.List<SubscriptionEntry> findAll();

	/**
	 * Returns a range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of subscription entries
	 */
	public java.util.List<SubscriptionEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subscription entries
	 */
	public java.util.List<SubscriptionEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subscription entries
	 */
	public java.util.List<SubscriptionEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SubscriptionEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the subscription entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of subscription entries.
	 *
	 * @return the number of subscription entries
	 */
	public int countAll();

}