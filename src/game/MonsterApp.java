package game;

import java.util.Scanner;

import game.monster.Monster;
import game.monster.FireMonster;
import game.monster.IceMonster;
import game.monster.BossMonster;
import game.player.Player;

public class MonsterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 이름 입력
        System.out.print("용사의 이름을 입력하세요: ");
        String playerName = scanner.nextLine();

        // 2. 체력 입력
        System.out.print("용사의 체력을 입력하세요 (기본값 100): ");
        int playerHp;
        try {
            playerHp = Integer.parseInt(scanner.nextLine());
            if (playerHp <= 0) {
                System.out.println("체력은 1 이상이어야 합니다. 기본값 100으로 설정됩니다.");
                playerHp = 100;
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닙니다. 기본값 100으로 설정됩니다.");
            playerHp = 100;
        }

        // 3. 플레이어 공격력 설정 : (10~40) 사이의 값 -> 이거 때문에 절대 기본 생성자 가 실행이 안됌.
        // int randomAttackPower = (int)(Math.random() * 31) + 10; // 

        // 4. 플레이어 객체 생성
        // Player player = new Player(playerName, randomAttackPower);
        // player.setHp(playerHp);



        // ✅ 여기부터 수정!
        System.out.print("공격력을 직접 입력하시겠습니까? (숫자 입력 / 엔터 시 기본값 30): ");
        String input = scanner.nextLine();

        Player player;  // 선언

        if (!input.isBlank()) {
            try {
                int attackPower = Integer.parseInt(input);
                player = new Player(playerName, attackPower);  // 매개변수 생성자 사용
            } catch (NumberFormatException e) {
                System.out.println("숫자가 아닙니다. 기본 공격력 30으로 설정됩니다.");
                player = new Player();  // 기본 생성자 호출
                player.setName(playerName); // 이름 설정
            }
        } else {
            // 아무것도 입력 안했을 때 → 기본 생성자 사용
            player = new Player();  // ✅ 기본 생성자 호출!
            player.setName(playerName);  // 이름만 덮어씌움
        }


        // 5. 상태 출력
        System.out.println("\n================ [플레이어 상태창] ================");
        System.out.println("이름   : " + player.getName());
        System.out.println("체력   : " + player.getHp());
        System.out.println("공격력 : " + player.getAttackPower());
        System.out.print("====================================================");

        // 6. 몬스터 배열
        Monster[] monsters = {
            new FireMonster("파이어 고블린", 60, 20),
            new IceMonster("아이스 슬라임", 50, 15),
            new BossMonster("드래곤", 150, 35),
            new Monster("기본 몬스터", 40, 10)
        };

        // 7. 전투 루프
        while (true) {
            // 7-1. 몬스터 상태 출력
            System.out.println("\n[남은 몬스터]");
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i].getHp() > 0) {
                    // System.out.println((i + 1) + ". " + monsters[i].getName() + " (HP: " + monsters[i].getHp() + ")");
					// ⭐ toString 출력으로 교체하여 속성 출력됨
                    System.out.println((i + 1) + ". " + monsters[i]);
                }
            }

            System.out.print("----------------------------------------------------");

            // 7-2. 몬스터 선택
            System.out.print("\n공격할 몬스터 번호를 선택하시오: ");
            int choice = scanner.nextInt();
            if (choice < 1 || choice > monsters.length || monsters[choice - 1].getHp() <= 0) {
                System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                continue;
            }

            // 7-3. 플레이어 공격
            player.attackMonster(monsters[choice - 1]);

            // 7-4. 모든 몬스터 쓰러졌는지 확인
            boolean allDead = true;
            for (Monster m : monsters) {
                if (m.getHp() > 0) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) {
                System.out.println("모든 몬스터를 물리쳤습니다! 승리!");
                break;
            }

            // 7-5. 랜덤 몬스터 반격 -> 랜덤 몬스터 아니고 지정한 몬스터가 반격하도록 수정 
            // Monster attacker = null;
            // while (true) {
            //     int idx = (int)(Math.random() * monsters.length);
            //     if (monsters[idx].getHp() > 0) {
            //         attacker = monsters[idx];
            //         break;
            //     }
            // }

			
			Monster attacker = monsters[choice - 1];
			if (attacker.getHp() > 0) {  // 살아있는 경우에만 반격
                // ⭐ (다형성 오버라이딩) 이 부분 변경: 몬스터가 직접 Player를 공격 
				System.out.println("\n[" + attacker.getName() + "의 반격]");
				attacker.attack(player);
			}

            // 7-6. 플레이어 생존 확인
            if (player.getHp() <= 0) {
                System.out.println(player.getName() + "이(가) 쓰러졌습니다!");
                System.out.println("당신은 쓰러졌습니다... 게임 오버");
                return;
            }
        }

        scanner.close(); // 스캐너 닫기
    }
}
