package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import jodd.util.StringUtil;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class JournalFolderProxy {
	JournalFolderProxy(ResourceActionLocalService resourceActionLocalService,
		RoleProxyFactory roleProxyFactory, String[] roleNames) throws Exception {
		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		//primKey may be the same with a JournalFolder
		//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_roleNames = roleNames;
		_treePath = "/" + _resourcePrimKey + "/";
		_mockRoles(roleNames);
	}

	JournalFolderProxy(
		ResourceActionLocalService resourceActionLocalService,
		RoleProxyFactory roleProxyFactory,
		JournalFolderProxy journalFolderProxy, String[] roleNames) throws Exception {
		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;
		_resourcePrimKey = StringUtil.toString(RandomTestUtil.randomLong());
		//primKey may be the same with a JournalFolder
		//_primKey = StringUtil.toString(RandomTestUtil.randomLong());
		_roleNames = roleNames;
		_createTreePath(journalFolderProxy);
		_mockRoles(roleNames);
	}

	private void _setUpMocks() throws Exception {
		_viewFolderResourceAction = Mockito.mock(ResourceAction.class);

//		try {
			Mockito.doReturn(
				_viewFolderResourceAction
			).when(
				_resourceActionLocalService
			).getResourceAction(_journalFolderClassName, _viewActionId);

//		} catch (Exception exception) {
//			System.out.println("_viewFolder/ArticleResourceAction mock failed");
//		}
	}

	private void _mockRoles(String[] roleNames) throws Exception {
		_setUpMocks();

//		try {
			List<Role> roles = new ArrayList<>();

			for(String roleName : roleNames) {
				roles.add(_roleProxyFactory.getRole(roleName));
			}

			_roles.addAll(roles);
			_roleProxyFactory.setRoleToAsset(
				_journalFolderClassName, _resourcePrimKey,
				_viewFolderResourceAction, roleNames);
//		} catch (Exception exception) {
//			_roles = null;
//			System.out.println("Failed to mock JournalFolder roles");
//		}
	}

	//journalFolder uses different primKey

	private void _createTreePath(JournalFolderProxy journalFolderProxy) {

		if(journalFolderProxy != null) {
			_treePath = journalFolderProxy.getTreePath();
		} else {
			_treePath = "/";
		}

		_treePath = _treePath.concat(_resourcePrimKey + "/");

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

	private ResourceAction _viewFolderResourceAction;

	private ResourceActionLocalService _resourceActionLocalService;
	private RoleProxyFactory _roleProxyFactory;
	final private String _viewActionId = ActionKeys.VIEW;
	final private String _journalFolderClassName = "com.liferay.journal.model.JournalFolder";
	private List<Role> _roles = new ArrayList<>();
	private String _resourcePrimKey;
	private String _treePath;
	private String[] _roleNames;
}
