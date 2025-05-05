package game.monster;

import game.player.Player;

// 얼음 속성 몬스터 클래스: Monster를 상속받아 얼음 공격 효과 부여
public class IceMonster extends Monster {

    public IceMonster(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
    }

    // 얼음 공격: 데미지 그대로, 공격 메시지만 다름
    @Override
    public void attack(Player player) {
        int damage = (int)(Math.random() * 16) + 5;
        System.out.println("얼음 공격! " + this.getName() + "이(가) " + player.getName() + "에게 느려지는 공격을 가했습니다.");
        player.receiveAttack(damage);
    }

    @Override
    public String toString() {
        return "[얼음 몬스터] " + super.toString();
    }
}
