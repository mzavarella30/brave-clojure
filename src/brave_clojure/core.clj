(ns brave-clojure.core
  (require [clojure.string :as s] 
       [clojure.set :as st])
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
(defn zip-data
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
   {:month 2 :day 3 :h 0 :c 5}
   {:month 3 :day 1 :h 5 :c 0}
   {:month 3 :day 2 :h 4 :c 1}
   {:month 3 :day 3 :h 3 :c 2}
   {:month 4 :day 1 :h 2 :c 3}
   {:month 4 :day 2 :h 1 :c 4}
   {:month 4 :day 3 :h 0 :c 5}])

;; get the first month of eating
(take-while #(< (:month %) 2) food)

;; `drop-while` works in the same way
;; expect it drops instead of takes

;; see how much critter was eaten 
;; in the first month
(reduce +
        (map :c 
             (take-while
               #(< (:month %) 2)
               food)))

;; `take-while` is used to get data from the head of
;; some kind of collection
;; `drop-while` is used for the tail
;; You can use the two in conjunction to get the data
;; in the middle of a collection.

;; Use of `take-while` and `drop-while` to get food from
;; months 2 and 3
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food))

;; You can use `filter` too but that will process 
;; all of your data. Since ours is filtered we can
;; use take and drop to process this more efficiently


;; You can use `some` to determine whether or
;; not a collection contains a value that
;; will test true for a certain filter function

(some even? [1 2 3 4 5]) ;; true

