/*
 * Copyright (C) 2023-2023 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.discovery.declarers;

import com.huawei.discovery.interceptors.JettyRequestInterceptor;

import com.huaweicloud.sermant.core.plugin.agent.declarer.InterceptDeclarer;
import com.huaweicloud.sermant.core.plugin.agent.matcher.ClassMatcher;
import com.huaweicloud.sermant.core.plugin.agent.matcher.MethodMatcher;

/**
 * webclient jetty拦截点，springboot 2.1.0.RELEASE+
 *
 * @author provenceee
 * @since 2023-04-25
 */
public class JettyRequestDeclarer extends BaseDeclarer {
    private static final String ENHANCE_CLASS = "org.eclipse.jetty.client.HttpRequest";

    private static final String INTERCEPT_CLASS = JettyRequestInterceptor.class.getCanonicalName();

    @Override
    public ClassMatcher getClassMatcher() {
        return ClassMatcher.nameEquals(ENHANCE_CLASS);
    }

    @Override
    public InterceptDeclarer[] getInterceptDeclarers(ClassLoader classLoader) {
        return new InterceptDeclarer[]{
                InterceptDeclarer.build(MethodMatcher.nameEquals("send").and(MethodMatcher.paramTypesEqual(
                        "org.eclipse.jetty.client.api.Response$CompleteListener")),
                        INTERCEPT_CLASS)
        };
    }
}