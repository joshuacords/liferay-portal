/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.internal.servlet;

import com.liferay.asset.util.AssetEntryUsageRecorder;
import com.liferay.asset.util.AssetHelper;
import com.liferay.fragment.contributor.FragmentCollectionContributorTracker;
import com.liferay.fragment.renderer.FragmentRendererTracker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael Bradford
 */
@Component(immediate = true, service = {})
public class ServletContextUtil {

	public static final Map<String, AssetEntryUsageRecorder>
		getAssetEntryUsageRecorders() {

		return _assetEntryUsageRecorders;
	}

	public static final AssetHelper getAssetHelper() {
		return _assetHelper;
	}

	public static final FragmentCollectionContributorTracker
		getFragmentCollectionContributorTracker() {

		return _fragmentCollectionContributorTracker;
	}

	public static final FragmentRendererTracker getFragmentRendererTracker() {
		return _fragmentRendererTracker;
	}

	public static final ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addAssetEntryUsageRecorder(
		AssetEntryUsageRecorder assetEntryUsageRecorder,
		Map<String, Object> properties) {

		String modelClassName = GetterUtil.getString(
			properties.get("model.class.name"));

		if (Validator.isNull(modelClassName)) {
			return;
		}

		_assetEntryUsageRecorders.put(modelClassName, assetEntryUsageRecorder);
	}

	protected void removeAssetEntryUsageRecorder(
		AssetEntryUsageRecorder assetEntryUsageRecorder,
		Map<String, Object> properties) {

		String modelClassName = GetterUtil.getString(
			properties.get("model.class.name"));

		if (Validator.isNull(modelClassName)) {
			return;
		}

		_assetEntryUsageRecorders.remove(modelClassName);
	}

	@Reference(unbind = "-")
	protected void setAssetHelper(AssetHelper assetHelper) {
		_assetHelper = assetHelper;
	}

	@Reference(unbind = "-")
	protected void setFragmentCollectionContributorTracker(
		FragmentCollectionContributorTracker
			fragmentCollectionContributorTracker) {

		_fragmentCollectionContributorTracker =
			fragmentCollectionContributorTracker;
	}

	@Reference(unbind = "-")
	protected void setFragmentRendererTracker(
		FragmentRendererTracker fragmentRendererTracker) {

		_fragmentRendererTracker = fragmentRendererTracker;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.taglib)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final Map<String, AssetEntryUsageRecorder>
		_assetEntryUsageRecorders = new ConcurrentHashMap<>();
	private static AssetHelper _assetHelper;
	private static FragmentCollectionContributorTracker
		_fragmentCollectionContributorTracker;
	private static FragmentRendererTracker _fragmentRendererTracker;
	private static ServletContext _servletContext;

}