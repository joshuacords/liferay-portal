package com.liferay.portal.service;

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
import com.liferay.portal.service.resource.permission.test.util.JournalArticleProxy;
import com.liferay.portal.service.resource.permission.test.util.JournalFolderProxy;
import com.liferay.portal.service.resource.permission.test.util.JournalProxyFactory;
import com.liferay.portal.service.resource.permission.test.util.RoleProxyFactory;
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
import java.util.HashSet;
import java.util.List;
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

		MockitoAnnotations.initMocks(this);

		_roleProxyFactory = new RoleProxyFactory(
			_roleLocalService, _resourcePermissionLocalService, _companyId);

		_initializeReflections();
		_mockResourceActions();
		_mockPersistedModelLocalServices();


		_journalProxyFactory = new JournalProxyFactory(
			_journalArticlePersistedModelLocalService, _roleProxyFactory,
			_resourceActionLocalService, _companyId);

	}

	private void _initializeReflections() {
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
		_viewActionId = ActionKeys.VIEW;
	}

	private void _mockResourceActions() {
		try {
			Mockito.doReturn(
				_viewFolderResourceAction
			).when(
				_resourceActionLocalService
			).getResourceAction(_journalFolderClassName, _viewActionId);

			Mockito.doReturn(
				_viewArticleResourceAction
			).when(
				_resourceActionLocalService
			).getResourceAction(_journalArticleClassName, _viewActionId);

		} catch (Exception exception) {
			System.out.println("_viewFolder/ArticleResourceAction mock failed");
		}
	}

	@Test
	public void getDynamicInheritanceRolesOnlyGuest() throws Exception {

		JournalFolderProxy journalFolderProxy =
			_journalProxyFactory.createJournalFolderProxy(
				RoleConstants.GUEST, RoleConstants.OWNER);

		JournalArticleProxy journalArticleProxy =
			_journalProxyFactory.createJournalArticleProxy(
				journalFolderProxy, RoleConstants.GUEST, RoleConstants.OWNER);

		Set<Set<Role>> roleSets =  _resourcePermissionLocalService.getDynamicInheritanceRoles(
		_companyId, _journalArticleClassName, _scope,
			journalArticleProxy.getResourcePrimKey(),
			journalArticleProxy.getPrimKey(), _viewActionId);

		Set<Set<Role>> expectedRoleSets = _rolesToSetSet(
			_roleProxyFactory.getRole(RoleConstants.GUEST),
			_roleProxyFactory.getRole(RoleConstants.OWNER));

		assertContainsRoleSets(roleSets, expectedRoleSets);
	}

	private Set<Set<Role>> _rolesToSetSet(Role ... roles) {

		Set<Set<Role>> roleSets = new HashSet<>();

		for(Role role : roles) {
			Set<Role> roleSet = new HashSet<>();
			roleSet.add(role);
			roleSets.add(roleSet);
		}

		return roleSets;
	}

	protected void assertContainsRoleSets(
		Set<Set<Role>> expectedRoleSets, Set<Set<Role>> actualRoleSets)
		throws Exception {

		if (expectedRoleSets.size() != actualRoleSets.size()) {
			StringBuilder sb = new StringBuilder(4);
			sb.append("expectedRoleSets size ");
			sb.append(expectedRoleSets.size());
			sb.append(" is not actualRoleSets size ");
			sb.append(actualRoleSets.size());
			sb.append(" ");
			_appendRoleSets(actualRoleSets, sb);

			throw new Exception(sb.toString());
		}

		for (Set<Role> expectedRoleSet : expectedRoleSets) {
			if (!actualRoleSets.contains(expectedRoleSet)) {

				StringBuilder sb = new StringBuilder();
				sb.append("expectedRoleSet [");
				_appendRoleSet(expectedRoleSet, sb);
				sb.append("] from expectedRoleSets [");
				_appendRoleSets(expectedRoleSets, sb);
				sb.append("] was not found in actualRoleSets [");
				_appendRoleSets(actualRoleSets, sb);
				sb.append("]");

				throw new Exception(sb.toString());
			}
		}
	}

	private StringBuilder _appendRoleSets(Set<Set<Role>> roleSets, StringBuilder sb) {

		try {
			for(Set<Role> roleSet : roleSets) {
				sb.append("[");
				_appendRoleSet(roleSet, sb);
				sb.append("], ");
			}
		} catch (Exception exception) {

		}

		return sb;
	}

	private StringBuilder _appendRoleSet(Set<Role> roleSet, StringBuilder sb) {
		try {
			for (Role role : roleSet) {
				sb.append(role.getDescriptiveName());
				sb.append(", ");
			}
		} catch (Exception exception) {

		}

		return sb;
	}

