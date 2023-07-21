/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.service.persistence.impl;

import com.liferay.osb.distributed.messaging.exception.NoSuchQueuedMessageException;
import com.liferay.osb.distributed.messaging.model.QueuedMessage;
import com.liferay.osb.distributed.messaging.model.impl.QueuedMessageImpl;
import com.liferay.osb.distributed.messaging.model.impl.QueuedMessageModelImpl;
import com.liferay.osb.distributed.messaging.service.persistence.QueuedMessagePersistence;
import com.liferay.osb.distributed.messaging.service.persistence.QueuedMessageUtil;
import com.liferay.osb.distributed.messaging.service.persistence.impl.constants.DMPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the queued message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = QueuedMessagePersistence.class)
public class QueuedMessagePersistenceImpl
	extends BasePersistenceImpl<QueuedMessage>
	implements QueuedMessagePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>QueuedMessageUtil</code> to access the queued message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		QueuedMessageImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByMessageBrokerClassName;
	private FinderPath _finderPathWithoutPaginationFindByMessageBrokerClassName;
	private FinderPath _finderPathCountByMessageBrokerClassName;

	/**
	 * Returns all the queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the matching queued messages
	 */
	@Override
	public List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName) {

		return findByMessageBrokerClassName(
			messageBrokerClassName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end) {

		return findByMessageBrokerClassName(
			messageBrokerClassName, start, end, null);
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
	@Override
	public List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return findByMessageBrokerClassName(
			messageBrokerClassName, start, end, orderByComparator, true);
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
	@Override
	public List<QueuedMessage> findByMessageBrokerClassName(
		String messageBrokerClassName, int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator,
		boolean useFinderCache) {

		messageBrokerClassName = Objects.toString(messageBrokerClassName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByMessageBrokerClassName;
				finderArgs = new Object[] {messageBrokerClassName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByMessageBrokerClassName;
			finderArgs = new Object[] {
				messageBrokerClassName, start, end, orderByComparator
			};
		}

		List<QueuedMessage> list = null;

		if (useFinderCache) {
			list = (List<QueuedMessage>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (QueuedMessage queuedMessage : list) {
					if (!messageBrokerClassName.equals(
							queuedMessage.getMessageBrokerClassName())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_QUEUEDMESSAGE_WHERE);

			boolean bindMessageBrokerClassName = false;

			if (messageBrokerClassName.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_3);
			}
			else {
				bindMessageBrokerClassName = true;

				sb.append(
					_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(QueuedMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMessageBrokerClassName) {
					queryPos.add(messageBrokerClassName);
				}

				list = (List<QueuedMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	@Override
	public QueuedMessage findByMessageBrokerClassName_First(
			String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws NoSuchQueuedMessageException {

		QueuedMessage queuedMessage = fetchByMessageBrokerClassName_First(
			messageBrokerClassName, orderByComparator);

		if (queuedMessage != null) {
			return queuedMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("messageBrokerClassName=");
		sb.append(messageBrokerClassName);

		sb.append("}");

		throw new NoSuchQueuedMessageException(sb.toString());
	}

	/**
	 * Returns the first queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	@Override
	public QueuedMessage fetchByMessageBrokerClassName_First(
		String messageBrokerClassName,
		OrderByComparator<QueuedMessage> orderByComparator) {

		List<QueuedMessage> list = findByMessageBrokerClassName(
			messageBrokerClassName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message
	 * @throws NoSuchQueuedMessageException if a matching queued message could not be found
	 */
	@Override
	public QueuedMessage findByMessageBrokerClassName_Last(
			String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws NoSuchQueuedMessageException {

		QueuedMessage queuedMessage = fetchByMessageBrokerClassName_Last(
			messageBrokerClassName, orderByComparator);

		if (queuedMessage != null) {
			return queuedMessage;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("messageBrokerClassName=");
		sb.append(messageBrokerClassName);

		sb.append("}");

		throw new NoSuchQueuedMessageException(sb.toString());
	}

	/**
	 * Returns the last queued message in the ordered set where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching queued message, or <code>null</code> if a matching queued message could not be found
	 */
	@Override
	public QueuedMessage fetchByMessageBrokerClassName_Last(
		String messageBrokerClassName,
		OrderByComparator<QueuedMessage> orderByComparator) {

		int count = countByMessageBrokerClassName(messageBrokerClassName);

		if (count == 0) {
			return null;
		}

		List<QueuedMessage> list = findByMessageBrokerClassName(
			messageBrokerClassName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public QueuedMessage[] findByMessageBrokerClassName_PrevAndNext(
			long queuedMessageId, String messageBrokerClassName,
			OrderByComparator<QueuedMessage> orderByComparator)
		throws NoSuchQueuedMessageException {

		messageBrokerClassName = Objects.toString(messageBrokerClassName, "");

		QueuedMessage queuedMessage = findByPrimaryKey(queuedMessageId);

		Session session = null;

		try {
			session = openSession();

			QueuedMessage[] array = new QueuedMessageImpl[3];

			array[0] = getByMessageBrokerClassName_PrevAndNext(
				session, queuedMessage, messageBrokerClassName,
				orderByComparator, true);

			array[1] = queuedMessage;

			array[2] = getByMessageBrokerClassName_PrevAndNext(
				session, queuedMessage, messageBrokerClassName,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected QueuedMessage getByMessageBrokerClassName_PrevAndNext(
		Session session, QueuedMessage queuedMessage,
		String messageBrokerClassName,
		OrderByComparator<QueuedMessage> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_QUEUEDMESSAGE_WHERE);

		boolean bindMessageBrokerClassName = false;

		if (messageBrokerClassName.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_3);
		}
		else {
			bindMessageBrokerClassName = true;

			sb.append(
				_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(QueuedMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindMessageBrokerClassName) {
			queryPos.add(messageBrokerClassName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						queuedMessage)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<QueuedMessage> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the queued messages where messageBrokerClassName = &#63; from the database.
	 *
	 * @param messageBrokerClassName the message broker class name
	 */
	@Override
	public void removeByMessageBrokerClassName(String messageBrokerClassName) {
		for (QueuedMessage queuedMessage :
				findByMessageBrokerClassName(
					messageBrokerClassName, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(queuedMessage);
		}
	}

	/**
	 * Returns the number of queued messages where messageBrokerClassName = &#63;.
	 *
	 * @param messageBrokerClassName the message broker class name
	 * @return the number of matching queued messages
	 */
	@Override
	public int countByMessageBrokerClassName(String messageBrokerClassName) {
		messageBrokerClassName = Objects.toString(messageBrokerClassName, "");

		FinderPath finderPath = _finderPathCountByMessageBrokerClassName;

		Object[] finderArgs = new Object[] {messageBrokerClassName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_QUEUEDMESSAGE_WHERE);

			boolean bindMessageBrokerClassName = false;

			if (messageBrokerClassName.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_3);
			}
			else {
				bindMessageBrokerClassName = true;

				sb.append(
					_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindMessageBrokerClassName) {
					queryPos.add(messageBrokerClassName);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_2 =
			"queuedMessage.messageBrokerClassName = ?";

	private static final String
		_FINDER_COLUMN_MESSAGEBROKERCLASSNAME_MESSAGEBROKERCLASSNAME_3 =
			"(queuedMessage.messageBrokerClassName IS NULL OR queuedMessage.messageBrokerClassName = '')";

	public QueuedMessagePersistenceImpl() {
		setModelClass(QueuedMessage.class);

		setModelImplClass(QueuedMessageImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the queued message in the entity cache if it is enabled.
	 *
	 * @param queuedMessage the queued message
	 */
	@Override
	public void cacheResult(QueuedMessage queuedMessage) {
		entityCache.putResult(
			entityCacheEnabled, QueuedMessageImpl.class,
			queuedMessage.getPrimaryKey(), queuedMessage);

		queuedMessage.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the queued messages in the entity cache if it is enabled.
	 *
	 * @param queuedMessages the queued messages
	 */
	@Override
	public void cacheResult(List<QueuedMessage> queuedMessages) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (queuedMessages.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (QueuedMessage queuedMessage : queuedMessages) {
			if (entityCache.getResult(
					entityCacheEnabled, QueuedMessageImpl.class,
					queuedMessage.getPrimaryKey()) == null) {

				cacheResult(queuedMessage);
			}
			else {
				queuedMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all queued messages.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(QueuedMessageImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the queued message.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(QueuedMessage queuedMessage) {
		entityCache.removeResult(
			entityCacheEnabled, QueuedMessageImpl.class,
			queuedMessage.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<QueuedMessage> queuedMessages) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (QueuedMessage queuedMessage : queuedMessages) {
			entityCache.removeResult(
				entityCacheEnabled, QueuedMessageImpl.class,
				queuedMessage.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, QueuedMessageImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new queued message with the primary key. Does not add the queued message to the database.
	 *
	 * @param queuedMessageId the primary key for the new queued message
	 * @return the new queued message
	 */
	@Override
	public QueuedMessage create(long queuedMessageId) {
		QueuedMessage queuedMessage = new QueuedMessageImpl();

		queuedMessage.setNew(true);
		queuedMessage.setPrimaryKey(queuedMessageId);

		return queuedMessage;
	}

	/**
	 * Removes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	@Override
	public QueuedMessage remove(long queuedMessageId)
		throws NoSuchQueuedMessageException {

		return remove((Serializable)queuedMessageId);
	}

	/**
	 * Removes the queued message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the queued message
	 * @return the queued message that was removed
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	@Override
	public QueuedMessage remove(Serializable primaryKey)
		throws NoSuchQueuedMessageException {

		Session session = null;

		try {
			session = openSession();

			QueuedMessage queuedMessage = (QueuedMessage)session.get(
				QueuedMessageImpl.class, primaryKey);

			if (queuedMessage == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQueuedMessageException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(queuedMessage);
		}
		catch (NoSuchQueuedMessageException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected QueuedMessage removeImpl(QueuedMessage queuedMessage) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(queuedMessage)) {
				queuedMessage = (QueuedMessage)session.get(
					QueuedMessageImpl.class, queuedMessage.getPrimaryKeyObj());
			}

			if (queuedMessage != null) {
				session.delete(queuedMessage);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (queuedMessage != null) {
			clearCache(queuedMessage);
		}

		return queuedMessage;
	}

	@Override
	public QueuedMessage updateImpl(QueuedMessage queuedMessage) {
		boolean isNew = queuedMessage.isNew();

		if (!(queuedMessage instanceof QueuedMessageModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(queuedMessage.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					queuedMessage);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in queuedMessage proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom QueuedMessage implementation " +
					queuedMessage.getClass());
		}

		QueuedMessageModelImpl queuedMessageModelImpl =
			(QueuedMessageModelImpl)queuedMessage;

		if (isNew && (queuedMessage.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				queuedMessage.setCreateDate(date);
			}
			else {
				queuedMessage.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(queuedMessage);

				queuedMessage.setNew(false);
			}
			else {
				session.evict(queuedMessage);

				session.saveOrUpdate(queuedMessage);
			}

			session.flush();
			session.clear();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				queuedMessageModelImpl.getMessageBrokerClassName()
			};

			finderCache.removeResult(
				_finderPathCountByMessageBrokerClassName, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByMessageBrokerClassName, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((queuedMessageModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByMessageBrokerClassName.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					queuedMessageModelImpl.getOriginalMessageBrokerClassName()
				};

				finderCache.removeResult(
					_finderPathCountByMessageBrokerClassName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMessageBrokerClassName,
					args);

				args = new Object[] {
					queuedMessageModelImpl.getMessageBrokerClassName()
				};

				finderCache.removeResult(
					_finderPathCountByMessageBrokerClassName, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByMessageBrokerClassName,
					args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, QueuedMessageImpl.class,
			queuedMessage.getPrimaryKey(), queuedMessage, false);

		queuedMessage.resetOriginalValues();

		return queuedMessage;
	}

	/**
	 * Returns the queued message with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the queued message
	 * @return the queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	@Override
	public QueuedMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQueuedMessageException {

		QueuedMessage queuedMessage = fetchByPrimaryKey(primaryKey);

		if (queuedMessage == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQueuedMessageException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return queuedMessage;
	}

	/**
	 * Returns the queued message with the primary key or throws a <code>NoSuchQueuedMessageException</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message
	 * @throws NoSuchQueuedMessageException if a queued message with the primary key could not be found
	 */
	@Override
	public QueuedMessage findByPrimaryKey(long queuedMessageId)
		throws NoSuchQueuedMessageException {

		return findByPrimaryKey((Serializable)queuedMessageId);
	}

	/**
	 * Returns the queued message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param queuedMessageId the primary key of the queued message
	 * @return the queued message, or <code>null</code> if a queued message with the primary key could not be found
	 */
	@Override
	public QueuedMessage fetchByPrimaryKey(long queuedMessageId) {
		return fetchByPrimaryKey((Serializable)queuedMessageId);
	}

	/**
	 * Returns all the queued messages.
	 *
	 * @return the queued messages
	 */
	@Override
	public List<QueuedMessage> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<QueuedMessage> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<QueuedMessage> findAll(
		int start, int end,
		OrderByComparator<QueuedMessage> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<QueuedMessage> findAll(
		int start, int end, OrderByComparator<QueuedMessage> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<QueuedMessage> list = null;

		if (useFinderCache) {
			list = (List<QueuedMessage>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_QUEUEDMESSAGE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_QUEUEDMESSAGE;

				sql = sql.concat(QueuedMessageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<QueuedMessage>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the queued messages from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (QueuedMessage queuedMessage : findAll()) {
			remove(queuedMessage);
		}
	}

	/**
	 * Returns the number of queued messages.
	 *
	 * @return the number of queued messages
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_QUEUEDMESSAGE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "queuedMessageId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_QUEUEDMESSAGE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return QueuedMessageModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the queued message persistence.
	 */
	@Activate
	public void activate() {
		QueuedMessageModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		QueuedMessageModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, QueuedMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, QueuedMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByMessageBrokerClassName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, QueuedMessageImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMessageBrokerClassName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByMessageBrokerClassName =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled, QueuedMessageImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByMessageBrokerClassName",
				new String[] {String.class.getName()},
				QueuedMessageModelImpl.MESSAGEBROKERCLASSNAME_COLUMN_BITMASK |
				QueuedMessageModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByMessageBrokerClassName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMessageBrokerClassName",
			new String[] {String.class.getName()});

		QueuedMessageUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		QueuedMessageUtil.setPersistence(null);

		entityCache.removeCache(QueuedMessageImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = DMPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.distributed.messaging.model.QueuedMessage"),
			true);
	}

	@Override
	@Reference(
		target = DMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_QUEUEDMESSAGE =
		"SELECT queuedMessage FROM QueuedMessage queuedMessage";

	private static final String _SQL_SELECT_QUEUEDMESSAGE_WHERE =
		"SELECT queuedMessage FROM QueuedMessage queuedMessage WHERE ";

	private static final String _SQL_COUNT_QUEUEDMESSAGE =
		"SELECT COUNT(queuedMessage) FROM QueuedMessage queuedMessage";

	private static final String _SQL_COUNT_QUEUEDMESSAGE_WHERE =
		"SELECT COUNT(queuedMessage) FROM QueuedMessage queuedMessage WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "queuedMessage.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No QueuedMessage exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No QueuedMessage exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		QueuedMessagePersistenceImpl.class);

}