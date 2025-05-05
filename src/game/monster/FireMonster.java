package game.monster;

import game.player.Player;

// 불 속성 몬스터 클래스: Monster를 상속받아 불꽃 데미지를 가함
public class FireMonster extends Monster {

    // 생성자: 이름과 체력을 부모 Monster 클래스에 전달
    public FireMonster(String name, int hp, int attackPower) {
        super(name, hp, attackPower); 
    }

    // 공격받을 때 데미지를 5 더 많이 입는 효과
    @Override
    public void attack(Player player) {
        int damage = (int)(Math.random() * 16) + 5;
        System.out.println("불꽃 공격! " + this.getName() + "이(가) " + player.getName() + "에게 데미지 +5 추가로 공격합니다.");
        player.receiveAttack(damage + 5); // 추가 데미지
    }

    // 상태 출력 시 "[불 몬스터]" 태그로 시각적으로 구분
    @Override
    public String toString() {
        return "[불 몬스터] " + super.toString();
    }
}
