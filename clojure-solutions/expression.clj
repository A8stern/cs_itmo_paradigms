(defn operation [op]
  (fn [& vars]
    (fn [args]
      (apply op (mapv #(% args) vars)))))

(defn constant [value]
  (fn [_] value))

(defn variable [var]
  (fn [args] (args var)))

(def add
  (operation +))

(def subtract
  (operation -))

(def multiply
  (operation *))

(def divide
  (operation #(double (/ %1 %2))))

(def negate
  (operation -))

(def exp
  (operation (fn [x] (Math/exp x))))
(def ln
  (operation (fn [x] (Math/log x))))

(def mapOp {'+ add '- subtract '* multiply '/ divide 'negate negate 'ln ln 'exp exp})

(defn parse [token]
  (cond
    (number? token) (constant token)
    (symbol? token) (variable (str token))
    :else (let [op (get mapOp (first token))]
            (apply op (map parse (rest token))))))

(defn parseFunction [expression] (parse (read-string expression)))

(defn proto-get [obj key]
  (cond
    (contains? obj key) (obj key)
    (contains? obj :prototype) (proto-get (obj :prototype) key)
    :else nil))

(defn proto-call [this key & args] (apply (proto-get this key) this args))

(defn field [key] (fn ([this] (proto-get this key))))

(defn method [key] (fn [this & args] (apply proto-call this key args)))



(def _vars (field :vars))

(def _opera (field :opera))

(def _designation (field :designation))

(def _value (field :value))

(def evaluate (method :evaluate))

(def toString (method :toString))



(defn constructor
  ([ctor, prototype, opera, designation] (fn [& args] (apply ctor {:prototype prototype, :opera opera,
                                                                  :designation designation} args)))
  ([ctor, proto] (fn [& args] (apply ctor {:prototype proto} args))))

(def PrototypeOpera
  {:evaluate (fn [this, args] (apply (_opera this) (mapv #(evaluate % args) (_vars this))))
   :toString (fn [this] (str "(" (_designation this) " " (clojure.string/join " " (mapv #(toString %) (_vars this))) ")"))})

(defn Operation [func, sym]
  (constructor (fn [this, & args] (assoc this :vars args)) PrototypeOpera func sym))

(def Add
  (Operation + "+"))

(def Subtract
  (Operation - "-"))

(def Multiply
  (Operation * "*"))

(def Divide
  (Operation (fn ([e & other] (reduce #(/ (double %1) (double %2)) e other))) "/"))

(def Negate
  (Operation - "negate"))

(def Sin
  (Operation #(Math/sin %1) "sin"))

(def Cos
  (Operation #(Math/cos %1) "cos"))



(def PrototypeConst
  {:evaluate (fn [this, _]  (_value this))
   :toString (fn [this] (str (_value this)))})

(def PrototypeVar
  {:evaluate (fn [this, args] (args (_value this)))
   :toString (fn [this] (_value this))})

(def Constant (constructor (fn [this, args] (assoc this :value args)) PrototypeConst))

(def Variable (constructor (fn [this, args] (assoc this :value args)) PrototypeVar))



(def mapOpObj {'+ Add '- Subtract '* Multiply '/ Divide 'negate Negate 'sin Sin 'cos Cos})

(defn parseObj [token]
  (cond
    (number? token) (Constant token)
    (symbol? token) (Variable (str token))
    :else (let [op (get mapOpObj (first token))]
            (apply op (map parseObj (rest token))))))

(defn parseObject [expression] (parseObj (read-string expression)))

(defn parseFunction [expression] (parse (read-string expression)))
