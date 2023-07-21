/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.persistence;

import com.liferay.osb.koroneiki.scion.exception.NoSuchAuthenticationTokenException;
import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the authentication token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenUtil
 * @generated
 */
@ProviderType
public interface AuthenticationTokenPersistence
	extends BasePersistence<AuthenticationToken> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuthenticationTokenUtil} to access the authentication token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the authentication tokens where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the matching authentication tokens
	 */
	public java.util.List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId);

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
	public java.util.List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end);

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
	public java.util.List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator);

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
	public java.util.List<AuthenticationToken> findByServiceProducerId(
		long serviceProducerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public AuthenticationToken findByServiceProducerId_First(
			long serviceProducerId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AuthenticationToken> orderByComparator)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns the first authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public AuthenticationToken fetchByServiceProducerId_First(
		long serviceProducerId,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator);

	/**
	 * Returns the last authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public AuthenticationToken findByServiceProducerId_Last(
			long serviceProducerId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AuthenticationToken> orderByComparator)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns the last authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public AuthenticationToken fetchByServiceProducerId_Last(
		long serviceProducerId,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator);

	/**
	 * Returns the authentication tokens before and after the current authentication token in the ordered set where serviceProducerId = &#63;.
	 *
	 * @param authenticationTokenId the primary key of the current authentication token
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public AuthenticationToken[] findByServiceProducerId_PrevAndNext(
			long authenticationTokenId, long serviceProducerId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AuthenticationToken> orderByComparator)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns all the authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the matching authentication tokens that the user has permission to view
	 */
	public java.util.List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId);

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
	public java.util.List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId, int start, int end);

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
	public java.util.List<AuthenticationToken> filterFindByServiceProducerId(
		long serviceProducerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator);

	/**
	 * Returns the authentication tokens before and after the current authentication token in the ordered set of authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param authenticationTokenId the primary key of the current authentication token
	 * @param serviceProducerId the service producer ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public AuthenticationToken[] filterFindByServiceProducerId_PrevAndNext(
			long authenticationTokenId, long serviceProducerId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AuthenticationToken> orderByComparator)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Removes all the authentication tokens where serviceProducerId = &#63; from the database.
	 *
	 * @param serviceProducerId the service producer ID
	 */
	public void removeByServiceProducerId(long serviceProducerId);

	/**
	 * Returns the number of authentication tokens where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the number of matching authentication tokens
	 */
	public int countByServiceProducerId(long serviceProducerId);

	/**
	 * Returns the number of authentication tokens that the user has permission to view where serviceProducerId = &#63;.
	 *
	 * @param serviceProducerId the service producer ID
	 * @return the number of matching authentication tokens that the user has permission to view
	 */
	public int filterCountByServiceProducerId(long serviceProducerId);

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or throws a <code>NoSuchAuthenticationTokenException</code> if it could not be found.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the matching authentication token
	 * @throws NoSuchAuthenticationTokenException if a matching authentication token could not be found
	 */
	public AuthenticationToken findByD_S(String digest, int status)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public AuthenticationToken fetchByD_S(String digest, int status);

	/**
	 * Returns the authentication token where digest = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching authentication token, or <code>null</code> if a matching authentication token could not be found
	 */
	public AuthenticationToken fetchByD_S(
		String digest, int status, boolean useFinderCache);

	/**
	 * Removes the authentication token where digest = &#63; and status = &#63; from the database.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the authentication token that was removed
	 */
	public AuthenticationToken removeByD_S(String digest, int status)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns the number of authentication tokens where digest = &#63; and status = &#63;.
	 *
	 * @param digest the digest
	 * @param status the status
	 * @return the number of matching authentication tokens
	 */
	public int countByD_S(String digest, int status);

	/**
	 * Caches the authentication token in the entity cache if it is enabled.
	 *
	 * @param authenticationToken the authentication token
	 */
	public void cacheResult(AuthenticationToken authenticationToken);

	/**
	 * Caches the authentication tokens in the entity cache if it is enabled.
	 *
	 * @param authenticationTokens the authentication tokens
	 */
	public void cacheResult(
		java.util.List<AuthenticationToken> authenticationTokens);

	/**
	 * Creates a new authentication token with the primary key. Does not add the authentication token to the database.
	 *
	 * @param authenticationTokenId the primary key for the new authentication token
	 * @return the new authentication token
	 */
	public AuthenticationToken create(long authenticationTokenId);

	/**
	 * Removes the authentication token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token that was removed
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public AuthenticationToken remove(long authenticationTokenId)
		throws NoSuchAuthenticationTokenException;

	public AuthenticationToken updateImpl(
		AuthenticationToken authenticationToken);

	/**
	 * Returns the authentication token with the primary key or throws a <code>NoSuchAuthenticationTokenException</code> if it could not be found.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token
	 * @throws NoSuchAuthenticationTokenException if a authentication token with the primary key could not be found
	 */
	public AuthenticationToken findByPrimaryKey(long authenticationTokenId)
		throws NoSuchAuthenticationTokenException;

	/**
	 * Returns the authentication token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token, or <code>null</code> if a authentication token with the primary key could not be found
	 */
	public AuthenticationToken fetchByPrimaryKey(long authenticationTokenId);

	/**
	 * Returns all the authentication tokens.
	 *
	 * @return the authentication tokens
	 */
	public java.util.List<AuthenticationToken> findAll();

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
	public java.util.List<AuthenticationToken> findAll(int start, int end);

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
	public java.util.List<AuthenticationToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator);

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
	public java.util.List<AuthenticationToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuthenticationToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the authentication tokens from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of authentication tokens.
	 *
	 * @return the number of authentication tokens
	 */
	public int countAll();

}