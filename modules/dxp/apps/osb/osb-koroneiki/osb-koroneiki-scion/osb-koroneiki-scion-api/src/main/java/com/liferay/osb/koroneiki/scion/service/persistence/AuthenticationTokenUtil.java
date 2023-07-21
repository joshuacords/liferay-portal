/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.persistence;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the authentication token service. This utility wraps <code>com.liferay.osb.koroneiki.scion.service.persistence.impl.AuthenticationTokenPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenPersistence
 * @generated
 */
public class AuthenticationTokenUtil {

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
	public static void clearCache(AuthenticationToken authenticationToken) {
		getPersistence().clearCache(authenticationToken);
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
	public static Map<Serializable, AuthenticationToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuthenticationToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuthenticationToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuthenticationToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuthenticationToken update(
		AuthenticationToken authenticationToken) {

		return getPersistence().update(authenticationToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuthenticationToken update(
		AuthenticationToken authenticationToken,
		ServiceContext serviceContext) {

		return getPersistence().update(authenticationToken, serviceContext);
	}

	/**
	 * Returns all the authentication tokens where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the matching authentication tokens
	 */
	public static List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId) {

		return getPersistence().findByServiceProducerId(serviceProducerId);
	}

	/**
	 * Returns a range of all the authentication tokens where serviceProducerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param serviceProducerId the service producer ID
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @return the range of matching authentication tokens
	 */
	public static List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end) {

		return getPersistence().findByServiceProducerId(
			serviceProducerId, start, end);
	}

	/**
	 * Returns an ordered range of all the authentication tokens where serviceProducerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param serviceProducerId the service producer ID
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching authentication tokens
	 */
	public static List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().findByServiceProducerId(
			serviceProducerId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the authentication tokens where serviceProducerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param serviceProducerId the service producer ID
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching authentication tokens
	 */
	public static List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByServiceProducerId(
			serviceProducerId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public static AuthenticationToken findByServiceProducerId_First(
			long serviceProducerId,
			OrderByComparator<AuthenticationToken> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().findByServiceProducerId_First(
			serviceProducerId, orderByComparator);
	}

	/**
	 * Returns the first authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public static AuthenticationToken fetchByServiceProducerId_First(
		long serviceProducerId,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().fetchByServiceProducerId_First(
			serviceProducerId, orderByComparator);
	}

	/**
	 * Returns the last authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public static AuthenticationToken findByServiceProducerId_Last(
			long serviceProducerId,
			OrderByComparator<AuthenticationToken> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().findByServiceProducerId_Last(
			serviceProducerId, orderByComparator);
	}

	/**
	 * Returns the last authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public static AuthenticationToken fetchByServiceProducerId_Last(
		long serviceProducerId,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().fetchByServiceProducerId_Last(
			serviceProducerId, orderByComparator);
	}

	/**
	 * Returns the authentication tokens before and after the current authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param authenticationTokenId the primary key of the current authentication token
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken[] findByServiceProducerId_PrevAndNext(
			long authenticationTokenId, long serviceProducerId,
			OrderByComparator<AuthenticationToken> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().findByServiceProducerId_PrevAndNext(
			authenticationTokenId, serviceProducerId, orderByComparator);
	}

	/**
	 * Returns all the authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the matching authentication tokens that the user has permission to view
	 */
	public static List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId) {

		return getPersistence().filterFindByServiceProducerId(
			serviceProducerId);
	}

	/**
	 * Returns a range of all the authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param serviceProducerId the service producer ID
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @return the range of matching authentication tokens that the user has permission to view
	 */
	public static List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId, int start, int end) {

		return getPersistence().filterFindByServiceProducerId(
			serviceProducerId, start, end);
	}

	/**
	 * Returns an ordered range of all the authentication tokens that the user has permissions to view where serviceProducerId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param serviceProducerId the service producer ID
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching authentication tokens that the user has permission to view
	 */
	public static List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId, int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().filterFindByServiceProducerId(
			serviceProducerId, start, end, orderByComparator);
	}

