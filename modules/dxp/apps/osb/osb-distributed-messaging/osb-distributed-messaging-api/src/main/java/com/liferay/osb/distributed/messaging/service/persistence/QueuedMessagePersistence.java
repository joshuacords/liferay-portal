/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.service.persistence;

import com.liferay.osb.distributed.messaging.exception.NoSuchQueuedMessageException;
import com.liferay.osb.distributed.messaging.model.QueuedMessage;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the queued message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessageUtil
 * @generated
 */
@ProviderType
public interface QueuedMessagePersistence
	extends BasePersistence<QueuedMessage> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link QueuedMessageUtil} to access the queued message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the matching queued messages
	 */
	public java.util.List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName);

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
	public java.util.List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end);

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
	public java.util.List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator);

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
	public java.util.List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	public QueuedMessage findByMessageBrokerClassName_First(
			String messageBrokerClassName,
			com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
				orderByComparator)
		throws NoSuchQueuedMessageException;

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	public QueuedMessage fetchByMessageBrokerClassName_First(
		String messageBrokerClassName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator);

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	public QueuedMessage findByMessageBrokerClassName_Last(
			String messageBrokerClassName,
			com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
				orderByComparator)
		throws NoSuchQueuedMessageException;

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	public QueuedMessage fetchByMessageBrokerClassName_Last(
		String messageBrokerClassName,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator);

	/**
	 * Returns the queued messages before and after the current queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param queuedMessageId the primary key of the current queued message
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public QueuedMessage[] findByMessageBrokerClassName_PrevAndNext(
			long queuedMessageId, String messageBrokerClassName,
			com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
				orderByComparator)
		throws NoSuchQueuedMessageException;

	/**
	 * Removes all the queued messages where messageBrokerClassName = &#63; from the database.
	 *
	 * @param messageBrokerClassName the message broker class name
	 */
	public void removeByMessageBrokerClassName(String messageBrokerClassName);

	/**
	 * Returns the number of queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the number of matching queued messages
	 */
	public int countByMessageBrokerClassName(String messageBrokerClassName);

	/**
	 * Caches the queued message in the entity cache if it is enabled.
	 *
	 * @param queuedMessage the queued message
	 */
	public void cacheResult(QueuedMessage queuedMessage);

	/**
	 * Caches the queued messages in the entity cache if it is enabled.
	 *
	 * @param queuedMessages the queued messages
	 */
	public void cacheResult(java.util.List<QueuedMessage> queuedMessages);

	/**
	 * Creates a new queued message with the primary key. Does not add the queued message to the database.
	 *
	 * @param queuedMessageId the primary key for the new queued message
	 * @return the new queued message
	 */
	public QueuedMessage create(long queuedMessageId);

	/**
	 * Removes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public QueuedMessage remove(long queuedMessageId)
		throws NoSuchQueuedMessageException;

	public QueuedMessage updateImpl(QueuedMessage queuedMessage);

	/**
	 * Returns the queued message with the primary key or throws a <code>NoSuchQueuedMessageException</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	public QueuedMessage findByPrimaryKey(long queuedMessageId)
		throws NoSuchQueuedMessageException;

	/**
	 * Returns the queued message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message, or <code>null</code> if a queued message with the primary key could not be found
	 */
	public QueuedMessage fetchByPrimaryKey(long queuedMessageId);

	/**
	 * Returns all the queued messages.
	 *
	 * @return the queued messages
	 */
	public java.util.List<QueuedMessage> findAll();

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
	public java.util.List<QueuedMessage> findAll(int start, int end);

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
	public java.util.List<QueuedMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator);

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
	public java.util.List<QueuedMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<QueuedMessage>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the queued messages from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of queued messages.
	 *
	 * @return the number of queued messages
	 */
	public int countAll();

}