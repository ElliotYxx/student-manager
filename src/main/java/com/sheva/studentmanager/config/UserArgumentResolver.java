package com.sheva.studentmanager.config;

import com.sheva.studentmanager.annotation.CurrentUser;
import com.sheva.studentmanager.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sheva
 * @data 2019/11/1  下午8:30
 * @Version 1.0
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * 过滤出符合条件的参数，这里指是家了CurrentUser注解的参数
     * @param parameter
     * @return
     */

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        String authorization = null;

        if (request != null){
            authorization = request.getHeader("Authorization");
        }

        UserInfo userInfo;
        if (StringUtils.isNotBlank(authorization)){
            String[] strArray = authorization.split(":");
            userInfo = new UserInfo(strArray[0], strArray[1]);
        }else{
            userInfo = new UserInfo();
        }
        return userInfo;
    }
}
