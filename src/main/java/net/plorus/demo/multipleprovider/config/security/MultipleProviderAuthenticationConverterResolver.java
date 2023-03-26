package net.plorus.demo.multipleprovider.config.security;

import net.plorus.demo.multipleprovider.config.security.converter.AmandaAuthenticationConverter;
import net.plorus.demo.multipleprovider.config.security.converter.DrakeAuthenticationConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * path 정보를 기준으로 각 서비스 별 AuthenticationConverter를 제공
 * AuthenticationManagerResolver와 다르게 Spring Security에서 공식적으로 제공해주는 기능은 아님
 * 구현 내용을 보면 AuthenticationConverterResolver는 AuthenticationConverter의 구현체임
 * 따라서 내부적으로는 convert 기능이 동작해야 하지만 AuthenticationManagerResolver과 유사하게
 * path를 기준으로 converters 속 각 서비스별 AuthenticationConverter.convert를 간접적으로 동작하게 구성되어 있음
 * <p>
 * 꼭 path가 아니라 header나 body를 기준으로도 matching이 가능하다.
 * RequestMatcher를 기반으로 이미 구현된 구조체(ex. AntPathRequestMatcher...)를 사용하거나 RequestMatcher를
 * 직접 구현한 구현체를 converters의 key(RequestMatcher 부분)에 넣으면 됨
 * <p>
 * 예시로 작성된 AuthenticationConverter에는 의존성이 필요가 없음. 그래서 new AmandaAuthenticationConverter()
 * 형태로 선언되어 있음. 하지만 AuthenticationConverter에 의존성이 필요한 경우 AuthenticationConverterResolver
 * 에서 의존성을 받아 AuthenticationConverter에 넘기려고 하면 의존성 객체의 초기화 오류가 발생함.
 * AuthenticationConverter에 의존성이 필요하다면 MultipleProviderAuthenticationManagerResolver 부분을 참고
 */
@Component
public class MultipleProviderAuthenticationConverterResolver implements AuthenticationConverter {
    private final Map<RequestMatcher, AuthenticationConverter> converters = Map.of(
            new AntPathRequestMatcher("/amanda/**"), new AmandaAuthenticationConverter(),
            new AntPathRequestMatcher("/drake/**"), new DrakeAuthenticationConverter()
    );

    @Override
    public Authentication convert(HttpServletRequest request) {
        for (Map.Entry<RequestMatcher, AuthenticationConverter> entry : converters.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue().convert(request);
            }
        }

        return null;
    }
}
