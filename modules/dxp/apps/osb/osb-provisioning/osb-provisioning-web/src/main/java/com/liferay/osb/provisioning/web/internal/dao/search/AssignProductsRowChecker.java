/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.List;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AssignProductsRowChecker extends EmptyOnClickRowChecker {

	public AssignProductsRowChecker(
		RenderResponse renderResponse, long[] productBundleIds,
		List<String> productKeys) {

		super(renderResponse);

		_productBundleIds = productBundleIds;
		_productKeys = productKeys;
	}

	@Override
	public String getRowCheckBox(
		HttpServletRequest httpServletRequest, ResultRow resultRow) {

		String primaryKey = StringPool.BLANK;

		if (resultRow.getObject() instanceof ProductBundle) {
			ProductBundle productBundle = (ProductBundle)resultRow.getObject();

			primaryKey =
				productBundle.getProductBundleId() + StringPool.UNDERLINE +
					HtmlUtil.escape(productBundle.getName());
		}
		else {
			ProductDisplay productDisplay =
				(ProductDisplay)resultRow.getObject();

			primaryKey =
				productDisplay.getKey() + StringPool.UNDERLINE +
					HtmlUtil.escape(productDisplay.getName());
		}

		return getRowCheckBox(
			httpServletRequest, isChecked(resultRow.getObject()),
			isDisabled(resultRow.getObject()), primaryKey);
	}

	@Override
	public boolean isChecked(Object obj) {
		try {
			if (obj instanceof ProductBundle) {
				ProductBundle productBundle = (ProductBundle)obj;

				return ArrayUtil.contains(
					_productBundleIds, productBundle.getProductBundleId());
			}

			ProductDisplay productDisplay = (ProductDisplay)obj;

			return _productKeys.contains(productDisplay.getKey());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignProductsRowChecker.class);

	private final long[] _productBundleIds;
	private final List<String> _productKeys;

}