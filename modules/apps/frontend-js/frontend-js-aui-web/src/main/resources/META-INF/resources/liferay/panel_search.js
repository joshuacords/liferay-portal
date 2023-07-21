/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-panel-search',
	A => {
		var Lang = A.Lang;

		var PanelSearch = A.Component.create({
			ATTRS: {
				categorySelector: {
					validator: Lang.isString
				},

				inputNode: {
					setter: A.one
				},

				nodeContainerSelector: {
					validator: Lang.isString
				},

				nodeList: {
					setter: A.one
				},

				nodeSelector: {
					validator: Lang.isString
				}
			},

			EXTENDS: A.Base,

			NAME: 'panelsearch',

			prototype: {
				_bindUISearch() {
					var instance = this;

					instance._eventHandles = instance._eventHandles || [];

					instance._eventHandles.push(
						instance._search.on(
							'results',
							instance._updateList,
							instance
						),
						instance
							.get('inputNode')
							.on(
								'keydown',
								instance._onSearchInputKeyDown,
								instance
							)
					);
				},

				_onSearchInputKeyDown(event) {
					if (event.isKey('ENTER')) {
						event.halt();
					}
				},

				_setItemsVisibility(visible) {
					var instance = this;

					instance._nodes.each(item => {
						var contentItem = item;

						var nodeContainerSelector = instance.get(
							'nodeContainerSelector'
						);

						if (nodeContainerSelector) {
							contentItem = item.ancestor(nodeContainerSelector);
						}

						if (contentItem) {
							contentItem.toggle(visible);
						}
					});
				},

				_updateList(event) {
					var instance = this;

					var categories = instance._categories;

					var query = event.query;

					if (!instance._collapsedCategories) {
						instance._collapsedCategories = [];

						categories.each(item => {
							var header = item.one('.list-group-heading');

							if (header && header.hasClass('collapsed')) {
								instance._collapsedCategories.push(item);
							}
						});
					}

					if (!query) {
						categories.show();

						instance._setItemsVisibility(true);

						if (instance._collapsedCategories) {
							instance._collapsedCategories.forEach(item => {
								item.one('.list-group-heading').addClass(
									'collapsed'
								);
								item.one('.list-group-panel').removeClass('in');
							});

							instance._collapsedCategories = null;
						}
					}
					else if (query === '*') {
						categories.show();

						instance._setItemsVisibility(true);
					}
					else {
						categories.hide();

						instance._setItemsVisibility(false);

						event.results.forEach(item => {
							var node = item.raw.node;

							var nodeContainerSelector = instance.get(
								'nodeContainerSelector'
							);

							if (nodeContainerSelector) {
								node = node.ancestor(nodeContainerSelector);
							}

							if (node) {
								node.show();
							}

							var contentParent = node.ancestorsByClassName(
								instance.get('categorySelector')
							);

							if (contentParent) {
								contentParent.show();

								contentParent
									.all('> .list-group-heading')
									.removeClass('collapsed');
								contentParent
									.all('> .list-group-panel')
									.addClass('in');
							}
						});
					}
				},

				initializer() {
					var instance = this;

					var nodeList = instance.get('nodeList');

					instance._categories = nodeList.all(
						instance.get('categorySelector')
					);

					var applicationSearch = new Liferay.SearchFilter({
						inputNode: instance.get('inputNode'),
						nodeList,
						nodeSelector: instance.get('nodeSelector')
					});

					instance._nodes = applicationSearch._nodes;
					instance._search = applicationSearch;

					instance._bindUISearch();
				}
			}
		});

		Liferay.PanelSearch = PanelSearch;
	},
	'',
	{
		requires: ['aui-base', 'liferay-search-filter']
	}
);
