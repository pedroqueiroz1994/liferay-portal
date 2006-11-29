/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MBThreadUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class MBThreadUtil {
	public static com.liferay.portlet.messageboards.model.MBThread create(
		java.lang.String threadId) {
		return getPersistence().create(threadId);
	}

	public static com.liferay.portlet.messageboards.model.MBThread remove(
		java.lang.String threadId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.messageboards.NoSuchThreadException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(threadId));
		}

		com.liferay.portlet.messageboards.model.MBThread mbThread = getPersistence()
																		.remove(threadId);

		if (listener != null) {
			listener.onAfterRemove(mbThread);
		}

		return mbThread;
	}

	public static com.liferay.portlet.messageboards.model.MBThread remove(
		com.liferay.portlet.messageboards.model.MBThread mbThread)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();

		if (listener != null) {
			listener.onBeforeRemove(mbThread);
		}

		mbThread = getPersistence().remove(mbThread);

		if (listener != null) {
			listener.onAfterRemove(mbThread);
		}

		return mbThread;
	}

	public static com.liferay.portlet.messageboards.model.MBThread update(
		com.liferay.portlet.messageboards.model.MBThread mbThread)
		throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = mbThread.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(mbThread);
			}
			else {
				listener.onBeforeUpdate(mbThread);
			}
		}

		mbThread = getPersistence().update(mbThread);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(mbThread);
			}
			else {
				listener.onAfterUpdate(mbThread);
			}
		}

		return mbThread;
	}

	public static com.liferay.portlet.messageboards.model.MBThread update(
		com.liferay.portlet.messageboards.model.MBThread mbThread,
		boolean saveOrUpdate) throws com.liferay.portal.SystemException {
		ModelListener listener = _getListener();
		boolean isNew = mbThread.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(mbThread);
			}
			else {
				listener.onBeforeUpdate(mbThread);
			}
		}

		mbThread = getPersistence().update(mbThread, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(mbThread);
			}
			else {
				listener.onAfterUpdate(mbThread);
			}
		}

		return mbThread;
	}

	public static com.liferay.portlet.messageboards.model.MBThread findByPrimaryKey(
		java.lang.String threadId)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.messageboards.NoSuchThreadException {
		return getPersistence().findByPrimaryKey(threadId);
	}

	public static com.liferay.portlet.messageboards.model.MBThread fetchByPrimaryKey(
		java.lang.String threadId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(threadId);
	}

	public static java.util.List findByCategoryId(java.lang.String categoryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCategoryId(categoryId);
	}

	public static java.util.List findByCategoryId(java.lang.String categoryId,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findByCategoryId(categoryId, begin, end);
	}

	public static java.util.List findByCategoryId(java.lang.String categoryId,
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCategoryId(categoryId, begin, end, obc);
	}

	public static com.liferay.portlet.messageboards.model.MBThread findByCategoryId_First(
		java.lang.String categoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.messageboards.NoSuchThreadException {
		return getPersistence().findByCategoryId_First(categoryId, obc);
	}

	public static com.liferay.portlet.messageboards.model.MBThread findByCategoryId_Last(
		java.lang.String categoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.messageboards.NoSuchThreadException {
		return getPersistence().findByCategoryId_Last(categoryId, obc);
	}

	public static com.liferay.portlet.messageboards.model.MBThread[] findByCategoryId_PrevAndNext(
		java.lang.String threadId, java.lang.String categoryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException, 
			com.liferay.portlet.messageboards.NoSuchThreadException {
		return getPersistence().findByCategoryId_PrevAndNext(threadId,
			categoryId, obc);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByCategoryId(java.lang.String categoryId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCategoryId(categoryId);
	}

	public static int countByCategoryId(java.lang.String categoryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCategoryId(categoryId);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static MBThreadPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(MBThreadPersistence persistence) {
		_persistence = persistence;
	}

	private static MBThreadUtil _getUtil() {
		if (_util == null) {
			_util = (MBThreadUtil)com.liferay.portal.kernel.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static ModelListener _getListener() {
		if (Validator.isNotNull(_LISTENER)) {
			try {
				return (ModelListener)Class.forName(_LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		return null;
	}

	private static final String _UTIL = MBThreadUtil.class.getName();
	private static final String _LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portlet.messageboards.model.MBThread"));
	private static Log _log = LogFactory.getLog(MBThreadUtil.class);
	private static MBThreadUtil _util;
	private MBThreadPersistence _persistence;
}