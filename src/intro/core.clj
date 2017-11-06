(ns intro.core)


;; 세미콜론부터 줄끝까지 주석(comment)

;; 숫자

123 -3 0              ; 정수 java.lang.Long
3.14                  ; 실수 java.lang.Double
2/3                   ; 분수 clojure.lang.Ratio
9223372036854775808N  ; 큰수 clojure.lang.BigInt

;; 괄호식
(inc 3)              ; increase 3 => 4
(dec 3)              ; decrease 3 => 2
(+ 1 2)              ; 1 + 2 => 3
(* 3 (inc 3))        ; 3 * (inc 3) => 3 * 4 => 12
                     ; mul(3, inc(3))

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
;; => false

;; 리스트 clojure.lang.PersistentList
'(1 2 3)                           ; 정수 리스트
'("이성계" "이방석" "이방과" "이방원")   ; 문자열 리스트
'(1 :2 "3" (4 5))                  ; 다른 타입 요소 OK. 중첩 OK

;; 벡터 clojure.lang.PersistentVector
[1 2 3]
[:타입무관 [:중첩 [1 2 3]]]

;; 집합 clojure.lang.PersistentHashSet
#{1 2 3}                  ; clojure.lang.PersistentHashSet
(sorted-set 1 2 3)        ; clojure.lang.PersistentTreeSet

;; 맵 clojure.lang.IPerisistentMap
{"태조" "이성계", "정종" "이방과", "태종" "이방원"}
(get {"태조" "이성계", "정종" "이방과", "태종" "이방원"} "태종")  ; => "이방원"
(get {"태조" "이성계", "정종" "이방과", "태종" "이방원"} "숙종")  ; => nil

;; if 특별식
;; (if 조건 참일때)
;; (if 조건 참일때 거짓일때?)

(if true "참" "거짓")   ; => "참"
(if false "참" "거짓")  ; => "거짓"
(if nil "참" "거짓")    ; => "거짓"
(if 0 "참" "거짓")      ; => "참"
