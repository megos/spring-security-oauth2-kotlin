package com.example.oauth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

@Configuration
@EnableAuthorizationServer
class OAuthAuthorizationConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var tokenStore: TokenStore

    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    lateinit var authenticationManager: AuthenticationManager

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        // OAuthのTokenをインメモリで管理
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        // OAuth認証用のクライアント（アプリケーション）の設定
        clients.inMemory()
                .withClient("client_id")
                .secret("client_secret")
                .authorities("USER")
                .resourceIds("my_resource_id")
                .scopes("read")
                .authorizedGrantTypes("authorization_code", "password")
                .redirectUris("http://localhost:8080/")
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        // Basic認証だけでなくPOSTのbodyでの認証を許可する
        security.allowFormAuthenticationForClients()
    }
}
