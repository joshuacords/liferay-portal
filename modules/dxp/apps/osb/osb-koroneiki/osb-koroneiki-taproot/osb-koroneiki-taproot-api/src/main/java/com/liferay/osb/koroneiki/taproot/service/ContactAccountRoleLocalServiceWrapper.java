/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactAccountRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRoleLocalService
 * @generated
 */
public class ContactAccountRoleLocalServiceWrapper
	implements ContactAccountRoleLocalService,
			   ServiceWrapper<ContactAccountRoleLocalService> {

	public ContactAccountRoleLocalServiceWrapper(
		ContactAccountRoleLocalService contactAccountRoleLocalService) {

		_contactAccountRoleLocalService = contactAccountRoleLocalService;
	}

	/**
	 * Adds the contact account role to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactAccountRole the contact account role
	 * @return the contact account role that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
		addContactAccountRole(
			com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
				contactAccountRole) {

		return _contactAccountRoleLocalService.addContactAccountRole(
			contactAccountRole);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			addContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	/**
	 * Creates a new contact account role with the primary key. Does not add the contact account role to the database.
	 *
	 * @param contactAccountRolePK the primary key for the new contact account role
	 * @return the new contact account role
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
		createContactAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactAccountRolePK contactAccountRolePK) {

		return _contactAccountRoleLocalService.createContactAccountRole(
			contactAccountRolePK);
	}

	/**
	 * Deletes the contact account role from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactAccountRole the contact account role
	 * @return the contact account role that was removed
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
		deleteContactAccountRole(
			com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
				contactAccountRole) {

		return _contactAccountRoleLocalService.deleteContactAccountRole(
			contactAccountRole);
	}

	/**
	 * Deletes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws PortalException if a contact account role with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.deleteContactAccountRole(
			contactAccountRolePK);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			deleteContactAccountRole(
				long contactId, long accountId, long contactRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contactAccountRoleLocalService.dynamicQuery();
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

		return _contactAccountRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleModelImpl</code>.
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

		return _contactAccountRoleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleModelImpl</code>.
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

		return _contactAccountRoleLocalService.dynamicQuery(
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

		return _contactAccountRoleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _contactAccountRoleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
		fetchContactAccountRole(
			com.liferay.osb.koroneiki.taproot.service.persistence.
				ContactAccountRolePK contactAccountRolePK) {

		return _contactAccountRoleLocalService.fetchContactAccountRole(
			contactAccountRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _contactAccountRoleLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the contact account role with the primary key.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role
	 * @throws PortalException if a contact account role with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
			getContactAccountRole(
				com.liferay.osb.koroneiki.taproot.service.persistence.
					ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.getContactAccountRole(
			contactAccountRolePK);
	}

	/**
	 * Returns a range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of contact account roles
	 */
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactAccountRole>
			getContactAccountRoles(int start, int end) {

		return _contactAccountRoleLocalService.getContactAccountRoles(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactAccountRole>
			getContactAccountRoles(long contactId, long accountId) {

		return _contactAccountRoleLocalService.getContactAccountRoles(
			contactId, accountId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.taproot.model.ContactAccountRole>
			getContactAccountRolesByAccountId(long accountId) {

		return _contactAccountRoleLocalService.
			getContactAccountRolesByAccountId(accountId);
	}

	/**
	 * Returns the number of contact account roles.
	 *
	 * @return the number of contact account roles
	 */
	@Override
	public int getContactAccountRolesCount() {
		return _contactAccountRoleLocalService.getContactAccountRolesCount();
	}

	@Override
	public int getContactAccountRolesCount(long contactId, long accountId) {
		return _contactAccountRoleLocalService.getContactAccountRolesCount(
			contactId, accountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _contactAccountRoleLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactAccountRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactAccountRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the contact account role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactAccountRoleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactAccountRole the contact account role
	 * @return the contact account role that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
		updateContactAccountRole(
			com.liferay.osb.koroneiki.taproot.model.ContactAccountRole
				contactAccountRole) {

		return _contactAccountRoleLocalService.updateContactAccountRole(
			contactAccountRole);
	}

	@Override
	public ContactAccountRoleLocalService getWrappedService() {
		return _contactAccountRoleLocalService;
	}

	@Override
	public void setWrappedService(
		ContactAccountRoleLocalService contactAccountRoleLocalService) {

		_contactAccountRoleLocalService = contactAccountRoleLocalService;
	}

	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

}