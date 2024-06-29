<h1>TicTacToe</h1>

<h3>🙂 Learnning Point</h3>
<ul>
  <li> 객체지향에 대하여 생각해봅니다.</li>
  <li> 짧게 플레이 할 수 있는 게임을 직접 구현해봅니다.</li>
  <li> 인터페이스를 활용하여 플레이할 수 있는 게임의 규격을 통일합니다.</li>
</ul>
<h3>🔖 Commit Convention</h3>

|Tag|Description|
|:---:|:---:|
|Init|레포지토리 생성 후 최초의 커밋|
|Create|<code>.java</code> 파일 및 새로운 <code>class</code> 생성|
|Feat|기능 구현시|
|Mod|커밋이 완료된 코드를 수정|
|Fix|구현된 기능의 버그 및 오류를 발견하고 수정 완료|
|Refactor|구현된 기능변화 없이 더 효율 좋은 구조로 변경|
|Test|테스트 코드 추가후 동작확인 및 수정(프로덕션 코드 수정 X)|
|Docs|<code>README.md</code>와 같은 문서 수정|
|Style|코드의 변경 없이 코드 포멧팅, 세미콜론 누락에대한 수정(프로덕션 코드 수정 X)|
|Chore|빌드 설정, 패키지 매니저 설정(프로덕션 코드 수정 X)|
|Remove|파일 삭제|
|Rename|파일 혹은 폴더명을 수정한 경우|

```
커밋 포맷
Tag : 커밋 메시지

ex)
$ git commit -m"Feat : TicTacToe 승리 조건 체크
```

<ul>
<li>
  Tag의 앞글자는 <code>대문자</code>로 작성합니다.
</li>
 <li>Tag를 기입후 한칸의 공백후 <code>:</code>을 기입하고 다시 한 번 한 칸의 공백을 기입하고 커밋 메시지를 작성합니다.<br />
 ex) <code>Style : {커밋 메시지}</code>
 </li>
  <li>커밋 메시지는 되도록 상세하게 작성합니다.</li>
  <li>❗❗ 각자의 <code>fork</code>받은 레포지토리에서 브랜치를 생성하여 작업 후 <code>main</code> 브랜치로 <code>merge</code>하기 전에 반드시 <code>fork</code>받은 레포지토리에서 <code>Sync Fork</code>후애 <code>git pull origin main</code>명령어로 <code>pull</code>을 받아주세요. </li>
</ul>

> [**git 명령어 참고**](https://nosy-rabbit.tistory.com/category/%EA%B9%83%20%ED%83%90%EA%B5%AC)


<h3>🧱 TicTacToe 레포지토리의 구성</h3>
<p> TicTacToe는 단순히 자바가지고 짧은 게임을 만들다가 다른 게임도 할 수 있도록 확장성을 생각하다가 GameManager로 확장되었습니다.</p>

src
> manager 패키지
>> GameLauncher : Playable 타입만 실행이 가능하도록 강제합니다. GameManager가 실행 시 선택한 게임을 실행하도록 합니다.<br />
>> GameList : 만든 게임의 리스트를 저장합니다. GameList를 바탕으로 GameManager 선택지를 표시합니다.<br />
>> GameManager : 싱글톤으로 애플리케이션 실행시 자동으로 한 번만 생성됩니다. 사용자의 UI와 조작할 수 있는 명령을 제공합니다.<br />
>
> module 패키지
>> Playable : GameLauncher가 게임을 실행하기 위해서는 Playable 인터페이스의 메서드를 구현해야 합니다.<br />
>> TicTacToe : TicTacToe 게임의 코드가 작성되어있습니다.

<h3>🎾 게임 규칙</h3>
<h4>1. TicTacToe</h4>
<div>
  두 명이 번갈아 가며 O와 X를 3X3판에 써서 가로, 세로, 대각선 상에 놓이도록 하는 놀이입니다.<br />
  판에 적힌 좌표값을 스페이스로 구분하여 입력하면 O, X를 표시 할 수 있습니다.<br />
  <img width="208" alt="스크린샷 2024-06-02 오후 5 43 25" src="https://github.com/OliveLover/TicTacToe/assets/118647313/c98a96bd-5c28-43fd-93ba-149f39edd003"> <br />
  <img width="188" alt="image" src="https://github.com/OliveLover/TicTacToe/assets/118647313/39bb18fc-cd99-4a18-8821-efbd749b6e6d">
</div>
