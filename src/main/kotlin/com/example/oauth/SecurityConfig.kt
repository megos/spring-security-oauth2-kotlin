package com.example.oauth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        // 今回はインメモリだが、ここでDBから読み込んだユーザを指定できる
        // auth.userDetailsService()...
        auth.inMemoryAuthentication()
                // .withUser().password().roles()のセットでユーザを追加していく
                .withUser("user").password("password").roles("USER")
    }

    override fun configure(http: HttpSecurity) {
        // リクエストにBasic認証を掛ける
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic().realmName("OAuth Server")
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    // TODO: For example
    // See. https://yusuke.blog/2018/03/02/2163
    //      https://www.harinathk.com/spring/no-passwordencoder-mapped-id-null/
    @Suppress("deprecation")
    @Bean
    fun passwordEncoder(): NoOpPasswordEncoder {
        // Spring Boot 2から平文パスワードの場合Exceptionが発生する
        // 今回はサンプルのため、平文パスワード許可する
        return NoOpPasswordEncoder.getInstance() as NoOpPasswordEncoder
    }
}
