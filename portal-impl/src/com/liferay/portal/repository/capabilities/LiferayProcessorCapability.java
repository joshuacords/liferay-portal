/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.repository.capabilities;

import com.liferay.document.library.kernel.util.DLProcessorRegistryUtil;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.Repository;
import com.liferay.portal.kernel.repository.capabilities.ProcessorCapability;
import com.liferay.portal.kernel.repository.event.RepositoryEventAware;
import com.liferay.portal.kernel.repository.event.RepositoryEventListener;
import com.liferay.portal.kernel.repository.event.RepositoryEventType;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.registry.RepositoryEventRegistry;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.repository.liferayrepository.LiferayProcessorLocalRepositoryWrapper;
import com.liferay.portal.repository.liferayrepository.LiferayProcessorRepositoryWrapper;
import com.liferay.portal.repository.util.RepositoryWrapperAware;

import java.util.concurrent.Callable;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.document.library.internal.capabilities.LiferayProcessorCapability}
 */
@Deprecated
public class LiferayProcessorCapability
	implements ProcessorCapability, RepositoryEventAware,
			   RepositoryWrapperAware {

	public LiferayProcessorCapability() {
		this(ResourceGenerationStrategy.REUSE);
	}

	public LiferayProcessorCapability(
		ResourceGenerationStrategy resourceGenerationStrategy) {

		_resourceGenerationStrategy = resourceGenerationStrategy;
	}

	@Override
	public void cleanUp(FileEntry fileEntry) {
		DLProcessorRegistryUtil.cleanUp(fileEntry);
	}

	@Override
	public void cleanUp(FileVersion fileVersion) {
		DLProcessorRegistryUtil.cleanUp(fileVersion);
	}

	@Override
	public void copy(FileEntry fileEntry, FileVersion fileVersion) {
		if (_resourceGenerationStrategy == ResourceGenerationStrategy.REUSE) {
			registerDLProcessorCallback(fileEntry, fileVersion);
		}
		else {
			generateNew(fileEntry);
		}
	}

	@Override
	public void generateNew(FileEntry fileEntry) {
		registerDLProcessorCallback(fileEntry, null);
	}

	@Override
	public void registerRepositoryEventListeners(
		RepositoryEventRegistry repositoryEventRegistry) {

		repositoryEventRegistry.registerRepositoryEventListener(
			RepositoryEventType.Delete.class, FileEntry.class,
			new RepositoryEventListener
				<RepositoryEventType.Delete, FileEntry>() {

				@Override
				public void execute(FileEntry fileEntry) {
					cleanUp(fileEntry);
				}

			});
	}

	@Override
	public LocalRepository wrapLocalRepository(
		LocalRepository localRepository) {

		return new LiferayProcessorLocalRepositoryWrapper(
			localRepository, this);
	}

	@Override
	public Repository wrapRepository(Repository repository) {
		return new LiferayProcessorRepositoryWrapper(repository, this);
	}

	protected void registerDLProcessorCallback(
		final FileEntry fileEntry, final FileVersion fileVersion) {

		TransactionCommitCallbackUtil.registerCallback(
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					DLProcessorRegistryUtil.trigger(
						fileEntry, fileVersion, true);

					return null;
				}

			});
	}

	private final ResourceGenerationStrategy _resourceGenerationStrategy;

}