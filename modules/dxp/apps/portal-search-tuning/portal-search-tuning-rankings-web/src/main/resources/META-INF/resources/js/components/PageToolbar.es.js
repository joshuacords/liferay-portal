/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayLink from '@clayui/link';
import ClayManagementToolbar from '@clayui/management-toolbar';
import PropTypes from 'prop-types';
import React, {Component} from 'react';

class PageToolbar extends Component {
	static props = {
		inactive: PropTypes.bool,
		onCancel: PropTypes.string.isRequired,
		onChangeActive: PropTypes.func,
		onPublish: PropTypes.func.isRequired,
		onSaveAsDraft: PropTypes.func,
		submitDisabled: PropTypes.bool
	};

	static defaultProps = {
		submitDisabled: false
	};

	render() {
		const {
			inactive,
			onCancel,
			onChangeActive,
			onPublish,
			onSaveAsDraft,
			submitDisabled
		} = this.props;

		return (
			<ClayManagementToolbar
				aria-label={Liferay.Language.get('save')}
				className="page-toolbar-root"
			>
				<ClayManagementToolbar.ItemList>
					<ClayManagementToolbar.Item>
						<label
							className="toggle-switch"
							htmlFor="active-switch-input"
						>
							<input
								checked={!inactive}
								className="toggle-switch-check"
								id="active-switch-input"
								onChange={onChangeActive}
								type="checkbox"
							/>

							<span className="toggle-switch-bar">
								<span className="toggle-switch-handle"></span>
							</span>

							<span className="toggle-switch-text-right">
								{inactive
									? Liferay.Language.get('inactive')
									: Liferay.Language.get('active')}
							</span>
						</label>
					</ClayManagementToolbar.Item>
				</ClayManagementToolbar.ItemList>

				<ClayManagementToolbar.ItemList
					expand
				></ClayManagementToolbar.ItemList>

				<ClayManagementToolbar.ItemList>
					<ClayManagementToolbar.Item>
						<ClayLink
							displayType="secondary"
							href={onCancel}
							outline="secondary"
						>
							{Liferay.Language.get('cancel')}
						</ClayLink>
					</ClayManagementToolbar.Item>

					{onSaveAsDraft && (
						<ClayManagementToolbar.Item>
							<ClayButton
								displayType="secondary"
								onClick={onSaveAsDraft}
								small
							>
								{Liferay.Language.get('save-as-draft')}
							</ClayButton>
						</ClayManagementToolbar.Item>
					)}

					<ClayManagementToolbar.Item>
						<ClayButton
							disabled={submitDisabled}
							onClick={onPublish}
							small
							type="submit"
						>
							{Liferay.Language.get('save')}
						</ClayButton>
					</ClayManagementToolbar.Item>
				</ClayManagementToolbar.ItemList>
			</ClayManagementToolbar>
		);
	}
}

export default PageToolbar;
