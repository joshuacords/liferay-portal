/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export default function({namespace}) {
	const dropdown = document.getElementById(`${namespace}dropdown`);
	const dropdownToggle = document.getElementById(
		`${namespace}dropdownToggle`
	);

	let closeSimulationPanelEvent = null;
	let openSimulationPanelEvent = null;

	const handleClick = () => {
		if (dropdownToggle.getAttribute('aria-expanded') === 'false') {
			dropdown.classList.add('show');
			dropdownToggle.setAttribute('aria-expanded', 'true');
		}
		else {
			dropdown.classList.remove('show');
			dropdownToggle.setAttribute('aria-expanded', 'false');
		}
	};

	const handleDisable = disable => {
		dropdown.classList.remove('show');
		dropdownToggle.classList[disable ? 'add' : 'remove']('disabled');
		dropdownToggle.setAttribute('aria-expanded', 'false');
	};

	if (dropdown && dropdownToggle) {
		closeSimulationPanelEvent = Liferay.on(
			'SimulationMenu:closeSimulationPanel',
			() => handleDisable(false)
		);
		dropdownToggle.addEventListener('click', handleClick);
		openSimulationPanelEvent = Liferay.on(
			'SimulationMenu:openSimulationPanel',
			() => handleDisable(true)
		);
	}

	return {
		dispose() {
			if (dropdownToggle) {
				closeSimulationPanelEvent.detach();
				dropdownToggle.removeEventListener('click', handleClick);
				openSimulationPanelEvent.detach();
			}
		}
	};
}
