# 아이템 21 인터페이스는 구현하는 쪽을 생각해 설계하라

생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어려운 법이다.

자바 8에서 새롭게 추가된 `Collection` 인터페이스의 `removeIf()` 메서드는 `Predicate`의 결과에 따라 원소를 제거하는 함수이다.  
범용적으로 구현되어 있지만 `모든 Collection` 구현체와 어울리는 것은 아니다.  
아래는 그 예인 `SynchroziedCollection`이다.  

> 클라이언트에가 제공한 객체로 락을 거는 능력을 추가로 제공한다.  
> 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 기능을 위임하는 래퍼 클래스이다.

<img src="https://user-images.githubusercontent.com/13347548/75671523-3886f180-5cc2-11ea-916b-ff5221bff427.png" alt="image" style="zoom:50%;" />

보다시피 `removeIf()`가 4.4 이전 버전까지는 동기적으로 구현이 되어있지않아 default 메서드를 부르게 되어 경우 동기적으로 작동하지 않을 것이다.

이렇게 된 구현체들은 컴파일 에러는 일으키지 않지만 `ConcurrentModificationException` 과 같은 런타임 에러를 일으키게 된다.

<img src="https://user-images.githubusercontent.com/13347548/75672647-84d33100-5cc4-11ea-9119-a62c91b6dd99.png" alt="image" style="zoom:50%;" />

### 꼭 필요한 경우가 아니면 디폴트 메서드를 추가하는 것은 피하자

기존 메서드를 제거하거나 수정하는 용도가 아니다.  
디폴트 메서드로 인해 기존 클라이언트를 망가뜨릴 수 있다.

따라서, 인터페이스를 설계 할 때는 여전히 세심한 주의를 기울여야 한다.  
이를 검증하기 위해 서로 다른 방식으로 최소 세 가지의 구현체를 만들어 보자.

**인터페이스를 릴리즈한 후라도 결함을 수정하는 게 가능한 경우도 있지만, 이를 보험삼아서는 안된다.**