(some #(> (:c %) 5) food) ;; nil
(some #(> (:c %) 2) food) ;; true

;; Sort

(sort [3 2 1]) ;; [1 2 3]
(sort-by count ["aaa" "nn" "r"]) ;; ["r "nn" "aaa"]

;; it sorts shit

;; concat

(concat [1 2] [3 4]) ;; [1 2 3 4]

;; k

;; Lazy seqs

(def tempdb
  {0 {:puns true :pulse false :name "frank"}
   1 {:puns false :pulse true :name "rick"}
   2 {:puns false :pulse true :name "randy"}
   3 {:puns true :pulse true :name "bill"}})

(defn vamp-details
  [ssn]
  ;;(Thread/sleep 1000)
  (get tempdb ssn))

(defn vamp?
  [r]
  (and (:puns r) (not (:pulse r))
       r))

(defn id-vamps
  [ssns]
  (first (filter vamp?
                 (map vamp-details ssns))))

(time (vamp-details 0)) ;; returns in 1 second

(def mapped-details (map vamp-details (range 0 1000000)))


;; this takes about 32 seconds for the first go
;; after that it's almost instant
(time (first mapped-details))

;; if map wasn't lazy, it'd take about 12 days
;; on the first try

;; laziness allows for infinite seqs

;; repeat is lazy
(concat (take 8 (repeat "na")) ["Batman!"])

(take 3 (repeatedly (fn [] (rand-int 10))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))

;;;;;;;;;;;;;;;;;
;; Collections ;;
;;;;;;;;;;;;;;;;;

;; Sequences are good for working on individual pieces
;; Collections are good for working on the entire thing

;; `count`, `empty?`, and `every?` are key collections
;; functions. They all have one thing in common and that
;; is that their scope is the collection as a whole
(empty? []) ;; true
(empty? [1]) ;; false

;; `into`

;; Many `seq` functions return a seq rather than
;; the original data structure. You can use `into`
;; to make that structure a map again

(map identity {:likes-sunlight false})
(into {} (map identity {:likes-sunlight false}))

;; You can use `into` for any data structure

;; Putting something `into` a set is a good way
;; to remove duplicates

(into #{} [1 1 1 2 2 2 3 3 3]) ;; #{1 3 2}
;; sets are unordered

;; `conj` is the literal friend that just wont
;; be normal

(conj [0] [1]) ;; [0 [1]]
(conj [0] [1 2 3]) ;; [0 [1 2 3]]

(conj [0] 1 2 3) ;; [0 1 2 3]

;;;;;;;;;;;;;;;;;;;;;;;;
;; Function Functions ;;
;;;;;;;;;;;;;;;;;;;;;;;;

;; Apply
;; explodes a seqable structure so it [the structure]
;; can be passed to a function normally

(max 1 2 3)         ;; 3
(max [1 2 3])       ;; [1 2 3] O_O
(apply max [1 2 3]) ;; 3

;; conj can be naturally defined in terms of into
;; into can be defined using conj along with apply

;; Partial

(def add10 (partial + 10))
(add10 10) ;; 20

(def add-missing-elements
  (partial conj ["water" "fire" "earth" "air"]))
(add-missing-elements "lightning" "unobtainium")
  ;; I'm not typing that out, you know what happens

;; Complement

(defn negative 
  [x] 
  (< 0 x))
(def positive
  (complement negative))


;; vampire thing

(def filename "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [s]
  (Integer. s))

(def conversions
  {:name identity
   :glitter-index str->int})

(defn csv->vamp
  [k v]
  ((get conversions k) v))

(csv->vamp :glitter-index "3")

(defn parse-csv
  "Parse csv string into rows of cols"
  [csv]
  (map #(s/split % #",")
       (s/split csv #"\n")))

(defn mapify
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [k v]]
                   (assoc row-map k (csv->vamp k v)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(def vamp-data
  (mapify (parse-csv (slurp filename))))

(defn glitter-filter
  [min records]
  (filter #(>= (:glitter-index %) min) records))

(glitter-filter 3 vamp-data)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functional Programming ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Woo!

;; Pure Functions

;; They are easy to reason about
;; 1 to 1
;; Referential transparency

;; pure functions depend on immutability
(defn wisdom
  [phrase]
  (str phrase ", Michael-san"))

;; the string ", ... " is immutable and so
;; this function is stable

;; anything involving randomness is not
;; rng is not mathematically pure because
;; the same input will not always return
;; the same output

;; IO is not referentially transparent
;; because the input can change

;; pure functions have no side affects

;; Beware. I just got a glass of whiskey

;; Oh... it's good.

;; The perfect code is written in such a way that
;; it is immediately understandably to even
;; the dumbest developer

;; side effects make code harder to understand
;; thus, they [side effects] bring code further
;; from perfection.

;; Shit like changing stuff attached to a server
;; context can wreak havoc on an application.

;; limit the use of side effects

;;;;;;;;;;;;;;;;;;
;; Immutability ;;
;;;;;;;;;;;;;;;;;;

;; clojure core data structures are all immutable

;; How to do stuff without changing stuff?

;; Recursion and map/reduce instead of loops

;; Clojure does not allow reassignment
(def baby-name "Alice")

(let [baby-name "Bloodthunder"]
  baby-name)

(prn baby-name) ;; its still alice

;; Recursion

(defn sum
  ([vals] (sum vals 0))
  ([vals acc-total]
   (if (empty? vals)
     acc-total
     (recur (rest vals) (+ acc-total (first vals))))))

;; Use recur to prevent a stackoverflow 
;; when the collections get too big

;; This uses arity overloading so that we can call
;; `sum` on just one integer without having to 
;; add that `0` param in there

(defn real-sum [xs]
  (reduce + xs))


;; Function Composition

;; better than attribute mutation

;; Typically, OO programs mutate the state until
;; they achieve the desired result.

;; The better way to do it is to pipe the state
;; through a transactional function composition 

;; OOP is stupid

;; Things to do with Pure Functions

;; `comp`

((comp inc *) 2 3) ;; 7

(def hero
  {:name "Naruto"
   :attr {:str 8
          :dex 14
          :int -6}})

(def c-int (comp :int :attr))
(def c-dex (comp :dex :attr))
(def c-str (comp :str :attr))

(c-int hero)
(c-str hero)
(c-dex hero)

;; comp creates functions
;; (fn [c] (:int (:attr c)))
;; etc...

(def spell-slots (comp int inc #(/ % 2) c-int))


;; Memoize

;; stores a function call w/ params for instant
;; return for preceding calls

;; Peg thing

(declare successful-move
         prompt-move
         game-over
         query-rows)


(defn tri*
  "Generates a lazy triangle"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(defn triangular?
  "Determines whether or not a number is triangular"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(defn row-tri
  "The triangular number at the end of row n"
  [n]
  (last (take n tri)))

(defn row-num
  "Returns the row num and position"
  [pos]
  (int (count (take-while #(> pos %) tri))))

(defn connect
  [board max-pos pos neighbor dest]
  (if (<= dest max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos dest] [dest pos]])))

(defn connect-right
  [board max-pos pos]
  (let [neighbor (inc pos)
        dest (inc neighbor)]
    (if-not (or (triangular? neighbor) (triangular? pos))
      (connect board max-pos pos neighbor dest)
      board)))

(defn connect-down-left
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ row pos)
        dest (+ 1 row neighbor)]
    (connect board max-pos pos neighbor dest)))

(defn connect-down-right
  [board max-pos pos]
  (let [row (row-num pos)
        neighbor (+ 1 row pos)
        dest (+ 2 row neighbor)]
    (connect board max-pos pos neighbor dest)))

(defn add-pos
  [board max-pos pos]
  (let [pegged-board (assoc-in board [pos :pegged] true)]
    (reduce (fn [new-board connection-creation-fn]
              (connection-creation-fn new-board max-pos pos))
            pegged-board
            [connect-right connect-down-left connect-down-right])))











(defn -main 
  "I don't do a whole lot ... yet."
  [& args] 
  (println "Hello, World!"))
