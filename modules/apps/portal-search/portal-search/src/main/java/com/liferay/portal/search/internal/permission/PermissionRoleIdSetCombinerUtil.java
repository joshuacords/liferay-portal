/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.permission;

import org.osgi.service.component.annotations.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Joshua Cords
 */
@Component(immediate = true, service = {})
public class PermissionRoleIdSetCombinerUtil {

	public static Set<Set<String>> combineRoleIdSets(
		Set<Set<String>> roleIdSets1, Set<Set<String>> roleIdSets2) {

		Set<Set<String>> roleIdsCombinations = new HashSet<>();

		roleIdsCombinations.addAll(roleIdSets1);
		roleIdsCombinations.addAll(roleIdSets2);

		_removeRedundantSets(roleIdsCombinations);

		return roleIdsCombinations;
	}

	public static Set<Set<String>> combineRoleIdSets(
		RoleSetContributorContextImpl roleSetContributorContextImpl) {
		return combineRoleIdSets(
			roleSetContributorContextImpl.getAccessPermissionRoleIdSets(),
			roleSetContributorContextImpl.getViewPermissionRoleIdSets());
	}

	private static void _removeRedundantSets(Set<Set<String>> roleIdSets) {

		//for each Set1 in sets
		Iterator<Set<String>> comparingIterator = roleIdSets.iterator();

		while (comparingIterator.hasNext()) {
			Set<String> comparingSet = comparingIterator.next();
			//for each other sets
			Iterator<Set<String>> searchingIterator = roleIdSets.iterator();

			while (searchingIterator.hasNext()) {
				Set<String> searchedSet = searchingIterator.next();

				if(comparingSet == searchedSet) {
					continue;
				}

				//if Set1 containsAll otherSet
				//delete Set1 and break
				if(comparingSet.containsAll(searchedSet)) {
					comparingIterator.remove();
					break;
				}

				if(searchedSet.containsAll(comparingSet)) {
					searchingIterator.remove();
				}

			}
		}
	}
}
