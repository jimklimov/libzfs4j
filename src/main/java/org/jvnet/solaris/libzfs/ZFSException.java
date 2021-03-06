/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
 * or http://www.opensolaris.org/os/licensing.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at usr/src/OPENSOLARIS.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */
package org.jvnet.solaris.libzfs;

import static org.jvnet.solaris.libzfs.jna.libzfs.LIBZFS;

import org.jvnet.solaris.libzfs.jna.libzfs_handle_t;

/**
 * Indicates an error in ZFS operation.
 *
 * @author Kohsuke Kawaguchi
 */
public class ZFSException extends RuntimeException {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8348969894255697338L;

    private final ErrorCode code;

    /* package */ZFSException(LibZFS zfs) {
        this(zfs,null);
    }

    /*package*/ ZFSException(LibZFS zfs, String message) {
        super((message==null?"":message+" : ")+LIBZFS.libzfs_error_description(zfs.getHandle()));

        final libzfs_handle_t h = zfs.getHandle();
        code = ErrorCode.fromCode(LIBZFS.libzfs_errno(h));
    }

    /**
     * Gets the ZFS error code.
     */
    public ErrorCode getCode() {
        return code;
    }

    public String toString() {
        return super.toString() + " " + code;
    }

}
