package game.monster; // 몬스터 관련 클래스를 담는 패키지

import game.player.Player;

// 몬스터 이름과 체력을 관리하고, 공격받아 체력이 줄어드는 클래스
public class Monster {
    private String name; // 몬스터 이름
    private int hp;      // 몬스터 체력
    private int attackPower; // 몬스터 공격력
    

    // 기본 생성자: 이름 없는 몬스터, 체력 100, 공격력 10
    public Monster() {
        this.name = "이름없는 몬스터";
        this.hp = 100;
        this.attackPower = 10;  // 기본 공격력 설정
    }

    // 이름, 체력, 공격력을 지정해서 초기화
    public Monster(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    // 데미지를 받아 체력을 줄임
	// Monster.java에 추가
	public void attack(Player player) {
		int damage = (int)(Math.random() * 16) + 5;
		System.out.println(this.name + "이(가) 공격합니다! (데미지: " + damage + ")"); // 고정된 공격력 출력력
		player.receiveAttack(attackPower);
	}


    // 부모 클래스(Monster)의 toString()

    // 몬스터 상태를 문자열로 반환
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("몬스터 이름: ").append(name)
          .append(" / 체력: ").append(hp)
          .append(" / 공격력: ").append(attackPower);
        return sb.toString();
    }

    // 2. 자식 클래스(FireMonster)의 toString() 
    // super.toString(); 은 바로 아래의 부모 클래스인 Monster의 toString() 메서드를 호출하는 거예요:

    // [불 몬스터] 몬스터 이름: 파이어 드래곤 / 체력: 80 / 공격력: 30
    // ㄴ이렇게 출력 하게     몬스터 이름~ = 자식에 작성이 되어있는 super 이다. 
}
