/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.service.persistence;

import com.liferay.osb.distributed.messaging.model.QueuedMessage;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the queued message service. This utility wraps <code>com.liferay.osb.distributed.messaging.service.persistence.impl.QueuedMessagePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessagePersistence
 * @generated
 */
public class QueuedMessageUtil {

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
	public static void clearCache(QueuedMessage queuedMessage) {
		getPersistence().clearCache(queuedMessage);
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
	public static Map<Serializable, QueuedMessage> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<QueuedMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<QueuedMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<QueuedMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static QueuedMessage update(QueuedMessage queuedMessage) {
		return getPersistence().update(queuedMessage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static QueuedMessage update(
		QueuedMessage queuedMessage, ServiceContext serviceContext) {

		return getPersistence().update(queuedMessage, serviceContext);
	}

	/**
	 * Returns all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the matching queued messages
	 */
	public static List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName) {

		return getPersistence().findByMessageBrokerClassName(
			messageBrokerClassName);
	}

	/**
	 * Returns a range of all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @return the range of matching queued messages
	 */
	public static List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end) {

		return getPersistence().findByMessageBrokerClassName(
			messageBrokerClassName, start, end);
	}

	/**
	 * Returns an ordered range of all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching queued messages
	 */
	public static List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return getPersistence().findByMessageBrokerClassName(
			messageBrokerClassName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching queued messages
	 */
	public static List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByMessageBrokerClassName(
			messageBrokerClassName, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	public static QueuedMessage findByMessageBrokerClassName_First(
			String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws com.liferay.osb.distributed.messaging.exception.
			NoSuchQueuedMessageException {

		return getPersistence().findByMessageBrokerClassName_First(
			messageBrokerClassName, orderByComparator);
	}

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	public static QueuedMessage fetchByMessageBrokerClassName_First(
		String messageBrokerClassName,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return getPersistence().fetchByMessageBrokerClassName_First(
			messageBrokerClassName, orderByComparator);
	}

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	public static QueuedMessage findByMessageBrokerClassName_Last(
			String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws com.liferay.osb.distributed.messaging.exception.
			NoSuchQueuedMessageException {

		return getPersistence().findByMessageBrokerClassName_Last(
			messageBrokerClassName, orderByComparator);
	}

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	public static QueuedMessage fetchByMessageBrokerClassName_Last(
		String messageBrokerClassName,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return getPersistence().fetchByMessageBrokerClassName_Last(
			messageBrokerClassName, orderByComparator);
	}

	/**
	 * Returns the queued messages before and after the current queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param queuedMessageId the primary key of the current queued message
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public static QueuedMessage[] findByMessageBrokerClassName_PrevAndNext(
			long queuedMessageId, String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws com.liferay.osb.distributed.messaging.exception.
			NoSuchQueuedMessageException {

		return getPersistence().findByMessageBrokerClassName_PrevAndNext(
			queuedMessageId, messageBrokerClassName, orderByComparator);
	}

	/**
	 * Removes all the queued messages where messageBrokerClassName = &#63; from the database.
	 *
	 * @param messageBrokerClassName the message broker class name
	 */
	public static void removeByMessageBrokerClassName(
		String messageBrokerClassName) {

		getPersistence().removeByMessageBrokerClassName(messageBrokerClassName);
	}

	/**
	 * Returns the number of queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the number of matching queued messages
	 */
	public static int countByMessageBrokerClassName(
		String messageBrokerClassName) {

		return getPersistence().countByMessageBrokerClassName(
			messageBrokerClassName);
	}

	/**
	 * Caches the queued message in the entity cache if it is enabled.
	 *
	 * @param queuedMessage the queued message
	 */
	public static void cacheResult(QueuedMessage queuedMessage) {
		getPersistence().cacheResult(queuedMessage);
	}

	/**
	 * Caches the queued messages in the entity cache if it is enabled.
	 *
	 * @param queuedMessages the queued messages
	 */
	public static void cacheResult(List<QueuedMessage> queuedMessages) {
		getPersistence().cacheResult(queuedMessages);
	}

	/**
	 * Creates a new queued message with the primary key. Does not add the queued message to the database.
	 *
	 * @param queuedMessageId the primary key for the new queued message
	 * @return the new queued message
	 */
	public static QueuedMessage create(long queuedMessageId) {
		return getPersistence().create(queuedMessageId);
	}

	/**
	 * Removes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public static QueuedMessage remove(long queuedMessageId)
		throws com.liferay.osb.distributed.messaging.exception.
			NoSuchQueuedMessageException {

		return getPersistence().remove(queuedMessageId);
	}

	public static QueuedMessage updateImpl(QueuedMessage queuedMessage) {
		return getPersistence().updateImpl(queuedMessage);
	}

	/**
	 * Returns the queued message with the primary key or throws a <code>NoSuchQueuedMessageException</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public static QueuedMessage findByPrimaryKey(long queuedMessageId)
		throws com.liferay.osb.distributed.messaging.exception.
			NoSuchQueuedMessageException {

		return getPersistence().findByPrimaryKey(queuedMessageId);
	}

	/**
	 * Returns the queued message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message, or <code>null</code> if a queued message with the primary key could not be found
	 */
	public static QueuedMessage fetchByPrimaryKey(long queuedMessageId) {
		return getPersistence().fetchByPrimaryKey(queuedMessageId);
	}

	/**
	 * Returns all the queued messages.
	 *
	 * @return the queued messages
	 */
	public static List<QueuedMessage> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the queued messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @return the range of queued messages
	 */
	public static List<QueuedMessage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the queued messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of queued messages
	 */
	public static List<QueuedMessage> findAll(
		int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the queued messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>QueuedMessageModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of queued messages
	 * @param end the upper bound of the range of queued messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of queued messages
	 */
	public static List<QueuedMessage> findAll(
		int start, int end, OrderByComparator<QueuedMessage> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the queued messages from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of queued messages.
	 *
	 * @return the number of queued messages
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static QueuedMessagePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(QueuedMessagePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile QueuedMessagePersistence _persistence;

}