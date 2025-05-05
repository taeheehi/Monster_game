package game.player;

import game.monster.Monster; // Monster 클래스를 사용하기 위해 import

// 플레이어의 이름, 체력, 공격력을 관리하고 몬스터를 공격할 수 있는 클래스
public class Player {
    private String name;         // 플레이어 이름
    private int attackPower;     // 공격력
    private int hp;              // 체력

    // 기본 생성자
    public Player() {
        this.name = "플레이어";
        this.attackPower = 30;
        this.hp = 100;
    }

    // 이름과 공격력을 지정하는 생성자 (체력은 이후 설정)
    public Player(String name, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }

    // Getter
    public String getName() { return name; }
    public int getAttackPower() { return attackPower; }
    public int getHp() { return hp; }

    // Setter
    public void setName(String name) { this.name = name; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public void setHp(int hp) { this.hp = hp; }

    // 플레이어가 몬스터를 공격 (몬스터에게 데미지를 입히는 부분)
    public void attackMonster(Monster monster) {
        System.out.println(this.name + "이(가) " + monster.getName() + "을/를 공격합니다!");
        int newHp = monster.getHp() - this.attackPower;
        if (newHp <= 0) {
            monster.setHp(0);
            System.out.println(monster.getName() + " 이(가) 쓰러졌습니다!");
        } else {
            monster.setHp(newHp);
            System.out.println(monster.getName() + " 이(가) " + this.attackPower + "의 데미지를 입었습니다. (HP: " + newHp + ")");
        }
    }

    // 플레이어가 데미지를 입음 (쓰러짐 메시지는 MonsterApp에서 처리)
    public void receiveAttack(int damage) {
        System.out.println(this.name + "이(가) " + damage + " 데미지를 입었습니다.");
        this.hp -= damage;

        if (this.hp <= 0) {
            this.hp = 0; // 메시지 출력 없음
        } else {
            System.out.println("현재 " + this.name + "의 체력: " + this.hp);
        }
    }

    // 플레이어 상태 출력
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("플레이어 이름: ").append(name).append("\n");
        sb.append("공격력: ").append(attackPower);
        return sb.toString();
    }
}
