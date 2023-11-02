find_middle_and_split(List, MiddleElement, Left, Right) :-
    length(List, Length),
    MiddleIndex is div(Length, 2),
    split_list(List, MiddleIndex, Left, [MiddleElement | Right]), !.

split_list(List, MiddleIndex, Left, [Middle|Right]) :-
    split_list(List, MiddleIndex, 0, Left, Middle, Right).

split_list([], _, _, [], _, []).
split_list([H|T], MiddleIndex, Index, [H|Left], Middle, Right) :-
    Index < MiddleIndex,
    NextIndex is Index + 1,
    split_list(T, MiddleIndex, NextIndex, Left, Middle, Right).
split_list([Middle|T], MiddleIndex, MiddleIndex, [], Middle, T).
split_list([H|T], MiddleIndex, Index, Left, Middle, [H|Right]) :-
    Index > MiddleIndex,
    NextIndex is Index + 1,
    split_list(T, MiddleIndex, NextIndex, Left, Middle, Right).


map_build([], null) :- !.
map_build(ListMap, TreeMap) :-
    find_middle_and_split(ListMap, (Key, Value), Left, Right),
    map_build(Left, Lhs),
    map_build(Right, Rhs),
    TreeMap = node(Key, Value, Lhs, Rhs), !.

map_get(node(Key, Value, _, _), Key, Value).
map_get(node(Key, _, Lhs, _), Val1, Val2) :-
    Val1 < Key,
    map_get(Lhs, Val1, Val2).
map_get(node(Key, _, _, Rhs), Val1, Val2) :-
    map_get(Rhs, Val1, Val2).

map_keys(null, []) :- !.
map_keys(node(Key, _, Lhs, Rhs), Keys) :-
    map_keys(Lhs, LhsKeys),
    map_keys(Rhs, RhsKeys),
    append(LhsKeys, [Key|RhsKeys], Keys).