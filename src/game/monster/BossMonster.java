package game.monster;

import game.player.Player;

// 보스 몬스터 클래스: 강력한 데미지를 가하는 몬스터
public class BossMonster extends Monster {

    public BossMonster(String name, int hp, int attackPower) {
        super(name, hp, attackPower); 
    }

    // 보스 공격: 데미지 +10
    @Override
    public void attack(Player player) {
        int damage = (int)(Math.random() * 16) + 5;
        System.out.println("보스의 강력한 공격! " + this.getName() + "이(가) " + player.getName() + "에게 데미지 +10 추가로 공격합니다.");
        player.receiveAttack(damage + 10); // 추가 데미지
    }

    @Override
    public String toString() {
        return "[보스 몬스터] " + super.toString();
    }
}
