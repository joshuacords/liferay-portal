/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AuthenticationToken. This utility wraps
 * <code>com.liferay.osb.koroneiki.scion.service.impl.AuthenticationTokenLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenLocalService
 * @generated
 */
public class AuthenticationTokenLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.scion.service.impl.AuthenticationTokenLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the authentication token to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthenticationTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authenticationToken the authentication token
	 * @return the authentication token that was added
	 */
	public static AuthenticationToken addAuthenticationToken(
		AuthenticationToken authenticationToken) {

		return getService().addAuthenticationToken(authenticationToken);
	}

	public static AuthenticationToken addAuthenticationToken(
			long userId, long serviceProducerId, String name, String token)
		throws PortalException {

		return getService().addAuthenticationToken(
			userId, serviceProducerId, name, token);
	}

	/**
	 * Creates a new authentication token with the primary key. Does not add the authentication token to the database.
	 *
	 * @param authenticationTokenId the primary key for the new authentication token
	 * @return the new authentication token
	 */
	public static AuthenticationToken createAuthenticationToken(
		long authenticationTokenId) {

		return getService().createAuthenticationToken(authenticationTokenId);
	}

	/**
	 * Deletes the authentication token from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthenticationTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authenticationToken the authentication token
	 * @return the authentication token that was removed
	 * @throws PortalException
	 */
	public static AuthenticationToken deleteAuthenticationToken(
			AuthenticationToken authenticationToken)
		throws PortalException {

		return getService().deleteAuthenticationToken(authenticationToken);
	}

	/**
	 * Deletes the authentication token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthenticationTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token that was removed
	 * @throws PortalException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken deleteAuthenticationToken(
			long authenticationTokenId)
		throws PortalException {

		return getService().deleteAuthenticationToken(authenticationTokenId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AuthenticationToken fetchAuthenticationToken(
		long authenticationTokenId) {

		return getService().fetchAuthenticationToken(authenticationTokenId);
	}

	public static AuthenticationToken fetchAuthenticationToken(
		String digest, int status) {

		return getService().fetchAuthenticationToken(digest, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the authentication token with the primary key.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token
	 * @throws PortalException if a authentication token with the primary key could not be found
	 */
	public static AuthenticationToken getAuthenticationToken(
			long authenticationTokenId)
		throws PortalException {

		return getService().getAuthenticationToken(authenticationTokenId);
	}

	/**
	 * Returns a range of all the authentication tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.scion.model.impl.AuthenticationTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of authentication tokens
	 * @param end the upper bound of the range of authentication tokens (not inclusive)
	 * @return the range of authentication tokens
	 */
	public static List<AuthenticationToken> getAuthenticationTokens(
		int start, int end) {

		return getService().getAuthenticationTokens(start, end);
	}

	public static List<AuthenticationToken> getAuthenticationTokens(
		long serviceProducerId, int start, int end) {

		return getService().getAuthenticationTokens(
			serviceProducerId, start, end);
	}

	/**
	 * Returns the number of authentication tokens.
	 *
	 * @return the number of authentication tokens
	 */
	public static int getAuthenticationTokensCount() {
		return getService().getAuthenticationTokensCount();
	}

	public static int getAuthenticationTokensCount(long serviceProducerId) {
		return getService().getAuthenticationTokensCount(serviceProducerId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the authentication token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AuthenticationTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param authenticationToken the authentication token
	 * @return the authentication token that was updated
	 */
	public static AuthenticationToken updateAuthenticationToken(
		AuthenticationToken authenticationToken) {

		return getService().updateAuthenticationToken(authenticationToken);
	}

	public static AuthenticationToken updateAuthenticationToken(
			long authenticationTokenId, String name)
		throws PortalException {

		return getService().updateAuthenticationToken(
			authenticationTokenId, name);
	}

	public static AuthenticationToken updateStatus(
			long authenticationTokenId, int status)
		throws PortalException {

		return getService().updateStatus(authenticationTokenId, status);
	}

	public static AuthenticationTokenLocalService getService() {
		return _service;
	}

	public static void setService(AuthenticationTokenLocalService service) {
		_service = service;
	}

	private static volatile AuthenticationTokenLocalService _service;

}