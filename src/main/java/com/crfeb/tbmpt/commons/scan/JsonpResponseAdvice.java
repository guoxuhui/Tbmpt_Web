/*--------------------------------------------------------------------------
 *  Copyright (c) 2009-2020, dennisit.pu All rights reserved. 
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the yinyuetai developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: dennisit.pu (dennisit@163.com)
 *--------------------------------------------------------------------------
*/
package com.crfeb.tbmpt.commons.scan;

import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 自动jsonp
 * @author：smxg
 * @date：2016-10-10 11:12
 */
@ControllerAdvice
public class JsonpResponseAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpResponseAdvice() {
        super("callback");
    }

    
    protected MediaType getContentType(MediaType contentType, ServerHttpRequest request, ServerHttpResponse response) {
        return new MediaType("application", "javascript", contentType.getCharset());
    }
}
