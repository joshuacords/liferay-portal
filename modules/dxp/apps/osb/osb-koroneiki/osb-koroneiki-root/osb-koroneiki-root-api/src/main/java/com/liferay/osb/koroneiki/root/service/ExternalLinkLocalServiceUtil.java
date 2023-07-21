/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ExternalLink. This utility wraps
 * <code>com.liferay.osb.koroneiki.root.service.impl.ExternalLinkLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLinkLocalService
 * @generated
 */
public class ExternalLinkLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.root.service.impl.ExternalLinkLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static ExternalLink addExternalLink(ExternalLink externalLink) {
		return getService().addExternalLink(externalLink);
	}

	public static ExternalLink addExternalLink(
			long userId, long classNameId, long classPK, String domain,
			String entityName, String entityId)
		throws PortalException {

		return getService().addExternalLink(
			userId, classNameId, classPK, domain, entityName, entityId);
	}

	public static ExternalLink addExternalLink(
			long userId, String className, long classPK, String domain,
			String entityName, String entityId)
		throws PortalException {

		return getService().addExternalLink(
			userId, className, classPK, domain, entityName, entityId);
	}

	/**
	 * Creates a new external link with the primary key. Does not add the external link to the database.
	 *
	 * @param externalLinkId the primary key for the new external link
	 * @return the new external link
	 */
	public static ExternalLink createExternalLink(long externalLinkId) {
		return getService().createExternalLink(externalLinkId);
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
	public static ExternalLink deleteExternalLink(ExternalLink externalLink) {
		return getService().deleteExternalLink(externalLink);
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
	public static ExternalLink deleteExternalLink(long externalLinkId)
		throws PortalException {

		return getService().deleteExternalLink(externalLinkId);
	}

	public static void deleteExternalLinks(long classNameId, long classPK) {
		getService().deleteExternalLinks(classNameId, classPK);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.root.model.impl.ExternalLinkModelImpl</code>.
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

	public static ExternalLink fetchExternalLink(long externalLinkId) {
		return getService().fetchExternalLink(externalLinkId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the external link with the primary key.
	 *
	 * @param externalLinkId the primary key of the external link
	 * @return the external link
	 * @throws PortalException if a external link with the primary key could not be found
	 */
	public static ExternalLink getExternalLink(long externalLinkId)
		throws PortalException {

		return getService().getExternalLink(externalLinkId);
	}

	public static ExternalLink getExternalLink(String externalLinkKey)
		throws PortalException {

		return getService().getExternalLink(externalLinkKey);
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
	public static List<ExternalLink> getExternalLinks(int start, int end) {
		return getService().getExternalLinks(start, end);
	}

	public static List<ExternalLink> getExternalLinks(
		long classNameId, long classPK, int start, int end) {

		return getService().getExternalLinks(classNameId, classPK, start, end);
	}

	public static List<ExternalLink> getExternalLinks(
			long classNameId, String domain, String entityName, String entityId,
			int start, int end)
		throws PortalException {

		return getService().getExternalLinks(
			classNameId, domain, entityName, entityId, start, end);
	}

	public static List<ExternalLink> getExternalLinks(
		String className, long classPK, int start, int end) {

		return getService().getExternalLinks(className, classPK, start, end);
	}

	/**
	 * Returns the number of external links.
	 *
	 * @return the number of external links
	 */
	public static int getExternalLinksCount() {
		return getService().getExternalLinksCount();
	}

	public static int getExternalLinksCount(long classNameId, long classPK) {
		return getService().getExternalLinksCount(classNameId, classPK);
	}

	public static int getExternalLinksCount(
			long classNameId, String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getExternalLinksCount(
			classNameId, domain, entityName, entityId);
	}

	public static int getExternalLinksCount(String className, long classPK) {
		return getService().getExternalLinksCount(className, classPK);
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

	public static List<String> search(String domain) {
		return getService().search(domain);
	}

	public static List<String> search(String domain, String entityName) {
		return getService().search(domain, entityName);
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
	public static ExternalLink updateExternalLink(ExternalLink externalLink) {
		return getService().updateExternalLink(externalLink);
	}

	public static ExternalLink updateExternalLink(
			long externalLinkId, String entityId)
		throws PortalException {

		return getService().updateExternalLink(externalLinkId, entityId);
	}

	public static ExternalLinkLocalService getService() {
		return _service;
	}

	public static void setService(ExternalLinkLocalService service) {
		_service = service;
	}

	private static volatile ExternalLinkLocalService _service;

}