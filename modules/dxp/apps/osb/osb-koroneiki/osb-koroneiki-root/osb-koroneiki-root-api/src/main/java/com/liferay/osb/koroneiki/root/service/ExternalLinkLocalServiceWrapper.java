/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExternalLinkLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkLocalService
 * @generated
 */
public class ExternalLinkLocalServiceWrapper
	implements ExternalLinkLocalService,
			   ServiceWrapper<ExternalLinkLocalService> {

	public ExternalLinkLocalServiceWrapper(
		ExternalLinkLocalService externalLinkLocalService) {

		_externalLinkLocalService = externalLinkLocalService;
	}

	/**
	 * Adds the external link to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExternalLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param externalLink the external link
	 * @return the external link that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink addExternalLink(
		com.liferay.osb.koroneiki.root.model.ExternalLink externalLink) {

		return _externalLinkLocalService.addExternalLink(externalLink);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink addExternalLink(
			long userId, long classNameId, long classPK, String domain,
			String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.addExternalLink(
			userId, classNameId, classPK, domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink addExternalLink(
			long userId, String className, long classPK, String domain,
			String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.addExternalLink(
			userId, className, classPK, domain, entityName, entityId);
	}

	/**
	 * Creates a new external link with the primary key. Does not add the external link to the database.
	 *
	 * @param externalLinkId the primary key for the new external link
	 * @return the new external link
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink createExternalLink(
		long externalLinkId) {

		return _externalLinkLocalService.createExternalLink(externalLinkId);
	}

	/**
	 * Deletes the external link from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExternalLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param externalLink the external link
	 * @return the external link that was removed
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink deleteExternalLink(
		com.liferay.osb.koroneiki.root.model.ExternalLink externalLink) {

		return _externalLinkLocalService.deleteExternalLink(externalLink);
	}

	/**
	 * Deletes the external link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExternalLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link that was removed
	 * @throws PortalException if a external link with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink deleteExternalLink(
			long externalLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.deleteExternalLink(externalLinkId);
	}

	@Override
	public void deleteExternalLinks(long classNameId, long classPK) {
		_externalLinkLocalService.deleteExternalLinks(classNameId, classPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _externalLinkLocalService.dynamicQuery();
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

		return _externalLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkModelImpl</code>.
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

		return _externalLinkLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkModelImpl</code>.
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

		return _externalLinkLocalService.dynamicQuery(
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

		return _externalLinkLocalService.dynamicQueryCount(dynamicQuery);
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

		return _externalLinkLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink fetchExternalLink(
		long externalLinkId) {

		return _externalLinkLocalService.fetchExternalLink(externalLinkId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _externalLinkLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the external link with the primary key.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link
	 * @throws PortalException if a external link with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink getExternalLink(
			long externalLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.getExternalLink(externalLinkId);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink getExternalLink(
			String externalLinkKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.getExternalLink(externalLinkKey);
	}

	/**
	 * Returns a range of all the external links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external links
	 * @param end the upper bound of the range of external links (not inclusive)
	 * @return the range of external links
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks(int start, int end) {

		return _externalLinkLocalService.getExternalLinks(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks(long classNameId, long classPK, int start, int end) {

		return _externalLinkLocalService.getExternalLinks(
			classNameId, classPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
			getExternalLinks(
				long classNameId, String domain, String entityName,
				String entityId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.getExternalLinks(
			classNameId, domain, entityName, entityId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks(String className, long classPK, int start, int end) {

		return _externalLinkLocalService.getExternalLinks(
			className, classPK, start, end);
	}

	/**
	 * Returns the number of external links.
	 *
	 * @return the number of external links
	 */
	@Override
	public int getExternalLinksCount() {
		return _externalLinkLocalService.getExternalLinksCount();
	}

	@Override
	public int getExternalLinksCount(long classNameId, long classPK) {
		return _externalLinkLocalService.getExternalLinksCount(
			classNameId, classPK);
	}

	@Override
	public int getExternalLinksCount(
			long classNameId, String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.getExternalLinksCount(
			classNameId, domain, entityName, entityId);
	}

	@Override
	public int getExternalLinksCount(String className, long classPK) {
		return _externalLinkLocalService.getExternalLinksCount(
			className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _externalLinkLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _externalLinkLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<String> search(String domain) {
		return _externalLinkLocalService.search(domain);
	}

	@Override
	public java.util.List<String> search(String domain, String entityName) {
		return _externalLinkLocalService.search(domain, entityName);
	}

	/**
	 * Updates the external link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ExternalLinkLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param externalLink the external link
	 * @return the external link that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink updateExternalLink(
		com.liferay.osb.koroneiki.root.model.ExternalLink externalLink) {

		return _externalLinkLocalService.updateExternalLink(externalLink);
	}

	@Override
	public com.liferay.osb.koroneiki.root.model.ExternalLink updateExternalLink(
			long externalLinkId, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _externalLinkLocalService.updateExternalLink(
			externalLinkId, entityId);
	}

	@Override
	public ExternalLinkLocalService getWrappedService() {
		return _externalLinkLocalService;
	}

	@Override
	public void setWrappedService(
		ExternalLinkLocalService externalLinkLocalService) {

		_externalLinkLocalService = externalLinkLocalService;
	}

	private ExternalLinkLocalService _externalLinkLocalService;

}