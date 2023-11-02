prime_divisors(1, []) :- !.
prime_divisors(N, Divisors) :- N > 1, prime_divisors(N, 2, Divisors).
square_divisors(N, D) :- N2 is N * N, prime_divisors(N2, D).

prime_divisors(1, _, []) :- !.
prime_divisors(N, P, [P|Divisors]) :-
    N mod P =:= 0,
    !,
    N1 is N // P,
    prime_divisors(N1, P, Divisors).
prime_divisors(N, P, Divisors) :-
    P1 is P + 1,
    prime_divisors(N, P1, Divisors).

divis(X, D) :- D * D =< X, X mod D =:= 0.
divis(X, D) :- D * D =< X, D2 is D + 1, divis(X, D2).

prime(N) :- N > 1, \+ divis(N, 2).

composite(N) :-  N > 1, divis(N, 2).