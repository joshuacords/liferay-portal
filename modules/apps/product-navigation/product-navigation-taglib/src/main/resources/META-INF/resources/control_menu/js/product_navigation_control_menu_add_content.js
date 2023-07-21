/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-product-navigation-control-menu-add-content',
	A => {
		var ControlMenu = Liferay.ControlMenu;

		var SELECTOR_ADD_CONTENT_ITEM = '.add-content-item';

		var STR_CLICK = 'click';

		var AddContent = A.Component.create({
			AUGMENTS: [ControlMenu.AddContentSearch, Liferay.PortletBase],

			EXTENDS: ControlMenu.AddBase,

			NAME: 'addcontent',

			prototype: {
				_afterSuccess(response) {
					var instance = this;

					instance._entriesPanel.setContent(response);
				},

				_bindUI() {
					var instance = this;

					instance._eventHandles.push(
						instance._entriesPanel.delegate(
							STR_CLICK,
							instance._addContent,
							SELECTOR_ADD_CONTENT_ITEM,
							instance
						),
						Liferay.on(
							'AddContent:refreshContentList',
							instance._refreshContentList,
							instance
						),
						Liferay.once('AddContent:addPortlet', event => {
							instance.addPortlet(event.node, event.options);
						})
					);
				},

				_refreshContentList(event) {
					var instance = this;

					var delta = event.delta;

					if (delta) {
						instance._delta = delta;

						Liferay.Store(
							'com.liferay.product.navigation.control.menu.web_addPanelNumItems',
							delta
						);
					}

					var displayStyle = event.displayStyle;

					if (displayStyle) {
						instance._displayStyle = displayStyle;

						Liferay.Util.Session.set(
							'com.liferay.product.navigation.control.menu.web_addPanelDisplayStyle',
							displayStyle
						);
					}

					var data = instance.ns({
						delta: instance._delta,
						displayStyle: instance._displayStyle,
						keywords: instance.get('inputNode').val()
					});

					Liferay.Util.fetch(
						instance._addContentForm.getAttribute('action'),
						{
							body: Liferay.Util.objectToFormData(data),
							method: 'POST'
						}
					)
						.then(response => {
							return response.text();
						})
						.then(response => {
							instance._afterSuccess(response);
						});
				},

				destructor() {
					var instance = this;

					new A.EventHandle(instance._eventHandles).detach();
				},

				initializer(config) {
					var instance = this;

					instance._config = config;
					instance._delta = config.delta;
					instance._displayStyle = config.displayStyle;

					instance._addContentForm = instance.byId('addContentForm');
					instance._entriesPanel = instance.byId('entriesContainer');

					instance._entriesPanel.plug(A.Plugin.ParseContent);

					instance._bindUI();
				}
			}
		});

		ControlMenu.AddContent = AddContent;
	},
	'',
	{
		requires: [
			'aui-parse-content',
			'liferay-product-navigation-control-menu',
			'liferay-product-navigation-control-menu-add-base',
			'liferay-product-navigation-control-menu-add-content-search'
		]
	}
);
