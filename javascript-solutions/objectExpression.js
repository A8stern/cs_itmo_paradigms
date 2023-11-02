function Const(value) {
    this.value = value
}

Const.prototype = {
    evaluate: function () {
        return this.value
    },
    toString: function () {
        return this.value.toString()
    },
    prefix: function () {
        return this.value.toString()
    }
}

function Variable(name) {
    this.name = name
}

Variable.prototype = {
    evaluate: function (...args) {
        if (this.name === "x") {
            return args[0]
        }
        if (this.name === "y") {
            return args[1]
        }
        return args[2]
    },
    toString: function () {
        return this.name
    },
    prefix: function () {
        return this.name
    }
}


Operation.prototype = {
    evaluate: function (...args) {
        return this.opera(...this.argument.map(vars => vars.evaluate(...args)))
    },
    toString: function () {
        return this.argument.join(" ") + " " + this.operaName
    },
    prefix: function () {
        return "(" + this.operaName + " " + this.argument.map(arg => arg.prefix()).join(" ") + ")"
    }
}

function makeOperation(opera, operaName) {
    function Creation(...args) {
        return new Operation(operaName, opera, args)
    }

    Creation.prototype = Object.create(Operation.prototype)
    return Creation
}

function Operation(operaName, opera, argument) {
    this.operaName = operaName
    this.opera = opera
    this.argument = argument
}

let Add = makeOperation((f, g) => f + g, "+")
let Subtract = makeOperation((f, g) => f - g, "-")
let Multiply = makeOperation((f, g) => f * g, "*")
let Divide = makeOperation((f, g) => f / g, "/")
let Negate = makeOperation(f => -f, "negate")
let Exp = makeOperation(Math.exp, "exp")
let Ln = makeOperation(Math.log, "ln")
let Sum = makeOperation((...elem) => elem.reduce((sum, current) => sum + current, 0), "sum")
let Avg = makeOperation((...elem) => elem.reduce((sum, current) => sum + current, 0) / elem.length, "avg")

const arr = {
    "+": [2, Add],
    "-": [2, Subtract],
    "/": [2, Divide],
    "*": [2, Multiply],
    "negate": [1, Negate],
    "exp": [1, Exp],
    "ln": [1, Ln],
    "sum": [Infinity, Sum],
    "avg": [Infinity, Avg]
}
const variables = {
    "x": [Variable],
    "y": [Variable],
    "z": [Variable]
}

function ParseError(message) {
    Error.call(this, message)
    this.message = message
}

ParseError.prototype = Object.create(Error.prototype);

function parsePrefix(str) {
    if (str.length === 0){
        throw new ParseError("Empty input")
    }
    let pos = 0
    let end = undefined;
    function skipWS() {
        while (str[pos] === " " && str[pos] !== end) {
            pos++
        }
    }

    function token(){
        skipWS();
        let res = str[pos]
        pos++
        while (res !== "(" && res!== ")" && str[pos] !== "(" && str[pos] !== ")" && str[pos] !== " " && str[pos] !== end){
            res += str[pos]
            pos++
        }
        skipWS()
        return res
    }

    function nextToken(){
        let prePos = pos
        let tok = token()
        pos = prePos
        return tok
    }
    function parseArgs(){
        let args = []
        let ind = -1
        skipWS()
        while (str[pos] !== ")" && str[pos] !== end){
            args.push(parseFunc())
            ind++
            skipWS()
            if (args[ind] in arr ){
                throw new ParseError("Unexpected operation")
            }
            if (nextToken() === ")"){
                break
            }
        }
        return args
    }

    function parseOperation(){
        let tok = token()
        if (!(tok in arr)){
            throw new ParseError("Undefined operation")
        }
        let args = parseArgs()
        if (args.length !== arr[tok][0] && arr[tok][0] !== Infinity){
            throw new ParseError("Incorrect number of arguments")
        }
        return arr[tok][1](...args)
    }

    function parseFunc(){
        let tok = token()
        if (tok === "("){
            let res = parseOperation()
            skipWS()
            if (str[pos] !== ")"){
                throw new ParseError("No closing parenthesis")
            }
            pos++
            return res
        } else if (tok === ")"){
            throw new ParseError("Extra closing parenthesis")
        } else if (tok in variables){
            return new Variable(tok)
        } else if (!isNaN(tok)){
            return new Const(Number(tok))
        }
        throw new ParseError("Undefined object")
    }

    let x = parseFunc()
    skipWS()
    if (pos < str.length){
        throw new ParseError("Undefined operations")
    }
    return x
}
function parse(str) {
    let line = str.split(" ").filter((el) => el !== ""), stack = []
    for (let currentPos = 0; currentPos < line.length; currentPos++){
        if (!isNaN(line[currentPos])) {
            stack.push(new Const(Number(line[currentPos])))
        } else if (line[currentPos] in variables) {
            stack.push(new Variable(line[currentPos]))
        } else {
            let opera = arr[line[currentPos]]
            stack.push(opera[1](...stack.splice(stack.length - opera[0], stack.length).map(i => i)))
        }
    }
    return stack.pop()
}
//console.log(parsePrefix("(x 2 2)"))
/*
Empty input              : org.graalvm.polyglot.PolyglotException: {message: "Empty input"}
Unknown variable         : org.graalvm.polyglot.PolyglotException: {message: "Undefined object"}
Invalid number           : org.graalvm.polyglot.PolyglotException: {message: "Undefined object"}
Missing )                : org.graalvm.polyglot.PolyglotException: {message: "No closing parenthesis"}
Unknown operation        : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Excessive info           : org.graalvm.polyglot.PolyglotException: {message: "Undefined operations"}
Empty op                 : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Invalid unary (0 args)   : org.graalvm.polyglot.PolyglotException: {message: "Incorrect number of arguments"}
Invalid unary (2 args)   : org.graalvm.polyglot.PolyglotException: {message: "Incorrect number of arguments"}
Invalid binary (0 args)  : org.graalvm.polyglot.PolyglotException: {message: "Incorrect number of arguments"}
Invalid binary (1 args)  : org.graalvm.polyglot.PolyglotException: {message: "Incorrect number of arguments"}
Invalid binary (3 args)  : org.graalvm.polyglot.PolyglotException: {message: "Incorrect number of arguments"}
Variable op (0 args)     : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Variable op (1 args)     : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Variable op (2 args)     : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Const op (0 args)        : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Const op (1 args)        : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
Const op (2 args)        : org.graalvm.polyglot.PolyglotException: TypeError: Cannot read property '0' of undefined
*/