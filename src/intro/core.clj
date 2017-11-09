(ns intro.core)

;; 세미콜론부터 줄끝까지 주석(comment)

(println "Hello World")

;; 숫자
123 -3 0xFF 2r110 040  ; 정수 java.lang.Long
3.14                   ; 실수 java.lang.Double
2/3                    ; 분수 clojure.lang.Ratio
(* 2/3 2/5)
9223372036854775808N   ; 큰수 clojure.lang.BigInt

;; 괄호식
(inc 3)
(dec 3)
(+ 1 2)                ; 1 + 2
(* 3 (inc 3))          ; 3 * (3 + 1)

(+ 1 7 6 4)
(+)
(*)

;; 참/거짓 - java.lang.Boolean
true false

;; 문자열 - java.lang.String
"hello" "안녕하세요"
(class "hello")

;; 문자 - java.lang.Character
\A \a \가 \나 \다 \u2606
(str \A \a \가 \나 \다 \u2606)

;; 심볼 - clojure.lang.Symbol

(symbol "some-name")
(class (symbol "some-name"))

;; 키워드 - clojure.lang.Keyword
:hello :키워드 :snake-case

;; nil 값없음. 자바 null, 루비 nil, 파이썬 None
nil
(boolean nil) ; 클로저에서는 nil과 false만이 거짓으로 평가

;; 리스트 clojure.lang.PersistentList - 앞에 추가됨
'(1 2 3)
(list 1 2 3)
'("이성계" "이방석" "이방과" "이방원")
'(1 :2 "3" (4 5))

;; 벡터 clojure.lang.PersistentVector - 뒤에 추가됨
[1 2 3]
[:타입무관 [:중첩 [1 2 3]]]

;; 집합 clojure.lang.PersistentHashSet - 중복값 없음
#{1 2 3}
(sorted-set 1 2 3 3)

;; 맵 clojure.lang.IPerisistentMap
{"태조" "이성계", "정종" "이방과", "태종" "이방원"}
(get {"태조" "이성계" "정종" "이방과" "태종" "이방원"} "태종")
(get {"태조" "이성계" "정종" "이방과" "태종" "이방원"} "숙종")

;; if 특별식
;; (if 조건 참일때)
;; (if 조건 참일때 거짓일때?)

(if true "참" "거짓")
(if false "참" "거짓")
(if false "참")
(if nil "참" "거짓")
(if 0 "참" "거짓")

(if (< 2 3)
  "2는 3보다 작아요"
  "2는 3보다 작지 않아요")

;; do

(do
  (println "부수효과 1")
  (println "부수효과 2")
  :결과)

;; Var 정의 - 현재 이름공간에서, 심볼에 값을 바인딩

(def 심볼 "아무값") ; => #'intro.core/심볼
심볼                ; => "아무값"

;; 함수정의

(defn 평균
  "컬렉션내 숫자들의 평균값을 구한다"
  [숫자들]
  (/ (apply + 숫자들) (count 숫자들)))

; (doc 평균)

(평균 [70 80 90 100])

;; 함수

(fn [x y]
  (+ x y))

((fn [x y] (+ x y)) 10 5)

#(+ %1 %2)

(def 덧셈 (fn [x y] (+ x y)))
(defn 덧셈' [x y] (+ x y))

(= (덧셈 2 3) (덧셈' 2 3) 5)

(defn 덧셈3 [x]
  (+ 3 x))

(덧셈3 2)

;; 함수를 만들어 주는 함수

(defn 새가산기 [n]
  (fn [x]
    (+ n x)))

(def 덧셈6 (새가산기 6))

(덧셈6 4)

;; 함수 조립 - partial, comp

(def 덧셈9 (fn [x] (+ 9 x)))
(def 덧셈9' (partial + 9))
(= (덧셈9 1) (덧셈9' 1) 10)

(def 덧셈3+6+9 (comp 덧셈9 덧셈6 덧셈3))
(덧셈3+6+9 2)

;; 로컬 바인딩 - let

(let [x 32, y 16]
  (* x y))

(let [x 3
      y 4
      x2 (* x x)
      y2 (* y y)]
  (Math/sqrt (+ x2 y2)))

(defn 피타고라스
  [x y]
  (let [x2 (* x x)
        y2 (* y y)]
    (Math/sqrt (+ x2 y2))))

(피타고라스 3 4)

;; 시퀀스 - 리스트, 벡터, 맵등 각종 컬렉션

(def 자연수10미만 '(1 2 3 4 5 6 7 8 9))

(first 자연수10미만)
(rest 자연수10미만)
(conj 자연수10미만 0)
(take 3 자연수10미만)
(map inc 자연수10미만)
(filter even? 자연수10미만)
(filter odd? 자연수10미만)

;; 퓨처

(def 퓨처 (future
            (println "잠자기")
            (Thread/sleep 10000)
            (println "일어나!")
            :완료))

(deref 퓨처)
@퓨처

;; 자바 호출

(import 'java.util.UUID)

(UUID/randomUUID)

(let [인사 (new StringBuilder "안녕하세요\n")]
  (.append 인사 "제 6회\n")
  (.append 인사 "리스프 세미나\n")
  (.append 인사 "클로저 소개\n")
  (println (.toString 인사))
  인사)

;; 동시성 지원 (0) - 잘못된 예
;; FAIL - mutable data 동기화 없이 여러 스레드 접근

(let [값들 (int-array 1 0)
      읽기 #(aget 값들 0)
      쓰기 #(aset 값들 0 %)
      증가 #(쓰기 (inc (읽기)))]
  (dorun
    (map deref [(future (dotimes [_ 100] (증가)))
                (future (dotimes [_ 100] (증가)))
                (future (dotimes [_ 100] (증가)))]))
  (str "최종 결과 => " (읽기)))

;; 동시성 지원 (1) - 여러 스레드가 atom에 값변경 요청

(let [아톰  (atom 0)
      증가  #(swap! 아톰 inc)]
  (dorun
    (map deref [(future (dotimes [_ 10000] (증가)))
                (future (dotimes [_ 10000] (증가)))
                (future (dotimes [_ 10000] (증가)))]))
  (str "아톰 결과 => " @아톰))

;; 동시성 지원 (2) - 비동기 처리 agent

(let [에이전트 (agent 0)
      slow-adder (fn [n]
                   (Thread/sleep 1000)
                   (let [결과 (+ n 1000)]
                     (println n "+ 1000 = " 결과)
                     결과))]
  (send 에이전트 slow-adder)
  (send 에이전트 slow-adder)
  (send 에이전트 slow-adder)
  (time (await 에이전트))
  (str "에이전트 결과 => " @에이전트))

;; 동시성 지원 (3) - 트랜잭션 refer

(let [계좌a (ref 10000)
      계좌b (ref 10000)
      계좌c (ref 10000)
      잠깐대기 #(Thread/sleep (rand-int 100))
      송금 (fn [출금좌 입금좌]
            (dosync
              (let [송금가능액 (rand-int @출금좌)]
                (alter 출금좌 - 송금가능액)
                (alter 입금좌 + 송금가능액))))
      막송금 (fn [출 입]
              (future
                (dotimes [_ 1000]
                  (잠깐대기)
                  (송금 출 입))))]
  (막송금 계좌a 계좌b)
  (막송금 계좌b 계좌c)
  (막송금 계좌c 계좌a)
  (future (dotimes [i 10]
            (Thread/sleep 500)
            (dosync (println
                      (str "(" i ") "
                           "A=" @계좌a ", B=" @계좌b ", C=" @계좌c
                           ", 총액=" (+ @계좌a @계좌b @계좌c)))))))
