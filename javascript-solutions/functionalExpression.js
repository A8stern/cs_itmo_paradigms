// :NOTE: use const
let cnst = a => () => a
let variable = a => (...args) => {
    if (a === "x") {
        return args[0];
    }
    if (a === "y") {
        return args[1];
    }
    return args[2];
}
// :NOTE: ... вместо apply и map вместо for
function func(operation){
    return function (){
        let args = arguments;
        return function (){
            let arr = []
            for (let i = 0; i < args.length; i++) {
                arr.push(args[i].apply(null, arguments))
            }
            return operation.apply(null, arr)
        }
    }
}
let add = func((f, g) => f + g)
let subtract = func((f, g) => f - g)
let divide = func((f, g) => f / g)
let multiply = func((f, g) => f * g)
let negate = func((f) => -f);
let cos = func((f) => Math.cos(f));
let sin = func((f) => Math.sin(f));
let one = cnst(1)
let two = cnst(2)

// expr = add(
//     subtract(
//         multiply(
//             variable("x"),
//             variable("x")
//         ),
//         multiply(
//             variable("x"),
//             cnst(2)
//         )
//     ),
//     cnst(1)
// );
// for (let i = 0; i <= 10; i++) {
//     console.log(expr(i, 0, 0));
// }
