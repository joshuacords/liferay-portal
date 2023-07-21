/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

'use strict';

var withAlloyUI = Liferay.Test.withAlloyUI;

var FORM_TEMPLATE = `<form id="_NAMESPACE_fm">
	<input class="search-bar-empty-search-input" type="hidden" value="{emptySearchEnabled}">
	<input class="search-bar-keywords-input" name="q" type="text" value="{keywords}">
	<button type="submit"></button>
</form>`;

function getFormTemplate(keywords, emptySearchEnabled) {
	var template = FORM_TEMPLATE.replace('{keywords}', keywords || '');

	template = template.replace('{emptySearchEnabled}', !!emptySearchEnabled);

	return template;
}

describe('Liferay.Search.SearchBar', () => {
	describe('.getKeywords', () => {
		it(
			'returns the keywords',
			withAlloyUI(
				(done, A) => {
					var form = A.Node.create(getFormTemplate('example'));

					var searchBar = new Liferay.Search.SearchBar(form);

					assert.equal('example', searchBar.getKeywords());

					done();
				},
				['aui-node', 'liferay-search-bar']
			)
		);
	});

	describe('.isSubmitEnabled', () => {
		it(
			'is false with no keywords',
			withAlloyUI(
				(done, A) => {
					var form = A.Node.create(getFormTemplate());

					var searchBar = new Liferay.Search.SearchBar(form);

					assert(
						!searchBar.isSubmitEnabled(),
						searchBar.getKeywords()
					);

					done();
				},
				['aui-node', 'liferay-search-bar']
			)
		);

		it(
			'is true with keywords',
			withAlloyUI(
				(done, A) => {
					var form = A.Node.create(getFormTemplate('example'));

					var searchBar = new Liferay.Search.SearchBar(form);

					assert(searchBar.isSubmitEnabled());

					done();
				},
				['aui-node', 'liferay-search-bar']
			)
		);

		it(
			'is true if no keyword but keyword-free search enabled',
			withAlloyUI(
				(done, A) => {
					var form = A.Node.create(getFormTemplate('', true));

					var searchBar = new Liferay.Search.SearchBar(form);

					assert(
						searchBar.isSubmitEnabled(),
						searchBar.getKeywords()
					);

					done();
				},
				['aui-node', 'liferay-search-bar']
			)
		);
	});

	describe.skip('.updateQueryString', () => {
		it(
			'removes p_p_id, p_p_state, start and add query keyword',
			withAlloyUI(
				(done, A) => {
					var form = A.Node.create(getFormTemplate('example'));

					var searchBar = new Liferay.Search.SearchBar(form);

					var queryString =
						'?p_p_lifecycle=0&p_p_mode=view&p_p_id=com_liferay_portal_search_web_search_bar_portlet_SearchBarPortlet&p_p_state=maximized&start=1';

					queryString = searchBar.updateQueryString(queryString);

					assert.equal(
						queryString,
						'?p_p_lifecycle=0&p_p_mode=view&q=example'
					);

					done();
				},
				['aui-node', 'liferay-search-bar']
			)
		);
	});
});
