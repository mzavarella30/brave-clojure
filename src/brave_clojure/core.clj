(ns brave-clojure.core
  (:gen-class))

;; Chapter 1 and 2 don't involved code
;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Chapter 3 - Do Things: A Clojure Crash Course ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;
;; Syntax ;;
;;;;;;;;;;;;

(+ 1 2 3) ;; 6
(- 1 2 3) ;; -4
 
(str "I am a panda" "," " and I want a taco!")

(if true 1 0) ;; 1
(if false 1 0) ;; 0

(if true
  (do (println "success")
      1)
  (do (println "fail")
      -1))

;; Using vim w/ fireplace 
;; `cp` will evaluate a line
;; `cpp` will evaluate the form which it is in

;; not bothering with `when`, it's anti-fp

;; nil and truthy/falsey-ness
(nil? 1) ;; false
(nil? nil) ;; true

(if "string"
  "anything not falsey is truthy"
  -1)

(if nil
  1
  "anything not truthy is falsey")

(= 1 1) ;; true
(= nil nil) ;; true

(= 1 2) ;; false

(or false nil :large :venti) ;; :large
(or false nil) ;; nil
(or false "asd" :a :b) ;; "asd"

;; `or` returns either the first truthy value
;; or the last value

;; and returns the first falsey value 
;; or the last truthy value

(and nil " ") ;; nil
(and false nil " ") ;; false
(and true false) ;; false

(and true :apples) ;; :apples
(and :apples "flubber" true) ;; true

;; This differs from a lot of languages
;; i.e. Java. `||` and `&&` only return
;; true or false... or my life is a lie.

;; def(n)

(def failed-inventions
  ["Paper blanket" "Neon camo" "Tisse plate"])

(def serverity :mild)

(defn error-msg
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOMED!")))

(error-msg :severe) ;; "... DOOOOMED!"


;;;;;;;;;;;;;;;;;;;;;
;; Data structures ;;
;;;;;;;;;;;;;;;;;;;;;

;; Numbers

;; 1
;; 1.0
;; 1/1

;; Strings
;; "This is a string"
;; "\"This is also a string\""
;; 'This is NOT a string'

;; The `'` is used to delimit clojure form literals

(def co-pilot "Chewbacca")
(def uglglg "UGGLLGLGLGLGLGLL!")

(println co-pilot " says " uglglg)

;; Maps aka The goat

;; {}
(def michael
  {:name "Michael"
   :surname "Zavarella"
   :height 81
   :weight 285
   :job "linebacker"
   :universe "not this fuckin one..."})

(get michael :name) ;; Michael
(get michael :job) ;; linebacker
(get michael :universe) ;; not this fuckin one...

(michael :name)
(michael :job)
(michael :universe)

(map #(michael %) [:name :job :universe]) ;; returns as list

;; Vectors

;; []
(def triangle [[0 0] [1 19] [19 1]])

;; Going through stuff you already know is brutal

(get triangle 0) ;; [0 0]
(get triangle 1) ;; [1 19]

(get (get triangle 2) 0) ;; 19

(def square (conj triangle [0 18]))

;; Vectors are linear collections

;; Lists are too. They act just like vectors

;; Lists conj to the front, vectors conj to the back

;; Sets

;; #{}

;; Act just like mathematical sets

;; useful functions are conj, contains?, and get

;; you can also do stuff like so

(:a #{:a :b}) ;; :a



;; K.I.S.S
;; That's why we use Clojure
;; It's better to have 100 functions operate on 1 entity
;; than have 10 functions operate on 10 entities


;; Functions

;; Prefix notation allows for fleixibility

;; Higher order functions allow for even more flexibility

((or fn1 fn2) params) ;; (fn1 paarams) or (fn2 params)

(or + -) ;; returns `+`

((and (= 1 1) +) 1 2 3) ;; 6

;; You can use HOFs to do some really funky shit
;; Also leads to denser/more concise code

;; Map, Reduce, Apply, Fold, Filter, etc...

(inc 1) ;; 2
(map inc [1 2 3]) ;; [2 3 4]

;; Map is used to transform every element of a collection
;; to something else via a function

;; (map function collection)

;; In the above example, we map the vector [1 2 3]
;; to [2 3 4] via the `inc` function

;; Function, macro, and special forms 

(if boolean
  then-stuff
  optional-else-stuff)

(if happy
  (say "Im happy")
  (say "Fuck off"))

;; There was an entire subsection for that!?

;; `defn`

(defn function
  "doc string for the function"
  [params go here]
  (str params go here "for the function"))

(doc function) ;; returns the doc string

(defn multiarity
  ([p] (multiarity p 0))
  ([p q] (str "mind your" p " and " q)))

;; better than overloading a function


(defn using-&
  [a b c & xs]
  (do-stuff-with a b c xs))

;; I'm not going to take notes on destructuring

;; The next section just talks about using stuff in functions





















(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
