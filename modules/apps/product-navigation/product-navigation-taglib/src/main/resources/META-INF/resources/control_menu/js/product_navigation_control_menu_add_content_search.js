/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-product-navigation-control-menu-add-content-search',
	() => {
		var AddContentSearch = function() {};

		AddContentSearch.prototype = {
			_bindUISearch() {
				var instance = this;

				instance._eventHandles = instance._eventHandles || [];

				instance._eventHandles.push(
					instance._search.after(
						'query',
						instance._refreshContentList,
						instance
					),
					instance
						.get('inputNode')
						.on('keydown', instance._onSearchInputKeyDown, instance)
				);
			},

			_onSearchInputKeyDown(event) {
				if (event.isKey('ENTER')) {
					event.halt();
				}
			},

			initializer() {
				var instance = this;

				var contentSearch = new Liferay.SearchFilter({
					inputNode: instance.get('inputNode')
				});

				instance._search = contentSearch;

				instance._bindUISearch();
			}
		};

		Liferay.ControlMenu.AddContentSearch = AddContentSearch;
	},
	'',
	{
		requires: [
			'aui-base',
			'liferay-product-navigation-control-menu',
			'liferay-search-filter'
		]
	}
);
