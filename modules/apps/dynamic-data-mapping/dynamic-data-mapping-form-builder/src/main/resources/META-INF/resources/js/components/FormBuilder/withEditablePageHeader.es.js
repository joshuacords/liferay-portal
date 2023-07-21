/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import './EditablePageHeader.soy';

import {PagesVisitor} from 'dynamic-data-mapping-form-renderer/js/util/visitors.es';
import {EventHandler} from 'metal-events';
import Component from 'metal-jsx';

import {sub} from '../../util/strings.es';
import formBuilderProps from './props.es';

const withEditablePageHeader = ChildComponent => {
	class EditablePageHeader extends Component {
		attached() {
			this._eventHandler = new EventHandler();

			this._eventHandler.add(
				this.delegate(
					'input',
					'.form-builder-page-header-title',
					this._handlePageTitleChanged.bind(this)
				),
				this.delegate(
					'input',
					'.form-builder-page-header-description',
					this._handlePageDescriptionChanged.bind(this)
				)
			);
		}

		disposed() {
			this._eventHandler.removeAllListeners();
		}

		getPages() {
			const {pages} = this.props;
			const total = pages.length;
			const visitor = new PagesVisitor(pages);

			return visitor.mapPages((page, pageIndex) => {
				return {
					...page,
					headerRenderer: 'editable',
					pageIndex,
					placeholder: sub(
						Liferay.Language.get('untitled-page-x-of-x'),
						[pageIndex + 1, total]
					),
					total
				};
			});
		}

		render() {
			return (
				<div>
					<ChildComponent {...this.props} pages={this.getPages()} />
				</div>
			);
		}

		_handlePageDescriptionChanged(event) {
			const {activePage, editingLanguageId, pages} = this.props;
			const {delegateTarget} = event;
			const {dispatch} = this.context;
			const value = delegateTarget.value;
			const visitor = new PagesVisitor(pages);

			dispatch(
				'pagesUpdated',
				visitor.mapPages((page, pageIndex) => {
					if (pageIndex === activePage) {
						page = {
							...page,
							description: value,
							localizedDescription: {
								...page.localizedDescription,
								[editingLanguageId]: value
							}
						};
					}

					return page;
				})
			);
		}

		_handlePageTitleChanged(event) {
			const {activePage, editingLanguageId, pages} = this.props;
			const {delegateTarget} = event;
			const {dispatch} = this.context;
			const value = delegateTarget.value;
			const visitor = new PagesVisitor(pages);

			dispatch(
				'pagesUpdated',
				visitor.mapPages((page, pageIndex) => {
					if (pageIndex === activePage) {
						page = {
							...page,
							localizedTitle: {
								...page.localizedTitle,
								[editingLanguageId]: value
							},
							title: value
						};
					}

					return page;
				})
			);
		}
	}

	EditablePageHeader.PROPS = {
		...formBuilderProps
	};

	return EditablePageHeader;
};

export default withEditablePageHeader;
