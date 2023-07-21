/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ContactRole. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContactRoleLocalService
 * @generated
 */
public class ContactRoleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactRoleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the contact role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRole the contact role
	 * @return the contact role that was added
	 */
	public static ContactRole addContactRole(ContactRole contactRole) {
		return getService().addContactRole(contactRole);
	}

	public static ContactRole addContactRole(
			long userId, String name, String description, String type)
		throws PortalException {

		return getService().addContactRole(userId, name, description, type);
	}

	public static void checkMemberRoles() throws PortalException {
		getService().checkMemberRoles();
	}

	/**
	 * Creates a new contact role with the primary key. Does not add the contact role to the database.
	 *
	 * @param contactRoleId the primary key for the new contact role
	 * @return the new contact role
	 */
	public static ContactRole createContactRole(long contactRoleId) {
		return getService().createContactRole(contactRoleId);
	}

	/**
	 * Deletes the contact role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRole the contact role
	 * @return the contact role that was removed
	 * @throws PortalException
	 */
	public static ContactRole deleteContactRole(ContactRole contactRole)
		throws PortalException {

		return getService().deleteContactRole(contactRole);
	}

	/**
	 * Deletes the contact role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role that was removed
	 * @throws PortalException if a contact role with the primary key could not be found
	 */
	public static ContactRole deleteContactRole(long contactRoleId)
		throws PortalException {

		return getService().deleteContactRole(contactRoleId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleModelImpl</code>.
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

	public static ContactRole fetchContactRole(long contactRoleId) {
		return getService().fetchContactRole(contactRoleId);
	}

	public static ContactRole fetchContactRole(String name, String type) {
		return getService().fetchContactRole(name, type);
	}

	/**
	 * Returns the contact role with the matching UUID and company.
	 *
	 * @param uuid the contact role's UUID
	 * @param companyId the primary key of the company
	 * @return the matching contact role, or <code>null</code> if a matching contact role could not be found
	 */
	public static ContactRole fetchContactRoleByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchContactRoleByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<ContactRole> getContactAccountContactRoles(
		long accountId, long contactId, String[] types, int start, int end) {

		return getService().getContactAccountContactRoles(
			accountId, contactId, types, start, end);
	}

	public static int getContactAccountContactRolesCount(
		long accountId, long contactId, String[] types) {

		return getService().getContactAccountContactRolesCount(
			accountId, contactId, types);
	}

	public static List<ContactRole> getContactContactRoles(
		long contactId, int start, int end) {

		return getService().getContactContactRoles(contactId, start, end);
	}

	/**
	 * Returns the contact role with the primary key.
	 *
	 * @param contactRoleId the primary key of the contact role
	 * @return the contact role
	 * @throws PortalException if a contact role with the primary key could not be found
	 */
	public static ContactRole getContactRole(long contactRoleId)
		throws PortalException {

		return getService().getContactRole(contactRoleId);
	}

	public static ContactRole getContactRole(String contactRoleKey)
		throws PortalException {

		return getService().getContactRole(contactRoleKey);
	}

	public static ContactRole getContactRole(String name, String type)
		throws PortalException {

		return getService().getContactRole(name, type);
	}

	/**
	 * Returns the contact role with the matching UUID and company.
	 *
	 * @param uuid the contact role's UUID
	 * @param companyId the primary key of the company
	 * @return the matching contact role
	 * @throws PortalException if a matching contact role could not be found
	 */
	public static ContactRole getContactRoleByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getContactRoleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the contact roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact roles
	 * @param end the upper bound of the range of contact roles (not inclusive)
	 * @return the range of contact roles
	 */
	public static List<ContactRole> getContactRoles(int start, int end) {
		return getService().getContactRoles(start, end);
	}

	public static List<ContactRole> getContactRoles(
		String type, int start, int end) {

		return getService().getContactRoles(type, start, end);
	}

	/**
	 * Returns the number of contact roles.
	 *
	 * @return the number of contact roles
	 */
	public static int getContactRolesCount() {
		return getService().getContactRolesCount();
	}

	public static int getContactRolesCount(String type) {
		return getService().getContactRolesCount(type);
	}

	public static List<ContactRole> getContactTeamContactRoles(
		long teamId, long contactId, String[] types, int start, int end) {

		return getService().getContactTeamContactRoles(
			teamId, contactId, types, start, end);
	}

	public static int getContactTeamContactRolesCount(
		long teamId, long contactId, String[] types) {

		return getService().getContactTeamContactRolesCount(
			teamId, contactId, types);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static ContactRole getMemberContactRole(String type) {
		return getService().getMemberContactRole(type);
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

	public static ContactRole reindex(long contactRoleId)
		throws PortalException {

		return getService().reindex(contactRoleId);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String type, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().search(companyId, type, keywords, start, end, sort);
	}

	/**
	 * Updates the contact role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactRole the contact role
	 * @return the contact role that was updated
	 */
	public static ContactRole updateContactRole(ContactRole contactRole) {
		return getService().updateContactRole(contactRole);
	}

	public static ContactRole updateContactRole(
			long contactRoleId, String name, String description)
		throws PortalException {

		return getService().updateContactRole(contactRoleId, name, description);
	}

	public static ContactRoleLocalService getService() {
		return _service;
	}

	public static void setService(ContactRoleLocalService service) {
		_service = service;
	}

	private static volatile ContactRoleLocalService _service;

}