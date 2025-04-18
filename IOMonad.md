# IO 解説

## Stateモナド

* $T(A)=(A \times S)^{S}$
* $\eta_A$ は $a \mapsto (\lambda s:S.\langle a,s \rangle)$
* $f:A \to T(B)$ かつ $c : T(A)$ ならば，
  $$f^*(c)=\lambda s:S.(\text{let}\, \langle a,s' \rangle = c(s) \,\text{in}\,f(a)(s'))$$

## IO モナド

State モナド $T(A)=(A \times S)^{S}$ の $S$ を $S:=\text{RealWorld}$ としたもの．

たいてい $\text{RealWorld}=()$ として模倣される．

* $T(A)=(A \times ())^{()} \simeq {() \to A}$
* $\eta_A$ は
  $$a \mapsto (\lambda \_:().\langle a,\_ \rangle)\simeq a\mapsto(\lambda\_:().a:A)$$
* $f:A \to T(B)$ かつ $c : T(A)$ ならば，
  $$f^*(c)=\lambda \_:().(\text{let}\, \langle a,\_' \rangle = c(\_) \,\text{in}\,f(a)(\_'))$$
