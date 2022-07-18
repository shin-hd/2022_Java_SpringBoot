package com.springboot.valid_exception.config.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 어노테이션 선언 위치 정의 (패키지, 타입, 생성자, 메소드, 필드 등등...)
@Retention(RetentionPolicy.RUNTIME) // 적용, 유지 범위 (런타임, 클래스, 컴파일전소스)
@Constraint(validatedBy = TelephoneValidator.class) // validatior와 매핑
public @interface Telephone {
    // 유효성 검사 실패 메시지
    String message() default "전화번호 형식이 일치하지 않습니다.";
    // 유효성 검사 사용하는 그룹으로 설정
    Class[] groups() default {};
    // 추가 정보를 위해 전달하는 값
    Class[] payload() default {};
}
