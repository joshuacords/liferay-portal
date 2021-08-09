package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.model.BaseChildModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.service.ResourcePermissionImplTest;
import jodd.util.StringUtil;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class JournalArticleProxy {
	JournalArticleProxy(
		RoleProxyFactory roleProxyFactory,
		PersistedModelLocalService journalArticlePersistedModelLocalService,
		ResourceActionLocalService resourceActionLocalService, String[] roleNames)
		throws Exception {
		new JournalArticleProxy( roleProxyFactory,
			journalArticlePersistedModelLocalService,
			resourceActionLocalService,	null, roleNames);
	}

	JournalArticleProxy(
		RoleProxyFactory roleProxyFactory,
		PersistedModelLocalService journalArticlePersistedModelLocalService,
		ResourceActionLocalService resourceActionLocalService,
		JournalFolderProxy journalFolderProxy, String[] roleNames)  throws Exception {
		_roleProxyFactory = roleProxyFactory;
		_resourceActionLocalService = resourceActionLocalService;
		_journalArticlePersistedModelLocalService = journalArticlePersistedModelLocalService;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_roleNames = roleNames;
		_parentClassPK = journalFolderProxy.getResourcePrimKey();
		_createTreePath(journalFolderProxy);
		_setUpMocks();
		_mockRoles(roleNames);
		_mockPersistedModel();
	}

	private void _setUpMocks() {
		_viewArticleResourceAction = Mockito.mock(ResourceAction.class);

		try {
			Mockito.doReturn(
				_viewArticleResourceAction
			).when(
				_resourceActionLocalService
			).getResourceAction(_journalArticleClassName, _viewActionId);

		} catch (Exception exception) {
			System.out.println("_viewFolder/ArticleResourceAction mock failed");
		}
	}

	private void _mockPersistedModel() throws Exception {

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

		_mockTreePath();

//		try {
			Mockito.doReturn(
				_journalArticlePersistedBaseChildModel
			).when(
				_journalArticlePersistedModelLocalService
			).getPersistedModel(Long.valueOf(_primKey));
//		} catch (Exception exception) {
//			System.out.println("_mockPersistedModel failed");
//		}
	}

	private void _mockTreePath() {
		Mockito.doReturn(
			_treePath
		).when(
			_journalArticlePersistedBaseChildModel
		).getTreePath();
	}

	private void _mockRoles(String[] roleNames) throws Exception {
//		try {
			List<Role> roles = new ArrayList<>();

			for(String roleName : roleNames) {
				roles.add(_roleProxyFactory.getRole(roleName));
			}

			_roles.addAll(roles);
			_roleProxyFactory.setRoleToAsset(
				_journalArticleClassName, _resourcePrimKey,
				_viewArticleResourceAction, roleNames);
//		} catch (Exception exception) {
//			_roles = null;
//			System.out.println("Failed to mock JournalArticle roles");
//		}
	}


	//journalArticle uses different primKey? needed to switch back to resource

	private void _createTreePath(JournalFolderProxy journalFolderProxy) {
		_treePath = journalFolderProxy != null ? journalFolderProxy.getTreePath() : "/";
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

	public abstract class PersistenceBaseChild implements BaseChildModel,
		PersistedModel {}

	private ResourceActionLocalService _resourceActionLocalService;

	@Mock
	private ResourceAction _viewArticleResourceAction;
	private PersistedModelLocalService _journalArticlePersistedModelLocalService;
	private RoleProxyFactory _roleProxyFactory;
	private String _parentClassPK;
	final private String _viewActionId = ActionKeys.VIEW;
	private List<Role> _roles = new ArrayList<>();
	private String _resourcePrimKey;
	final private String _journalFolderClassName = "com.liferay.journal.model.JournalFolder";
	final private String _journalArticleClassName = "com.liferay.journal.model.JournalArticle";
	private String _primKey;
	private String _treePath;
	private String[] _roleNames;
	private PersistenceBaseChild
		_journalArticlePersistedBaseChildModel;
}