//	class JournalArticleFolderProxyFactory {
//		JournalArticleFolderProxyFactory(long companyId) {
//			_companyId = companyId;
//		}
//
//		JournalArticleProxy createJournalArticleProxy(String ... roleNames) {
//			return new JournalArticleProxy(roleNames);
//		}
//
//		JournalArticleProxy createJournalArticleProxy(
//			JournalFolderProxy journalFolderProxy, String ... roleNames) {
//			return new JournalArticleProxy(journalFolderProxy, roleNames);
//		}
//
//		JournalFolderProxy createJournalFolderProxy(String ... roleNames) {
//			return new JournalFolderProxy(roleNames);
//		}
//
//		JournalFolderProxy createJournalFolderProxy(
//			JournalFolderProxy journalFolderProxy, String ... roleNames) {
//			return new JournalFolderProxy(journalFolderProxy, roleNames);
//		}
//
//		private long _companyId;
//	}

//	class JournalArticleProxy {
//		JournalArticleProxy(String[] roleNames) {
//			new JournalArticleProxy(null, roleNames);
//		}
//
//		JournalArticleProxy(JournalFolderProxy journalFolderProxy, String[] roleNames) {
//			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
//			_primKey = StringUtil.toString(RandomTestUtil.randomLong());
//			_roleNames = roleNames;
//			_parentClassPK = journalFolderProxy.getResourcePrimKey();
//			_createTreePath(journalFolderProxy);
//			_mockRoles(roleNames);
//			_mockPersistedModel();
//		}
//
//		private void _mockPersistedModel() {
//
//			_journalArticlePersistedBaseChildModel = Mockito.mock(PersistenceBaseChild.class);
//
//			Mockito.doReturn(
//				_parentClassPK
//			).when(
//				_journalArticlePersistedBaseChildModel
//			).getParentClassPK();
//
//			Mockito.doReturn(
//				_journalFolderClassName
//			).when(
//				_journalArticlePersistedBaseChildModel
//			).getParentClassName();
//
//			_mockTreePath();
//
//			try {
//				Mockito.doReturn(
//					_journalArticlePersistedBaseChildModel
//				).when(
//					_journalArticlePersistedModelLocalService
//				).getPersistedModel(Long.valueOf(_primKey));
//			} catch (Exception exception) {
//				System.out.println("_mockPersistedModel failed");
//			}
//		}
//
//		private void _mockTreePath() {
//			Mockito.doReturn(
//				_treePath
//			).when(
//				_journalArticlePersistedBaseChildModel
//			).getTreePath();
//		}
//
//		private void _mockRoles(String[] roleNames) {
//			try {
//				List<Role> roles = new ArrayList<>();
//
//				for(String roleName : roleNames) {
//					roles.add(_roleProxyFactory.getRole(roleName));
//				}
//
//				_roles.addAll(roles);
//				_roleProxyFactory.setRoleToAsset(
//					_journalArticleClassName, _resourcePrimKey,
//					_viewArticleResourceAction, roleNames);
//			} catch (Exception exception) {
//				_roles = null;
//				System.out.println("Failed to mock JournalArticle roles");
//			}
//		}
//
//
//		//journalArticle uses different primKey? needed to switch back to resource
//
//		private void _createTreePath(JournalFolderProxy journalFolderProxy) {
//			_treePath = journalFolderProxy != null ? journalFolderProxy.getTreePath() : "/";
//		}
//
//		public String getResourcePrimKey() {
//			return _resourcePrimKey;
//		}
//
//		public String getPrimKey() {
//			return _primKey;
//		}
//
//		public String[] getRoleNames() {
//			return _roleNames;
//		}
//
//		public String getTreePath() {
//			return _treePath;
//		}
//
//		private String _parentClassPK;
//		private List<Role> _roles = new ArrayList<>();
//		private String _resourcePrimKey;
//		private String _primKey;
//		private String _treePath;
//		private String[] _roleNames;
//		private PersistenceBaseChild _journalArticlePersistedBaseChildModel;
//	}

