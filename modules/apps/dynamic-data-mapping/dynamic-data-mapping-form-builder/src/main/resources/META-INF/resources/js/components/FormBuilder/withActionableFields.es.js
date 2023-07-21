/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as FormSupport from 'dynamic-data-mapping-form-renderer/js/components/FormRenderer/FormSupport.es';
import dom from 'metal-dom';
import {EventHandler} from 'metal-events';
import Component from 'metal-jsx';

import FieldActionsDropDown, {
	getFieldContainer
} from './FieldActionsDropDown.es';
import formBuilderProps from './props.es';

const _CSS_HOVERED = 'hovered';

const withActionableFields = ChildComponent => {
	class ActionableFields extends Component {
		attached() {
			this._eventHandler = new EventHandler();

			this._eventHandler.add(
				this.delegate(
					'mouseenter',
					'.ddm-field-container',
					this._handleMouseEnterField.bind(this)
				)
			);

			this._eventHandler.add(
				this.delegate(
					'mouseleave',
					'.ddm-field-container',
					this._handleMouseLeaveField.bind(this)
				)
			);
		}

		disposeInternal() {
			super.disposeInternal();

			this._eventHandler.removeAllListeners();
		}

		isActionsEnabled() {
			const {defaultLanguageId, editingLanguageId} = this.props;

			return defaultLanguageId === editingLanguageId;
		}

		render() {
			const {fieldActions, pages, spritemap} = this.props;

			return (
				<div>
					<ChildComponent {...this.props} />

					<FieldActionsDropDown
						disabled={!this.isActionsEnabled()}
						items={fieldActions}
						pages={pages}
						portalElement={document.body}
						ref="selectedFieldActions"
						spritemap={spritemap}
						visible={false}
					/>

					<FieldActionsDropDown
						disabled={!this.isActionsEnabled()}
						items={fieldActions}
						pages={pages}
						portalElement={document.body}
						ref="hoveredFieldActions"
						spritemap={spritemap}
						visible={false}
					/>
				</div>
			);
		}

		rendered() {
			const {focusedField} = this.props;
			const {hoveredFieldActions, selectedFieldActions} = this.refs;

			if (Object.keys(focusedField).length > 0) {
				const {fieldName} = focusedField;

				this.showActions(selectedFieldActions, null, fieldName);
			}
			else {
				selectedFieldActions.props.visible = false;
			}

			if (hoveredFieldActions.state.fieldName) {
				this.showActions(
					hoveredFieldActions,
					null,
					hoveredFieldActions.state.fieldName
				);
			}
		}

		showActions(actions, field, fieldName) {
			actions.props.label = this._getFieldType(field, fieldName);
			actions.props.visible = true;

			if (fieldName !== actions.state.fieldName) {
				actions.setState({fieldName});

				actions.refs.dropdown.expanded = false;
			}

			actions.forceUpdate(() => {
				window.requestAnimationFrame(() => {
					const {pages} = this.props;
					const fieldContainer = getFieldContainer(pages, fieldName);

					if (fieldContainer) {
						fieldContainer.appendChild(actions.element);
					}
				});
			});
		}

		_getClosestParent(node) {
			return dom.closest(node.parentElement, `.ddm-field-container`);
		}

		_getFieldType(field, fieldName) {
			const {fieldTypes, pages} = this.props;

			if (!field) {
				field = FormSupport.findFieldByName(pages, fieldName);
			}

			return (
				field &&
				fieldTypes.find(fieldType => {
					return fieldType.name === field.type;
				}).label
			);
		}

		_handleMouseEnterField(event) {
			event.stopPropagation();

			const {delegateTarget} = event;

			this._handleActionsMouseEnter(delegateTarget);
			this._handleHoverMouseEnter(delegateTarget);

			event.stopPropagation();
		}

		_handleMouseLeaveField(event) {
			const {delegateTarget} = event;

			this._handleActionsMouseLeave(delegateTarget);
			this._handleHoverMouseLeave(delegateTarget);

			event.stopPropagation();
		}

		_handleActionsMouseEnter(delegateTarget) {
			if (!delegateTarget.classList.contains('selected')) {
				const {fieldName} = delegateTarget.dataset;
				const {hoveredFieldActions} = this.refs;
				const {pages} = this.props;

				const field = FormSupport.findFieldByName(pages, fieldName);

				if (field) {
					this.showActions(hoveredFieldActions, field, fieldName);
				}
			}
		}

		_handleActionsMouseLeave(delegateTarget) {
			const closestParent = this._getClosestParent(delegateTarget);

			if (
				closestParent &&
				!closestParent.classList.contains('selected')
			) {
				const {fieldName} = closestParent.dataset;
				const {hoveredFieldActions} = this.refs;
				const {pages} = this.props;

				const field = FormSupport.findFieldByName(pages, fieldName);

				if (field) {
					this.showActions(hoveredFieldActions, field, fieldName);
				}
			}
		}

		_handleHoverMouseEnter(delegateTarget) {
			let closestParent = this._getClosestParent(delegateTarget);

			while (closestParent) {
				closestParent.classList.remove(_CSS_HOVERED);

				closestParent = this._getClosestParent(closestParent);
			}

			delegateTarget.classList.add(_CSS_HOVERED);
		}

		_handleHoverMouseLeave(delegateTarget) {
			const closestParent = this._getClosestParent(delegateTarget);

			if (closestParent) {
				closestParent.classList.add(_CSS_HOVERED);
			}

			delegateTarget.classList.remove(_CSS_HOVERED);
		}
	}

	ActionableFields.PROPS = {
		...formBuilderProps
	};

	return ActionableFields;
};

export default withActionableFields;
