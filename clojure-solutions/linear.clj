(defn func [opera] (fn [v2, v3] (mapv opera v2 v3)))
(def v+ (func +))
(def v- (func -))
(def v* (func *))
(def vd (func /))

(defn scalar [v1, v2] (reduce + (v* v1 v2)))
(defn vect [v1, v2] (vector
                      (- (* (nth v1 1) (nth v2 2)) (* (nth v1 2) (nth v2 1)))
                      (- (* (nth v1 2) (nth v2 0)) (* (nth v1 0) (nth v2 2)))
                      (- (* (nth v1 0) (nth v2 1)) (* (nth v1 1) (nth v2 0))))
      )
(defn v*s [v1, con] (mapv #(* % con) v1))

(defn funcMatrix [opera] (fn [m1 m2] (mapv (fn [row1 row2] (mapv opera row1 row2)) m1 m2)))

(def m+ (funcMatrix +))
(def m- (funcMatrix -))
(def m* (funcMatrix *))
(def md (funcMatrix /))
(defn m*s [m1, con] (mapv (fn [row1] (v*s row1 con)) m1))
(defn m*v [m1, v1] (mapv (fn [row1] (scalar row1 v1)) m1))
(defn transpose [m1] (apply mapv vector m1))
(defn m*m [a b]
      (let [b-t (transpose b)]
           (mapv (fn [row1]
                     (mapv #(scalar %1 %2) (repeat row1) b-t)) a)))
(defn shapeless [con] (fn [v1, v2] (if (and (vector? v1) (vector? v2))
                                     (mapv (shapeless con) v1 v2)
                                     (con v1 v2))))
(def s+ (shapeless +))
(def s- (shapeless -))
(def s* (shapeless *))
(def sd (shapeless /))

