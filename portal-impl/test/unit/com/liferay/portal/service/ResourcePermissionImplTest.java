package com.liferay.portal.service;

import com.liferay.portal.kernel.model.BaseChildModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


//test command: ant test-class -Dtest.class=ResourcePermissionImplTest -Djvm.debug=true
public class ResourcePermissionImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_initializeBasicVariables();

		RegistryUtil.setRegistry(new BasicRegistryImpl());

		_resourcePermissionLocalService = new ResourcePermissionLocalServiceImpl();
		_journalArticleFolderProxyFactory = new JournalArticleFolderProxyFactory(_companyId);

		MockitoAnnotations.initMocks(this);

		_initializeReflections();
		_mockPersistedModelLocalServices();
	}

	private void _initializeReflections() {
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "resourcePermissionPersistence",
			_resourcePermissionPersistence);
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "resourceActionLocalService",
			_resourceActionLocalService);
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "roleLocalService",
			_roleLocalService);
		ReflectionTestUtil.setFieldValue(
			_resourcePermissionLocalService, "persistedModelLocalServiceRegistry",
			_persistedModelLocalServiceRegistry);
	}

	private void _mockPersistedModelLocalServices() {
		Mockito.doReturn(
			_journalArticlePersistedModelLocalService
		).when(
			_persistedModelLocalServiceRegistry
		).getPersistedModelLocalService(_journalArticleClassName);

		Mockito.doReturn(
			_journalFolderPersistedModelLocalService
		).when(
			_persistedModelLocalServiceRegistry
		).getPersistedModelLocalService(_journalFolderClassName);


		//need to implement mock roles instead of just role names

		try {
			Mockito.doReturn(
				_roleProxyFactory.getRole(RoleConstants.GUEST)
			).when(
				_roleLocalService
			).getRole(
				_companyId, RoleConstants.GUEST);
		} catch (Exception exception) {
			System.out.println("Failed to mock fetching Guest role");
		}
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

		JournalFolderProxy journalFolderProxy =
			_journalArticleFolderProxyFactory.createJournalFolderProxy(
				RoleConstants.GUEST, RoleConstants.OWNER);

		JournalFolderProxy innerJournalFolderProxy =
			_journalArticleFolderProxyFactory.createJournalFolderProxy(
				journalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		JournalArticleProxy journalArticleProxy =
			_journalArticleFolderProxyFactory.createJournalArticleProxy(
				innerJournalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		Set<Set<Role>> roleSets =  _resourcePermissionLocalService.getDynamicInheritanceRoles(
		_companyId, _journalArticleClassName, _scope,
			journalArticleProxy.getResourcePrimKey(),
			journalArticleProxy.getPrimKey(), _actionId);

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
			_parentClassPK = journalFolderProxy.getResourcePrimKey();
			_createTreePath(journalFolderProxy);
			_mockRoles(roleNames);
			_mockPersistedModel();
		}

		private void _mockPersistedModel() {

			_journalArticlePersistedBaseChildModel = Mockito.mock(PersistenceBaseChild.class);

			Mockito.doReturn(
				_parentClassPK
			).when(
				_journalArticlePersistedBaseChildModel
			).getParentClassPK();

			Mockito.doReturn(
				_journalFolderClassName
			).when(
				_journalArticlePersistedBaseChildModel
			).getParentClassName();

			try {
				Mockito.doReturn(
					_journalArticlePersistedBaseChildModel
				).when(
					_journalArticlePersistedModelLocalService
				).getPersistedModel(Long.valueOf(_primKey));
			} catch (Exception exception) {
				System.out.println("_mockPersistedModel failed");
			}
		}

		private void _mockRoles(String[] roleNames) {
			try {
				//need to add all roles at once
				List<Role> roles = new ArrayList<>();

				for(String roleName : roleNames) {
					roles.add(_roleProxyFactory.getRole(roleName));
				}

				_roles.addAll(roles);
				_roleProxyFactory.setRoleToAsset(_journalArticleClassName, _resourcePrimKey, roleNames);
			} catch (Exception exception) {
				_roles = null;
				System.out.println("Failed to mock JournalArticle roles");
			}
		}


		//journalArticle uses different primKey? needed to switch back to resource
//		private void _mockRoles2() {
//			try {
//				_roles = _mockRolesForClass(
//					_journalArticleClassName, _resourcePrimKey, _roleNames);
//			} catch (Exception exception) {
//				_roles = null;
//				System.out.println("Failed to mock JournalArticle roles");
//			}
//		}

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

		private String _parentClassPK;
		private List<Role> _roles = new ArrayList<>();
		private String _resourcePrimKey;
		private String _primKey;
		private String _treePath;
		private String[] _roleNames;
		private PersistenceBaseChild _journalArticlePersistedBaseChildModel;
	}

	public abstract class PersistenceBaseChild implements BaseChildModel, PersistedModel {}

	class JournalFolderProxy {
		JournalFolderProxy(String[] roleNames) {
			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
			//primKey may be the same with a JournalFolder
			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
			_roleNames = roleNames;
			_treePath = "0/";
			_mockRoles(roleNames);
		}

		JournalFolderProxy(JournalFolderProxy journalFolderProxy, String[] roleNames) {
			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
			//primKey may be the same with a JournalFolder
			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
			_roleNames = roleNames;
			_createTreePath(journalFolderProxy);
			_mockRoles(roleNames);
		}

		private void _mockRoles(String[] roleNames) {
			try {
//				for(String roleName : roleNames) {
//					_roles.add(_roleProxyFactory.getRole(roleName));
//					_roleProxyFactory.setRoleToAsset(_journalArticleClassName, _resourcePrimKey, roleName);
//				}

				List<Role> roles = new ArrayList<>();

				for(String roleName : roleNames) {
					roles.add(_roleProxyFactory.getRole(roleName));
				}

				_roles.addAll(roles);
				_roleProxyFactory.setRoleToAsset(_journalFolderClassName, _resourcePrimKey, roleNames);
			} catch (Exception exception) {
				_roles = null;
				System.out.println("Failed to mock JournalFolder roles");
			}
		}

		//journalFolder uses different primKey
//		private void _mockRoles() {
//			try {
//				_roles = _mockRolesForClass(
//					_journalFolderClassName, _resourcePrimKey, _roleNames);
//			} catch (Exception exception) {
//				System.out.println("Failed to mock JournalFolder roles");
//				_roles = null;
//			}
//		}

		private void _createTreePath(JournalFolderProxy journalFolderProxy) {
			_treePath = journalFolderProxy != null ? journalFolderProxy.getTreePath() : "0/";
			_treePath = _treePath.concat(_resourcePrimKey);
		}

		public String getResourcePrimKey() {
			return _resourcePrimKey;
		}

		public String[] getRoleNames() {
			return _roleNames;
		}

		public String getTreePath() {
			return _treePath;
		}

		private List<Role> _roles = new ArrayList<>();
		private String _resourcePrimKey;
		private String _treePath;
		private String[] _roleNames;
	}

//	private List<Role> _mockRolesForClass(
//		String className, String primKey, String ... roles) throws Exception {
//
//		List<Role> classRoles = new ArrayList<>();
//
//		for(String role : roles) {
//			classRoles.add(_mockRole(role));
//		}
//
//		_mockResourcePermissionLocalServiceGetRoles(
//			_companyId, className, _scope, primKey, classRoles);
//
//		return classRoles;
//	}

	class RoleProxyFactory {
		Map<String, RoleProxy> _roleProxies = new HashMap<>();

		public Role getRole(String roleName) {
			if (_roleProxies.containsKey(roleName)) {
				return _roleProxies.get(roleName).getRole();
			}

			RoleProxy roleProxy = new RoleProxy(roleName);
			_roleProxies.put(roleName, roleProxy);
			return roleProxy.getRole();
		}

		public RoleProxy getRoleProxy(String roleName) {
			if (_roleProxies.containsKey(roleName)) {
				return _roleProxies.get(roleName);
			}

			RoleProxy roleProxy = new RoleProxy(roleName);
			_roleProxies.put(roleName, roleProxy);
			return roleProxy;
		}

		public void setRoleToAsset(String className, String primKey, String ... roleNames) {
			List<Role> classRoles = new ArrayList<>();

			for(String roleName : roleNames) {
				classRoles.add(getRole(roleName));
			}

			try {
				_mockResourcePermissionLocalServiceGetRolesInside(
					_companyId, className, _scope, primKey, classRoles);
			} catch (Exception exception) {
				System.out.println("Failed to mock ResourcePermissionLocalService roles");
			}
		}

		private void _mockResourcePermissionLocalServiceGetRolesInside(
			long companyId, String className, int scope, String resourcePrimKey, List<Role> roles) throws Exception {

			ResourceAction resourceAction = Mockito.mock(ResourceAction.class);

//			Mockito.when(
//				_resourceActionLocalService.getResourceAction(className, _actionId)
//			).thenReturn(
//				resourceAction
//			);

			Mockito.doReturn(
				resourceAction
			).when(
				_resourceActionLocalService
			).getResourceAction(className, _actionId);

			List<ResourcePermission> resourcePermissions =
				_createResourcePermissionMocksInside(resourceAction, roles);

			_addRolesToRoleLocalServiceInside(roles);

			Mockito.doReturn(
				resourcePermissions
			).when(
				_resourcePermissionPersistence
			).findByC_N_S_P(companyId, className, scope, resourcePrimKey);

		}

		private List<ResourcePermission> _createResourcePermissionMocksInside(
			ResourceAction resourceAction, List<Role> roles) {

			List<ResourcePermission> resourcePermissions = new ArrayList<>();

			for(Role role : roles) {
				ResourcePermission resourcePermission =
					Mockito.mock(ResourcePermission.class);

				Mockito.doReturn(
					true
				).when(
					resourcePermission
				).hasAction(resourceAction);

				Mockito.doReturn(
					role.getRoleId()
				).when(
					resourcePermission
				).getRoleId();

				resourcePermissions.add(resourcePermission);
			}

			return resourcePermissions;
		}

		private void _addRolesToRoleLocalServiceInside(List<Role> roles) throws Exception {
			for(Role role : roles) {

				Mockito.when(
					_roleLocalService.getRole(role.getRoleId())
				).thenReturn(
					role
				);
			}
		}
	}

	class RoleProxy{
		RoleProxy(String roleName) {
			_role = Mockito.mock(Role.class);
			_roleId = RandomTestUtil.randomLong();
			_roleName = roleName;
			_mockRole();
		}

		private void _mockRole() {
			try {
//				Mockito.when(
//					_roleLocalService.getRole(Long.parseLong(_roleId))
//				).thenReturn(
//					_role
//				);

				Mockito.doReturn(
					_role
				).when(
					_roleLocalService
				).getRole(_roleId);

				Mockito.doReturn(
					_role
				).when(
					_roleLocalService
				).getRole(_companyId, _roleName);

				Mockito.doReturn(
					_roleId
				).when(
					_role
				).getRoleId();

			} catch (Exception exception) {
				System.out.println("Failed to mock role " + _roleName);
			}
		}

		public Role getRole() {
			return _role;
		}

		private long _roleId;
		private String _roleName;
		private Role _role;
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
//	private void _mockResourcePermissionLocalServiceGetRoles(
//		long companyId, String className, int scope, String resourcePrimKey, List<Role> roles) throws Exception {
//
//		ResourceAction resourceAction = Mockito.mock(ResourceAction.class);
//
//		Mockito.when(
//			_resourceActionLocalService.getResourceAction(className, _actionId)
//		).thenReturn(
//			resourceAction
//		);
//
//		List<ResourcePermission> resourcePermissions =
//			_createResourcePermissionMocks(resourceAction, roles);
//
//		_addRolesToRoleLocalService(roles);
//
//		Mockito.doReturn(
//			resourcePermissions
//		).when(
//			_resourcePermissionPersistence
//		).findByC_N_S_P(companyId, className, scope, resourcePrimKey);
//
//	}

	private void _addRolesToRoleLocalService(List<Role> roles) throws Exception {
		for(Role role : roles) {

//			Mockito.doReturn(
//				role
//			).when(
//				_roleLocalService
//			).getRole(role.getRoleId());

			Mockito.when(
				_roleLocalService.getRole(role.getRoleId())
			).thenReturn(
				role
			);
		}
	}

//	private List<ResourcePermission> _createResourcePermissionMocks(
//		ResourceAction resourceAction, List<Role> roles) {
//
//		List<ResourcePermission> resourcePermissions = new ArrayList<>();
//
//		for(Role role : roles) {
//			ResourcePermission resourcePermission =
//				Mockito.mock(ResourcePermission.class);
//
//			Mockito.doReturn(
//				true
//			).when(
//				resourcePermission
//			).hasAction(resourceAction);
//
//			Mockito.doReturn(
//				role.getRoleId()
//			).when(
//				resourcePermission
//			).getRoleId();
//
//			resourcePermissions.add(resourcePermission);
//		}
//
//		return resourcePermissions;
//	}

	List<List<ResourcePermission>> resourcePermissionsList = new ArrayList<>();

	@Mock
	private ResourceActionLocalService _resourceActionLocalService;

	@Mock
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	@Mock
	private RoleLocalService _roleLocalService;

	@Mock
	private PersistedModelLocalServiceRegistry
		_persistedModelLocalServiceRegistry;

	@Mock
	private PersistedModelLocalService _journalArticlePersistedModelLocalService;

	@Mock
	private PersistedModelLocalService _journalFolderPersistedModelLocalService;

	private RoleProxyFactory _roleProxyFactory = new RoleProxyFactory();
	private JournalArticleFolderProxyFactory _journalArticleFolderProxyFactory;
	private long _companyId;
	private String _journalArticleClassName;
	private String _journalFolderClassName;
	private int _scope;
	private String _resourcePrimKey;
	private String _primKey;
	private String _actionId;
	private ResourcePermissionLocalService _resourcePermissionLocalService;
}
