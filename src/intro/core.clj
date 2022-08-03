(ns intro.core)

;; 세미콜론부터 줄끝까지 주석(comment)
;; 공백과 쉼표도 주석처럼 무시합니다.

(println "차례로 따라해봅시다.")



;; 숫자
123 -3 0xFF 2r110 040  ; 정수 java.lang.Long
3.14                   ; 실수 java.lang.Double
2/3                    ; 분수 clojure.lang.Ratio
(* 2/3 2/5)
9223372036854775808N   ; 큰수 clojure.lang.BigInt



;; 문자열 - java.lang.String
"hello" "안녕하세요"
(class "hello")



;; 문자 - java.lang.Character
\A \a \가 \나 \다 \u2606
(str \A \a \가 \나 \다 \u2606)



;; 참/거짓 - java.lang.Boolean
true false



;; nil 값없음. 자바 null, 루비 nil, 파이썬 None
nil
(boolean nil) ; 클로저에서는 nil과 false만이 거짓으로 평가
(boolean "뭐든지")
(boolean? "뭐든지")
(false? false)



;; 괄호식
(inc 3)
(dec 3)
(+ 1 2)                ; 1 + 2
(* 3 (inc 3))          ; 3 * (3 + 1)

(+ 1 2 3 4)
(+)
(*)

(pos? 77)
(even? 2)
(zero? 0)

(< 2 3)
(> 1 2)
(< 3 6 9 12)
(< 3 9 6 12)



;; 심볼 - clojure.lang.Symbol
(symbol "some-name")
(class (symbol "some-name"))



;; 키워드 - clojure.lang.Keyword
:hello :키워드 :snake-case



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



;; do - 부수효과
(do
  (println "부수효과 1")
  (println "부수효과 2")
  :결과)



;; 로컬 바인딩 - let
(let [x 32, y 16]
  (* x y))

(let [x 3
      y 4
      x2 (* x x)
      y2 (* y y)]
  (Math/sqrt (+ x2 y2)))



;; Var 정의 - 현재 이름공간에서, 심볼에 값을 바인딩
(def 심볼 "아무값")     ; => #'intro.core/심볼
심볼                    ; => "아무값"



;; 함수 - fn
(fn [x y]
  (+ x y))

((fn [x y] (+ x y)) 10 5)

#(+ %1 %2)

(def 덧셈 (fn [x y]
          (+ x y)))

(defn 덧셈' [x y] (+ x y))

(= (덧셈 2 3) (덧셈' 2 3) 5)

(defn 덧셈3 [x]
  (+ 3 x))

(덧셈3 2)

(defn 피타고라스
  [x y]
  (let [x2 (* x x)
        y2 (* y y)]
    (Math/sqrt (+ x2 y2))))

(피타고라스 3 4)



;; HOF - 함수를 만들어 주는 함수
(defn 새가산기 [n]
  (fn [x]
    (+ n x)))

(def 덧셈6 (새가산기 6))

(덧셈6 4)



;; 함수 조립 - partial, comp, complement
(def 덧셈9 (fn [x] (+ 9 x)))
(def 덧셈9' (partial + 9))
(= (덧셈9 1) (덧셈9' 1) 10)

(def 덧셈3+6+9 (comp 덧셈9 덧셈6 덧셈3))
(덧셈3+6+9 2)

(pos? 10)
(pos? -1)
(def 양이아닌수? (complement pos?))
(양이아닌수? 0)



;; 시퀀스 - 리스트, 벡터, 맵등 각종 컬렉션
(def 자연수10미만 '(1 2 3 4 5 6 7 8 9))

(first 자연수10미만)
(rest 자연수10미만)
(conj 자연수10미만 0)
(take 3 자연수10미만)
(map inc 자연수10미만)
(filter even? 자연수10미만)
(filter odd? 자연수10미만)



;; 퓨처 - future, realized?, deref, @
(def 퓨처 (future
          (println "퓨처 시작")
          (Thread/sleep 10000)
          (println "퓨처 끝!")
          ::완료))

(println "다른일 처리. realized? =>" (realized? 퓨처))
(println "처리결과 =>" (deref 퓨처))
@퓨처



;; 자바 호출
(import 'java.util.UUID)

(UUID/randomUUID)

(let [인사 (new StringBuilder "안녕하세요\n")]
  (.append 인사 "제 2회\n")
  (.append 인사 "제주 개발자 모임\n")
  (.append 인사 "클로저 소개\n")
  (println (.toString 인사))
  인사)



;; 매크로
(defmacro 만약 [조건식 참일때 거짓일때]
  (list 'if 조건식 참일때 거짓일때))

(만약 (< 2 3)
    "2는 3미만"
    "2는 3이상")

(macroexpand-1 '(만약 (< 2 3)
                    "2는 3미만"
                    "2는 3이상"))



;; 동시성 처리 (0) - 잘못된 예
;; FAIL - mutable data (java int)에 동기화 없이 여러 스레드 접근
(let [값들 (int-array 1 0)
      읽기 #(aget 값들 0)
      쓰기 #(aset 값들 0 %)
      증가 #(쓰기 (inc (읽기)))
      백번 #(dotimes [_ 100] (Thread/sleep 10) (증가))]
  (time (doall (pcalls 백번 백번 백번)))
  (str "최종 결과 => " (읽기)))



;; 동시성 처리 Java 유틸 활용 - concurrent.atomic.*
(let [값   (new java.util.concurrent.atomic.AtomicInteger)
      읽기 #(.get 값)
      증가 #(.incrementAndGet 값)
      백번 #(dotimes [_ 100] (Thread/sleep 10) (증가))]
  (time (dorun (apply pcalls (repeat 5 백번))))
  (str "최종 결과 => " (읽기)))



;; 동시성 지원 (1) - 여러 스레드가 atom에 값변경 요청
(let [아톰 (atom 0)
      증가 #(swap! 아톰 inc)
      만번 #(dotimes [_ 10000] (증가))]
  (dorun (pcalls 만번 만번 만번 만번 만번))
  (str "아톰 결과 => " @아톰))




;; 동시성 지원 (2) - 비동기 처리 agent
(let [에이전트    (agent 0)
      slow-adder (fn [n]
                   (Thread/sleep 1000)
                   (let [결과 (+ n 1000)]
                     (println "에이전트 + 1000 =" 결과)
                     결과))]
  (send 에이전트 slow-adder)
  (send 에이전트 slow-adder)
  (send 에이전트 slow-adder)
  (println "레플스레드는 이미 진행중")
  (time (await 에이전트))
  (str "에이전트 결과 => " @에이전트))



;; 동시성 지원 (3) - 트랜잭션 ref
(let [계좌A  (ref 10000), 계좌B (ref 10000), 계좌C (ref 10000)
      거래수 (atom 0)
      잠깐   #(Thread/sleep 1)
      송금 (fn [출금좌 입금좌]
           (dosync (let [금액 (min @출금좌 (rand-int 1000))]
                     (alter 출금좌 - 금액)
                     (alter 입금좌 + 금액)))
           (swap! 거래수 inc))
      막송금 (fn [출 입] (dotimes [_ 1000] (잠깐) (송금 출 입)))]
  (future (막송금 계좌A 계좌B))
  (future (막송금 계좌B 계좌C))
  (future (막송금 계좌C 계좌A))
  (while (< @거래수 3000)
    (Thread/sleep 200) pcalls
    (dosync (printf "A=%5d, B=%5d, C=%5d, 총액=%d, 거래수=%d\n"
                    @계좌A @계좌B @계좌C
                    (+ @계좌A @계좌B @계좌C)
                    @거래수))
    (flush)))
