(ns brave-clojure.core
  (:gen-class)
  (use [clojure.string :as s]))


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

;; (:a #{:a :b}) ;; :a



;; K.I.S.S
;; That's why we use Clojure
;; It's better to have 100 functions operate on 1 entity
;; than have 10 functions operate on 10 entities


;; Functions

;; Prefix notation allows for fleixibility

;; Higher order functions allow for even more flexibility

;; ((or fn1 fn2) params) ;; (fn1 paarams) or (fn2 params)

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

;;(if boolean
;;  then-stuff
;;  optional-else-stuff)

(def happy true)

(defn say [a] (println a))

(if happy
  (say "Im happy")
  (say "Fuck off"))

;; There was an entire subsection for that!?

;; `defn`

(defn function
  "doc string for the function"
  [p g h]
  (str p g h "for the function"))

;; (doc function) ;; returns the doc string

;;(defn multiarity
;;  ([p] (multiarity p 0))
;;  ([p q] (str "mind your" p " and " q)))

;; better than overloading a function


;;(defn using-&
;;  [a b c & xs]
;;  (do-stuff-with a b c xs))

;; I'm not going to take notes on destructuring

;; The next section just talks about using stuff in functions

;; It's worth mentioning that clojure has no privileged functions
;; It's not that important really if you write simple code
;; but the simplicity is kind of cool

;; Anonymous Functions

;; There are a few ways to define a function

;; `defn` <- for defining actual functions
;; (fn [] ...) <- for above AND anon functions
;; #(... %1 %2 %3) <- for anon functions

;;(def all-the-data)

;;(defn mappy-mcmapface []
;;  ...)

;;(map mappy-mcmapface all-the-data)

;;(map (fn [[a b c]] ...) all-the-data
;; here the vector will be taken one-by-one from `all-the-data`
;; use this for slightly beyond trivial stuff

;;(map #(... %1) all-the-data)
;; here the param %1 is taken from all-the-data directly
;; use this for the stupidly simple

;; Returning functions

(defn inc-maker
  "Create a custom incrementer"
  [x]
  #(+ % x))

(def inc8 (inc-maker 8))

(inc8 0) ;; 8

;; Hobbit thing

(def asym-bits
  [{:name "head" :size 3}
   {:name "left-ear" :size 1}
   {:name "left-eye" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 3}
   {:name "left-lower-leg" :size 4}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}])

(defn matching-part
  "Creates a matching hobbit part"
  [part]
  {:name (s/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps with :name and :size"
  [asym-parts]
  (loop [remaining-parts asym-parts
         final-parts []]
    (if (empty? remaining-parts)
      final-parts
      (let [[part & remaining] remaining-parts]
        (recur remaining
               (into final-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-bits)

;; I forgot that this was an acutal namespace 
;; and was writing a ton of pseudo-code.
;; Took me an hour to fix the damn thing

;; Their symmetrize function sucks

(defn sym
  [ap]
  (reduce (fn [fp p]
            (into fp (set [p (matching-part p)])))
          []
          ap))

(sym asym-bits)

;; Time to beat up hobbits

(defn hit
  [parts]
  (let [sym-parts (sym parts)
        part-size-sum (reduce + (map :size sym-parts))
        target (rand part-size-sum)]
    (loop [[part & remaining] sym-parts
           acc-size (:size part)]
      (if (> acc-size target)
        part
        (recur remaining (+ acc-size (:size (first remaining))))))))

;; weighted targeting... I like it
;; The loop creates target ranges so that larger parts
;; have a higher chance of being hit

(hit asym-bits)

;; Since this uses an RNG it's not a mathematical function

;; mapset

(defn mapset
  [f c]
  (set (map f c)))

(mapset inc [1 1 2 3 3])

;; sets are unordered 


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Chapter 4 - Core Functions in Depth ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Seq
;; If `first`, `last`, and `rest` work on the thing
;; its a sequence or `seq`

(defn titleize [t]
  (str t " for the Brave and True"))

(def titles
  (map titleize ["Hampers" "Scooters" "VooDoo Medicine Practice"]))

(map prn titles)

;; On to Zombies?

(def h-con [8.1 7.2 6.3 5.4 4.5 2.0 0.4])
(def c-con [0.1 1.6 3.0 4.2 5.9 6.2 7.0])
(def zip-data
  [h c]
  {:human h
   :critter c})

(def zipped-data (map zip-data h-con c-con))

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats [n]
  (map #(% n) [sum count avg]))

;; when using map on a hashmap
;; you can leverage the fact that seq
;; on a hashmap returns a vector of 
;; key, value pairs
;; same goes for the other `seq` functions

;; reduce is really useful

(reduce (fn [m [k v]]
          (if (> v 4)
            (assoc m k v)
            m))
        {}
        {:human 4.1
         :critter 3.9}) ;; {:human 4.1}}

;; `reduce` is really bendy

;; take, drop, *-while

(take 3 [1 2 3 4 5 6]) ;; [1 2 3]
(drop 3 [1 2 3 4 5 6]) ;; [4 5 6]

(def food
  [{:month 1 :day 1 :h 5 :c 0}
   {:month 1 :day 2 :h 4 :c 1}
   {:month 1 :day 3 :h 3 :c 2}
   {:month 2 :day 1 :h 2 :c 3}
   {:month 2 :day 2 :h 1 :c 4}
   {:month 2 :day 3 :h 0 :c 5}])

(take-while #(< (:month %) 2) food)
(reduce +
        (map :c 
             (take-while
               #(< (:month %) 2)
               food)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
