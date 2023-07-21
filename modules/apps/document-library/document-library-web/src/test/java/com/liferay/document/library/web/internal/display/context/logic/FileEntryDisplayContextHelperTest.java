/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.display.context.logic;

import com.liferay.document.library.web.internal.security.permission.resource.DLFileEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Objects;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Cristina González
 */
public class FileEntryDisplayContextHelperTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testIsCancelCheckoutDocumentActionAvailableWithCheckedOutAndOverridePermissionAndMoreThat1Version()
		throws PortalException {

		FileEntry fileEntry = Mockito.mock(FileEntry.class);

		Mockito.when(
			fileEntry.getFileVersionsCount(Mockito.anyInt())
		).thenReturn(
			2
		);

		Mockito.when(
			fileEntry.isCheckedOut()
		).thenReturn(
			true
		);

		_configureDLFileEntryPermission(true, false);

		FileEntryDisplayContextHelper fileEntryDisplayContextHelper =
			new FileEntryDisplayContextHelper(null, fileEntry);

		Assert.assertTrue(
			fileEntryDisplayContextHelper.
				isCancelCheckoutDocumentActionAvailable());
	}

	@Test
	public void testIsCancelCheckoutDocumentActionAvailableWithLockedFileAnd1Version()
		throws PortalException {

		FileEntry fileEntry = Mockito.mock(FileEntry.class);

		Mockito.when(
			fileEntry.getFileVersionsCount(Mockito.anyInt())
		).thenReturn(
			1
		);

		Mockito.when(
			fileEntry.hasLock()
		).thenReturn(
			true
		);

		Mockito.when(
			fileEntry.isSupportsLocking()
		).thenReturn(
			true
		);

		_configureDLFileEntryPermission(true, true);

		FileEntryDisplayContextHelper fileEntryDisplayContextHelper =
			new FileEntryDisplayContextHelper(null, fileEntry);

		Assert.assertFalse(
			fileEntryDisplayContextHelper.
				isCancelCheckoutDocumentActionAvailable());
	}

	@Test
	public void testIsCancelCheckoutDocumentActionAvailableWithLockedFileAndMoreThan1Version()
		throws PortalException {

		FileEntry fileEntry = Mockito.mock(FileEntry.class);

		Mockito.when(
			fileEntry.getFileVersionsCount(Mockito.anyInt())
		).thenReturn(
			2
		);

		Mockito.when(
			fileEntry.hasLock()
		).thenReturn(
			true
		);

		Mockito.when(
			fileEntry.isSupportsLocking()
		).thenReturn(
			true
		);

		_configureDLFileEntryPermission(true, true);

		FileEntryDisplayContextHelper fileEntryDisplayContextHelper =
			new FileEntryDisplayContextHelper(null, fileEntry);

		Assert.assertTrue(
			fileEntryDisplayContextHelper.
				isCancelCheckoutDocumentActionAvailable());
	}

	@Test
	public void testIsCancelCheckoutDocumentActionAvailableWithNullFileEntry()
		throws PortalException {

		_configureDLFileEntryPermission(true, true);

		FileEntryDisplayContextHelper fileEntryDisplayContextHelper =
			new FileEntryDisplayContextHelper(null, null);

		Assert.assertFalse(
			fileEntryDisplayContextHelper.
				isCancelCheckoutDocumentActionAvailable());
	}

	public class MockModelResourcePermission
		implements ModelResourcePermission<FileEntry> {

		public MockModelResourcePermission(
			boolean overrideCheckOutPermission, boolean updatePermission) {

			_overrideCheckOutPermission = overrideCheckOutPermission;
			_updatePermission = updatePermission;
		}

		@Override
		public void check(
				PermissionChecker permissionChecker, FileEntry model,
				String actionId)
			throws PortalException {
		}

		@Override
		public void check(
				PermissionChecker permissionChecker, long primaryKey,
				String actionId)
			throws PortalException {
		}

		@Override
		public boolean contains(
				PermissionChecker permissionChecker, FileEntry model,
				String actionId)
			throws PortalException {

			return _hasPermission(actionId);
		}

		@Override
		public boolean contains(
				PermissionChecker permissionChecker, long primaryKey,
				String actionId)
			throws PortalException {

			return _hasPermission(actionId);
		}

		@Override
		public String getModelName() {
			return null;
		}

		@Override
		public PortletResourcePermission getPortletResourcePermission() {
			return null;
		}

		private boolean _hasPermission(String actionId) {
			if (Objects.equals(actionId, ActionKeys.OVERRIDE_CHECKOUT)) {
				return _overrideCheckOutPermission;
			}
			else if (Objects.equals(actionId, ActionKeys.UPDATE)) {
				return _updatePermission;
			}

			return false;
		}

		private final boolean _overrideCheckOutPermission;
		private final boolean _updatePermission;

	}

	private void _configureDLFileEntryPermission(
		boolean overrideCheckOutPermission, boolean updatePermission) {

		ReflectionTestUtils.setField(
			_dlFileEntryPermission, "_dlFileEntryModelResourcePermission",
			new MockModelResourcePermission(
				overrideCheckOutPermission, updatePermission));

		ReflectionTestUtils.setField(
			_dlFileEntryPermission, "_fileEntryModelResourcePermission",
			new MockModelResourcePermission(
				overrideCheckOutPermission, updatePermission));
	}

	private final DLFileEntryPermission _dlFileEntryPermission =
		new DLFileEntryPermission();

}