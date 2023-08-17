# [Gold V] 암호 만들기 - 1759 

[문제 링크](https://www.acmicpc.net/problem/1759) 

### 성능 요약

메모리: 16344 KB, 시간: 180 ms

### 분류

백트래킹, 브루트포스 알고리즘, 조합론, 수학

### 문제 설명

<p>바로 어제 최백준 조교가 방 열쇠를 주머니에 넣은 채 깜빡하고 서울로 가 버리는 황당한 상황에 직면한 조교들은, 702호에 새로운 보안 시스템을 설치하기로 하였다. 이 보안 시스템은 열쇠가 아닌 암호로 동작하게 되어 있는 시스템이다.</p>

<p>암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다. 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.</p>

<p>새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.</p>

### 출력 

 <p>각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.</p>

### 문제풀이
 - 해당 문제의 경우 C개의 문자를 이용하여 L 크기의 암호문을 만드는 문제로 조합 문제였습니다.
 - 여기서 주의할 점은 암호문의 조건입니다. (설명 잘 읽어야함..)
   1. 암호문에는 `최소 1개`의 모음(a, e, i, o, u)이 있어야 한다.
   2. 암호문에는 `최소 2개`의 자음이 있어야 한다.
  - 따라서 C개의 문자를 이용한 L 크기의 암호문의 조합들을 구한 후에 마지막으로 조건처리를 하여 풀이하면 되겠습니다.

### Next Permutation 풀이
![image](https://github.com/zsa332/Algorithms/assets/78728865/e97a2dc0-ff4a-4ba1-a00c-f7f8642c888c)
___Main___
```java
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    L = Integer.parseInt(st.nextToken()); // 암호문의 크기
    C = Integer.parseInt(st.nextToken()); // 주어진 문자의 갯수

    String str = br.readLine().replace(" ", "");
    chars = str.toCharArray(); // 문자를 저장할 배열

    Arrays.sort(chars); // 생략가능
    
    int[] mask = new int[C]; // 조합을 생성할 때 사용할 배열
    for(int i = C - L; i < C; i++) { // 암호문의 크기만큼 배열의 뒷부분 값입력
     mask[i] = 1;
    }
    
    List<String> list = new ArrayList<String>(); // 암호문을 담을 리스트
    do{
     String line = "";
     int v = 0, c = 0; // 모음, 자음의 갯수
     for(int i = 0; i < C; i++) {
      if(mask[i] == 1) {
       line += chars[i];
       if(chars[i] == 'a' || chars[i] == 'e'||chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u')
        v++; // 모음 카운트
       else c++; // 자음 카운트
      }
     }
     if(v >= 1 && c >= 2) // 모음이 1개 이상이며 자음이 2개 이상일 경우만 암호문 추가
      list.add(line);
    }while(np(mask));
    
    Collections.sort(list); // 사전순으로 출력하기 위해 정렬
    
    for(String s : list) { // 암호문 출력
     System.out.println(s);
    }
}
```
___Next Permutation___
```java
private static boolean np(int[] p) {
 // 1. 꼭지점을 탐색
 int N = p.length;
 int i = N - 1;
 while (i > 0 && p[i - 1] >= p[i])
  i--;

 if (i == 0)
  return false; // 꼭지점이 첫번째인 경우 더이상의 경우의 수 없음

 // 2. 꼭지점전 값과 바꿀 한단계 높은 값을 탐색
 int j = N - 1;
 while (p[i - 1] >= p[j])
  j--;

 // 3. 교체
 swap(p, i - 1, j);

 // 4. 꼭지점 이후의 값을 오름차순 정렬
 int k = N - 1;
 while (i < k) {
  swap(p, i++, k--);
 }

 return true;
}

private static void swap(int[] mask, int i, int j) {
 int temp = mask[i];
 mask[i] = mask[j];
 mask[j] = temp;
}
```

### BackTracking 풀이
![image](https://github.com/zsa332/Algorithms/assets/78728865/a100fd02-a5cd-4217-8dfa-afdd011e3114)
___Main___
```java
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    L = Integer.parseInt(st.nextToken()); // 암호문의 크기
    C = Integer.parseInt(st.nextToken()); // 주어진 문자의 갯수

    String str = br.readLine().replace(" ", "");
    chars = str.toCharArray(); // 문자를 저장할 배열

    Arrays.sort(chars); // 사전순으로 출력하기 위해 정렬

    combination(0, 0, 0, "");
}

   
```
___Combination___
```java
/**
* 암호문의 조합을 구하고 조건 확인후 출력하는 함수
* @param start : 시작 인덱스
* @param cnt : 사용된 문자의 수
* @param flag : 사용여부를 확인할 변수 
* @param password : 현재까지 완성된 암호문
*/
private static void combination(int start , int cnt, int flag, String password){
 if(cnt == L){
     int vowel = 0, consonant = 0; // 모음과 자음의 개수
     for(int i = 0; i < password.length(); i++){
         if(password.charAt(i) =='a' || password.charAt(i) =='e' || password.charAt(i) =='i'
                 || password.charAt(i) =='o' || password.charAt(i) =='u')vowel++; // 모음 카운트
         else consonant++; // 자음 카운트
     }
     if(vowel >= 1 && consonant >= 2) // 모음이 1개 이상이며 자음이 2개 이상일 경우만 출력
         System.out.println(password); 
     return;
 }

 for(int i = start; i < C; i++){
     if((flag & 1 << i) != 0) continue; // 사용된 문자라면 넘어가기
     combination(i+1, cnt+1, flag | 1 << i, password + chars[i]); // 다음 문자를 받기위해 재귀
 }
}
```
