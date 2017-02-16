package com.umspreadsheet.config;

import com.umspreadsheet.user.SimpleUserDetails;
import com.umspreadsheet.user.SimpleUserService;
import com.umspreadsheet.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SimpleSignInAdapter implements SignInAdapter
{
    private SimpleUserService simpleUserService;

    private final RequestCache requestCache;

    @Inject
    public SimpleSignInAdapter(SimpleUserService simpleUserService,
                               RequestCache requestCache)
    {
        this.simpleUserService = simpleUserService;
        this.requestCache = requestCache;
    }

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request)
    {
        UserDetails userDetails = new SimpleUserDetails(simpleUserService.findByUserId(localUserId));
        SignInUtils.signin(userDetails);

        return extractOriginalUrl(request);
    }

    private String extractOriginalUrl(NativeWebRequest request)
    {
        HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
        SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
        if (saved == null)
        {
            return null;
        }
        requestCache.removeRequest(nativeReq, nativeRes);
        removeAuthenticationAttributes(nativeReq.getSession(false));

        return saved.getRedirectUrl();
    }

    private void removeAuthenticationAttributes(HttpSession session)
    {
        if (session == null)
        {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
