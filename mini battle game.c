#include <stdio.h>
#include <stdlib.h>
#include <time.h>
 
#define PLAYER_START_HP 100
#define ENEMY_START_HP  100
 
/* Returns a random number between min and max (inclusive) */
int rollDamage(int min, int max) {
    return min + rand() % (max - min + 1);
}
 
void printStatus(int playerHP, int enemyHP) {
    printf("\nPlayer HP: %d   Enemy HP: %d\n", playerHP, enemyHP);
}
 
/* Keeps asking until the player enters 1, 2, or 3 */
int getMenuChoice(void) {
    int choice;
 
    while (1) {
        printf("\n1. Attack\n");
        printf("2. Heal\n");
        printf("3. Run\n");
        printf("Choose an action: ");
 
        if (scanf("%d", &choice) != 1) {
            /* clear the invalid input */
            while (getchar() != '\n');
            printf("Invalid input. Please enter a number.\n");
            continue;
        }
 
        /* clear anything left on the line */
        while (getchar() != '\n');
 
        if (choice < 1 || choice > 3) {
            printf("Please choose 1, 2, or 3.\n");
            continue;
        }
        return choice;
    }
}
 
int main(void) {
    srand((unsigned)time(NULL));
 
    char playAgain;
 
    do {
        int playerHP = PLAYER_START_HP;
        int enemyHP  = ENEMY_START_HP;
        int escaped  = 0;
 
        printf("==============================\n");
        printf("       MINI BATTLE GAME\n");
        printf("==============================\n");
 
        /* Main battle loop */
        while (playerHP > 0 && enemyHP > 0 && !escaped) {
            printStatus(playerHP, enemyHP);
 
            int choice = getMenuChoice();
 
            if (choice == 1) {
                /* Attack */
                int damage = rollDamage(10, 25);
                enemyHP -= damage;
                printf("You hit the enemy for %d damage!\n", damage);
                if (enemyHP <= 0) {
                    enemyHP = 0;
                    break;
                }
            } else if (choice == 2) {
                /* Heal (but the enemy still gets a turn) */
                int heal = rollDamage(10, 20);
                playerHP += heal;
                if (playerHP > PLAYER_START_HP) playerHP = PLAYER_START_HP;
                printf("You healed for %d HP!\n", heal);
            } else {
                /* Run — 50% chance */
                if (rand() % 2 == 0) {
                    printf("You escaped safely!\n");
                    escaped = 1;
                    break;
                } else {
                    printf("You tried to run but failed!\n");
                }
            }
 
            /* Enemy turn */
            if (enemyHP > 0) {
                int enemyDamage = rollDamage(5, 18);
                playerHP -= enemyDamage;
                printf("Enemy hits you for %d damage!\n", enemyDamage);
                if (playerHP < 0) playerHP = 0;
            }
        }
 
        /* Result */
        printf("\n--- RESULT ---\n");
        if (escaped) {
            printf("You fled the battle.\n");
        } else if (enemyHP <= 0 && playerHP > 0) {
            printf("Victory! You defeated the enemy.\n");
        } else if (playerHP <= 0 && enemyHP <= 0) {
            printf("It's a draw - you both fell.\n");
        } else {
            printf("Defeat. The enemy defeated you.\n");
        }
 
        printf("\nPlay again? (y/n): ");
        scanf(" %c", &playAgain);
        while (getchar() != '\n');   /* clear the buffer */
 
    } while (playAgain == 'y' || playAgain == 'Y');
 
    printf("Thanks for playing!\n");
    return 0;
}
 