//	public abstract class PersistenceBaseChild implements BaseChildModel, PersistedModel {}

//	class JournalFolderProxy {
//		JournalFolderProxy(String[] roleNames) {
//			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
//			//primKey may be the same with a JournalFolder
//			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
//			_roleNames = roleNames;
//			_treePath = "/" + _resourcePrimKey + "/";
//			_mockRoles(roleNames);
//		}
//
//		JournalFolderProxy(JournalFolderProxy journalFolderProxy, String[] roleNames) {
//			_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
//			//primKey may be the same with a JournalFolder
//			//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
//			_roleNames = roleNames;
//			_createTreePath(journalFolderProxy);
//			_mockRoles(roleNames);
//		}
//
//		private void _mockRoles(String[] roleNames) {
//			try {
//				List<Role> roles = new ArrayList<>();
//
//				for(String roleName : roleNames) {
//					roles.add(_roleProxyFactory.getRole(roleName));
//				}
//
//				_roles.addAll(roles);
//				_roleProxyFactory.setRoleToAsset(_journalFolderClassName, _resourcePrimKey, _viewFolderResourceAction, roleNames);
//			} catch (Exception exception) {
//				_roles = null;
//				System.out.println("Failed to mock JournalFolder roles");
//			}
//		}
//
//		//journalFolder uses different primKey
//
//		private void _createTreePath(JournalFolderProxy journalFolderProxy) {
//
//			if(journalFolderProxy != null) {
//				_treePath = journalFolderProxy.getTreePath();
//			} else {
//				_treePath = "/";
//			}
//
//			_treePath = _treePath.concat(_resourcePrimKey + "/");
//
//		}
//
//		public String getResourcePrimKey() {
//			return _resourcePrimKey;
//		}
//
//		public String[] getRoleNames() {
//			return _roleNames;
//		}
//
//		public String getTreePath() {
//			return _treePath;
//		}
//
//		private List<Role> _roles = new ArrayList<>();
//		private String _resourcePrimKey;
//		private String _treePath;
//		private String[] _roleNames;
//	}

