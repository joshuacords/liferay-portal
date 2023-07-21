/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuthenticationTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenLocalService
 * @generated
 */
public class AuthenticationTokenLocalServiceWrapper
	implements AuthenticationTokenLocalService,
			   ServiceWrapper<AuthenticationTokenLocalService> {

	public AuthenticationTokenLocalServiceWrapper(
		AuthenticationTokenLocalService authenticationTokenLocalService) {

		_authenticationTokenLocalService = authenticationTokenLocalService;
	}

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
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
		addAuthenticationToken(
			com.liferay.osb.koroneiki.scion.model.AuthenticationToken
				authenticationToken) {

		return _authenticationTokenLocalService.addAuthenticationToken(
			authenticationToken);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			addAuthenticationToken(
				long userId, long serviceProducerId, String name, String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.addAuthenticationToken(
			userId, serviceProducerId, name, token);
	}

	/**
	 * Creates a new authentication token with the primary key. Does not add the authentication token to the database.
	 *
	 * @param authenticationTokenId the primary key for the new authentication token
	 * @return the new authentication token
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
		createAuthenticationToken(long authenticationTokenId) {

		return _authenticationTokenLocalService.createAuthenticationToken(
			authenticationTokenId);
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
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			deleteAuthenticationToken(
				com.liferay.osb.koroneiki.scion.model.AuthenticationToken
					authenticationToken)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.deleteAuthenticationToken(
			authenticationToken);
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
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			deleteAuthenticationToken(long authenticationTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.deleteAuthenticationToken(
			authenticationTokenId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _authenticationTokenLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _authenticationTokenLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _authenticationTokenLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _authenticationTokenLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _authenticationTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _authenticationTokenLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
		fetchAuthenticationToken(long authenticationTokenId) {

		return _authenticationTokenLocalService.fetchAuthenticationToken(
			authenticationTokenId);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
		fetchAuthenticationToken(String digest, int status) {

		return _authenticationTokenLocalService.fetchAuthenticationToken(
			digest, status);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _authenticationTokenLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the authentication token with the primary key.
	 *
	 * @param authenticationTokenId the primary key of the authentication token
	 * @return the authentication token
	 * @throws PortalException if a authentication token with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			getAuthenticationToken(long authenticationTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.getAuthenticationToken(
			authenticationTokenId);
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
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.scion.model.AuthenticationToken>
			getAuthenticationTokens(int start, int end) {

		return _authenticationTokenLocalService.getAuthenticationTokens(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.scion.model.AuthenticationToken>
			getAuthenticationTokens(
				long serviceProducerId, int start, int end) {

		return _authenticationTokenLocalService.getAuthenticationTokens(
			serviceProducerId, start, end);
	}

	/**
	 * Returns the number of authentication tokens.
	 *
	 * @return the number of authentication tokens
	 */
	@Override
	public int getAuthenticationTokensCount() {
		return _authenticationTokenLocalService.getAuthenticationTokensCount();
	}

	@Override
	public int getAuthenticationTokensCount(long serviceProducerId) {
		return _authenticationTokenLocalService.getAuthenticationTokensCount(
			serviceProducerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _authenticationTokenLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _authenticationTokenLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.getPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
		updateAuthenticationToken(
			com.liferay.osb.koroneiki.scion.model.AuthenticationToken
				authenticationToken) {

		return _authenticationTokenLocalService.updateAuthenticationToken(
			authenticationToken);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateAuthenticationToken(long authenticationTokenId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.updateAuthenticationToken(
			authenticationTokenId, name);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateStatus(long authenticationTokenId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenLocalService.updateStatus(
			authenticationTokenId, status);
	}

	@Override
	public AuthenticationTokenLocalService getWrappedService() {
		return _authenticationTokenLocalService;
	}

	@Override
	public void setWrappedService(
		AuthenticationTokenLocalService authenticationTokenLocalService) {

		_authenticationTokenLocalService = authenticationTokenLocalService;
	}

	private AuthenticationTokenLocalService _authenticationTokenLocalService;

}