	/**
	 * Returns the authentication tokens before and after the current authentication token in the ordered set of authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param authenticationTokenId the primary key of the current authentication token
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken[]
			filterFindByServiceProducerId_PrevAndNext(
				long authenticationTokenId, long serviceProducerId,
				OrderByComparator<AuthenticationToken> orderByComparator)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().filterFindByServiceProducerId_PrevAndNext(
			authenticationTokenId, serviceProducerId, orderByComparator);
	}

	/**
	 * Removes all the authentication tokens where serviceProducerId = &#63; from the database.
	 *
	 * @param serviceProducerId the service producer ID
	 */
	public static void removeByServiceProducerId(long serviceProducerId) {
		getPersistence().removeByServiceProducerId(serviceProducerId);
	}

	/**
	 * Returns the number of authentication tokens where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the number of matching authentication tokens
	 */
	public static int countByServiceProducerId(long serviceProducerId) {
		return getPersistence().countByServiceProducerId(serviceProducerId);
	}

	/**
	 * Returns the number of authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the number of matching authentication tokens that the user has permission to view
	 */
	public static int filterCountByServiceProducerId(long serviceProducerId) {
		return getPersistence().filterCountByServiceProducerId(
			serviceProducerId);
	}

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or throws a <code>NoSuchAuthenticationTokenException</code> if it could not be found.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public static AuthenticationToken findByD_S(String digest, int status)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().findByD_S(digest, status);
	}

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public static AuthenticationToken fetchByD_S(String digest, int status) {
		return getPersistence().fetchByD_S(digest, status);
	}

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public static AuthenticationToken fetchByD_S(
		String digest, int status, boolean useFinderCache) {

		return getPersistence().fetchByD_S(digest, status, useFinderCache);
	}

	/**
	 * Removes the authentication token where digest = &#63; and status = &#63; from the database.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the authentication token that was removed
	 */
	public static AuthenticationToken removeByD_S(String digest, int status)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().removeByD_S(digest, status);
	}

	/**
	 * Returns the number of authentication tokens where digest = &#63; and status = &#63;.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the number of matching authentication tokens
	 */
	public static int countByD_S(String digest, int status) {
		return getPersistence().countByD_S(digest, status);
	}

	/**
	 * Caches the authentication token in the entity cache if it is enabled.
	 *
	 * @param authenticationToken the authentication token
	 */
	public static void cacheResult(AuthenticationToken authenticationToken) {
		getPersistence().cacheResult(authenticationToken);
	}

	/**
	 * Caches the authentication tokens in the entity cache if it is enabled.
	 *
	 * @param authenticationTokens the authentication tokens
	 */
	public static void cacheResult(
		List<AuthenticationToken> authenticationTokens) {

		getPersistence().cacheResult(authenticationTokens);
	}

	/**
	 * Creates a new authentication token with the primary key. Does not add the authentication token to the database.
	 *
	 * @param authenticationTokenId the primary key for the new authentication token
	 * @return the new authentication token
	 */
	public static AuthenticationToken create(long authenticationTokenId) {
		return getPersistence().create(authenticationTokenId);
	}

	/**
	 * Removes the authentication token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token that was removed
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken remove(long authenticationTokenId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().remove(authenticationTokenId);
	}

	public static AuthenticationToken updateImpl(
		AuthenticationToken authenticationToken) {

		return getPersistence().updateImpl(authenticationToken);
	}

	/**
	 * Returns the authentication token with the primary key or throws a <code>NoSuchAuthenticationTokenException</code> if it could not be found.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken findByPrimaryKey(
			long authenticationTokenId)
		throws com.liferay.osb.koroneiki.scion.exception.
			NoSuchAuthenticationTokenException {

		return getPersistence().findByPrimaryKey(authenticationTokenId);
	}

	/**
	 * Returns the authentication token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token, or <code>null</code> if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken fetchByPrimaryKey(
		long authenticationTokenId) {

		return getPersistence().fetchByPrimaryKey(authenticationTokenId);
	}

	/**
	 * Returns all the authentication tokens.
	 *
	 * @return the authentication tokens
	 */
	public static List<AuthenticationToken> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the authentication tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @return the range of authentication tokens
	 */
	public static List<AuthenticationToken> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the authentication tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of authentication tokens
	 */
	public static List<AuthenticationToken> findAll(
		int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the authentication tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of authentication tokens
	 */
	public static List<AuthenticationToken> findAll(
		int start, int end,
		OrderByComparator<AuthenticationToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the authentication tokens from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of authentication tokens.
	 *
	 * @return the number of authentication tokens
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuthenticationTokenPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		AuthenticationTokenPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile AuthenticationTokenPersistence _persistence;

}