//	class RoleProxyFactory {
//		Map<String, RoleProxy> _roleProxies = new HashMap<>();
//
//		public Role getRole(String roleName) {
//			if (_roleProxies.containsKey(roleName)) {
//				return _roleProxies.get(roleName).getRole();
//			}
//
//			RoleProxy roleProxy = new RoleProxy(roleName);
//			_roleProxies.put(roleName, roleProxy);
//			return roleProxy.getRole();
//		}
//
//		public RoleProxy getRoleProxy(String roleName) {
//			if (_roleProxies.containsKey(roleName)) {
//				return _roleProxies.get(roleName);
//			}
//
//			RoleProxy roleProxy = new RoleProxy(roleName);
//			_roleProxies.put(roleName, roleProxy);
//			return roleProxy;
//		}
//
//		public void setRoleToAsset(String className, String primKey, ResourceAction resourceAction, String ... roleNames) {
//			List<Role> classRoles = new ArrayList<>();
//
//			for(String roleName : roleNames) {
//				classRoles.add(getRole(roleName));
//			}
//
//			try {
//				_mockResourcePermissionLocalServiceGetRolesInside(
//					_companyId, className, _scope, primKey, classRoles, resourceAction);
//			} catch (Exception exception) {
//				System.out.println("Failed to mock ResourcePermissionLocalService roles");
//			}
//		}
//
//		private void _mockResourcePermissionLocalServiceGetRolesInside(
//			long companyId, String className, int scope, String resourcePrimKey,
//			List<Role> roles, ResourceAction resourceAction) throws Exception {
//
//			List<ResourcePermission> resourcePermissions =
//				_createResourcePermissionMocksInside(resourceAction, roles);
//
//			_addRolesToRoleLocalServiceInside(roles);
//
//			Mockito.doReturn(
//				resourcePermissions
//			).when(
//				_resourcePermissionPersistence
//			).findByC_N_S_P(companyId, className, scope, resourcePrimKey);
//
//		}
//
//		private List<ResourcePermission> _createResourcePermissionMocksInside(
//			ResourceAction resourceAction, List<Role> roles) {
//
//			List<ResourcePermission> resourcePermissions = new ArrayList<>();
//
//			for(Role role : roles) {
//				ResourcePermission resourcePermission =
//					Mockito.mock(ResourcePermission.class);
//
//				Mockito.doReturn(
//					true
//				).when(
//					resourcePermission
//				).hasAction(resourceAction);
//
//				Mockito.doReturn(
//					role.getRoleId()
//				).when(
//					resourcePermission
//				).getRoleId();
//
//				resourcePermissions.add(resourcePermission);
//			}
//
//			return resourcePermissions;
//		}
//
//		private void _addRolesToRoleLocalServiceInside(List<Role> roles) throws Exception {
//			for(Role role : roles) {
//
//				Mockito.when(
//					_roleLocalService.getRole(role.getRoleId())
//				).thenReturn(
//					role
//				);
//			}
//		}
//	}

//	class RoleProxy{
//		RoleProxy(String roleName) {
//			_role = Mockito.mock(Role.class);
//			_roleId = RandomTestUtil.randomLong();
//			_roleName = roleName;
//			_mockRole();
//		}
//
//		private void _mockRole() {
//			try {
//
//				Mockito.doReturn(
//					_role
//				).when(
//					_roleLocalService
//				).getRole(_roleId);
//
//				Mockito.doReturn(
//					_role
//				).when(
//					_roleLocalService
//				).getRole(_companyId, _roleName);
//
//				Mockito.doReturn(
//					_roleId
//				).when(
//					_role
//				).getRoleId();
//
//				Mockito.doReturn(
//					_roleName
//				).when(
//					_role
//				).getDescriptiveName();
//
//			} catch (Exception exception) {
//				System.out.println("Failed to mock role " + _roleName);
//			}
//		}
//
//		public Role getRole() {
//			return _role;
//		}
//
//		private long _roleId;
//		private String _roleName;
//		private Role _role;
//	}

	private Role _mockRole(String roleString) throws Exception {
		Role role = Mockito.mock(Role.class);

		Mockito.when(
			role.getDescriptiveName()
		).thenReturn(
			roleString
		);

		return role;
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

	@Mock
	private ResourceAction _viewFolderResourceAction;

	@Mock
	private ResourceAction _viewArticleResourceAction;

	@Mock
	private ResourceAction _accessFolderResourceAction;

	private RoleProxyFactory _roleProxyFactory;// = new RoleProxyFactory();
	private JournalProxyFactory _journalProxyFactory;
	private long _companyId;
	private String _journalArticleClassName;
	private String _journalFolderClassName;
	private int _scope;
	private String _resourcePrimKey;
	private String _primKey;
	private String _viewActionId;
	private ResourcePermissionLocalService _resourcePermissionLocalService;
}
