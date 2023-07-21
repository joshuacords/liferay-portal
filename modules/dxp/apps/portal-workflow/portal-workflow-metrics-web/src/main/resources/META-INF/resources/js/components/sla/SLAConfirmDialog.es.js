/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import SLAListCardContext from './SLAListCardContext.es';

class SLAConfirmDialog extends React.Component {
	cancel() {
		this.context.hideConfirmDialog();
	}

	removeItem() {
		const {itemToRemove} = this.props;

		this.context.removeItem(itemToRemove);
	}

	render() {
		return (
			<div className="modal show" role="dialog" tabIndex="-1">
				<div className="modal-dialog modal-lg">
					<div className="modal-content">
						<div className="modal-body">
							<p>
								{Liferay.Language.get(
									'deleting-slas-will-reflect-on-report-data'
								)}
							</p>
						</div>

						<div className="modal-footer">
							<div className="modal-item-last">
								<div className="btn-group">
									<div className="btn-group-item">
										<button
											className="btn btn-secondary"
											onClick={this.cancel.bind(this)}
											type="button"
										>
											{Liferay.Language.get('cancel')}
										</button>
									</div>

									<div className="btn-group-item">
										<button
											className="btn btn-secondary"
											id="remove_sla_button"
											onClick={this.removeItem.bind(this)}
											type="button"
										>
											{Liferay.Language.get('ok')}
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

SLAConfirmDialog.contextType = SLAListCardContext;
export default SLAConfirmDialog;
