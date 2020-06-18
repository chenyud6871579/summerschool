import sys
import pygame
from settings import Settings
from ship import Ship
import game_functions as gf
from pygame.sprite import Group
from game_stats import GameStats



def run_game():
    #创建屏幕对象
    pygame.init()
    ai_settings = Settings()
    screen = pygame.display.set_mode(
        (ai_settings.screen_width,ai_settings.screen_height)
    )
    pygame.display.set_caption("Alien Invasion")

    stats = GameStats(ai_settings)
    # #背景颜色
    # bg_color = (230,230,230)

    #创建飞船
    ship = Ship(ai_settings,screen)

    #创建一个用于存储子弹的编组
    bullets = Group()
    aliens = Group()

    # #创建一个外星人
    # alien = Alien(ai_settings,screen)

    #创建一个外星人群
    gf.create_fleet(ai_settings,screen,ship,aliens)

    #主循环
    while True:
        #监听事件
        gf.check_events(ai_settings,screen,ship,bullets)
        ship.update()
        gf.update_bullets(ai_settings,screen,ship,aliens,bullets)
        gf.update_aliens(ai_settings,ship,aliens)
        gf.update_screen(ai_settings,screen,ship,aliens,bullets)
        

#开始展示
run_game()
