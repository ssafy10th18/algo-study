# [D3] 1860. 진기의 최고급 붕어빵

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LsaaqDzYDFAXc)

### 풀이방법
- 손님이 도착하는 시간이 순서대로 주어지지 않기 때문에 sort를 해주어야 한다.
- M초가 지날 때 마다 K개의 붕어빵을 만들 수 있기 때문에 ```현재 시간 % M == 0``` 일 떄 붕어빵의 개수를 K개 증가시킨다. 
- 손님이 도착했을 때 
  - 붕어빵이 1개 이상 있다면 붕어빵을 제공하고 현재 잔여 붕어빵의 개수를 1 감소시킨다.
  - 손님에게 제공할 붕어빵의 개수가 부족하다면 Impossible를 출력한다.
- 추가로, 0초에 손님이 오는 경우도 고려해야 한다. 0초에는 아직 만들어진 붕어빵이 없기 때문에 Impossible를 출력한다.
- 위의 Impossible의 경우들에 해당되지 않으면 Possible을 출력한다.