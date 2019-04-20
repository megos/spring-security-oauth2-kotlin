package com.example.oauth

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableResourceServer
class OAuthResourceConfig : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        // OAuthAuthorizationConfigで利用するリソースIDの設定
        resources.resourceId("my_resource_id")
    }

    override fun configure(http: HttpSecurity) {
        // アクセス権限の設定
        http.authorizeRequests()
                // GETにはreadスコープが必要
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
    }
}
