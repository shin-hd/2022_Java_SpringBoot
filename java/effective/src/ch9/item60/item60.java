package ch9.item60;

/*
 * item60. 정확한 답이 필요하다면 float와 double은 피하라
 *
 *  float와 double 타입은 '근사치'로 계산하도록 설계되어서
 *  정확한 결과가 필요할 때는 사용하면 안됨
 * 특히 금융 관련 계산에서는 절대 사용하지 말고 BigDecimal, int, long 등을 사용해야 함
 * 
 *  BigDecimal 단점 : 기본 타입보다 쓰기 불편하고 느림
 * int, long을 대신 쓰면 : 값 크기가 제한되고, 소수점을 직접 관리해야함
 */
public class item60 {
}
