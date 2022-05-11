package com.xxxx.server.config.security;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author ZedQ
 * @Date 2022/5/10 15:01
 * @ClassName: SecurityConfig
 * @Description: TODO
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ico",
                //swagger2+三方插件必须放行的必须放行
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs/**",
                //我自己加的测试
                "/captcha"
        );
//        云e办
//                使用第三方swagger图形框架
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
////                //允许登录
////                .antMatchers("/login","/logout","/doc.html")
////                .permitAll()
//                //除了上面的都不让访问
//                .anyRequest()
//                .authenticated()
//                .and()
//                .headers()
//                .cacheControl();
        //使用jwt不需要csrf
        http.csrf()
                .disable()//意思 使残废，关闭后面and之前的配置
                //基于token存储登录用户信息，不需要session，关闭session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //所有请求都要求认证
                .anyRequest()
                .authenticated()
                .and()
                //禁用缓存
                .headers()
                .cacheControl();

        //添加 jwt 登录授权拦截器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义 未授权 和 未登录 结果返回
        http.exceptionHandling()
                //自定义 未授权
                .accessDeniedHandler(restfulAccessDeniedHandler)
                //自定义 未登录结果返回
                .authenticationEntryPoint(restAuthorizationEntryPoint);

    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username->{
            Admin admin = adminService.getAdminByUserName(username);
            if(null!=admin){
                return admin;
            }
            return null;
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();
    }

}
