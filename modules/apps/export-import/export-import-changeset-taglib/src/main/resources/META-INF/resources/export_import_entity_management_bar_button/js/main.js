/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

AUI.add(
	'liferay-export-import-management-bar-button',
	A => {
		var Lang = A.Lang;

		var ExportImportManagementBarButton = A.Component.create({
			ATTRS: {
				actionNamespace: {
					validator: Lang.isString()
				},

				cmd: {
					validator: Lang.isString()
				},

				exportImportEntityUrl: {
					validator: Lang.isString()
				},

				searchContainerId: {
					validator: Lang.isString
				},

				searchContainerMappingId: {
					validator: Lang.isString
				}
			},

			AUGMENTS: [Liferay.PortletBase],

			EXTENDS: A.Base,

			NAME: 'exportImportManagementBarButton',

			prototype: {
				_bindUI() {
					var instance = this;

					instance._eventHandles = [
						Liferay.on(
							instance.ns(instance.get('cmd')),
							instance._exportImportEntity,
							instance
						)
					];
				},

				_exportImportEntity() {
					var instance = this;

					var searchContainer = instance._searchContainer.plug(
						A.Plugin.SearchContainerSelect
					);

					var selectedRows = searchContainer.select.getAllSelectedElements();

					var namespace = instance.NS;

					var searchContainerMapping = A.one(
						'#' +
							namespace +
							instance.get('searchContainerMappingId')
					);

					var form = document.getElementById('hrefFm');

					if (form) {
						selectedRows._nodes.forEach(selectedElement => {
							var node = searchContainerMapping.one(
								'div[data-rowpk=' + selectedElement.value + ']'
							);

							var input = document.createElement('input');
							input.setAttribute('type', 'hidden');
							input.setAttribute(
								'name',
								instance.get('actionNamespace') +
									'exportingEntities'
							);
							input.value =
								node.attr('data-classNameId') +
								'#' +
								node.attr('data-groupId') +
								'#' +
								node.attr('data-uuid');

							form.appendChild(input);
						});

						form.setAttribute('method', 'POST');

						submitForm(form, instance.get('exportImportEntityUrl'));
					}
				},

				destructor() {
					var instance = this;

					new A.EventHandle(instance._eventHandles).detach();
				},

				initializer() {
					var instance = this;

					var namespace = instance.NS;

					var searchContainer = Liferay.SearchContainer.get(
						namespace + instance.get('searchContainerId')
					);

					instance._searchContainer = searchContainer;

					instance._bindUI();
				}
			}
		});

		Liferay.ExportImportManagementBarButton = ExportImportManagementBarButton;
	},
	'',
	{
		requires: [
			'aui-component',
			'liferay-search-container',
			'liferay-search-container-select'
		]
	}
);
