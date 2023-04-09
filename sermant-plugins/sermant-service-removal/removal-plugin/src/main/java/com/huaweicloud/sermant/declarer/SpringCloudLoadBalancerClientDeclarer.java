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

package com.huaweicloud.sermant.declarer;

import com.huaweicloud.sermant.core.plugin.agent.declarer.AbstractPluginDeclarer;
import com.huaweicloud.sermant.core.plugin.agent.declarer.InterceptDeclarer;
import com.huaweicloud.sermant.core.plugin.agent.matcher.ClassMatcher;
import com.huaweicloud.sermant.core.plugin.agent.matcher.MethodMatcher;
import com.huaweicloud.sermant.interceptor.SpringCloudLoadBalancerClientInterceptor;

/**
 * SpringCloud服务调用拦截定义
 *
 * @author zhp
 * @since 2023-02-17
 */
public class SpringCloudLoadBalancerClientDeclarer extends AbstractPluginDeclarer {
    private static final String ENHANCE_CLASS = "org.springframework.cloud.client.loadbalancer.LoadBalancerClient";

    private static final String[] PARAM_CLASS = {"java.lang.String", "org.springframework.cloud.client.ServiceInstance",
        "org.springframework.cloud.client.loadbalancer.LoadBalancerRequest"};

    private static final String INTERCEPT_CLASS = SpringCloudLoadBalancerClientInterceptor.class.getCanonicalName();

    private static final String METHOD_NAME = "execute";

    @Override
    public ClassMatcher getClassMatcher() {
        return ClassMatcher.isExtendedFrom(ENHANCE_CLASS);
    }

    @Override
    public InterceptDeclarer[] getInterceptDeclarers(ClassLoader classLoader) {
        return new InterceptDeclarer[]{InterceptDeclarer.build(
                MethodMatcher.nameEquals(METHOD_NAME).and(
                        MethodMatcher.paramTypesEqual(PARAM_CLASS)), INTERCEPT_CLASS)};
    }
}
