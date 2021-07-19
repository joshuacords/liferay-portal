package com.liferay.portal.service;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import jodd.util.StringUtil;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResourcePermissionImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		_initializeBasicVariables();

		_resourcePermissionLocalService = new ResourcePermissionLocalServiceImpl();
	}

	private void _initializeBasicVariables() {
		_companyId = RandomTestUtil.randomLong();
		_journalArticleClassName = "com.liferay.journal.model.JournalArticle";
		_journalFolderClassName = "com.liferay.journal.model.JournalFolder";
		_scope = 4;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_actionId = ActionKeys.VIEW;
	}


	@Test
	public void getDynamicInheritanceRolesOnlyGuest() throws Exception {

		Set<Set<Role>> roleSets =  _resourcePermissionLocalService.getDynamicInheritanceRoles(
		_companyId, _journalArticleClassName, _scope, _resourcePrimKey,
			_primKey, _actionId);


	}

	//_mockResourcePermissionPersistenceFindByC_N_S_P
	private void _mockResourcePermissionLocalServiceGetRoles(
		long companyId, String className, int scope, String resourcePrimKey, List<Role> roles) throws Exception {

		List<ResourcePermission> resourcePermissions =
			_createResourcePermissionMocks(roles);

		_addRolesToRoleLocalService(roles);

		Mockito.when(
			_resourcePermissionPersistence.findByC_N_S_P(
				companyId, className, scope, resourcePrimKey)
		).thenReturn(
			resourcePermissions
		);

		ResourceAction resourceAction = Mockito.mock(ResourceAction.class);

		Mockito.when(
			_resourceActionLocalService.getResourceAction(className, _actionId)
		).thenReturn(
			resourceAction
		);
	}

	private void _addRolesToRoleLocalService(List<Role> roles) throws Exception {
		for(Role role : roles) {
			Mockito.when(
				_roleLocalService.getRole(role.getRoleId())
			).thenReturn(
				role
			);
		}
	}

	private List<ResourcePermission> _createResourcePermissionMocks(List<Role> roles) {

		List<ResourcePermission> resourcePermissions = new ArrayList<>();

		for(Role role : roles) {
			ResourcePermission resourcePermission =
				Mockito.mock(ResourcePermission.class);

			Mockito.when(
				resourcePermission.hasAction(Mockito.any())
			).thenReturn(
				true
			);

			Mockito.when(
				resourcePermission.getRoleId()
			).thenReturn(
				role.getRoleId()
			);

			resourcePermissions.add(resourcePermission);
		}

		return resourcePermissions;
	}

	List<List<ResourcePermission>> resourcePermissionsList = new ArrayList<>();

	@Mock
	private ResourceActionLocalService _resourceActionLocalService;

	@Mock
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	@Mock
	private RoleLocalService _roleLocalService;

	private long _companyId;
	private String _journalArticleClassName;
	private String _journalFolderClassName;
	private int _scope;
	private String _resourcePrimKey;
	private String _primKey;
	private String _actionId;
	private ResourcePermissionLocalService _resourcePermissionLocalService;
	//=		new ResourcePermissionLocalServiceImpl();
}
