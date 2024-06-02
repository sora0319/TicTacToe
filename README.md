<h1>TicTacToe</h1>

<h3>🙂 Learnning Point</h3>
<ul>
  <li> 객체지향에 대하여 생각해봅니다.</li>
  <li> 짧게 플레이 할 수 있는 게임을 직접 구현해봅니다.</li>
  <li> 인터페이스를 활용하여 플레이할 수 있는 게임의 규격을 통일합니다.</li>
</ul>

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
