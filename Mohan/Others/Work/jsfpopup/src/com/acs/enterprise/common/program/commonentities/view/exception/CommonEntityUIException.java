/*
 * Created on May 14, 2007 TODO To change the template for this generated file
 * go to Window - Preferences - Java - Code Style - Code Templates
 */

package com.acs.enterprise.common.program.commonentities.view.exception;

import com.acs.enterprise.common.base.view.exception.EnterpriseBaseUIException;

/**
 * @author madhach TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonEntityUIException
        extends EnterpriseBaseUIException
{

    /**
     * Default Constructor.
     * 
     * @param errorCode -
     *            The errorCode of type String is to uniquely identify an
     *            exception.
     * @param message -
     *            The message of type String is the description of the
     *            exception.
     */
    public CommonEntityUIException(String errorCode, String message)
    {
        super(errorCode, message);
    }

    /**
     * Default Constructor.
     * 
     * @param errorCode -
     *            The errorCode of type String is to uniquely identify an
     *            exception.
     * @param message -
     *            The message of type String is the description of the
     *            exception.
     * @param throwable -
     *            The throwable of type Throwable is cause of the exception.
     */
    public CommonEntityUIException(String errorCode, String message,
            Throwable throwable)
    {
        super(errorCode, message, throwable);
    }

    /**
     * Default Constructor.
     * 
     * @param errorCode -
     *            The errorCode of type String is to uniquely identify an
     *            exception.
     * @param throwable -
     *            The throwable of type Throwable is cause of the exception.
     */
    public CommonEntityUIException(String errorCode, Throwable throwable)
    {
        super(errorCode, throwable);
    }

    /**
     * Default Constructor.
     * 
     * @param throwable -
     *            The throwable of type Throwable is cause of the exception.
     */
    public CommonEntityUIException(Throwable throwable)
    {
        super(throwable);
    }
}
