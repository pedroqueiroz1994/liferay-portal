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

package com.liferay.sharing.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.security.permission.SharingEntryAction;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for SharingEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryLocalServiceUtil
 * @see com.liferay.sharing.service.base.SharingEntryLocalServiceBaseImpl
 * @see com.liferay.sharing.service.impl.SharingEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SharingEntryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SharingEntryLocalServiceUtil} to access the sharing entry local service. Add custom service methods to {@link com.liferay.sharing.service.impl.SharingEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds a sharing entry in the database if it does not exist or it updates
	* it if it exists.
	*
	* @param fromUserId the user id sharing the resource
	* @param toUserId the user id whose resource was shared
	* @param classNameId the class name ID of the resource being shared
	* @param classPK the primary key of the resource being shared
	* @param groupId the primary key of the group containing the resource
	being shared
	* @param shareable whether the to user id can share the resource as well
	* @param sharingEntryActions the sharing entry actions
	* @param expirationDate the date when the sharing entry expires
	* @return the sharing entry
	* @param serviceContext the service context to be applied
	* @throws PortalException if sharing entry actions are invalid (it is
	empty, it doesn't contain {@link SharingEntryAction#VIEW,} or
	it contains a <code>null</code> value) or from user id and to
	user id are the same or the expiration date is a value in the
	past.
	*/
	public SharingEntry addOrUpdateSharingEntry(long fromUserId, long toUserId,
		long classNameId, long classPK, long groupId, boolean shareable,
		Collection<SharingEntryAction> sharingEntryActions,
		Date expirationDate, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds a sharing entry in the database.
	*
	* @param fromUserId the user id sharing the resource
	* @param toUserId the user id whose resource was shared
	* @param classNameId the class name ID of the resource being shared
	* @param classPK the primary key of the resource being shared
	* @param groupId the primary key of the group containing the resource
	being shared
	* @param shareable whether the to user id can share the resource as well
	* @param sharingEntryActions the sharing entry actions
	* @param expirationDate the date when the sharing entry expires
	* @return the sharing entry
	* @param serviceContext the service context to be applied
	* @throws PortalException if there is already a sharing entry for the same
	from user id, to user id and resource or the sharing entry
	actions are invalid (it is empty, it doesn't contain
	{@link SharingEntryAction#VIEW,} or it contains a
	<code>null</code> value) or from user id and to user id are the
	same or the expiration date is a value in the past.
	*/
	public SharingEntry addSharingEntry(long fromUserId, long toUserId,
		long classNameId, long classPK, long groupId, boolean shareable,
		Collection<SharingEntryAction> sharingEntryActions,
		Date expirationDate, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Adds the sharing entry to the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SharingEntry addSharingEntry(SharingEntry sharingEntry);

	/**
	* Returns the number of sharing entries that have been shared by a user.
	*
	* @param fromUserId the user id sharing the resource
	* @return the number of sharing entries
	*/
	public int countFromUserSharingEntries(long fromUserId);

	/**
	* Returns the number of sharing entries of a resource that have been shared
	* by a user.
	*
	* @param fromUserId the user id sharing the resource
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @return the number of sharing entries
	*/
	public int countFromUserSharingEntries(long fromUserId, long classNameId,
		long classPK);

	/**
	* Returns the number of sharing entries that have been shared to a user.
	*
	* @param toUserId the user id who was shared the resource
	* @return the number of sharing entries
	*/
	public int countToUserSharingEntries(long toUserId);

	/**
	* Creates a new sharing entry with the primary key. Does not add the sharing entry to the database.
	*
	* @param sharingEntryId the primary key for the new sharing entry
	* @return the new sharing entry
	*/
	@Transactional(enabled = false)
	public SharingEntry createSharingEntry(long sharingEntryId);

	/**
	* Deletes all sharing entries whose expiration date is before the current
	* date.
	*/
	public void deleteExpiredEntries();

	/**
	* Deletes all sharing entries that belong to a group.
	*/
	public void deleteGroupSharingEntries(long groupId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes all sharing entries of a resource.
	*
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	*/
	public void deleteSharingEntries(long classNameId, long classPK);

	/**
	* Deletes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntryId the primary key of the sharing entry
	* @return the sharing entry that was removed
	* @throws PortalException if a sharing entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SharingEntry deleteSharingEntry(long sharingEntryId)
		throws PortalException;

	/**
	* Deletes the sharing entry of a user to another user for a resource.
	*
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @return the deleted sharing entry
	*/
	public SharingEntry deleteSharingEntry(long fromUserId, long toUserId,
		long classNameId, long classPK) throws PortalException;

	/**
	* Deletes the sharing entry from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SharingEntry deleteSharingEntry(SharingEntry sharingEntry);

	/**
	* Deletes all sharing entries shared to a user.
	*
	* @param toUserId the user id who was shared the resource
	*/
	public void deleteToUserSharingEntries(long toUserId);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SharingEntry fetchSharingEntry(long sharingEntryId);

	/**
	* Returns the sharing entry matching the UUID and group.
	*
	* @param uuid the sharing entry's UUID
	* @param groupId the primary key of the group
	* @return the matching sharing entry, or <code>null</code> if a matching sharing entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SharingEntry fetchSharingEntryByUuidAndGroupId(String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	* Returns a list of all the sharing entries that has been shared by a user.
	*
	* @param fromUserId the user id sharing the resource
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getFromUserSharingEntries(long fromUserId);

	/**
	* Returns a list of all the sharing entries of a resource that has been
	* shared by a user
	*
	* @param fromUserId the user id sharing the resource
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getFromUserSharingEntries(long fromUserId,
		long classNameId, long classPK);

	/**
	* Returns a range of all the sharing entries of a resource that has been
	* shared by a user
	*
	* @param fromUserId the user id sharing the resource
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @param start the lower bound of the range of results
	* @param end the upper bound of the range of results (not inclusive)
	* @return the range of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getFromUserSharingEntries(long fromUserId,
		long classNameId, long classPK, int start, int end);

	/**
	* Returns a list of all the sharing entries of a group.
	*
	* @param groupId the primary key of the group
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getGroupSharingEntries(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns a range of all the sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getSharingEntries(int start, int end);

	/**
	* Returns a list of all the sharing entries of a resource.
	*
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getSharingEntries(long classNameId, long classPK);

	/**
	* Returns a list of all the sharing entries of a resource that has been
	* shared to a user.
	*
	* @param toUserId the user id that has been shared the resource
	* @param classNameId the class name ID of the resource
	* @param classPK the primary key of the resource
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getSharingEntries(long toUserId,
		long classNameId, long classPK);

	/**
	* Returns all the sharing entries matching the UUID and company.
	*
	* @param uuid the UUID of the sharing entries
	* @param companyId the primary key of the company
	* @return the matching sharing entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getSharingEntriesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of sharing entries matching the UUID and company.
	*
	* @param uuid the UUID of the sharing entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sharing entries, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getSharingEntriesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<SharingEntry> orderByComparator);

	/**
	* Returns the number of sharing entries.
	*
	* @return the number of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSharingEntriesCount();

	/**
	* Returns the sharing entry with the primary key.
	*
	* @param sharingEntryId the primary key of the sharing entry
	* @return the sharing entry
	* @throws PortalException if a sharing entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SharingEntry getSharingEntry(long sharingEntryId)
		throws PortalException;

	/**
	* Returns the sharing entry matching the UUID and group.
	*
	* @param uuid the sharing entry's UUID
	* @param groupId the primary key of the group
	* @return the matching sharing entry
	* @throws PortalException if a matching sharing entry could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SharingEntry getSharingEntryByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a list of all the sharing entries that has been shared to a user.
	*
	* @param toUserId the user id that has been shared the resource
	* @return the range of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getToUserSharingEntries(long toUserId);

	/**
	* Returns a range of all the sharing entries that has been shared to a
	* user.
	*
	* @param toUserId the user id that has been shared the resource
	* @return the range of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getToUserSharingEntries(long toUserId, int start,
		int end);

	/**
	* Returns a list of sharing entries of a specific class name id that has
	* been shared to a user.
	*
	* @param toUserId the user id that has been shared the resource
	* @param classNameId the class name ID of the shared resource
	* @return the list of sharing entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SharingEntry> getToUserSharingEntries(long toUserId,
		long classNameId);

	/**
	* Returns <code>true</code> if the to user id has been shared a resource
	* with a sharing entry action and, in addition, he can share the resource
	* as well.
	*
	* @param toUserId the user id that has been shared the resource
	* @param classNameId the class name ID of the shared resource
	* @param classPK the primary key of the shared resource
	* @param sharingEntryAction the sharing entry action
	* @return <code>true</code> if the user has been shared a resource with a
	sharing entry action and he can, in additino, share the resource
	as well; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasShareableSharingPermission(long toUserId,
		long classNameId, long classPK, SharingEntryAction sharingEntryAction);

	/**
	* Returns <code>true</code> if the to user id has been shared a resource
	* with a sharing entry action
	*
	* @param toUserId the user id that has been shared the resource
	* @param classNameId the class name ID of the shared resource
	* @param classPK the primary key of the shared resource
	* @param sharingEntryAction the sharing entry action
	* @return <code>true</code> if the user has been shared a resource with a
	sharing entry action; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSharingPermission(long toUserId, long classNameId,
		long classPK, SharingEntryAction sharingEntryAction);

	/**
	* Returns <code>true</code> if the sharing entry has certain sharing entry
	* action
	*
	* @param sharingEntry the sharing entry
	* @param sharingEntryAction the sharing entry action
	* @return <code>true</code> if the sharing entry has the sharing entry
	action; <code>false</code> otherwise
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSharingPermission(SharingEntry sharingEntry,
		SharingEntryAction sharingEntryAction);

	/**
	* Updates a sharing entry in the database.
	*
	* @param sharingEntryId the primary key of the sharing entry
	* @param sharingEntryActions the sharing entry actions
	* @param shareable whether the to user id can share the resource as well
	* @param expirationDate the date when the sharing entry expires
	* @return the sharing entry
	* @param serviceContext the service context to be applied
	* @throws PortalException if the sharing entry does not exist or sharing
	entry actions are invalid (it is empty, it doesn't contain
	{@link SharingEntryAction#VIEW,} or it contains a
	<code>null</code> value) or the expiration date is a value in the
	past.
	*/
	public SharingEntry updateSharingEntry(long sharingEntryId,
		Collection<SharingEntryAction> sharingEntryActions, boolean shareable,
		Date expirationDate, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Updates the sharing entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SharingEntry updateSharingEntry(SharingEntry sharingEntry);
}