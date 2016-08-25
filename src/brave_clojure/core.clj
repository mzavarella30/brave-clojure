(ns brave-clojure.core
  (:gen-class))

;; Chapter 1 and 2 don't involved code

;; Chapter 3 - Do Things: A Clojure Crash Course

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








(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
