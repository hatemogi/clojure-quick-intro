(ns intro)

;; 세미콜론부터 줄끝까지 주석(comment)

;; 숫자

123 -3 0              ; 정수 java.lang.Long
3.14                  ; 실수 java.lang.Double
2/3                   ; 분수 clojure.lang.Ratio
9223372036854775808N  ; 큰수 clojure.lang.BigInt

;; 괄호식
(* 2/3 3)
(* (/ 3 2) 2)

;; 참/거짓
true false            ; java.lang.Boolean

;; 문자열
"hello" "안녕하세요"     ; 문자열 java.lang.String
\A \a \가 \나 \다       ; 문자 java.lang.Character

;; 키워드 clojure.lang.Keyword
:hello :키워드 :snake-case

;; nil 값없음. 자바 null, 루비 nil, 파이썬 None
nil
(boolean nil)         ; 클로저에서는 nil과 false만이 거짓으로 평가된다


;; if 특별식
;; (if 조건 참일때)
;; (if 조건 참일때 거짓일때?)

(if true "참" "거짓")   ; => "참"
(if false "참" "거짓")  ; => "거짓"
(if nil "참" "거짓")    ; => "거짓"
(if 0 "참" "거짓")      ; => "참"
