package com.ewok.springbootproject.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 세션값을 가져오는 것은 여러 컨트롤러와 메소드에서 사용할 수 있으므로 중복을 제거하기 위해 어노테이션 생성
// 메소드 인자로 세션값을 바로 받을 수 있도록 하기 위함
@Target(ElementType.PARAMETER)  // 메소드의 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
