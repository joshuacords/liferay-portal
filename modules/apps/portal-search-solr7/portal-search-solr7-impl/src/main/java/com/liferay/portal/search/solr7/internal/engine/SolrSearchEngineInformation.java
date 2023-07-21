/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr7.internal.engine;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.engine.ConnectionInformation;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.solr7.configuration.SolrConfiguration;
import com.liferay.portal.search.solr7.internal.SolrSearchEngine;
import com.liferay.portal.search.solr7.internal.connection.SolrClientManager;

import java.util.List;
import java.util.Map;

import org.apache.lucene.util.Version;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.request.GenericSolrRequest;
import org.apache.solr.client.solrj.response.SimpleSolrResponse;
import org.apache.solr.common.util.NamedList;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(
	configurationPid = "com.liferay.portal.search.solr7.configuration.SolrConfiguration",
	immediate = true, service = SearchEngineInformation.class
)
public class SolrSearchEngineInformation implements SearchEngineInformation {

	@Override
	public String getClientVersionString() {
		return Version.LATEST.toString();
	}

	@Override
	public List<ConnectionInformation> getConnectionInformationList() {
		return null;
	}

	@Override
	public String getNodesString() {
		try {
			SolrClient solrClient = solrClientManager.getSolrClient();

			GenericSolrRequest request = new GenericSolrRequest(
				SolrRequest.METHOD.POST, "/admin/info/system", null);

			SimpleSolrResponse response = request.process(solrClient);

			NamedList namedList = response.getResponse();

			NamedList<Object> luceneInfo = (NamedList<Object>)namedList.get(
				"lucene");

			String version = (String)luceneInfo.get("solr-spec-version");

			StringBundler sb = new StringBundler(5);

			sb.append(_defaultCollection);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append(version);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
		catch (Exception exception) {
			_log.error("Could not retrieve node information", exception);

			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("Error: ");
			sb.append(exception.toString());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public String getStatusString() {
		StringBundler sb = new StringBundler(8);

		sb.append("Vendor: ");
		sb.append(getVendorString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Client Version: ");
		sb.append(getClientVersionString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Nodes: ");
		sb.append(getNodesString());

		return sb.toString();
	}

	@Override
	public String getVendorString() {
		return solrSearchEngine.getVendor();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_solrConfiguration = ConfigurableUtil.createConfigurable(
			SolrConfiguration.class, properties);

		_defaultCollection = _solrConfiguration.defaultCollection();
	}

	@Reference
	protected SolrClientManager solrClientManager;

	@Reference
	protected SolrSearchEngine solrSearchEngine;

	private static final Log _log = LogFactoryUtil.getLog(
		SolrSearchEngineInformation.class);

	private String _defaultCollection;
	private volatile SolrConfiguration _solrConfiguration;

}