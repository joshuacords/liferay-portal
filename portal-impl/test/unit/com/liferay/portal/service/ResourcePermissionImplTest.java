package com.liferay.portal.service;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.RegistryUtil;
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
		_initializeBasicVariables();

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		_resourcePermissionPersistence = Mockito.mock(ResourcePermissionPersistence.class);
		_resourcePermissionLocalService = new ResourcePermissionLocalServiceImpl();
		_journalArticleFolderProxyFactory = new JournalArticleFolderProxyFactory(_companyId);

		MockitoAnnotations.initMocks(this);
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

		//_mockRolesForClass(_journalArticleClassName, RoleConstants.GUEST, RoleConstants.OWNER);

		JournalFolderProxy journalFolderProxy =
			_journalArticleFolderProxyFactory.createJournalFolderProxy(
				RoleConstants.GUEST, RoleConstants.OWNER);

//		System.out.println("journalFolderProxy.treePath " + journalFolderProxy.getTreePath());

		JournalFolderProxy innerJournalFolderProxy =
			_journalArticleFolderProxyFactory.createJournalFolderProxy(
				journalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		JournalArticleProxy journalArticleProxy =
			_journalArticleFolderProxyFactory.createJournalArticleProxy(
				innerJournalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		Set<Set<Role>> roleSets =  _resourcePermissionLocalService.getDynamicInheritanceRoles(
		_companyId, _journalArticleClassName, _scope, _resourcePrimKey,
			_primKey, _actionId);


	}

	class JournalArticleFolderProxyFactory {
		JournalArticleFolderProxyFactory(long companyId) {
			_companyId = companyId;
		}

		JournalArticleProxy createJournalArticleProxy(String ... roleNames) {
			return new JournalArticleProxy(roleNames);
		}

		JournalArticleProxy createJournalArticleProxy(
			JournalFolderProxy journalFolderProxy, String ... roleNames) {
			return new JournalArticleProxy(journalFolderProxy, roleNames);
		}

		JournalFolderProxy createJournalFolderProxy(String ... roleNames) {
			return new JournalFolderProxy(roleNames);
		}

		JournalFolderProxy createJournalFolderProxy(
			JournalFolderProxy journalFolderProxy, String ... roleNames) {
			return new JournalFolderProxy(journalFolderProxy, roleNames);
		}

		private long _companyId;
	}

	class JournalArticleProxy {
		JournalArticleProxy(String[] roleNames) {
			new JournalArticleProxy(null, roleNames);
		}

		JournalArticleProxy(JournalFolderProxy journalFolderProxy, String[] roleNames) {
			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
			_primKey = StringUtil.toString(RandomTestUtil.randomLong());
			_roleNames = roleNames;
			_createTreePath(journalFolderProxy);
			_mockRoles();
		}

		//journalArticle uses different primKey
		private void _mockRoles() {
			try {
				_roles = _mockRolesForClass(
					_journalArticleClassName, _primKey, _roleNames);
			} catch (Exception exception) {
				_roles = null;
			}
		}

		private void _createTreePath(JournalFolderProxy journalFolderProxy) {
			_treePath = journalFolderProxy != null ? journalFolderProxy.getTreePath() : "0/";
		}
		public String getResourcePrimKey() {
			return _resourcePrimKey;
		}

		public String getPrimKey() {
			return _primKey;
		}

		public String[] getRoleNames() {
			return _roleNames;
		}

		public String getTreePath() {
			return _treePath;
		}

		private List<Role> _roles;
		private String _resourcePrimKey;
		private String _primKey;
		private String _treePath;
		private String[] _roleNames;
	}

	class JournalFolderProxy {
		JournalFolderProxy(String[] roleNames) {
			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
			//primKey may be the same with a JournalFolder
			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
			_roleNames = roleNames;
			_treePath = "0/";
			System.out.println("constructor treePath " + _treePath);
			_mockRoles();
		}

		JournalFolderProxy(JournalFolderProxy journalFolderProxy, String[] roleNames) {
			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
			//primKey may be the same with a JournalFolder
			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
			_roleNames = roleNames;
			_createTreePath(journalFolderProxy);
//			System.out.println("constructor treePath " + _treePath);
			_mockRoles();
		}

		//journalFolder uses different primKey
		private void _mockRoles() {
			try {
				_roles = _mockRolesForClass(
					_journalFolderClassName, _resourcePrimKey, _roleNames);
			} catch (Exception exception) {
				System.out.println("Failed to mock roles");
				_roles = null;
			}
		}

		private void _createTreePath(JournalFolderProxy journalFolderProxy) {
			_treePath = journalFolderProxy != null ? journalFolderProxy.getTreePath() : "0/";
//			System.out.println("treePath " + _treePath);
//			System.out.println("ResourcePrimKey " + _resourcePrimKey);
			_treePath = _treePath.concat(_resourcePrimKey);
//			System.out.println("new treePath " + _treePath);
		}

		public String getResourcePrimKey() {
			return _resourcePrimKey;
		}

//		public String getPrimKey() {
//			return _primKey;
//		}

		public String[] getRoleNames() {
			return _roleNames;
		}

		public String getTreePath() {
//			System.out.println("getTreePath " + _treePath);
			return _treePath;
		}

		private List<Role> _roles;
		private String _resourcePrimKey;
		//private String _primKey;
		private String _treePath;
		private String[] _roleNames;
	}

	private List<Role> _mockRolesForClass(
		String className, String primKey, String ... roles) throws Exception {

		List<Role> classRoles = new ArrayList<>();

		for(String role : roles) {
			classRoles.add(_mockRole(role));
		}

		_mockResourcePermissionLocalServiceGetRoles(
			_companyId, className, _scope, primKey, classRoles);

		return classRoles;
	}

	private Role _mockRole(String roleString) throws Exception {
		Role role = Mockito.mock(Role.class);

		Mockito.when(
			role.getDescriptiveName()
		).thenReturn(
			roleString
		);

		return role;
	}

	//_mockResourcePermissionPersistenceFindByC_N_S_P
	private void _mockResourcePermissionLocalServiceGetRoles(
		long companyId, String className, int scope, String resourcePrimKey, List<Role> roles) throws Exception {

		ResourceAction resourceAction = Mockito.mock(ResourceAction.class);

		Mockito.when(
			_resourceActionLocalService.getResourceAction(className, _actionId)
		).thenReturn(
			resourceAction
		);

		List<ResourcePermission> resourcePermissions =
			_createResourcePermissionMocks(resourceAction, roles);

		_addRolesToRoleLocalService(roles);

//		Mockito.when(
//			_resourcePermissionPersistence.findByC_N_S_P(
//				companyId, className, scope, resourcePrimKey)
//		).thenReturn(
//			resourcePermissions
//		);

		Mockito.doReturn(
			resourcePermissions
		).when(
			_resourcePermissionPersistence
		).findByC_N_S_P(companyId, className, scope, resourcePrimKey);

	}

	private void _addRolesToRoleLocalService(List<Role> roles) throws Exception {
		for(Role role : roles) {
//			Mockito.when(
//				_roleLocalService.getRole(role.getRoleId())
//			).thenReturn(
//				role
//			);

			Mockito.doReturn(
				role
			).when(
				_roleLocalService
			).getRole(role.getRoleId());
		}
	}

	private List<ResourcePermission> _createResourcePermissionMocks(
		ResourceAction resourceAction, List<Role> roles) {

		List<ResourcePermission> resourcePermissions = new ArrayList<>();

		for(Role role : roles) {
			ResourcePermission resourcePermission =
				Mockito.mock(ResourcePermission.class);

//			Mockito.when(
//				resourcePermission.hasAction(resourceAction)
//			).thenReturn(
//				true
//			);

			Mockito.doReturn(
				true
			).when(
				resourcePermission
			).hasAction(resourceAction);

//			Mockito.when(
//				resourcePermission.getRoleId()
//			).thenReturn(
//				role.getRoleId()
//			);

			Mockito.doReturn(
				role.getRoleId()
			).when(
				resourcePermission
			).getRoleId();

			resourcePermissions.add(resourcePermission);
		}

		return resourcePermissions;
	}

	List<List<ResourcePermission>> resourcePermissionsList = new ArrayList<>();

	@Mock
	private ResourceActionLocalService _resourceActionLocalService;

	@Mock
	protected ResourcePermissionPersistence _resourcePermissionPersistence;

	@Mock
	private RoleLocalService _roleLocalService;

	private JournalArticleFolderProxyFactory _journalArticleFolderProxyFactory;
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
