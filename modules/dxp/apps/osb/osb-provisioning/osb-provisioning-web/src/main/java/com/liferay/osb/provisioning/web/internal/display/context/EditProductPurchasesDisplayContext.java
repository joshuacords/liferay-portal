/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

/**
 * @author Yuanyuan Huang
 */
public class EditProductPurchasesDisplayContext
	extends ViewAccountDisplayContext {

	public EditProductPurchasesDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_products = (List<Product>)renderRequest.getAttribute(
			ProvisioningWebKeys.PRODUCTS);

		_productPurchases = (List<ProductPurchase>)renderRequest.getAttribute(
			ProvisioningWebKeys.PRODUCT_PURCHASES);

		_productPurchaseViews =
			(List<ProductPurchaseView>)renderRequest.getAttribute(
				ProvisioningWebKeys.PRODUCT_PURCHASE_VIEWS);

		setWindowTitle();
	}

	public Map<String, Object> getEditProductPurchasesData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountName", account.getName());

		String redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNull(redirect)) {
			redirect = getRedirectURL();
		}

		data.put("redirect", redirect);

		PortletURL selectProductsActionURL = renderResponse.createActionURL();

		selectProductsActionURL.setParameter(
			ActionRequest.ACTION_NAME,
			"/accounts/edit_product_purchases_select_products");
		selectProductsActionURL.setParameter("redirect", redirect);
		selectProductsActionURL.setParameter("accountKey", account.getKey());

		data.put("selectProductsActionURL", selectProductsActionURL.toString());

		PortletURL editProductPurchasesURL = renderResponse.createActionURL();

		editProductPurchasesURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_product_purchases");
		editProductPurchasesURL.setParameter("redirect", redirect);
		editProductPurchasesURL.setParameter("accountKey", account.getKey());

		if (_products != null) {
			editProductPurchasesURL.setParameter(
				"productBundleIds",
				ParamUtil.getString(renderRequest, "productBundleIds"));
			editProductPurchasesURL.setParameter(
				"productKeys",
				ParamUtil.getString(renderRequest, "productKeys"));

			data.put("addSubscriptions", true);
			data.put("details", _getAddProductPurchasesJSONArray());

			PortletURL selectProductsRenderURL =
				renderResponse.createRenderURL();

			selectProductsRenderURL.setParameter(
				"mvcRenderCommandName", "/accounts/assign_products");
			selectProductsRenderURL.setParameter(
				"accountKey", account.getKey());
			selectProductsRenderURL.setWindowState(LiferayWindowState.POP_UP);

			data.put(
				"selectProductsRenderURL", selectProductsRenderURL.toString());
		}
		else {
			editProductPurchasesURL.setParameter(
				"productPurchaseKeys",
				ParamUtil.getString(renderRequest, "productPurchaseKeys"));

			data.put("addSubscriptions", false);
			data.put("details", _getEditProductPurchasesJSONArray());

			String backURL = ParamUtil.getString(httpServletRequest, "backURL");

			if (Validator.isNotNull(backURL)) {
				data.put("backURL", backURL);

				editProductPurchasesURL.setParameter("backURL", backURL);
			}
		}

		data.put("editProductPurchasesURL", editProductPurchasesURL.toString());

		List<Integer> sizing = new ArrayList<>();

		for (int i = 0; i <= 4; i++) {
			sizing.add(i);
		}

		data.put("sizing", sizing);

		List<String> status = new ArrayList<>();

		for (ProductPurchase.Status curStatus :
				ProductPurchase.Status.values()) {

			status.add(curStatus.toString());
		}

		data.put("status", status);

		return data;
	}

	public ProductPurchaseDisplay getProductPurchaseDisplay(
			ProductPurchase productPurchase)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(
			true, "productPurchaseKey", productPurchase.getKey());

		long productConsumptionsCount =
			productConsumptionWebService.searchCount(filterQuery);

		return new ProductPurchaseDisplay(
			httpServletRequest, productPurchase, productConsumptionsCount);
	}

	public List<ProductPurchaseDisplay> getProductPurchaseDisplays(
		ProductPurchaseView productPurchaseView) {

		Map<String, Long> productConsumptionsCount = new HashMap<>();

		ProductConsumption[] productConsumptions =
			productPurchaseView.getProductConsumptions();

		if (productConsumptions != null) {
			for (ProductConsumption productConsumption : productConsumptions) {
				String productPurchaseKey =
					productConsumption.getProductPurchaseKey();

				if (Validator.isNotNull(productPurchaseKey)) {
					long curProductConsumptionsCount =
						productConsumptionsCount.getOrDefault(
							productPurchaseKey, 0L);

					productConsumptionsCount.put(
						productPurchaseKey, curProductConsumptionsCount + 1);
				}
			}
		}

		return TransformUtil.transformToList(
			productPurchaseView.getProductPurchases(),
			productPurchase -> new ProductPurchaseDisplay(
				httpServletRequest, productPurchase,
				productConsumptionsCount.getOrDefault(
					productPurchase.getKey(), 0L)));
	}

	public List<ProductPurchaseView> getProductPurchaseViews() {
		return _productPurchaseViews;
	}

	public String getRedirectURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", account.getKey());

		String tabs2 = ParamUtil.getString(httpServletRequest, "tabs2");

		if (Validator.isNotNull(tabs2)) {
			portletURL.setParameter("tabs2", tabs2);
		}

		return portletURL.toString();
	}

	public String getTitle() {
		if (_products != null) {
			return "add-subscriptions";
		}

		return "edit-subscriptions";
	}

	@Override
	protected void setWindowTitle() {
		String title = "edit-subscriptions";

		if (_products != null) {
			title = "add-subscriptions";
		}

		renderResponse.setTitle(
			StringBundler.concat(
				account.getCode(), StringPool.SPACE,
				LanguageUtil.get(httpServletRequest, title)));
	}

	private JSONArray _getAddProductPurchasesJSONArray() throws Exception {
		JSONArray productPurchasesJSONArray = JSONFactoryUtil.createJSONArray();

		for (Product product : _products) {
			productPurchasesJSONArray.put(
				JSONUtil.put(
					"originalEndDate", _getDate(null, "originalEndDate")
				).put(
					"productKey", product.getKey()
				).put(
					"productName", product.getName()
				).put(
					"startDate", _getDate(null, "startDate")
				));
		}

		return productPurchasesJSONArray;
	}

	private String _getDate(Date date, String type) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		if (type.equals("endDate")) {
			calendar.add(Calendar.YEAR, 1);
			calendar.add(Calendar.DATE, 30);
		}
		else if (type.equals("originalEndDate")) {
			calendar.add(Calendar.YEAR, 1);
		}

		if (date != null) {
			calendar.setTime(date);
		}

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd");

		return dateFormat.format(calendar.getTime());
	}

	private JSONArray _getEditProductPurchasesJSONArray() throws Exception {
		JSONArray productPurchasesJSONArray = JSONFactoryUtil.createJSONArray();

		for (ProductPurchase productPurchase : _productPurchases) {
			ProductPurchaseDisplay productPurchaseDisplay =
				getProductPurchaseDisplay(productPurchase);

			int sizing = 1;

			Map<String, String> properties = productPurchase.getProperties();

			if ((properties != null) && (properties.get("sizing") != null)) {
				sizing = GetterUtil.getInteger(properties.get("sizing"));
			}

			ProductPurchase.Status status = productPurchase.getStatus();

			productPurchasesJSONArray.put(
				JSONUtil.put(
					"endDate", _getDate(productPurchase.getEndDate(), "endDate")
				).put(
					"externalLinkKey",
					productPurchaseDisplay.getExternalLinkKey()
				).put(
					"key", productPurchase.getKey()
				).put(
					"originalEndDate",
					_getDate(
						productPurchase.getOriginalEndDate(), "originalEndDate")
				).put(
					"perpetual", productPurchase.getPerpetual()
				).put(
					"productName", productPurchaseDisplay.getProductName()
				).put(
					"quantity", productPurchase.getQuantity()
				).put(
					"salesforceOpportunityKey",
					productPurchaseDisplay.getSalesforceOpportunityKey()
				).put(
					"sizing", sizing
				).put(
					"startDate",
					_getDate(productPurchase.getStartDate(), "startDate")
				).put(
					"status", status.toString()
				));
		}

		return productPurchasesJSONArray;
	}

	private List<ProductPurchase> _productPurchases;
	private List<ProductPurchaseView> _productPurchaseViews;
	private List<Product> _products;

}