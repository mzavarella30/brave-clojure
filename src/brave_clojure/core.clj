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








 (defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
