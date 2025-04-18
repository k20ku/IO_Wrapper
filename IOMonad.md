# IO 解説

## Stateモナド

- $T(A)=(A \times S)^{S}$
- $\eta_A$ は $a \mapsto (\lambda s:S.\langle a,s \rangle)$
- $f:A \to T(B)$ かつ $c : T(A)$ ならば，
  $$f^*(c)=\lambda s:S.(\text{let}\, \langle a,s' \rangle = c(s) \,\text{in}\,f(a)(s'))$$

## IO モナド

Haskellでは，State モナド $T(A)=(A \times S)^{S}$ の $S$ を $S:=\text{RealWorld}$ としたものとして，実装．

自分は $\text{RealWorld}=()$ として模倣されるし，されることが多い気がする（要出典）．

- $T(A)=(A \times ())^{()} \simeq {() \to A}$
- $\eta_A$ は
  $$a \mapsto (\lambda \_:().\langle a,\_ \rangle)\simeq a\mapsto(\lambda\_:().a:A)$$
- $f:A \to T(B)$ かつ $c : T(A)$ ならば，
  $$f^*(c)=\lambda \_:().(\text{let}\, \langle a,\_' \rangle = c(\_) \,\text{in}\,f(a)(\_'))$$

## 参考

### 理論面

- funnycatさんの解説
  - url: <https://zenn.dev/funnycat/articles/5a0762f4501a2f>
- Moggiの論文
  - Moggi, E. (1991). Notions of computation and monads. Information and computation, 93(1), 55-92. 
