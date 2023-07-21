/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountFieldException;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldImpl;
import com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldModelImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountFieldPersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountFieldUtil;
import com.liferay.osb.koroneiki.taproot.service.persistence.impl.constants.KoroneikiPersistenceConstants;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the account field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AccountFieldPersistence.class)
public class AccountFieldPersistenceImpl
	extends BasePersistenceImpl<AccountField>
	implements AccountFieldPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AccountFieldUtil</code> to access the account field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AccountFieldImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account fields
	 */
	@Override
	public List<AccountField> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @return the range of matching account fields
	 */
	@Override
	public List<AccountField> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account fields
	 */
	@Override
	public List<AccountField> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountField> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account fields
	 */
	@Override
	public List<AccountField> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountField> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAccountId;
				finderArgs = new Object[] {accountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAccountId;
			finderArgs = new Object[] {
				accountId, start, end, orderByComparator
			};
		}

		List<AccountField> list = null;

		if (useFinderCache) {
			list = (List<AccountField>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountField accountField : list) {
					if (accountId != accountField.getAccountId()) {
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

			sb.append(_SQL_SELECT_ACCOUNTFIELD_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccountFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<AccountField>)QueryUtil.list(
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
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	@Override
	public AccountField findByAccountId_First(
			long accountId, OrderByComparator<AccountField> orderByComparator)
		throws NoSuchAccountFieldException {

		AccountField accountField = fetchByAccountId_First(
			accountId, orderByComparator);

		if (accountField != null) {
			return accountField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchAccountFieldException(sb.toString());
	}

	/**
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field, or <code>null</code> if a matching account field could not be found
	 */
	@Override
	public AccountField fetchByAccountId_First(
		long accountId, OrderByComparator<AccountField> orderByComparator) {

		List<AccountField> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	@Override
	public AccountField findByAccountId_Last(
			long accountId, OrderByComparator<AccountField> orderByComparator)
		throws NoSuchAccountFieldException {

		AccountField accountField = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (accountField != null) {
			return accountField;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchAccountFieldException(sb.toString());
	}

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field, or <code>null</code> if a matching account field could not be found
	 */
	@Override
	public AccountField fetchByAccountId_Last(
		long accountId, OrderByComparator<AccountField> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<AccountField> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account fields before and after the current account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountFieldId the primary key of the current account field
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	@Override
	public AccountField[] findByAccountId_PrevAndNext(
			long accountFieldId, long accountId,
			OrderByComparator<AccountField> orderByComparator)
		throws NoSuchAccountFieldException {

		AccountField accountField = findByPrimaryKey(accountFieldId);

		Session session = null;

		try {
			session = openSession();

			AccountField[] array = new AccountFieldImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, accountField, accountId, orderByComparator, true);

			array[1] = accountField;

			array[2] = getByAccountId_PrevAndNext(
				session, accountField, accountId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountField getByAccountId_PrevAndNext(
		Session session, AccountField accountField, long accountId,
		OrderByComparator<AccountField> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACCOUNTFIELD_WHERE);

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

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
			sb.append(AccountFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(accountField)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccountField> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account fields where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (AccountField accountField :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(accountField);
		}
	}

	/**
	 * Returns the number of account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account fields
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCOUNTFIELD_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

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

	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 =
		"accountField.accountId = ?";

	public AccountFieldPersistenceImpl() {
		setModelClass(AccountField.class);

		setModelImplClass(AccountFieldImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the account field in the entity cache if it is enabled.
	 *
	 * @param accountField the account field
	 */
	@Override
	public void cacheResult(AccountField accountField) {
		entityCache.putResult(
			entityCacheEnabled, AccountFieldImpl.class,
			accountField.getPrimaryKey(), accountField);

		accountField.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the account fields in the entity cache if it is enabled.
	 *
	 * @param accountFields the account fields
	 */
	@Override
	public void cacheResult(List<AccountField> accountFields) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (accountFields.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AccountField accountField : accountFields) {
			if (entityCache.getResult(
					entityCacheEnabled, AccountFieldImpl.class,
					accountField.getPrimaryKey()) == null) {

				cacheResult(accountField);
			}
			else {
				accountField.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account fields.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountFieldImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account field.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountField accountField) {
		entityCache.removeResult(
			entityCacheEnabled, AccountFieldImpl.class,
			accountField.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccountField> accountFields) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountField accountField : accountFields) {
			entityCache.removeResult(
				entityCacheEnabled, AccountFieldImpl.class,
				accountField.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, AccountFieldImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new account field with the primary key. Does not add the account field to the database.
	 *
	 * @param accountFieldId the primary key for the new account field
	 * @return the new account field
	 */
	@Override
	public AccountField create(long accountFieldId) {
		AccountField accountField = new AccountFieldImpl();

		accountField.setNew(true);
		accountField.setPrimaryKey(accountFieldId);

		accountField.setCompanyId(CompanyThreadLocal.getCompanyId());

		return accountField;
	}

	/**
	 * Removes the account field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field that was removed
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	@Override
	public AccountField remove(long accountFieldId)
		throws NoSuchAccountFieldException {

		return remove((Serializable)accountFieldId);
	}

	/**
	 * Removes the account field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account field
	 * @return the account field that was removed
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	@Override
	public AccountField remove(Serializable primaryKey)
		throws NoSuchAccountFieldException {

		Session session = null;

		try {
			session = openSession();

			AccountField accountField = (AccountField)session.get(
				AccountFieldImpl.class, primaryKey);

			if (accountField == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountFieldException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(accountField);
		}
		catch (NoSuchAccountFieldException noSuchEntityException) {
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
	protected AccountField removeImpl(AccountField accountField) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountField)) {
				accountField = (AccountField)session.get(
					AccountFieldImpl.class, accountField.getPrimaryKeyObj());
			}

			if (accountField != null) {
				session.delete(accountField);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (accountField != null) {
			clearCache(accountField);
		}

		return accountField;
	}

	@Override
	public AccountField updateImpl(AccountField accountField) {
		boolean isNew = accountField.isNew();

		if (!(accountField instanceof AccountFieldModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(accountField.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					accountField);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in accountField proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AccountField implementation " +
					accountField.getClass());
		}

		AccountFieldModelImpl accountFieldModelImpl =
			(AccountFieldModelImpl)accountField;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(accountField);

				accountField.setNew(false);
			}
			else {
				accountField = (AccountField)session.merge(accountField);
			}
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
			Object[] args = new Object[] {accountFieldModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((accountFieldModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					accountFieldModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {accountFieldModelImpl.getAccountId()};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, AccountFieldImpl.class,
			accountField.getPrimaryKey(), accountField, false);

		accountField.resetOriginalValues();

		return accountField;
	}

	/**
	 * Returns the account field with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account field
	 * @return the account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	@Override
	public AccountField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountFieldException {

		AccountField accountField = fetchByPrimaryKey(primaryKey);

		if (accountField == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountFieldException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return accountField;
	}

	/**
	 * Returns the account field with the primary key or throws a <code>NoSuchAccountFieldException</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	@Override
	public AccountField findByPrimaryKey(long accountFieldId)
		throws NoSuchAccountFieldException {

		return findByPrimaryKey((Serializable)accountFieldId);
	}

	/**
	 * Returns the account field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field, or <code>null</code> if a account field with the primary key could not be found
	 */
	@Override
	public AccountField fetchByPrimaryKey(long accountFieldId) {
		return fetchByPrimaryKey((Serializable)accountFieldId);
	}

	/**
	 * Returns all the account fields.
	 *
	 * @return the account fields
	 */
	@Override
	public List<AccountField> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @return the range of account fields
	 */
	@Override
	public List<AccountField> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account fields
	 */
	@Override
	public List<AccountField> findAll(
		int start, int end, OrderByComparator<AccountField> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account fields
	 */
	@Override
	public List<AccountField> findAll(
		int start, int end, OrderByComparator<AccountField> orderByComparator,
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

		List<AccountField> list = null;

		if (useFinderCache) {
			list = (List<AccountField>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ACCOUNTFIELD);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTFIELD;

				sql = sql.concat(AccountFieldModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AccountField>)QueryUtil.list(
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
	 * Removes all the account fields from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountField accountField : findAll()) {
			remove(accountField);
		}
	}

	/**
	 * Returns the number of account fields.
	 *
	 * @return the number of account fields
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ACCOUNTFIELD);

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
		return "accountFieldId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ACCOUNTFIELD;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AccountFieldModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account field persistence.
	 */
	@Activate
	public void activate() {
		AccountFieldModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		AccountFieldModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			AccountFieldModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		AccountFieldUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AccountFieldUtil.setPersistence(null);

		entityCache.removeCache(AccountFieldImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.taproot.model.AccountField"),
			true);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_ACCOUNTFIELD =
		"SELECT accountField FROM AccountField accountField";

	private static final String _SQL_SELECT_ACCOUNTFIELD_WHERE =
		"SELECT accountField FROM AccountField accountField WHERE ";

	private static final String _SQL_COUNT_ACCOUNTFIELD =
		"SELECT COUNT(accountField) FROM AccountField accountField";

	private static final String _SQL_COUNT_ACCOUNTFIELD_WHERE =
		"SELECT COUNT(accountField) FROM AccountField accountField WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "accountField.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AccountField exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AccountField exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AccountFieldPersistenceImpl.class);

}